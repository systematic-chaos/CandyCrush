package com.trolltech.candycrush;

import candycrush.Movement;
import candycrush.Square;

public interface IBoard {
	public int[][] getBoard();

	public void setBoard(int[][] newBoardValues);

	public void updateBoardSelectively(Square[] newSquaresValues);

	public int getNRows();

	public int getNColumns();

	public int getNFruits();

	public boolean makeMovement(Movement mov);
}
