package com.trolltech.candycrush.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Random;

import Ice.Communicator;
import Ice.Current;
import Ice.Identity;
import candycrush.ClientPrx;
import candycrush.ClientPrxHelper;
import candycrush.Movement;
import candycrush.Square;
import candycrush._ServerDisp;

import com.trolltech.candycrush.SupportedMovement;

// Server's servant
public class ServerI extends _ServerDisp implements BoardListener {
	private static final long serialVersionUID = 4859254848610517962L;

	// Game structures
	private ArrayList<Team> teams;
	private Board board;

	private int turn, movementsLeft;
	private ArrayList<SupportedMovement> movementProposals;
	private int receivedProposals, validProposals;
	private GameLoop gameLoopThread;
	private boolean gameStart;

	private static final int MOVEMENTS_PER_TURN = 3;
	private static final int POLLS_PER_MOVEMENT = 4;
	private static final int SCORE_LIMIT = 100;

	// Constructor for an indeterminate number of teams
	public ServerI(Communicator communicator, int nTeams) {
		teams = new ArrayList<Team>();
		for (int n = 0; n < nTeams; n++)
			teams.add(new Team(String.valueOf(n + 1)));
		gameStart = false;
		new Thread(gameLoopThread = new GameLoop()).start();
	}

	// Default constructor, creates two known named teams
	public ServerI(Communicator communicator) {
		teams = new ArrayList<Team>();
		teams.add(new Team("Equipo Rojo"));
		teams.add(new Team("Equipo Azul"));
		new Thread(gameLoopThread = new GameLoop()).start();
	}

	// A new player enters the game
	public boolean joinTeam(Identity id, String nick, int team, Current current) {
		if (team >= teams.size())
			return false;
		if (teams.get(team).getMemberNames().contains(nick))
			return false;

		// Proxy for communicating with the player and deliver her the game
		// events
		Ice.ObjectPrx base = current.con.createProxy(id);
		ClientPrx playerProxy = ClientPrxHelper.uncheckedCast(base);
		if (playerProxy == null) {
			throw new Error("Invalid proxy");
		}

		// Notify new player's arrival to any other player in the game
		for (int n = 0; n < teams.size(); n++) {
			for (Player p : teams.get(n).getMembers()) {
				p.getProxy().newParticipant(nick, team);
				playerProxy.newParticipant(p.getName(), n);
			}
		}
		Player newPlayer = new Player(nick, playerProxy);
		teams.get(team).addMember(newPlayer);

		// If the new player is late in the game, update her board to the
		// current game status
		if (gameStart) {
			newPlayer.setLate(true);
			playerProxy.setBoard(board.getBoard());
		} else {
			teams.get(team).kick(false);
		}

		return true;
	}

	// Make a team complete, indicating it is ready for the game
	public synchronized void teamComplete(Identity id, Current current) {
		for (Team t : teams)
			if (t.hasMember(id))
				t.teamComplete();
		synchronized (gameLoopThread) {
			if (gameLoopThread.gameCanStart()) {
				gameStart = true;
				gameLoopThread.notify();
			}
		}
	}

	public void leaveGame(Identity id, Current current) {
		for (int n = 0; n < teams.size(); n++) {
			Team gonePlayerTeam = teams.get(n);
			Player gonePlayer;
			if ((gonePlayer = gonePlayerTeam.getMember(id)) != null) {
				for (Team t : teams)
					for (Player p : t.getMembers())
						p.getProxy().participantLeft(gonePlayer.getName(), n);
				gonePlayerTeam.removeMember(id);

				synchronized (gameLoopThread) {
					gameLoopThread.notify();
				}

				if (gonePlayerTeam.getNActiveMembers() == 0) {
					gonePlayerTeam.kick(true);
				}
			}
		}
	}

	private class GameLoop implements Runnable {
		public GameLoop() {
		}

		public void run() {
			while (true) {
				synchronized (this) {
					while (!gameStart) {
						try {
							wait();
						} catch (InterruptedException ie) {
							System.err
									.println("InterruptedException in ServerI.GameLoop.run().wait()");
						}
					}
				}
				startGame();

				try {
					Thread.sleep(5000);
				} catch (InterruptedException ie) {
					System.err.println("InterruptedException: "
							+ ie.getMessage());
				}

				resetGame();
			}
		}

		private void startGame() {
			board = new Board(ServerI.this);

			for (Team t : teams) {
				for (Player p : t.getMembers()) {
					p.getProxy().setBoard(board.getBoard());
					p.getProxy().startGame();
				}
			}

			turn = new Random().nextInt(teams.size());

			int winner;
			do {
				playTurn();
				winner = Math.max(scoreWinner(), lastTeamStanding());
			} while (winner < 0);

			for (Team t : teams) {
				for (Player p : t.getMembers()) {
					p.getProxy().endGame(winner);
				}
			}
		}

		private void playTurn() {
			for (movementsLeft = MOVEMENTS_PER_TURN; movementsLeft > 0; movementsLeft--) {
				boolean movementCommited = false;
				SupportedMovement winnerProposal = null;

				for (int n = 0; n < POLLS_PER_MOVEMENT && !movementCommited; n++) {
					for (Team t : teams) {
						for (Player p : t.getMembers()) {
							if (!p.isLate())
								p.getProxy().turn(turn);
							p.getProxy().logStatus(
									String.format("Ronda %d", n + 1));
							p.getProxy()
									.customLog(
											String.format(
													"Al equipo %s le quedan %d movimientos",
													teams.get(turn).getName(),
													movementsLeft), turn);
						}
					}
					movementProposals = new ArrayList<SupportedMovement>();
					receivedProposals = validProposals = 0;
					receiveMovementProposals();
					Collections.sort(movementProposals);
					Collections.reverse(movementProposals);

					ListIterator<SupportedMovement> movIter = movementProposals
							.listIterator();
					while (movIter.hasNext()) {
						SupportedMovement mov = movIter.next();
						for (Team t : teams) {
							for (Player p : t.getMembers()) {
								p.getProxy().customLog(mov.toString(), turn);
							}
						}
					}

					if (validProposals > 0) {
						winnerProposal = movementProposals.get(0);
						if (winnerProposal.nSupporters > validProposals / 2) {
							if (board.validMovement(winnerProposal)) {
								for (Team t : teams) {
									for (Player p : t.getMembers()) {
										p.getProxy().makeMovement(
												winnerProposal);
									}
								}
								board.makeMovement(winnerProposal);
								movementCommited = true;
							}
						}
					} else {
						winnerProposal = null;
					}
				}

				if (!movementCommited && winnerProposal != null) {
					if (board.validMovement(winnerProposal)) {
						for (Team t : teams) {
							for (Player p : t.getMembers()) {
								p.getProxy().makeMovement(winnerProposal);
							}
						}
						board.makeMovement(winnerProposal);
						movementCommited = true;
					}
				}
			}

			turn = (turn + 1) % teams.size();
		}

		private void receiveMovementProposals() {
			synchronized (this) {
				while (receivedProposals < teams.get(turn).getNActiveMembers()) {
					try {
						wait();
					} catch (InterruptedException ie) {
						System.err
								.println("InterruptedException in ServerI.GameLoop.receiveMovementProposals().wait()");
					}
				}
			}
		}

		private void resetGame() {
			for (Team t : teams) {
				for (Player p : t.getMembers())
					p.setLate(false);
				if (t.getNActiveMembers() > 0) {
					t.kick(false);
					t.teamComplete();
					t.setScore(0);
				}
			}
			synchronized (this) {
				gameStart = gameCanStart();
			}
		}

		private boolean gameCanStart() {
			int nTeams = 0;
			for (Team t : teams) {
				if (t.isTeamComplete() && !t.isOut()) {
					for (Player p : t.getMembers()) {
						if (!p.isLate()) {
							nTeams++;
							break;
						}
					}
				}
			}
			return nTeams >= 2;
		}

		private int scoreWinner() {
			for (int n = 0; n < teams.size(); n++)
				if (teams.get(n).getScore() >= SCORE_LIMIT)
					return n;
			return -1;
		}

		private int lastTeamStanding() {
			int lts = -1;
			for (int n = 0; n < teams.size(); n++) {
				if (!teams.get(n).isOut()) {
					if (lts < 0) {
						lts = n;
					} else {
						lts = -1;
						break;
					}
				}
			}
			return lts;
		}
	}

	public synchronized void movementProposal(Identity id, Movement mov,
			Ice.Current current) {
		if (board.validMovement(mov)) {
			SupportedMovement m = new SupportedMovement(mov.fromRow,
					mov.fromColumn, mov.toRow, mov.toColumn);
			if (movementProposals.contains(m)) {
				movementProposals.get(movementProposals.indexOf(m)).nSupporters++;
			} else {
				m.nSupporters = 1;
				movementProposals.add(m);
			}
			validProposals++;
			for (Team t : teams)
				for (Player p : t.getMembers())
					p.getProxy().logStatus(
							String.format("[%d/%d]", validProposals,
									teams.get(turn).getNActiveMembers()));
		}

		++receivedProposals;
		synchronized (gameLoopThread) {
			gameLoopThread.notify();
		}
	}

	public void message(Identity id, String message, Ice.Current current) {
		Player from;
		for (Team t : teams)
			if ((from = t.getMember(id)) != null)
				for (Player p : t.getMembers())
					p.getProxy().receiveMessage(from.getName(), message);
	}

	public void boardUpdated() {
	}

	public void movementSelected(Movement mov) {
	}

	@Override
	public void movementScored(int score) {
		teams.get(turn).addScore(score);
		for (Team t : teams) {
			for (Player p : t.getMembers()) {
				p.getProxy().score(turn, score);
			}
		}
	}

	@Override
	public void boardSelectivelyUpdated(Square[] newSquares) {
		for (Team t : teams) {
			for (Player p : t.getMembers()) {
				p.getProxy().fillNewSquares(newSquares);
			}
		}
	}
}
