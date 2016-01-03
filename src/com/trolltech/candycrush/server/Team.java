package com.trolltech.candycrush.server;

import java.util.ArrayList;
import java.util.List;

import Ice.Identity;

public class Team {
	private String name;
	private ArrayList<Player> members;
	private boolean complete;
	private int score;
	private boolean out;

	public Team(String name) {
		this.name = name;
		members = new ArrayList<Player>();
		complete = false;
		score = 0;
		out = false;
	}

	public String getName() {
		return name;
	}

	public List<Player> getMembers() {
		return members;
	}

	public List<String> getMemberNames() {
		ArrayList<String> names = new ArrayList<String>(members.size());
		for (Player member : members)
			names.add(member.getName());
		return names;
	}

	public int getNActiveMembers() {
		int nActiveMembers = 0;
		for (Player p : members)
			if (!p.isLate())
				nActiveMembers++;
		return nActiveMembers;
	}

	public boolean addMember(Player player) {
		if (player == null || members.contains(player))
			return false;
		return members.add(player);
	}

	public Player getMember(Identity id) {
		Player member = null;
		for (Player p : members)
			if (p.getIdentity().name.equals(id.name)
					&& p.getIdentity().category.equals(id.category))
				member = p;
		return member;
	}

	public boolean hasMember(Identity id) {
		return getMember(id) != null;
	}

	public boolean removeMember(Identity id) {
		boolean removed = false;
		for (int n = 0; n < members.size() && !removed; n++) {
			Identity memberId = members.get(n).getIdentity();
			if (id.name.equals(memberId.name)
					&& id.category.equals(memberId.category)) {
				members.remove(n);
				removed = true;
			}
		}
		return removed;
	}

	public void teamComplete() {
		complete = true;
	}

	public boolean isTeamComplete() {
		return complete;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public boolean isOut() {
		return out;
	}

	public void kick(boolean out) {
		this.out = out;
	}

	@Override
	public boolean equals(Object o) {
		boolean eq = false;
		if (o != null && o instanceof Team) {
			eq = name.equals(((Team) o).getName());
		}
		return eq;
	}
}
