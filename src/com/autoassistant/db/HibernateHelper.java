package com.autoassistant.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateHelper {

	private SessionFactory sessionFactory;
	private Configuration configuration;
	private ServiceRegistry serviceRegistry;
	private static HibernateHelper helper;

	private HibernateHelper(String dataStorageType) {
		try {
			configuration = new Configuration().configure(dataStorageType);
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable e) {
			System.err.println("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory(String dataStorageType) {
		if (helper == null) {
			helper = new HibernateHelper(dataStorageType);
		}
		return helper.sessionFactory;
	}

}
