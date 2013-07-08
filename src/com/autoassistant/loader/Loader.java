/**
 * 
 */
package com.autoassistant.loader;

import java.awt.EventQueue;

import com.autoassistant.db.DataProvider;
import com.autoassistant.db.DataProviderFactory;
import com.autoassistant.view.MainView;

/**
 * @author Sergei_Meranovich
 * 
 */
public class Loader {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final DataProvider dataProvider = DataProviderFactory.getDataProvider("HIBERNATE");
		EventQueue.invokeLater(new MainView(dataProvider));
	}
}
