/********************************************************************************
 ** Form generated from reading ui file 'join.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.6.3
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
package com.trolltech.candycrush.client;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_JoinDialog implements com.trolltech.qt.QUiForm<QDialog> {
	public QSplitter splitter;
	public QWidget widget;
	public QVBoxLayout verticalLayout;
	public QHBoxLayout horizontalLayout_2;
	public QLabel nameLabel;
	public QLineEdit nameTextEdit;
	public QHBoxLayout horizontalLayout;
	public QLabel teamsLabel;
	public QComboBox teamsComboBox;
	public QCheckBox fullTeamCheckBox;
	public QPushButton joinButton;
	public QPushButton exitButton;

	public static final String[] TEAMS = { "Equipo Rojo", "Equipo Azul" };
	public final QColor[] COLORS = { QColor.red, QColor.blue };

	public Ui_JoinDialog() {
		super();
	}

	public void setupUi(QDialog Dialog) {
		Dialog.setObjectName("Dialog");
		Dialog.resize(new QSize(244, 170).expandedTo(Dialog.minimumSizeHint()));
		Dialog.setWindowTitle("Unirse a la partida");
		Dialog.setSizeGripEnabled(false);
		splitter = new QSplitter(Dialog);
		splitter.setObjectName("splitter");
		splitter.setGeometry(new QRect(11, 11, 219, 154));
		splitter.setOrientation(com.trolltech.qt.core.Qt.Orientation.Vertical);
		widget = new QWidget(splitter);
		widget.setObjectName("widget");
		verticalLayout = new QVBoxLayout(widget);
		verticalLayout.setObjectName("verticalLayout");
		horizontalLayout_2 = new QHBoxLayout();
		horizontalLayout_2.setObjectName("horizontalLayout_2");
		nameLabel = new QLabel(widget);
		nameLabel.setObjectName("nameLabel");
		nameLabel
				.setLayoutDirection(com.trolltech.qt.core.Qt.LayoutDirection.RightToLeft);

		horizontalLayout_2.addWidget(nameLabel);

		nameTextEdit = new QLineEdit(widget);
		nameTextEdit.setObjectName("nameTextEdit");
		QSizePolicy sizePolicy = new QSizePolicy(
				com.trolltech.qt.gui.QSizePolicy.Policy.Expanding,
				com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
		sizePolicy.setHorizontalStretch((byte) 0);
		sizePolicy.setVerticalStretch((byte) 0);
		sizePolicy.setHeightForWidth(nameTextEdit.sizePolicy()
				.hasHeightForWidth());
		nameTextEdit.setSizePolicy(sizePolicy);
		nameTextEdit.setMaximumSize(new QSize(160, 30));

		horizontalLayout_2.addWidget(nameTextEdit);

		verticalLayout.addLayout(horizontalLayout_2);

		horizontalLayout = new QHBoxLayout();
		horizontalLayout.setObjectName("horizontalLayout");
		teamsLabel = new QLabel(widget);
		teamsLabel.setObjectName("teamsLabel");
		teamsLabel
				.setLayoutDirection(com.trolltech.qt.core.Qt.LayoutDirection.RightToLeft);

		horizontalLayout.addWidget(teamsLabel);

		teamsComboBox = new QComboBox(widget);
		teamsComboBox.setObjectName("teamsComboBox");
		QSizePolicy sizePolicy1 = new QSizePolicy(
				com.trolltech.qt.gui.QSizePolicy.Policy.Expanding,
				com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
		sizePolicy1.setHorizontalStretch((byte) 0);
		sizePolicy1.setVerticalStretch((byte) 0);
		sizePolicy1.setHeightForWidth(teamsComboBox.sizePolicy()
				.hasHeightForWidth());
		teamsComboBox.setSizePolicy(sizePolicy1);
		teamsComboBox.setMaximumSize(new QSize(160, 30));

		teamsComboBox.setEditable(false);
		teamsComboBox.addItem(null);
		for (String team : TEAMS)
			teamsComboBox.addItem(team);

		horizontalLayout.addWidget(teamsComboBox);

		verticalLayout.addLayout(horizontalLayout);

		fullTeamCheckBox = new QCheckBox(widget);
		fullTeamCheckBox.setObjectName("fullTeamCheckBox");
		fullTeamCheckBox
				.setLayoutDirection(com.trolltech.qt.core.Qt.LayoutDirection.LeftToRight);
		fullTeamCheckBox.setAutoFillBackground(false);

		verticalLayout.addWidget(fullTeamCheckBox);

		splitter.addWidget(widget);
		joinButton = new QPushButton(splitter);
		joinButton.setObjectName("joinButton");
		QSizePolicy sizePolicy2 = new QSizePolicy(
				com.trolltech.qt.gui.QSizePolicy.Policy.Preferred,
				com.trolltech.qt.gui.QSizePolicy.Policy.MinimumExpanding);
		sizePolicy2.setHorizontalStretch((byte) 0);
		sizePolicy2.setVerticalStretch((byte) 0);
		sizePolicy2.setHeightForWidth(joinButton.sizePolicy()
				.hasHeightForWidth());
		joinButton.setSizePolicy(sizePolicy2);
		joinButton.setMaximumSize(new QSize(250, 25));
		splitter.addWidget(joinButton);
		exitButton = new QPushButton(splitter);
		exitButton.setObjectName("exitButton");
		QSizePolicy sizePolicy3 = new QSizePolicy(
				com.trolltech.qt.gui.QSizePolicy.Policy.Preferred,
				com.trolltech.qt.gui.QSizePolicy.Policy.MinimumExpanding);
		sizePolicy3.setHorizontalStretch((byte) 0);
		sizePolicy3.setVerticalStretch((byte) 0);
		sizePolicy3.setHeightForWidth(exitButton.sizePolicy()
				.hasHeightForWidth());
		exitButton.setSizePolicy(sizePolicy3);
		exitButton.setMaximumSize(new QSize(250, 25));
		splitter.addWidget(exitButton);
		retranslateUi(Dialog);

		Dialog.connectSlotsByName();
	} // setupUi

	void retranslateUi(QDialog Dialog) {
		nameLabel.setText(com.trolltech.qt.core.QCoreApplication.translate(
				"Dialog", "Nombre", null));
		teamsLabel.setText(com.trolltech.qt.core.QCoreApplication.translate(
				"Dialog", "Equipo", null));
		fullTeamCheckBox.setText(com.trolltech.qt.core.QCoreApplication
				.translate("Dialog", "Equipo completo", null));
		joinButton.setText(com.trolltech.qt.core.QCoreApplication.translate(
				"Dialog", "Jugar!", null));
		exitButton.setText(com.trolltech.qt.core.QCoreApplication.translate(
				"Dialog", "Salir", null));
	} // retranslateUi

}
