package com.autoassistant.db;

public class DataProviderFactory {
	public static DataProvider getDataProvider(String type) {		
		return DataProviderHibernateImpl.getInstance(type);
	}
}
