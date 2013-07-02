/**
 * 
 */
package com.autoassistant.loader;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.autoassistant.db.DataProvider;
import com.autoassistant.db.DataProviderFactory;
import com.autoassistant.model.AutoAssistantImpl;
import com.autoassistant.view.MainView;

/**
 * @author Sergei_Meranovich
 * 
 */
public class Loader {
	
	private static final Logger log = Logger.getLogger(Loader.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Properties prop = new Properties();

		try {			
			// load a properties file from class path, inside static method
			prop.load(Loader.class.getClassLoader().getResourceAsStream("config.properties"));
			
			String dataStorageType = prop.getProperty("DataStorageType", "hibernate_mssql.cfg.xml");
			log.info("DataStorageType is set to " + dataStorageType);
			DataProvider dataProvider = DataProviderFactory.getDataProvider(dataStorageType);		
			AutoAssistantImpl autoAssistant = new AutoAssistantImpl(dataProvider);		
			EventQueue.invokeLater(new MainView(autoAssistant));

		} catch (IOException ex) {
			ex.printStackTrace();
		}		
	}

}
