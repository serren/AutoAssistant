/**
 * 
 */
package com.autoassistant.loader;

import java.awt.EventQueue;

import com.autoassistant.db.DataProvider;
import com.autoassistant.db.DataProviderFactory;
import com.autoassistant.model.AutoAssistant;
import com.autoassistant.model.AutoAssistantImpl;
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
		DataProvider dataProvider = DataProviderFactory.getDataProvider("HIBERNATE");
		AutoAssistant autoAssistant = new AutoAssistantImpl(dataProvider);
		EventQueue.invokeLater(new MainView(autoAssistant));
	}

}
