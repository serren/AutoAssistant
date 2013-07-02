package com.autoassistant.view;

import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.autoassistant.model.Entity;
import com.toedter.calendar.JDateChooser;

public abstract class View extends JPanel {

	private static final long serialVersionUID = 6887907407216074041L;
	protected final Entity entity;

	public View(final Entity entity) {
		this.entity = entity;
		setLayout(null);
		init();
	}

	protected abstract void init();
	public abstract void updateObject();
	public abstract boolean isValidData();
	
	protected void addLabel(String text, int x, int y, int width, int height) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, width, height);
		add(label);
	}
	
	protected JTextField addTextField(String name, String text, int x, int y, int width, int height) {
		final JTextField textField = new JTextField();
		add(textField);
		textField.setBounds(x, y, width, height);
		textField.setName(name);
		textField.setText(text);
		textField.setColumns(10);				
		return textField;
	}
	
	protected JFormattedTextField addFormattedTextField(String text, int x, int y, int width, int height) {
		final JFormattedTextField formattedTextField = new JFormattedTextField();
		add(formattedTextField);
		formattedTextField.setBounds(x, y, width, height);
		formattedTextField.setText(text);
		formattedTextField.setColumns(10);
		return formattedTextField;		
	}
	
	protected JDateChooser addDateChooser(final Date date, int x, int y, int width, int height) {
		final JDateChooser dateChooser = new JDateChooser();
		add(dateChooser);
		dateChooser.setBounds(x, y, width, height);
		dateChooser.setDate(date);
		return dateChooser;
	}
}
