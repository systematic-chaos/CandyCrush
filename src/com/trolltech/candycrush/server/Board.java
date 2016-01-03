package com.trolltech.candycrush.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import candycrush.Match;
import candycrush.Movement;
import candycrush.Square;

import com.trolltech.candycrush.IBoard;

public class Board implements IBoard {

	protected final static int DIMENSION = 9;
	protected final static int FRUITS = 6;

	protected BoardListener listener;

	protected int[][] boardValues;

	protected int nRows;
	protected int nColumns;
	protected int nFruits;

	public Board(BoardListener listener) {
		this(listener, DIMENSION, DIMENSION, FRUITS);
	}

	public Board(BoardListener listener, int xSize, int ySize, int nFruits) {
		this.listener = listener;
		this.nColumns = xSize > 0 ? xSize : DIMENSION;
		this.nRows = ySize > 0 ? ySize : DIMENSION;
		this.nFruits = nFruits > 1 ? nFruits : FRUITS;

		createBoard();
	}

	public int[][] getBoard() {
		return boardValues;
	}

	public void setBoard(int[][] newBoardValues) {
		if (newBoardValues.length == nRows
				&& newBoardValues[0].length == nColumns) {
			for (int i = 0; i < nRows; i++) {
				boardValues[i] = newBoardValues[i].clone();
			}
		}
		listener.boardUpdated();
	}

	public void updateBoardSelectively(Square[] newSquareValues) {
		for (Square newValue : newSquareValues)
			boardValues[newValue.row][newValue.column] = newValue.value;
	}

	public int getNRows() {
		return nRows;
	}

	public int getNColumns() {
		return nColumns;
	}

	public int getNFruits() {
		return nFruits;
	}

	private void createBoard() {
		boardValues = new int[nRows][nColumns];
		Random rand = new Random(new Date().getTime());
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				boardValues[i][j] = rand.nextInt(nFruits);
			}
		}
	}

	public boolean makeMovement(Movement mov) {
		if (!validMovement(mov))
			return false;

		boardValues[mov.fromRow][mov.fromColumn] = boardValues[mov.fromRow][mov.fromColumn]
				^ boardValues[mov.toRow][mov.toColumn];
		boardValues[mov.toRow][mov.toColumn] = boardValues[mov.fromRow][mov.fromColumn]
				^ boardValues[mov.toRow][mov.toColumn];
		boardValues[mov.fromRow][mov.fromColumn] = boardValues[mov.fromRow][mov.fromColumn]
				^ boardValues[mov.toRow][mov.toColumn];

		listener.movementScored(applyBoardEffects());
		return true;
	}

	public boolean validMovement(Movement mov) {
		int dir = -1;
		if (mov.fromRow == mov.toRow || mov.fromColumn == mov.toColumn) {
			if (mov.fromColumn == mov.toColumn) {
				if (mov.fromRow == mov.toRow + 1 && mov.toRow >= 0
						&& mov.fromRow < nRows)
					dir = 0;
				if (mov.fromRow == mov.toRow - 1 && mov.toRow < nRows
						&& mov.fromRow >= 0)
					dir = 2;
			} else {
				if (mov.fromColumn == mov.toColumn - 1 && mov.fromColumn >= 0
						&& mov.toColumn < nColumns)
					dir = 1;
				if (mov.fromColumn == mov.toColumn + 1
						&& mov.fromColumn < nColumns && mov.toColumn >= 0)
					dir = 3;
			}
		}
		return dir > -1 && dir < 4;
	}

	private List<Match> findMatches() {
		List<Match> matches = new ArrayList<Match>();
		int rowLength;
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				if (boardValues[i][j] < nFruits) {
					if (i < nRows - 1) {
						int k;
						for (k = i; k < nRows
								&& boardValues[k][j] == boardValues[i][j]; k++)
							;
						if ((rowLength = k - i) > 2)
							matches.add(new Match(i, j, 2, rowLength));
					}
					if (j < nColumns - 1) {
						int k;
						for (k = j; k < nColumns
								&& boardValues[i][k] == boardValues[i][j]; k++)
							;
						if ((rowLength = k - j) > 2)
							matches.add(new Match(i, j, 1, rowLength));
					}
				}
			}
		}
		return matches;
	}

	private List<Match> removeMatches() {
		List<Match> matches = findMatches();

		for (Match match : matches) {
			switch (match.dir) {
			case 0:
				for (int i = 0; i < match.length; i++) {
					boardValues[match.row - i][match.column] = nFruits;
				}
				break;
			case 1:
				for (int i = 0; i < match.length; i++) {
					boardValues[match.row][match.column + i] = nFruits;
				}
				break;
			case 2:
				for (int i = 0; i < match.length; i++) {
					boardValues[match.row + i][match.column] = nFruits;
				}
				break;
			case 3:
				for (int i = 0; i < match.length; i++) {
					boardValues[match.row][match.column - i] = nFruits;
				}
				break;
			}
		}

		listener.boardUpdated();

		return matches;
	}

	private void applyGravity() {
		for (int i = 0; i < nColumns; i++) {
			for (int j = nRows - 2; j >= 0; j--) {
				if (boardValues[j][i] < nFruits) {
					int k;
					for (k = j + 1; k < nRows && boardValues[k][i] == nFruits; k++)
						;
					if (--k > j) {
						boardValues[k][i] = boardValues[j][i];
						boardValues[j][i] = nFruits;
					}
				}
			}
		}

		listener.boardUpdated();
	}

	private Square[] fillEmptySquares() {
		ArrayList<Square> filling = new ArrayList<Square>();
		Random rand = new Random(new Date().getTime());
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				if (boardValues[i][j] == nFruits) {
					boardValues[i][j] = rand.nextInt(nFruits);
					filling.add(new Square(i, j, boardValues[i][j]));
				}
			}
		}

		listener.boardUpdated();
		return filling.toArray(new Square[0]);
	}

	private int applyBoardEffects() {
		List<Match> matches;
		int totalScore = 0, score;
		while ((matches = removeMatches()).size() > 0) {
			score = 0;
			for (Match match : matches) {
				switch (match.length) {
				case 3:
					score += 3;
					break;
				case 4:
					score += 4;
					break;
				case 5:
				default:
					score += 20;
				}
			}
			totalScore += score;

			applyGravity();
			listener.boardSelectivelyUpdated(fillEmptySquares());
		}

		return totalScore;
	}
}
