package com.autoassistant.controller;

import java.awt.Frame;

import javax.swing.JOptionPane;

import com.autoassistant.model.Entity;
import com.autoassistant.util.ActionType;
import com.autoassistant.view.CustomDialog;
import com.autoassistant.view.View;

public class ViewController {

	private final Entity entity;
	private final ActionType actionType;
	private View view;

	public ViewController(Entity object, ActionType actionType, View view) {
		this.entity = object;
		this.actionType = actionType;
		this.view = view;
	}

	/**
	 * Shows dialog
	 * 
	 * @param frame
	 *            - parent frame
	 * 
	 * @return actual WindowAction.
	 *  If user cancel changes method'll return
	 *         WindowAction.CANCEL instead of received one.
	 */
	public ActionType start(Frame frame) {

		ActionType actionType = ActionType.CANCEL;

		try {
			String caption = this.actionType.columnName() + " " + entity.getObjectType();

			CustomDialog dialog = new CustomDialog(frame, caption, view);

			if (dialog.show() == JOptionPane.OK_OPTION) {
				// return real action type
				actionType = this.actionType;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actionType;
	}
}
