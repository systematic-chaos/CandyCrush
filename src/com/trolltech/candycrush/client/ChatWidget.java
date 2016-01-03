package com.trolltech.candycrush.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.trolltech.qt.core.QProcess;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.MatchFlag;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QListWidgetItem;
import com.trolltech.qt.gui.QScrollBar;
import com.trolltech.qt.gui.QTextTableFormat;
import com.trolltech.qt.gui.QWidget;
import com.trolltech.qt.network.QHostInfo;

public class ChatWidget extends QWidget {

	public static void main(String[] args) {
		QApplication.initialize(args);
		ChatWidget chat = new ChatWidget();
		chat.show();
		QApplication.exec();
	}

	public Signal1<String> sendMessage = new Signal1<String>();
	public Signal2<String, String> sendUserMessage = new Signal2<String, String>();

	private Ui_ChatWidget ui = new Ui_ChatWidget();
	private HashMap<String, QColor> participants;
	private String myNickName;
	private QTextTableFormat tableFormat = new QTextTableFormat();

	public ChatWidget() {
		this(null);
	}

	public ChatWidget(QWidget parent) {
		super(parent);

		ui.setupUi(this);

		ui.lineEdit.setFocusPolicy(Qt.FocusPolicy.StrongFocus);
		ui.textEdit.setFocusPolicy(Qt.FocusPolicy.NoFocus);
		ui.textEdit.setReadOnly(true);
		ui.listWidget.setFocusPolicy(Qt.FocusPolicy.NoFocus);

		ui.lineEdit.returnPressed.connect(this, "sendMessage()");

		participants = new HashMap<String, QColor>();

		myNickName = "";
		List<String> sysEnv = QProcess.systemEnvironment();
		for (String env : sysEnv) {
			if (env.startsWith("USER=")) {
				if (myNickName.length() > 0) {
					myNickName += ":";
				}
				myNickName += env.substring("USER=".length());
			}
		}
		myNickName += "@" + QHostInfo.localHostName();

		tableFormat.setBorder(0);

		setWindowTitle("Candy Crush ChatWidget " + myNickName);
	}

	public String tr(String str, Object... arguments) {
		return String.format(tr(str), arguments);
	}

	public void messageReceived(final String from, final String message) {
		if (from.equals("") || message.equals(""))
			return;

		ui.textEdit.setTextColor(participants.get(from));
		ui.textEdit.append("<" + from + "> " + message);
		QScrollBar bar = ui.textEdit.verticalScrollBar();
		bar.setValue(bar.maximum());
	}

	protected void sendMessage() {
		String text = ui.lineEdit.text();
		if (text.equals(""))
			return;

		if (text.startsWith("/")) {
			QColor color = ui.textEdit.textColor();
			ui.textEdit.setTextColor(QColor.red);
			ui.textEdit.append(tr("! Unknown command: ")
					+ text.substring(text.indexOf(' ')));
			ui.textEdit.setTextColor(color);
		} else {
			sendMessage.emit(text);
			sendUserMessage.emit(myNickName, text);
		}

		ui.lineEdit.clear();
	}

	public void newParticipant(final String nick) {
		if (nick.equals(""))
			return;

		String[] colors = { "black", "green", "darkRed", "darkGreen",
				"darkBlue", "cyan", "magenta", "yellow", "gray", "darkCyan",
				"darkMagenta", "darkYellow", "darkGray", "blue", "red" };
		QColor color = new QColor(colors[participants.size() % colors.length]);
		participants.put(nick, color);

		ui.textEdit.setTextColor(color);
		ui.textEdit.append(String.format(tr("* %1$s se ha unido"), nick));
		QListWidgetItem item = new QListWidgetItem(nick);
		item.setForeground(new QBrush(color));
		ui.listWidget.addItem(item);
	}

	public void participantLeft(final String nick) {
		if (nick.equals(""))
			return;

		List<QListWidgetItem> items = ui.listWidget.findItems(nick,
				MatchFlag.MatchExactly);

		for (Iterator<QListWidgetItem> iterator = items.iterator(); iterator
				.hasNext();) {
			QListWidgetItem item = (QListWidgetItem) iterator.next();
			ui.listWidget.removeItemWidget(item);
			ui.listWidget.takeItem(ui.listWidget.row(item));
		}

		QColor color = ui.textEdit.textColor();
		ui.textEdit.setTextColor(participants.get(nick));
		ui.textEdit.append(String.format(tr("* %1$s ha abandonado la partida"),
				nick));
		ui.textEdit.setTextColor(color);
		participants.remove(nick);
	}
}
