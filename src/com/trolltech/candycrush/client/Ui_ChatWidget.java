/********************************************************************************
 ** Form generated from reading ui file 'chatdialog.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.6.3
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
package com.trolltech.candycrush.client;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_ChatWidget implements com.trolltech.qt.QUiForm<QWidget> {
	public QVBoxLayout vboxLayout;
	public QHBoxLayout hboxLayout;
	public QTextEdit textEdit;
	public QListWidget listWidget;
	public QHBoxLayout hboxLayout1;
	public QLabel label;
	public QLineEdit lineEdit;

	public Ui_ChatWidget() {
		super();
	}

	public void setupUi(QWidget ChatWidget) {
		ChatWidget.setObjectName("ChatDialog");
		ChatWidget.resize(new QSize(513, 349).expandedTo(ChatWidget
				.minimumSizeHint()));
		vboxLayout = new QVBoxLayout(ChatWidget);
		vboxLayout.setSpacing(6);
		vboxLayout.setMargin(9);
		vboxLayout.setObjectName("vboxLayout");
		hboxLayout = new QHBoxLayout();
		hboxLayout.setSpacing(6);
		hboxLayout.setMargin(0);
		hboxLayout.setObjectName("hboxLayout");
		textEdit = new QTextEdit(ChatWidget);
		textEdit.setObjectName("textEdit");
		textEdit.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.NoFocus);
		textEdit.setReadOnly(true);

		hboxLayout.addWidget(textEdit);

		listWidget = new QListWidget(ChatWidget);
		listWidget.setObjectName("listWidget");
		listWidget.setMaximumSize(new QSize(180, 16777215));
		listWidget.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.NoFocus);

		hboxLayout.addWidget(listWidget);

		vboxLayout.addLayout(hboxLayout);

		hboxLayout1 = new QHBoxLayout();
		hboxLayout1.setSpacing(6);
		hboxLayout1.setMargin(0);
		hboxLayout1.setObjectName("hboxLayout1");
		label = new QLabel(ChatWidget);
		label.setObjectName("label");

		hboxLayout1.addWidget(label);

		lineEdit = new QLineEdit(ChatWidget);
		lineEdit.setObjectName("lineEdit");

		hboxLayout1.addWidget(lineEdit);

		vboxLayout.addLayout(hboxLayout1);

		retranslateUi(ChatWidget);

		ChatWidget.connectSlotsByName();
	} // setupUi

	void retranslateUi(QWidget ChatWidget) {
		ChatWidget.setWindowTitle(com.trolltech.qt.core.QCoreApplication
				.translate("ChatDialog", "Candy Crush Chat", null));
		label.setText(com.trolltech.qt.core.QCoreApplication.translate(
				"ChatDialog", "Mensaje: ", null));
	} // retranslateUi
}
