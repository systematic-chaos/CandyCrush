package com.trolltech.candycrush.client;

import com.trolltech.qt.QtBlockedSlot;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.core.QTimer;
import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QPushButton;

public class FruitButton extends QPushButton {
	protected int row, column;

	public Signal2<Integer, Integer> fruitClicked = new Signal2<Integer, Integer>();
	public Signal2<Integer, Integer> fruitPressed = new Signal2<Integer, Integer>();
	public Signal2<Integer, Integer> fruitReleased = new Signal2<Integer, Integer>();

	protected QTimer animationTimer;
	protected int iconSize;
	protected boolean toEnlarge;

	public static final int MIN_SIZE = 40, MAX_SIZE = 67;
	public static final int ANIMATION_TIME = 60;

	public FruitButton(int row, int column, QIcon icon) {
		super(icon, "");
		setRowColumn(row, column);

		setMinimumSize(new QSize(MAX_SIZE, MAX_SIZE));
		setMaximumSize(new QSize(MAX_SIZE, MAX_SIZE));
		setIconSize(new QSize(MIN_SIZE, MIN_SIZE));

		animationTimer = new QTimer(this);
		animationTimer.setInterval(ANIMATION_TIME);
		// animationTimer.setSingleShot(true);
		iconSize = MIN_SIZE;
		toEnlarge = true;

		clicked.connect(this, "onFruitClick()");
		pressed.connect(this, "onFruitPress()");
		released.connect(this, "onFruitRelease()");
		animationTimer.timeout.connect(this, "animateButtonIcon()");
	}

	@QtBlockedSlot
	public void setRowColumn(int row, int column) {
		this.row = row;
		this.column = column;
	}

	@QtBlockedSlot
	public int getRow() {
		return row;
	}

	@QtBlockedSlot
	public void setRow(int row) {
		this.row = row;
	}

	@QtBlockedSlot
	public int getColumn() {
		return column;
	}

	@QtBlockedSlot
	public void setColumn(int column) {
		this.column = column;
	}

	protected void onFruitClick() {
		fruitClicked.emit(row, column);
		animationTimer.start();
	}

	protected void onFruitPress() {
		fruitPressed.emit(row, column);
		animationTimer.start();
	}

	protected void onFruitRelease() {
		fruitReleased.emit(row, column);
		animationTimer.stop();
	}

	public void startAnimation() {
		animationTimer.start();
	}

	public void _setIcon(QIcon icon) {
		setIcon(icon);
		setIconSize(new QSize(iconSize, iconSize));
	}

	protected void animateButtonIcon() {
		if (toEnlarge) {
			if (++iconSize + 4 >= MAX_SIZE) {
				toEnlarge = false;
			}
		} else {
			if (--iconSize <= MIN_SIZE) {
				animationTimer.stop();
				toEnlarge = true;
			}
		}
		setIconSize(new QSize(iconSize, iconSize));
	}
}
