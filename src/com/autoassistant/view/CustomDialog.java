package com.autoassistant.view;

import java.awt.Frame;
import java.awt.Window.Type;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class CustomDialog {

	private final JDialog dialog;
	private int response = JOptionPane.CANCEL_OPTION;

	public CustomDialog(Frame frame, String caption, final View view) {

		final JOptionPane optionPane = new JOptionPane(view, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		
		dialog = new JDialog(frame, caption, true);
		dialog.setContentPane(optionPane);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(frame);

		optionPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {

				if (dialog.isVisible() && JOptionPane.VALUE_PROPERTY.equals(e.getPropertyName())) {

					response = JOptionPane.CANCEL_OPTION;

					// if OK button was pressed
					if (((Integer) optionPane.getValue()) == 0) {

						response = JOptionPane.OK_OPTION;

						// if input data is not valid don't close dialog
						if (view.isDataNotValid()) {
							return;
						}
					}

					dialog.setVisible(false);
				}
			}
		});

		dialog.setType(Type.UTILITY);
		dialog.pack();
	}

	/**
	 * Shows dialog
	 * 
	 * @return user choice
	 */
	public int show() {
		dialog.setVisible(true);
		return response;
	}
}
