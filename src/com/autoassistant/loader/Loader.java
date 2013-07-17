package com.autoassistant.loader;

/**
 * @author Sergei_Meranovich
 * 
 */
public class Loader {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(MainViewProviderFactory.getMainView("Alternative")); //Default 
	}
}
