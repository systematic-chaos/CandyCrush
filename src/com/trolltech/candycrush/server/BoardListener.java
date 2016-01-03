package com.trolltech.candycrush.server;

import candycrush.Movement;
import candycrush.Square;

public interface BoardListener {
	public void boardUpdated();

	public void movementSelected(Movement movement);

	public void movementScored(int score);

	public void boardSelectivelyUpdated(Square[] newSquares);
}
