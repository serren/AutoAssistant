/**
 * Implements DB methods
 */
package com.autoassistant.db;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.autoassistant.model.Car;
import com.autoassistant.model.Expense;
import com.autoassistant.model.ExpenseCategory;

/**
 * Implements DB methods
 */
public class DataProviderHibernateImpl implements DataProvider {

	private Session session = null;
	private static DataProviderHibernateImpl instance;
	
	public static DataProviderHibernateImpl getInstance(String dataStorageType) {
		if (instance == null) {
			instance = new DataProviderHibernateImpl(dataStorageType);
		}
		return instance;
	}

	/**
	 * Creates DB context to work with DB
	 */
	private DataProviderHibernateImpl(String dataStorageType) {
		try {
			session = HibernateHelper.getSessionFactory(dataStorageType).openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		expenseCategories.addAll(session.createQuery(
				String.format("from ExpenseCategory where autoid = %s order by name", car.getId())).list());
		return expenseCategories;
	}

	/**
	 * Loads expenses list from DB
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Expense> getExpenses(ExpenseCategory expenseCategory) {
		Set<Expense> expenses = new HashSet<Expense>();
		expenses.addAll(session.createQuery(
				String.format("from Expense where categoryid = %s order by date", expenseCategory.getId())).list());
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
