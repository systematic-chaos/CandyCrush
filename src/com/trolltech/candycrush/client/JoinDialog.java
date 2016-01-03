package com.trolltech.candycrush.client;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QPalette;
import com.trolltech.qt.gui.QWidget;

public class JoinDialog extends QDialog {
	public static void main(String[] args) {

		QApplication.initialize(args);
		JoinDialog dialog = new JoinDialog();
		dialog.setModal(true);
		dialog.show();
		QApplication.exec();
	}

	public Signal3<String, Integer, Boolean> newPlayer = new Signal3<String, Integer, Boolean>();
	public Signal0 close = new Signal0();

	private Ui_JoinDialog ui = new Ui_JoinDialog();

	public JoinDialog() {
		this(null);
	}

	public JoinDialog(QWidget parent) {
		super(parent);

		ui.setupUi(this);

		ui.joinButton.clicked.connect(this, "newPlayer()");
		ui.nameTextEdit.returnPressed.connect(this, "newPlayer()");
		ui.teamsComboBox.currentIndexChanged.connect(this,
				"setPlayerColor(int)");
		ui.exitButton.clicked.connect(close);
	}

	protected boolean newPlayer() {
		if (ui.teamsComboBox.currentIndex() > 0
				&& ui.nameTextEdit.text().trim().length() > 0) {
			newPlayer.emit(ui.nameTextEdit.text().trim(),
					ui.teamsComboBox.currentIndex() - 1,
					ui.fullTeamCheckBox.isChecked());
			return true;
		}
		return false;
	}

	public void setPlayerColor(int colorIndex) {
		QPalette palette = colorIndex > 0 ? new QPalette(
				ui.COLORS[colorIndex - 1]) : null;
		ui.nameTextEdit.setPalette(palette);
		ui.joinButton.setPalette(palette);
		ui.fullTeamCheckBox.setPalette(palette);
	}
}
