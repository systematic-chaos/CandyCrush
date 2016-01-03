package com.trolltech.candycrush.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import candycrush.Match;
import candycrush.Movement;
import candycrush.Square;

import com.trolltech.candycrush.IBoard;
import com.trolltech.candycrush.SupportedMovement;
import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QLayout;
import com.trolltech.qt.gui.QPixmap;
import com.trolltech.qt.gui.QWidget;

public class BoardWidget extends QWidget implements IBoard {

	protected final static int DIMENSION = 9;

	public Signal0 boardUpdated = new Signal0();
	public Signal1<SupportedMovement> movementSelected = new Signal1<SupportedMovement>();
	public Signal1<Integer> movementScored = new Signal1<Integer>();

	protected FruitButton[][] board;
	protected int[][] boardValues;
	private int[][] previousBoardValues;

	protected int nRows;
	protected int nColumns;
	protected QIcon[] fruits;

	private boolean previouslyClicked;
	private boolean previouslyPressed;
	private int previousRow;
	private int previousColumn;

	public BoardWidget(QWidget parent) {
		this(parent, DIMENSION, DIMENSION, null);
	}

	public BoardWidget(QWidget parent, int xSize, int ySize, QIcon[] fruits) {

		super(parent);

		this.nColumns = xSize > 0 ? xSize : DIMENSION;
		this.nRows = ySize > 0 ? ySize : DIMENSION;
		if (fruits != null) {
			if (fruits.length > 1) {
				this.fruits = fruits;
			}
		}
		if (this.fruits == null) {
			this.fruits = new QIcon[6];
			this.fruits[0] = new QIcon(new QPixmap("images/bananas.xpm"));
			this.fruits[1] = new QIcon(new QPixmap("images/grapes.xpm"));
			this.fruits[2] = new QIcon(new QPixmap("images/pear.xpm"));
			this.fruits[3] = new QIcon(new QPixmap("images/pineapple.xpm"));
			this.fruits[4] = new QIcon(new QPixmap("images/strawberry.xpm"));
			this.fruits[5] = new QIcon(new QPixmap("images/watermelon.xpm"));
		}

		QGridLayout layout = new QGridLayout();

		createBoard();

		putBoardInLayout(layout);

		layout.setSizeConstraint(QLayout.SizeConstraint.SetFixedSize);
		setLayout(layout);

		setWindowTitle("Candy Crush");

		resize(600, 600);
		move(0, 0);
		show();
	}

	public int[][] getBoard() {
		return boardValues;
	}

	public void setBoard(int[][] newBoardValues) {
		if (newBoardValues.length == nRows
				&& newBoardValues[0].length == nColumns) {
			for (int i = 0; i < nRows; i++) {
				boardValues[i] = newBoardValues[i].clone();
				for (int j = 0; j < nColumns; j++) {
					board[i][j].setIcon(fruits[boardValues[i][j]]);
				}
			}
		}
		boardUpdated.emit();
	}

	public void updateBoardSelectively(Square[] newSquareValues) {
		for (Square newValue : newSquareValues) {
			boardValues[newValue.row][newValue.column] = newValue.value;
			board[newValue.row][newValue.column]
					.setIcon(fruits[newValue.value]);
		}
		applyBoardEffects(false);
	}

	public int getNRows() {
		return nRows;
	}

	public int getNColumns() {
		return nColumns;
	}

	public int getNFruits() {
		return fruits.length;
	}

	private void createBoard() {
		FruitButton qfb;
		board = new FruitButton[nRows][nColumns];
		boardValues = new int[nRows][nColumns];
		Random rand = new Random(new Date().getTime());
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				boardValues[i][j] = rand.nextInt(fruits.length);
				qfb = new FruitButton(i, j, fruits[boardValues[i][j]]);
				qfb.fruitClicked.connect(this, "clicked(int, int)");
				board[i][j] = qfb;
			}
		}

		previousBoardValues = null;
		previouslyClicked = false;
		previouslyPressed = false;
	}

	private void putBoardInLayout(QGridLayout layout) {
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				layout.addWidget(board[i][j], i, j);
			}
		}
	}

	protected void clicked(int row, int column) {
		if (!isEnabled())
			return;

		if (row < 0 || row >= nRows || column < 0 || column >= nColumns)
			return;

		if (previouslyClicked) {
			SupportedMovement mov = new SupportedMovement(previousRow,
					previousColumn, row, column);
			if (makeMovement(mov, false)) {
				movementSelected.emit(mov);
			}
			previouslyClicked = false;
		} else {
			previouslyClicked = true;
			previousRow = row;
			previousColumn = column;
			previouslyPressed = false;
		}
	}

	protected void pressed(int row, int column) {
		if (!isEnabled())
			return;

		if (row < 0 || row >= nRows || column < 0 || column >= nColumns)
			return;

		previouslyPressed = true;
		previousRow = row;
		previousColumn = column;
		previouslyClicked = false;
	}

	protected void released(int row, int column) {
		if (!isEnabled())
			return;

		if (row < 0 || row >= nRows || column < 0 || column >= nColumns)
			return;

		if (previouslyPressed) {
			SupportedMovement mov = new SupportedMovement(previousRow,
					previousColumn, row, column);
			if (makeMovement(mov, false)) {
				movementSelected.emit(mov);
			}
			previouslyPressed = false;
		} else {
			previouslyClicked = false;
		}
	}

	@QtBlockedSlot
	public boolean makeMovement(Movement mov) {
		return makeMovement(mov, true);
	}

	@QtBlockedSlot
	public boolean makeMovement(Movement mov, boolean commit) {
		if (!validMovement(mov))
			return false;

		if (!commit) {
			previousBoardValues = boardValues.clone();
			for (int i = 0; i < boardValues.length; i++)
				previousBoardValues[i] = boardValues[i].clone();
			animateButtons(mov);
		} else {
			previousBoardValues = null;
		}

		boardValues[mov.fromRow][mov.fromColumn] = boardValues[mov.fromRow][mov.fromColumn]
				^ boardValues[mov.toRow][mov.toColumn];
		boardValues[mov.toRow][mov.toColumn] = boardValues[mov.fromRow][mov.fromColumn]
				^ boardValues[mov.toRow][mov.toColumn];
		boardValues[mov.fromRow][mov.fromColumn] = boardValues[mov.fromRow][mov.fromColumn]
				^ boardValues[mov.toRow][mov.toColumn];
		board[mov.fromRow][mov.fromColumn]
				._setIcon(fruits[boardValues[mov.fromRow][mov.fromColumn]]);
		board[mov.toRow][mov.toColumn]
				._setIcon(fruits[boardValues[mov.toRow][mov.toColumn]]);

		int score = applyBoardEffects(!commit);
		if (commit) {
			movementScored.emit(score);
		}

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

	@QtBlockedSlot
	public boolean undoUncommitedMovement() {
		if (previousBoardValues == null)
			return false;

		boardValues = previousBoardValues;
		previousBoardValues = null;

		for (int i = 0; i < nRows; i++)
			for (int j = 0; j < nColumns; j++)
				board[i][j]
						.setIcon(boardValues[i][j] < fruits.length ? fruits[boardValues[i][j]]
								: null);

		boardUpdated.emit();

		return true;
	}

	private List<Match> findMatches() {
		List<Match> matches = new ArrayList<Match>();
		int rowLength;
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				if (boardValues[i][j] < fruits.length) {
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
					boardValues[match.row - i][match.column] = fruits.length;
					board[match.row - i][match.column].setIcon(null);
				}
				break;
			case 1:
				for (int i = 0; i < match.length; i++) {
					boardValues[match.row][match.column + i] = fruits.length;
					board[match.row][match.column + i].setIcon(null);
				}
				break;
			case 2:
				for (int i = 0; i < match.length; i++) {
					boardValues[match.row + i][match.column] = fruits.length;
					board[match.row + i][match.column].setIcon(null);
				}
				break;
			case 3:
				for (int i = 0; i < match.length; i++) {
					boardValues[match.row][match.column - i] = fruits.length;
					board[match.row][match.column - i].setIcon(null);
				}
				break;
			}
		}

		boardUpdated.emit();

		return matches;
	}

	private void applyGravity() {
		for (int i = 0; i < nColumns; i++) {
			for (int j = nRows - 2; j >= 0; j--) {
				if (boardValues[j][i] < fruits.length) {
					int k;
					for (k = j + 1; k < nRows
							&& boardValues[k][i] == fruits.length; k++)
						;
					if (--k > j) {
						boardValues[k][i] = boardValues[j][i];
						boardValues[j][i] = fruits.length;
						board[k][i].setIcon(board[j][i].icon());
						board[j][i].setIcon(null);
					}
				}
			}
		}

		boardUpdated.emit();
	}

	protected void fillEmptySquares() {
		Random rand = new Random(new Date().getTime());
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				if (boardValues[i][j] == fruits.length) {
					boardValues[i][j] = rand.nextInt(fruits.length);
					board[i][j].setIcon(fruits[boardValues[i][j]]);
				}
			}
		}

		boardUpdated.emit();
	}

	private void animateButtons(Movement mov) {
		board[mov.fromRow][mov.fromColumn].startAnimation();
		board[mov.toRow][mov.toColumn].startAnimation();
	}

	private int applyBoardEffects(boolean iterativeWipe) {
		List<Match> matches;
		int totalScore = 0, score;
		do {
			matches = removeMatches();
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
		} while (matches.size() > 0 && iterativeWipe);

		return totalScore;
	}
}
