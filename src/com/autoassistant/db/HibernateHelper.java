package com.autoassistant.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateHelper {
	private static HibernateHelper helper;
	private SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory(String dataStorageType) {
		if (helper == null) {
			helper = new HibernateHelper(dataStorageType);
		}
		return helper.sessionFactory;
	}

	private HibernateHelper(String dataStorageType) {		
		Configuration configuration = new Configuration().configure(dataStorageType);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);		
	}
}
