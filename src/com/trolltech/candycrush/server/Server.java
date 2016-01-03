package com.trolltech.candycrush.server;

import Ice.Application;
import Ice.ObjectAdapter;

public class Server extends Application {
	public int run(String[] args) {
		// Game server: adapter and servant
		ObjectAdapter adapter = communicator().createObjectAdapter(
				"CandyCrushServer");
		adapter.add(new ServerI(communicator()), communicator()
				.stringToIdentity("CandyCrushServant"));
		adapter.activate();

		// The game loop starts: let's wait
		communicator().waitForShutdown();
		return 0;
	}

	public static void main(String[] args) {
		Server app = new Server();
		int status = app.main("CandyCrushServer", args, "CandyCrushServer.cfg");
		System.exit(status);
	}
}
