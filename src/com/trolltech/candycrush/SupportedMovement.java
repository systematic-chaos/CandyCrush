package com.trolltech.candycrush;

public class SupportedMovement extends candycrush.Movement implements
		Comparable<SupportedMovement> {

	private static final long serialVersionUID = 737447887554579376L;

	public SupportedMovement(int fr, int fc, int tr, int tc) {
		super(fr, fc, tr, tc, 1);
	}

	public SupportedMovement(int fr, int fc, int tr, int tc, int ns) {
		super(fr, fc, tr, tc, ns);
	}

	@Override
	public boolean equals(Object o) {
		SupportedMovement m = (SupportedMovement) o;
		return m.fromRow == this.fromRow && m.fromColumn == this.fromColumn
				&& m.toRow == this.toRow && m.toColumn == this.toColumn;
	}

	public int compareTo(SupportedMovement m) {
		return this.nSupporters - m.nSupporters;
	}

	@Override
	public String toString() {
		return String.format("[%d][%d] <---> [%d][%d]\t%d votos", fromRow + 1,
				fromColumn + 1, toRow + 1, toColumn + 1, nSupporters);
	}
}
