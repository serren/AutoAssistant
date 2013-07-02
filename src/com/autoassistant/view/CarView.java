package com.autoassistant.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.autoassistant.model.Car;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class CarView extends View {

	/**
	 * Creates view to show Car.
	 * 
	 * @param Car
	 */
	public CarView(final Car car) {

		super(car);

		setLayout(null);

		JLabel lblCar = new JLabel("Car name:");
		lblCar.setBounds(0, 1, 384, 30);
		add(lblCar);

		final JTextField txtCar = new JTextField();
		txtCar.setBounds(0, 31, 384, 30);
		txtCar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                car.setName(txtCar.getText());
            }
        });
		txtCar.setName("Car");
		txtCar.setText(car.getName());
		add(txtCar);
		txtCar.setColumns(10);

		JLabel lblComment = new JLabel("Car description:");
		lblComment.setBounds(0, 61, 384, 30);
		add(lblComment);

		final JTextField txtComment = new JTextField();
		txtComment.setBounds(0, 91, 384, 30);
		txtComment.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				car.setComment(txtComment.getText());
			}
		});	
		txtComment.setName("Comment");
		txtComment.setText(car.getComment());
		add(txtComment);
		txtComment.setColumns(10);
		
		this.setPreferredSize(new Dimension(385,140));
	}
}
