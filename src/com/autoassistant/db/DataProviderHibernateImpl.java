/**
 * Implements DB methods
 */
package com.autoassistant.db;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.autoassistant.loader.Loader;
import com.autoassistant.model.Car;
import com.autoassistant.model.Expense;
import com.autoassistant.model.ExpenseCategory;

/**
 * Implements DB methods
 */
public class DataProviderHibernateImpl implements DataProvider {
	private static final Logger log = Logger.getLogger(DataProviderHibernateImpl.class);

	private Session session = null;
	private static DataProviderHibernateImpl instance;

	public static DataProviderHibernateImpl getInstance() {
		if (instance == null) {
			instance = new DataProviderHibernateImpl();
		}
		return instance;
	}

	/**
	 * Creates DB context to work with DB
	 */
	private DataProviderHibernateImpl() {
		Properties prop = new Properties();
		try {
			// load a properties file from class path, inside static method
			prop.load(Loader.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			log.error(e);
		}

		String hibernateDbConf = prop.getProperty("DataStorageType", "hibernate_mssql.cfg.xml");
		log.info("DataStorageType is set to " + hibernateDbConf);
		Configuration configuration = new Configuration().configure(hibernateDbConf);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		session = configuration.buildSessionFactory(serviceRegistry).openSession();
	}

	/**
	 * Closes DB connection
	 */
	@Override
	public void close() {
		try {
			if (session != null) {
				session.flush();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads cars from DB
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Car> getCars() {
		Set<Car> cars = new HashSet<Car>();
		cars.addAll(session.createQuery("from Car order by id desc").list());
		return cars;
	}

	/**
	 * Loads expense categories list from DB
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<ExpenseCategory> getExpenseCategories(Car car) {
		Set<ExpenseCategory> expenseCategories = new HashSet<ExpenseCategory>();
		expenseCategories.addAll(session.createQuery(String.format("from ExpenseCategory where autoid = %s order by name", car.getId())).list());
		return expenseCategories;
	}

	/**
	 * Loads expenses list from DB
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Expense> getExpenses(ExpenseCategory expenseCategory) {
		Set<Expense> expenses = new HashSet<Expense>();
		expenses.addAll(session.createQuery(String.format("from Expense where categoryid = %s order by date", expenseCategory.getId())).list());
		return expenses;
	}

	/**
	 * Adds new object to DB
	 */
	@Override
	public void add(Object object) {
		save(object);
	}

	/**
	 * Saves object to DB
	 */
	@Override
	public void save(Object object) {

		try {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(object);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes object from DB
	 */
	@Override
	public void remove(Object object) {

		try {
			Transaction transaction = session.beginTransaction();
			session.delete(object);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
