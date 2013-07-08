package com.autoassistant.db;

public class DataProviderFactory {
	public static DataProvider getDataProvider(String dbType) {
		if ("HIBERNATE".equals(dbType)) {
			return new DataProviderHibernateImpl();
		}
		return null;
	}
}
