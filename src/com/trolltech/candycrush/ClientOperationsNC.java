package com.trolltech.candycrush;

import java.util.List;

import candycrush.Square;
import candycrush._ClientOperationsNC;

public interface ClientOperationsNC extends _ClientOperationsNC {
	void _setBoard(List<List<Integer>> boardValues);

	void _fillNewSquares(List<Square> newSquares);
}
