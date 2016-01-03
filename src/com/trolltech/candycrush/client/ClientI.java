package com.trolltech.candycrush.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.core.Qt.ConnectionType;

import candycrush.Movement;
import candycrush.Square;
import candycrush._ClientDisp;
import candycrush._ClientOperationsNC;
import Ice.Current;

// Servant class (for the player-client)
public class ClientI extends _ClientDisp {

	private static final long serialVersionUID = 3866178579611822514L;
	private _ClientOperationsNC clientOperator;
	private SignalEmitter middleMan;

	public ClientI(_ClientOperationsNC client) {
		super();
		clientOperator = client;
		middleMan = new SignalEmitter();
		connectSignalsToSlots();
	}

	public void setClientOperator(_ClientOperationsNC presentation) {
		clientOperator = presentation;
	}

	public void newParticipant(String nick, int team, Current __current) {
		middleMan.newParticipant.emit(nick, team);
	}

	public void participantLeft(String nick, int team, Current __current) {
		middleMan.participantLeft.emit(nick, team);
	}

	public void startGame(Current __current) {
		middleMan.startGame.emit();
	}

	public void endGame(int team, Current __current) {
		middleMan.endGame.emit(team);
	}

	public void setBoard(int[][] boardValues, Current __current) {
		List<List<Integer>> ba = new ArrayList<List<Integer>>(
				boardValues.length);
		for (int i = 0; i < boardValues.length; i++) {
			List<Integer> baAux = new ArrayList<Integer>(boardValues[i].length);
			for (int j = 0; j < boardValues[i].length; j++) {
				baAux.add(new Integer(boardValues[i][j]));
			}
			ba.add(baAux);
		}
		middleMan.setBoard.emit(ba);
	}

	public void turn(int team, Current __current) {
		middleMan.turn.emit(team);
	}

	public void log(String message, Current __current) {
		middleMan.log.emit(message);
	}

	public void customLog(String message, int code, Current __current) {
		middleMan.customLog.emit(message, code);
	}

	public void logStatus(String message, Current __current) {
		middleMan.logStatus.emit(message);
	}

	public void makeMovement(Movement mov, Current __current) {
		middleMan.makeMovement.emit(mov);
	}

	public void fillNewSquares(Square[] newSquares, Current __current) {
		middleMan.fillNewSquares.emit(Arrays.asList(newSquares));
	}

	public void score(int team, int scored, Current __current) {
		middleMan.score.emit(team, scored);
	}

	public void receiveMessage(String from, String message, Current __current) {
		middleMan.receiveMessage.emit(from, message);
	}

	private void connectSignalsToSlots() {
		middleMan.newParticipant.connect(clientOperator,
				"newParticipant(String, int)", ConnectionType.QueuedConnection);
		middleMan.participantLeft
				.connect(clientOperator, "participantLeft(String, int)",
						ConnectionType.QueuedConnection);
		middleMan.startGame.connect(clientOperator, "startGame()",
				ConnectionType.QueuedConnection);
		middleMan.endGame.connect(clientOperator, "endGame(int)",
				ConnectionType.QueuedConnection);
		middleMan.setBoard.connect(clientOperator, "_setBoard(java.util.List)",
				ConnectionType.QueuedConnection);
		middleMan.turn.connect(clientOperator, "turn(int)",
				ConnectionType.QueuedConnection);
		middleMan.log.connect(clientOperator, "log(String)",
				ConnectionType.QueuedConnection);
		middleMan.customLog.connect(clientOperator, "customLog(String, int)",
				ConnectionType.QueuedConnection);
		middleMan.logStatus.connect(clientOperator, "logStatus(String)",
				ConnectionType.QueuedConnection);
		middleMan.makeMovement.connect(clientOperator,
				"makeMovement(Movement)", ConnectionType.QueuedConnection);
		middleMan.fillNewSquares.connect(clientOperator,
				"_fillNewSquares(java.util.List)",
				ConnectionType.QueuedConnection);
		middleMan.score.connect(clientOperator, "score(int, int)",
				ConnectionType.QueuedConnection);
		middleMan.receiveMessage.connect(clientOperator,
				"receiveMessage(String, String)",
				ConnectionType.QueuedConnection);
	}

	public class SignalEmitter extends QSignalEmitter {

		public Signal2<String, Integer> newParticipant = new Signal2<String, Integer>();
		public Signal2<String, Integer> participantLeft = new Signal2<String, Integer>();
		public Signal0 startGame = new Signal0();
		public Signal1<Integer> endGame = new Signal1<Integer>();
		public Signal1<List<List<Integer>>> setBoard = new Signal1<List<List<Integer>>>();
		public Signal1<Integer> turn = new Signal1<Integer>();
		public Signal1<String> log = new Signal1<String>();
		public Signal2<String, Integer> customLog = new Signal2<String, Integer>();
		public Signal1<String> logStatus = new Signal1<String>();
		public Signal1<Movement> makeMovement = new Signal1<Movement>();
		public Signal1<List<Square>> fillNewSquares = new Signal1<List<Square>>();
		public Signal2<Integer, Integer> score = new Signal2<Integer, Integer>();
		public Signal2<String, String> receiveMessage = new Signal2<String, String>();
	}
}
