package com.trolltech.candycrush.client;

import com.trolltech.candycrush.ServerOperationsNC;

import candycrush.Movement;
import candycrush.ServerPrx;
import candycrush.ServerPrxHelper;
import candycrush._ClientOperationsNC;
import Ice.Application;
import Ice.Identity;
import Ice.LocalException;
import Ice.ObjectAdapter;

public class Client extends Application implements Runnable, ServerOperationsNC {

	private ServerPrx clientProxy;
	private Identity id;
	private ClientI servant;

	public Client() {
		super();
	}

	public int run(String[] args) {
		// Client proxy for handling communication with the game server
		clientProxy = ServerPrxHelper.checkedCast(communicator()
				.propertyToProxy("CandyCrushClient.Proxy"));

		// Receiver for messages sent from the game server (bidirectional
		// communication)
		ObjectAdapter adapter = communicator().createObjectAdapter("");
		id = new Ice.Identity();
		id.name = java.util.UUID.randomUUID().toString();
		id.category = "";
		adapter.add(servant, id);
		adapter.activate();
		clientProxy.ice_getConnection().setAdapter(adapter);

		// Clear the Application installed interrupt callback and install our
		// own shutdown hook
		setInterruptHook(new ShutdownHook());

		// The game starts
		communicator().waitForShutdown();
		return 0;
	}

	public void run() {
		this.main("Client", new String[0], "CandyCrushClient.cfg");
	}

	public void initializeClientOperator(_ClientOperationsNC presentation) {
		servant = new ClientI(presentation);
	}

	public synchronized void shutdown() {
		clientProxy.leaveGame(id);
		communicator().destroy();
	}

	public boolean joinTeam(String nick, int team) {
		return clientProxy.joinTeam(id, nick, team);
	}

	public void teamComplete() {
		clientProxy.teamComplete(id);
	}

	public void movementProposal(Movement mov) {
		clientProxy.movementProposal(id, mov);
	}

	public void message(String msg) {
		clientProxy.message(id, msg);
	}

	class ShutdownHook extends Thread {
		public void run() {
			try {
				shutdown();
			} catch (LocalException le) {
				le.printStackTrace();
			}
		}
	}
}
