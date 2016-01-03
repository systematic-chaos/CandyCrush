/********************************************************************************
 ** Form generated from reading ui file 'CandyCrush.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.6.3
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
package com.trolltech.candycrush.client;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_MainWindow implements com.trolltech.qt.QUiForm<QMainWindow> {
	public QWidget centralWidget;
	public QSplitter splitter2;
	public QSplitter splitter1;
	public QTextEdit logText;
	public ChatWidget chatWidget;
	public BoardWidget boardWidget;
	public QSplitter splitter4;
	public QListView team2listView;
	public QLCDNumber team2Score;
	public QSplitter splitter3;
	public QListView team1listView;
	public QLCDNumber team1Score;
	public QStatusBar statusBar;

	public Ui_MainWindow() {
		super();
	}

	public void setupUi(QMainWindow MainWindow) {
		MainWindow.setObjectName("MainWindow");
		MainWindow.resize(new QSize(1264, 833).expandedTo(MainWindow
				.minimumSizeHint()));
		centralWidget = new QWidget(MainWindow);
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
		logText.setReadOnly(true);
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
		team2listView = new QListView(splitter4);
		team2listView.setObjectName("team2listWidget");
		team2listView.setMinimumSize(new QSize(300, 100));
		team2listView.setMaximumSize(new QSize(300, 100));
		QPalette palette = new QPalette();
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
		team2listView.setPalette(palette);
		splitter4.addWidget(team2listView);
		team2Score = new QLCDNumber(splitter4);
		team2Score.setObjectName("team2Score");
		team2Score.setMinimumSize(new QSize(80, 80));
		team2Score.setMaximumSize(new QSize(100, 100));
		QPalette palette1 = new QPalette();
		palette1.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Button, new QColor(255, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Light,
				new QColor(255, 127, 127));
		palette1.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Midlight, new QColor(255, 63, 63));
		palette1.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Dark,
				new QColor(127, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Mid,
				new QColor(170, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Text,
				new QColor(0, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette1.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Base,
				new QColor(255, 255, 255));
		palette1.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Window, new QColor(255, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.AlternateBase, new QColor(255, 127, 127));
		palette1.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette1.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Button, new QColor(255, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Light, new QColor(255, 127, 127));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Midlight, new QColor(255, 63, 63));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Dark, new QColor(127, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Mid,
				new QColor(170, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Text, new QColor(0, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Base, new QColor(255, 255, 255));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Window, new QColor(255, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.AlternateBase, new QColor(255, 127, 127));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette1.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.WindowText, new QColor(127, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Button, new QColor(255, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Light, new QColor(255, 127, 127));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Midlight, new QColor(255, 63, 63));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Dark, new QColor(127, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Mid,
				new QColor(170, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Text, new QColor(127, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ButtonText, new QColor(127, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Base, new QColor(255, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Window, new QColor(255, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.AlternateBase, new QColor(255, 0, 0));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette1.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		team2Score.setPalette(palette1);
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
		team1listView = new QListView(splitter3);
		team1listView.setObjectName("team1listWidget");
		team1listView.setMinimumSize(new QSize(300, 100));
		team1listView.setMaximumSize(new QSize(300, 100));
		QPalette palette2 = new QPalette();
		palette2.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
		palette2.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Button, new QColor(0, 0, 255));
		palette2.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Light,
				new QColor(127, 127, 255));
		palette2.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Midlight, new QColor(63, 63, 255));
		palette2.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Dark,
				new QColor(0, 0, 127));
		palette2.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Mid,
				new QColor(0, 0, 170));
		palette2.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Text,
				new QColor(0, 0, 0));
		palette2.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette2.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
		palette2.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Base,
				new QColor(255, 255, 255));
		palette2.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Window, new QColor(0, 0, 255));
		palette2.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette2.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.AlternateBase, new QColor(127, 127, 255));
		palette2.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette2.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Button, new QColor(0, 0, 255));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Light, new QColor(127, 127, 255));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Midlight, new QColor(63, 63, 255));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Dark, new QColor(0, 0, 127));
		palette2.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Mid,
				new QColor(0, 0, 170));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Text, new QColor(0, 0, 0));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Base, new QColor(255, 255, 255));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Window, new QColor(0, 0, 255));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.AlternateBase, new QColor(127, 127, 255));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette2.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 127));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Button, new QColor(0, 0, 255));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Light, new QColor(127, 127, 255));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Midlight, new QColor(63, 63, 255));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Dark, new QColor(0, 0, 127));
		palette2.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Mid,
				new QColor(0, 0, 170));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Text, new QColor(0, 0, 127));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 127));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Base, new QColor(0, 0, 255));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Window, new QColor(0, 0, 255));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.AlternateBase, new QColor(0, 0, 255));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette2.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		team1listView.setPalette(palette2);
		splitter3.addWidget(team1listView);
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
		QPalette palette3 = new QPalette();
		palette3.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
		palette3.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Button, new QColor(0, 0, 255));
		palette3.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Light,
				new QColor(127, 127, 255));
		palette3.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Midlight, new QColor(63, 63, 255));
		palette3.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Dark,
				new QColor(0, 0, 127));
		palette3.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Mid,
				new QColor(0, 0, 170));
		palette3.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Text,
				new QColor(0, 0, 0));
		palette3.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette3.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
		palette3.setColor(QPalette.ColorGroup.Active, QPalette.ColorRole.Base,
				new QColor(255, 255, 255));
		palette3.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Window, new QColor(0, 0, 255));
		palette3.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette3.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.AlternateBase, new QColor(127, 127, 255));
		palette3.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette3.setColor(QPalette.ColorGroup.Active,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 0));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Button, new QColor(0, 0, 255));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Light, new QColor(127, 127, 255));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Midlight, new QColor(63, 63, 255));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Dark, new QColor(0, 0, 127));
		palette3.setColor(QPalette.ColorGroup.Inactive, QPalette.ColorRole.Mid,
				new QColor(0, 0, 170));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Text, new QColor(0, 0, 0));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 0));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Base, new QColor(255, 255, 255));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Window, new QColor(0, 0, 255));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.AlternateBase, new QColor(127, 127, 255));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette3.setColor(QPalette.ColorGroup.Inactive,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.WindowText, new QColor(0, 0, 127));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Button, new QColor(0, 0, 255));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Light, new QColor(127, 127, 255));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Midlight, new QColor(63, 63, 255));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Dark, new QColor(0, 0, 127));
		palette3.setColor(QPalette.ColorGroup.Disabled, QPalette.ColorRole.Mid,
				new QColor(0, 0, 170));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Text, new QColor(0, 0, 127));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.BrightText, new QColor(255, 255, 255));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ButtonText, new QColor(0, 0, 127));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Base, new QColor(0, 0, 255));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Window, new QColor(0, 0, 255));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.Shadow, new QColor(0, 0, 0));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.AlternateBase, new QColor(0, 0, 255));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ToolTipBase, new QColor(255, 255, 220));
		palette3.setColor(QPalette.ColorGroup.Disabled,
				QPalette.ColorRole.ToolTipText, new QColor(0, 0, 0));
		team1Score.setPalette(palette3);
		team1Score.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.NoFocus);
		team1Score.setFrameShape(com.trolltech.qt.gui.QFrame.Shape.StyledPanel);
		team1Score.setFrameShadow(com.trolltech.qt.gui.QFrame.Shadow.Sunken);
		team1Score.setNumDigits(3);
		splitter3.addWidget(team1Score);
		MainWindow.setCentralWidget(centralWidget);
		statusBar = new QStatusBar(MainWindow);
		statusBar.setObjectName("statusBar");
		MainWindow.setStatusBar(statusBar);
		retranslateUi(MainWindow);

		MainWindow.connectSlotsByName();
	} // setupUi

	void retranslateUi(QMainWindow MainWindow) {
		MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication
				.translate("MainWindow", "Candy Crush", null));
	} // retranslateUi
}
