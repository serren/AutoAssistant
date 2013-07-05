package com.autoassistant.db;

public class DataProviderFactory {
	public static DataProvider getDataProvider(String dbType) {
		if ("HIBERNATE".equals(dbType)) {
			return DataProviderHibernateImpl.getInstance();
		}
		return null;
	}
}
