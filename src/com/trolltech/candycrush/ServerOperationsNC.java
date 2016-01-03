package com.trolltech.candycrush;

import candycrush.Movement;

public interface ServerOperationsNC {
	boolean joinTeam(String nick, int team);

	void teamComplete();

	void movementProposal(Movement mov);

	void message(String msg);

	void shutdown();
}
