package com.trolltech.candycrush.server;

import Ice.Identity;
import candycrush.ClientPrx;

public class Player {
	private String name;
	private ClientPrx proxy;
	private boolean late;

	public Player(String name, ClientPrx proxy) {
		this.name = name;
		this.proxy = proxy;
		this.late = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientPrx getProxy() {
		return proxy;
	}

	public void setProxy(ClientPrx proxy) {
		this.proxy = proxy;
	}

	public Identity getIdentity() {
		return proxy.ice_getIdentity();
	}

	public boolean isLate() {
		return late;
	}

	public void setLate(boolean late) {
		this.late = late;
	}

	@Override
	public boolean equals(Object o) {
		boolean eq = false;
		if (o != null && o instanceof Player) {
			Player p = (Player) o;
			eq = proxy.ice_getIdentity().equals(p.getProxy().ice_getIdentity())
					&& name.equals(p.getName());
		}
		return eq;
	}
}
