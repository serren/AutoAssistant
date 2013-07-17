package com.autoassistant.view.defaultview;

import javax.swing.JTextField;

import com.autoassistant.model.Car;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class CarView extends View {
	private JTextField tfCarName;
	private JTextField tfCarComment;
	
	public CarView(final Object object) {
		super(object);
	}

	@Override
	protected void init() {
		addLabel("Car name:", 0, 1, 384, 30);
		addLabel("Car description:", 0, 61, 384, 30);
		final Car car = (Car) object;
		tfCarName = addTextField("Car", car.getName(), 0, 31, 384, 30);		
		tfCarComment = addTextField("Comment", car.getComment(), 0, 91, 384, 30);
		setPreferredSize(new Dimension(385, 140));
	}
	
	@Override
	public void updateObject() {
		final Car car = (Car) object;
		car.setName(tfCarName.getText());
		car.setComment(tfCarComment.getText());		
	}

	@Override
	public boolean isValidData() {
		return (tfCarName.getText().length() != 0);
	}
}
