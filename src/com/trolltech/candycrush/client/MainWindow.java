package com.trolltech.candycrush.client;

import java.util.Iterator;
import java.util.List;

import candycrush.Movement;
import candycrush.Square;

import com.trolltech.candycrush.ClientOperationsNC;
import com.trolltech.candycrush.ServerOperationsNC;
import com.trolltech.candycrush.SupportedMovement;
import com.trolltech.qt.core.*;
import com.trolltech.qt.core.Qt.MatchFlag;
import com.trolltech.qt.gui.*;

public class MainWindow extends QMainWindow implements ClientOperationsNC {
	public QWidget centralWidget;
	public QSplitter splitter1;
	public QSplitter splitter2;
	public QTextEdit logText;
	public ChatWidget chatWidget;
	public BoardWidget boardWidget;
	public QSplitter splitter4;
	public QListWidget team1listWidget;
	public QLCDNumber team1Score;
	public QSplitter splitter3;
	public QListWidget team2listWidget;
	public QLCDNumber team2Score;
	public QStatusBar statusBar;

	private static final int TIMEOUT = 60000;

	private int myTeam;
	private ServerOperationsNC serverOperator;
	private QTimer timer;
	private JoinDialog joinDialog;
	private int currentTurn;

	public MainWindow() {
		super();
		setupUi();
		timer = new QTimer();
		boardWidget.setEnabled(false);
		boardWidget.movementSelected.connect(this,
				"sendMovement(SupportedMovement)");
		chatWidget.sendMessage.connect(this, "sendMessage(String)");
		timer.timeout.connect(this, "timeout()");
		QApplication.instance().aboutToQuit.connect(this, "aboutToQuit()");
		joinDialog = new JoinDialog(this) {
			@Override
			public void reject() {
			}
		};
		joinDialog.newPlayer.connect(this, "login(String, int, boolean)");
		joinDialog.close.connect(this, "close()");
		joinDialog.setModal(true);
		joinDialog.show();
	}

	public void initializeServerOperator(ServerOperationsNC domain) {
		serverOperator = domain;
	}

	protected synchronized void aboutToQuit() {
		if (joinDialog.isVisible())
			joinDialog.close();
		serverOperator.shutdown();
	}

	public void setupUi() {
		this.setObjectName("MainWindow");
		this.resize(new QSize(1264, 833).expandedTo(this.minimumSizeHint()));
		centralWidget = new QWidget(this);
		centralWidget.setObjectName("centralWidget");
		splitter2 = new QSplitter(centralWidget);
		splitter2.setObjectName("splitter2");
		splitter2.setGeometry(new QRect(20, 20, 1226, 600));
		splitter2
				.setOrientation(com.trolltech.qt.core.Qt.Orientation.Horizontal);
		splitter2.setHandleWidth(20);
		splitter1 = new QSplitter(splitter2);
		splitter1.setObjectName("splitter1");
		splitter1.setOrientation(com.trolltech.qt.core.Qt.Orientation.Vertical);
		splitter1.setHandleWidth(20);
		logText = new QTextEdit(splitter1);
		logText.setObjectName("logText");
		QSizePolicy sizePolicy = new QSizePolicy(
				com.trolltech.qt.gui.QSizePolicy.Policy.Preferred,
				com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
		sizePolicy.setHorizontalStretch((byte) 0);
		sizePolicy.setVerticalStretch((byte) 0);
		sizePolicy.setHeightForWidth(logText.sizePolicy().hasHeightForWidth());
		logText.setSizePolicy(sizePolicy);
		logText.setMinimumSize(new QSize(590, 280));
		logText.setMaximumSize(new QSize(590, 280));
		logText.setVerticalScrollBarPolicy(com.trolltech.qt.core.Qt.ScrollBarPolicy.ScrollBarAlwaysOn);
		splitter1.addWidget(logText);
		chatWidget = new ChatWidget(splitter1);
		chatWidget.setObjectName("chatWidget");
		chatWidget.setMinimumSize(new QSize(590, 300));
		chatWidget.setMaximumSize(new QSize(590, 300));
		splitter1.addWidget(chatWidget);
		splitter2.addWidget(splitter1);
		boardWidget = new BoardWidget(splitter2);
		boardWidget.setObjectName("boardWidget");
		boardWidget.setMinimumSize(new QSize(600, 600));
		boardWidget.setMaximumSize(new QSize(630, 600));
		splitter2.addWidget(boardWidget);
		splitter4 = new QSplitter(centralWidget);
		splitter4.setObjectName("splitter4");
		splitter4.setGeometry(new QRect(660, 640, 420, 100));
		QSizePolicy sizePolicy1 = new QSizePolicy(
				com.trolltech.qt.gui.QSizePolicy.Policy.MinimumExpanding,
				com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
		sizePolicy1.setHorizontalStretch((byte) 0);
		sizePolicy1.setVerticalStretch((byte) 0);
		sizePolicy1.setHeightForWidth(splitter4.sizePolicy()
				.hasHeightForWidth());
		splitter4.setSizePolicy(sizePolicy1);
		splitter4
				.setOrientation(com.trolltech.qt.core.Qt.Orientation.Horizontal);
		splitter4.setHandleWidth(20);
		team2listWidget = new QListWidget(splitter4);
		team2listWidget.setObjectName("team2listWidget");
		team2listWidget.setMinimumSize(new QSize(300, 100));
		team2listWidget.setMaximumSize(new QSize(300, 100));
		QPalette palette = new QPalette();
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Button,
				new QColor(0, 0, 255));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Light,
				new QColor(127, 127, 255));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Midlight, new QColor(63, 63, 255));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Dark,
				new QColor(0, 0, 127));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Mid,
				new QColor(0, 0, 170));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Text,
				new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Base,
				new QColor(255, 255, 255));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Window,
				new QColor(0, 0, 255));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Shadow,
				new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.AlternateBase, new QColor(127, 127, 255));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Button, new QColor(0, 0, 255));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Light, new QColor(127, 127, 255));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Midlight, new QColor(63, 63, 255));
		palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Dark,
				new QColor(0, 0, 127));
		palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Mid,
				new QColor(0, 0, 170));
		palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Text,
				new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Base,
				new QColor(255, 255, 255));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Window, new QColor(0, 0, 255));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.AlternateBase, new QColor(127, 127, 255));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 127));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Button, new QColor(0, 0, 255));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Light, new QColor(127, 127, 255));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Midlight, new QColor(63, 63, 255));
		palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Dark,
				new QColor(0, 0, 127));
		palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Mid,
				new QColor(0, 0, 170));
		palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Text,
				new QColor(0, 0, 127));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 127));
		palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Base,
				new QColor(0, 0, 255));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Window, new QColor(0, 0, 255));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.AlternateBase, new QColor(0, 0, 255));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		team2listWidget.setPalette(palette);
		splitter4.addWidget(team2listWidget);
		team2Score = new QLCDNumber(splitter4);
		team2Score.setObjectName("team2Score");
		team2Score.setMinimumSize(new QSize(80, 80));
		team2Score.setMaximumSize(new QSize(100, 100));
		team2Score.setPalette(palette);
		team2Score.setFrameShape(com.trolltech.qt.gui.QFrame.Shape.StyledPanel);
		team2Score.setFrameShadow(com.trolltech.qt.gui.QFrame.Shadow.Sunken);
		team2Score.setNumDigits(3);
		splitter4.addWidget(team2Score);
		splitter3 = new QSplitter(centralWidget);
		splitter3.setObjectName("splitter3");
		splitter3.setGeometry(new QRect(160, 640, 420, 100));
		QSizePolicy sizePolicy2 = new QSizePolicy(
				com.trolltech.qt.gui.QSizePolicy.Policy.MinimumExpanding,
				com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
		sizePolicy2.setHorizontalStretch((byte) 0);
		sizePolicy2.setVerticalStretch((byte) 0);
		sizePolicy2.setHeightForWidth(splitter3.sizePolicy()
				.hasHeightForWidth());
		splitter3.setSizePolicy(sizePolicy2);
		splitter3
				.setOrientation(com.trolltech.qt.core.Qt.Orientation.Horizontal);
		splitter3.setHandleWidth(20);
		team1listWidget = new QListWidget(splitter3);
		team1listWidget.setObjectName("team1listWidget");
		team1listWidget.setMinimumSize(new QSize(300, 100));
		team1listWidget.setMaximumSize(new QSize(300, 100));
		palette = new QPalette();
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Button,
				new QColor(255, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Light,
				new QColor(255, 127, 127));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Midlight, new QColor(255, 63, 63));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Dark,
				new QColor(127, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Mid,
				new QColor(170, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Text,
				new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Base,
				new QColor(255, 255, 255));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Window,
				new QColor(255, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Shadow,
				new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.AlternateBase, new QColor(255, 127, 127));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Button, new QColor(255, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Light, new QColor(255, 127, 127));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Midlight, new QColor(255, 63, 63));
		palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Dark,
				new QColor(127, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Mid,
				new QColor(170, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Text,
				new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Base,
				new QColor(255, 255, 255));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Window, new QColor(255, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.AlternateBase, new QColor(255, 127, 127));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.WindowText, new QColor(127, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Button, new QColor(255, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Light, new QColor(255, 127, 127));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Midlight, new QColor(255, 63, 63));
		palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Dark,
				new QColor(127, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Mid,
				new QColor(170, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Text,
				new QColor(127, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ButtonText, new QColor(127, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Base,
				new QColor(255, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Window, new QColor(255, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.AlternateBase, new QColor(255, 0, 0));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		team1listWidget.setPalette(palette);
		splitter3.addWidget(team1listWidget);
		team1Score = new QLCDNumber(splitter3);
		team1Score.setObjectName("team1Score");
		QSizePolicy sizePolicy3 = new QSizePolicy(
				com.trolltech.qt.gui.QSizePolicy.Policy.Minimum,
				com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
		sizePolicy3.setHorizontalStretch((byte) 0);
		sizePolicy3.setVerticalStretch((byte) 20);
		sizePolicy3.setHeightForWidth(team1Score.sizePolicy()
				.hasHeightForWidth());
		team1Score.setSizePolicy(sizePolicy3);
		team1Score.setMinimumSize(new QSize(80, 80));
		team1Score.setMaximumSize(new QSize(100, 100));
		team1Score.setPalette(palette);
		team1Score.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.NoFocus);
		team1Score.setFrameShape(com.trolltech.qt.gui.QFrame.Shape.StyledPanel);
		team1Score.setFrameShadow(com.trolltech.qt.gui.QFrame.Shadow.Sunken);
		team1Score.setNumDigits(3);
		splitter3.addWidget(team1Score);
		this.setCentralWidget(centralWidget);
		statusBar = new QStatusBar(this);
		statusBar.setObjectName("statusBar");
		statusBar.setSizeGripEnabled(false);
		this.setStatusBar(statusBar);

		retranslateUi();

		this.connectSlotsByName();
	} // setupUi

	public void retranslateUi() {
		this.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate(
				"MainWindow", "Candy Crush", null));
	} // retranslateUi

	protected boolean login(String nick, int team, boolean complete) {
		if (serverOperator.joinTeam(nick, team)) {
			joinDialog.accept();
			myTeam = team;
			newParticipant(nick, team);
			if (complete)
				serverOperator.teamComplete();
			return true;
		}
		return false;
	}

	public void newParticipant(String nick, int team) {
		switch (team) {
		case 0:
			team1listWidget.addItem(new QListWidgetItem(nick));
			customLog(nick + " se ha unido al equipo rojo", 0);
			break;
		case 1:
			team2listWidget.addItem(new QListWidgetItem(nick));
			customLog(nick + " se ha unido al equipo azul", 1);
			break;
		}
		if (team == myTeam) {
			chatWidget.newParticipant(nick);
		}
	}

	public void teamComplete(int team) {
		switch (team) {
		case 0:
			customLog("El equipo rojo está listo", 0);
			break;
		case 1:
			customLog("El equipo azul está listo", 0);
			break;
		}
	}

	public void participantLeft(String nick, int team) {
		List<QListWidgetItem> items;
		QListWidgetItem item;
		if (team == myTeam) {
			chatWidget.participantLeft(nick);
		}
		switch (team) {
		case 0:
			customLog(nick + ", del equipo rojo, ha abandonado la partida", 0);
			items = team1listWidget.findItems(nick, MatchFlag.MatchExactly);
			for (Iterator<QListWidgetItem> iterator = items.iterator(); iterator
					.hasNext();) {
				item = (QListWidgetItem) iterator.next();
				team1listWidget.removeItemWidget(item);
				team1listWidget.takeItem(team1listWidget.row(item));
			}
			break;
		case 1:
			customLog(nick + ", del equipo azul, ha abandonado la partida", 1);
			items = team2listWidget.findItems(nick, MatchFlag.MatchExactly);
			for (Iterator<QListWidgetItem> iterator = items.iterator(); iterator
					.hasNext();) {
				item = (QListWidgetItem) iterator.next();
				team2listWidget.removeItemWidget(item);
				team2listWidget.takeItem(team2listWidget.row(item));
			}
			break;
		}
	}

	public void startGame() {
		team1Score.display(0);
		team2Score.display(0);
		logText.clear();
		log("Comienza el juego!!!");
	}

	public void endGame(int team) {
		boardWidget.setEnabled(false);
		log("Final del juego!");
		switch (team) {
		case 0:
			customLog("Gana el equipo rojo!!!!", 0);
			break;
		case 1:
			customLog("Gana el equipo azul!!!!", 1);
			break;
		default:
			log("Nadie ha ganado...");
		}
	}

	public void setBoard(int[][] board) {
		boardWidget.setBoard(board);
	}

	public void _setBoard(List<List<Integer>> board) {
		int[][] b = new int[board.size()][];
		for (int i = 0; i < b.length; i++) {
			List<Integer> bAux = board.get(i);
			b[i] = new int[bAux.size()];
			for (int j = 0; j < b[i].length; j++) {
				b[i][j] = bAux.get(j);
			}
		}
		setBoard(b);
	}

	public void turn(int team) {
		if ((currentTurn = team) == myTeam) {
			boardWidget.undoUncommitedMovement();
			boardWidget.setEnabled(true);
			timer.start(TIMEOUT);
		}
		switch (team) {
		case 0:
			customLog("Es el turno del equipo rojo", 0);
			break;
		case 1:
			customLog("Es el turno del equipo azul", 1);
			break;
		}
	}

	public void log(String message) {
		customLog(message, 2);
	}

	public void customLog(String message, int code) {
		QTextCursor cursor = new QTextCursor(logText.textCursor());
		cursor.movePosition(QTextCursor.MoveOperation.End);
		QColor logColor;
		switch (code) {
		case 0:
			logColor = QColor.red;
			break;
		case 1:
			logColor = QColor.blue;
			break;
		default:
			logColor = QColor.black;
		}
		logText.setTextColor(logColor);
		logText.append(message);
		QScrollBar bar = logText.verticalScrollBar();
		bar.setValue(bar.maximum());
	}

	public void logStatus(String statusMessage) {
		String currentMessage = statusBar.currentMessage();
		if (statusMessage.startsWith("[") && statusMessage.endsWith("]")) {
			if (currentMessage.contains("[") && currentMessage.endsWith("]")) {
				statusMessage = currentMessage.replace(
						currentMessage.substring(currentMessage.indexOf("[")),
						statusMessage);
			} else {
				statusMessage = currentMessage.concat("\t" + statusMessage);
			}
		}
		statusBar.showMessage(statusMessage);
	}

	public void makeMovement(Movement mov) {
		boardWidget.undoUncommitedMovement();
		boardWidget.makeMovement(mov);
		SupportedMovement m = new SupportedMovement(mov.fromRow,
				mov.fromColumn, mov.toRow, mov.toColumn, mov.nSupporters);
		switch (currentTurn) {
		case 0:
			customLog("El equipo rojo mueve: " + m, 0);
			break;
		case 1:
			customLog("El equipo azul mueve: " + m, 1);
			break;
		}
	}

	public void fillNewSquares(Square[] newSquares) {
		boardWidget.updateBoardSelectively(newSquares);
	}

	public void _fillNewSquares(List<Square> newSquares) {
		fillNewSquares(newSquares.toArray(new Square[0]));
	}

	public void score(int team, int score) {
		switch (team) {
		case 0:
			customLog("El equipo rojo consigue " + score + " puntos", 0);
			team1Score.display(team1Score.intValue() + score);
			customLog("El equipo rojo acumula " + team1Score.intValue()
					+ " puntos", 0);
			break;
		case 1:
			customLog("El equipo azul consigue " + score + " puntos", 1);
			team2Score.display(team2Score.intValue() + score);
			customLog("El equipo azul acumula " + team2Score.intValue()
					+ " puntos", 1);
			break;
		}
	}

	public void receiveMessage(String from, String message) {
		chatWidget.messageReceived(from, message);
	}

	protected void timeout() {
		sendMovement(new SupportedMovement(-1, -1, -1, -1));
	}

	private void sendMovement(SupportedMovement mov) {
		timer.stop();
		serverOperator.movementProposal(mov);
		boardWidget.setEnabled(false);
	}

	protected void sendMessage(String message) {
		serverOperator.message(message);
	}

	public static void main(String[] args) {
		QApplication.initialize(args);
		MainWindow mWindow = new MainWindow();
		Client client = new Client();
		Thread iceThread = new Thread(client);
		mWindow.initializeServerOperator(client);
		client.initializeClientOperator(mWindow);
		iceThread.start();
		mWindow.show();
		QApplication.exec();
		if (iceThread.isAlive()) {
			try {
				iceThread.join();
			} catch (InterruptedException ie) {
				System.err.println("InterruptedException: " + ie.getMessage());
			}
		}
	}
}
