package com.autoassistant.view;

import java.awt.Dimension;
import javax.swing.JLabel;

import com.autoassistant.model.Entity;

@SuppressWarnings("serial")
public class DeleteDialogView extends View {

	public DeleteDialogView(Entity object) {

		super(object);

		setLayout(null);

		JLabel lblText = new JLabel(object.toString());
		lblText.setBounds(0, 1, 384, 30);
		add(lblText);

		this.setPreferredSize(new Dimension(385, 60));
	}

	@Override
	protected boolean isDataNotValid() {
		return false;
	}

}
