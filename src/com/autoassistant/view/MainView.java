package com.autoassistant.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import com.autoassistant.model.AutoAssistant;
import com.autoassistant.model.Car;
import com.autoassistant.model.Entity;
import com.autoassistant.model.Expense;
import com.autoassistant.model.ExpenseCategory;
import com.autoassistant.util.ActionType;
import com.autoassistant.util.ExpenseTable;
import com.autoassistant.util.SortedComboBoxModel;

public class MainView implements Runnable {

	private final AutoAssistant autoAssistant;
	private Car currentCar;
	private ExpenseCategory currentExpenseCategory;
	private Expense currentExpense;

	private JFrame frmAutoAssistant;
	private JComboBox<Car> cbxCars;
	private JLabel lblCarComment;
	private JComboBox<ExpenseCategory> cbxExpenseCategories;
	private JTable tblExpenses;
	private JLabel lblStatus;

	// actions
	private EntityAction addCarAction;
	private EntityAction editCarAction;
	private EntityAction removeCarAction;

	private EntityAction addCategoryAction;
	private EntityAction editCategoryAction;
	private EntityAction removeCategoryAction;

	private EntityAction addExpenseAction;
	private EntityAction editExpenseAction;
	private EntityAction removeExpenseAction;

	/**
	 * Creates the main view.
	 * 
	 * @param dataProvider
	 */
	public MainView(AutoAssistant autoAssistant) {
		this.autoAssistant = autoAssistant;
		initialize();
	}

	@Override
	public void run() {
		showCars();
		frmAutoAssistant.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAutoAssistant = new JFrame();
		frmAutoAssistant.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				autoAssistant.dispose();
			}
		});
		frmAutoAssistant.setTitle("Auto Assistant");
		frmAutoAssistant.setBounds(100, 100, 598, 414);
		frmAutoAssistant.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmAutoAssistant.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));

		JPanel pnlMain = new JPanel();
		pnlMain.setBorder(UIManager.getBorder("Button.border"));
		frmAutoAssistant.getContentPane().add(pnlMain);
		GridBagLayout gbl_pnlMain = new GridBagLayout();
		gbl_pnlMain.columnWidths = new int[] { 0, 0 };
		gbl_pnlMain.rowHeights = new int[] { 30, 30, 0, 10, 0, 0 };
		gbl_pnlMain.columnWeights = new double[] { 1.0, 1.0 };
		gbl_pnlMain.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
		pnlMain.setLayout(gbl_pnlMain);

		JLabel lblSelectCar = new JLabel("Select car:");
		GridBagConstraints gbc_lblSelectCar = new GridBagConstraints();
		gbc_lblSelectCar.anchor = GridBagConstraints.WEST;
		gbc_lblSelectCar.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectCar.gridx = 0;
		gbc_lblSelectCar.gridy = 0;
		pnlMain.add(lblSelectCar, gbc_lblSelectCar);

		JLabel lblSelectCategory = new JLabel("Select category:");
		GridBagConstraints gbc_lblSelectCategory = new GridBagConstraints();
		gbc_lblSelectCategory.anchor = GridBagConstraints.WEST;
		gbc_lblSelectCategory.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelectCategory.gridx = 1;
		gbc_lblSelectCategory.gridy = 0;
		pnlMain.add(lblSelectCategory, gbc_lblSelectCategory);

		SortedComboBoxModel<Car> carComboBoxModel = new SortedComboBoxModel<Car>();
		cbxCars = new JComboBox<Car>(carComboBoxModel);
		cbxCars.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object selected = ((JComboBox<?>) e.getSource()).getSelectedItem();
				if (selected != null) {
					currentCar = (Car) selected;
					lblCarComment.setText(currentCar.getComment());
					showExpenseCategories(currentCar);
				}
			}
		});
		GridBagConstraints gbc_cbxCarNames = new GridBagConstraints();
		gbc_cbxCarNames.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxCarNames.insets = new Insets(0, 0, 5, 5);
		gbc_cbxCarNames.gridx = 0;
		gbc_cbxCarNames.gridy = 1;
		pnlMain.add(cbxCars, gbc_cbxCarNames);

		SortedComboBoxModel<ExpenseCategory> categoryComboBoxModel = new SortedComboBoxModel<ExpenseCategory>();
		cbxExpenseCategories = new JComboBox<ExpenseCategory>(categoryComboBoxModel);
		cbxExpenseCategories.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object selected = ((JComboBox<?>) e.getSource()).getSelectedItem();
				if (selected != null) {
					currentExpenseCategory = (ExpenseCategory) selected;
					showExpenses(currentExpenseCategory);
				}
			}
		});

		GridBagConstraints gbc_cbxExpenseCategories = new GridBagConstraints();
		gbc_cbxExpenseCategories.insets = new Insets(0, 0, 5, 0);
		gbc_cbxExpenseCategories.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxExpenseCategories.gridx = 1;
		gbc_cbxExpenseCategories.gridy = 1;
		pnlMain.add(cbxExpenseCategories, gbc_cbxExpenseCategories);

		lblCarComment = new JLabel("Car comment");
		lblCarComment.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblCarComment = new GridBagConstraints();
		gbc_lblCarComment.anchor = GridBagConstraints.WEST;
		gbc_lblCarComment.gridwidth = 2;
		gbc_lblCarComment.insets = new Insets(0, 0, 5, 0);
		gbc_lblCarComment.gridx = 0;
		gbc_lblCarComment.gridy = 2;
		pnlMain.add(lblCarComment, gbc_lblCarComment);

		JLabel lblExpenses = new JLabel("Expenses:");
		GridBagConstraints gbc_lblExpenses = new GridBagConstraints();
		gbc_lblExpenses.anchor = GridBagConstraints.WEST;
		gbc_lblExpenses.gridwidth = 2;
		gbc_lblExpenses.insets = new Insets(0, 0, 5, 0);
		gbc_lblExpenses.gridx = 0;
		gbc_lblExpenses.gridy = 3;
		pnlMain.add(lblExpenses, gbc_lblExpenses);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		pnlMain.add(scrollPane, gbc_scrollPane);

		tblExpenses = new JTable();
		tblExpenses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// double click on expense grid opens expense for editing
				if (e.getClickCount() == 2) {
					try {
						editExpenseAction.processAction(ActionType.EDIT);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					checkActions();
				}
			}
		});
		scrollPane.setViewportView(tblExpenses);

		TableModel tableModel = new ExpenseTable(new HashSet<Expense>());
		// binding the table to the model
		tblExpenses.setModel(tableModel);
		tblExpenses.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					int selectedRow = tblExpenses.getSelectedRow();
					if (selectedRow != -1) {
						ExpenseTable tableModel = (ExpenseTable) tblExpenses.getModel();
						currentExpense = (Expense) tableModel.getElement(selectedRow);
					} else {
						currentExpense = null;
					}
				}
			}
		});
		tblExpenses.getColumnModel().getColumn(0).setResizable(false);
		tblExpenses.getColumnModel().getColumn(0).setMinWidth(140);
		tblExpenses.getColumnModel().getColumn(0).setMaxWidth(140);
		tblExpenses.getColumnModel().getColumn(1).setResizable(false);
		tblExpenses.getColumnModel().getColumn(1).setMaxWidth(100);
		tblExpenses.getColumnModel().getColumn(2).setResizable(false);
		tblExpenses.getColumnModel().getColumn(2).setMaxWidth(100);
		tblExpenses.setRowHeight(20);

		scrollPane.setViewportView(tblExpenses);

		lblStatus = new JLabel("Status");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.WEST;
		gbc_lblStatus.gridwidth = 2;
		gbc_lblStatus.insets = new Insets(0, 0, 0, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 5;
		pnlMain.add(lblStatus, gbc_lblStatus);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(10, 10, 10, 10));
		frmAutoAssistant.setJMenuBar(menuBar);

		// actions
		addCarAction = new EntityAction("Add", "Car");
		editCarAction = new EntityAction("Edit", "Car");
		removeCarAction = new EntityAction("Remove", "Car");
		addCategoryAction = new EntityAction("Add", "Expense Category");
		editCategoryAction = new EntityAction("Edit", "Expense Category");
		removeCategoryAction = new EntityAction("Remove", "Expense Category");
		addExpenseAction = new EntityAction("Add", "Expense");
		editExpenseAction = new EntityAction("Edit", "Expense");
		removeExpenseAction = new EntityAction("Remove", "Expense");

		JMenu mnCar = new JMenu("Car");
		menuBar.add(mnCar);

		JMenuItem mntmAddCar = new JMenuItem("Add");
		mntmAddCar.setAction(addCarAction);
		mntmAddCar.setActionCommand("Add");
		mnCar.add(mntmAddCar);

		JMenuItem mntmEditCar = new JMenuItem("Edit");
		mntmEditCar.setAction(editCarAction);
		mntmEditCar.setActionCommand("Edit");
		mnCar.add(mntmEditCar);

		JMenuItem mntmRemoveCar = new JMenuItem("Remove");
		mntmRemoveCar.setAction(removeCarAction);
		mntmRemoveCar.setActionCommand("Remove");
		mnCar.add(mntmRemoveCar);

		JMenu mnCategory = new JMenu("Category");
		menuBar.add(mnCategory);

		JMenuItem mntmAddCategory = new JMenuItem("Add");
		mntmAddCategory.setAction(addCategoryAction);
		mntmAddCategory.setActionCommand("Add");
		mnCategory.add(mntmAddCategory);

		JMenuItem mntmEditCategory = new JMenuItem("Edit");

		mntmEditCategory.setAction(editCategoryAction);
		mntmEditCategory.setActionCommand("Edit");
		mnCategory.add(mntmEditCategory);

		JMenuItem mntmRemoveCategory = new JMenuItem("Remove");

		mntmRemoveCategory.setAction(removeCategoryAction);
		mntmRemoveCategory.setActionCommand("Remove");
		mnCategory.add(mntmRemoveCategory);

		JMenu mnExpense = new JMenu("Expense");
		menuBar.add(mnExpense);

		JMenuItem mntmAddExpense = new JMenuItem("Add");
		mntmAddExpense.setAction(addExpenseAction);
		mntmAddExpense.setActionCommand("Add");
		mnExpense.add(mntmAddExpense);

		JMenuItem mntmEditExpense = new JMenuItem("Edit");
		mntmEditExpense.setAction(editExpenseAction);
		mntmEditExpense.setActionCommand("Edit");
		mnExpense.add(mntmEditExpense);

		JMenuItem mntmRemoveExpense = new JMenuItem("Remove");
		mntmRemoveExpense.setAction(removeExpenseAction);
		mntmRemoveExpense.setActionCommand("Remove");
		mnExpense.add(mntmRemoveExpense);
	}

	/**
	 * Fills car list in UI
	 */
	private void showCars() {
		cbxCars.removeAllItems();
		for (Car car : autoAssistant.getCars()) {
			cbxCars.addItem(car);
		}
		checkActions();
	}

	/**
	 * Fills category list in UI
	 */
	private void showExpenseCategories(Car car) {
		cbxExpenseCategories.removeAllItems();
		// clear expenses
		showExpenses(null);
		for (ExpenseCategory ec : car.getExpenseCategories()) {
			cbxExpenseCategories.addItem(ec);
		}
		checkActions();
	}

	/**
	 * Fills expenses grid in UI
	 */
	private void showExpenses(ExpenseCategory expenseCategory) {
		Set<Expense> expenses = null;
		if (expenseCategory != null) {
			expenses = expenseCategory.getExpenses();
		}
		ExpenseTable tableModel = (ExpenseTable) tblExpenses.getModel();
		tableModel.setElements(expenses);
		tblExpenses.setAutoCreateRowSorter(true);
		tblExpenses.getRowSorter().toggleSortOrder(0);
		tblExpenses.updateUI();
		checkActions();
	}

	/**
	 * Checks if actions are available or not
	 */
	private void checkActions() {

		boolean enabledFlag = (cbxCars.getItemCount() != 0);

		// check car actions
		editCarAction.setEnabled(enabledFlag);
		removeCarAction.setEnabled(enabledFlag);

		// check categories actions
		enabledFlag = (cbxExpenseCategories.getItemCount() != 0);

		editCategoryAction.setEnabled(enabledFlag);
		removeCategoryAction.setEnabled(enabledFlag);
		addExpenseAction.setEnabled(enabledFlag);

		// check expenses actions
		enabledFlag = (cbxExpenseCategories.getItemCount() != 0 && tblExpenses.getRowCount() != 0 && tblExpenses.getSelectedRow() != -1);

		editExpenseAction.setEnabled(enabledFlag);
		removeExpenseAction.setEnabled(enabledFlag);
	}

	/**
	 * Applies changes to specified entity
	 * 
	 * @param entity
	 * @param actionType
	 */
	private void applyChanges(Entity entity, ActionType actionType) {

		String objectType = entity.getObjectType();

		if ("Car".equals(objectType)) {
			if (actionType == ActionType.REMOVE) {
				autoAssistant.remove(entity);
			}
			if (actionType == ActionType.ADD) {
				currentCar = (Car) entity;
				autoAssistant.save(currentCar);
			}
			showCars();
		}

		if ("Expense Category".equals(objectType)) {
			if (actionType == ActionType.REMOVE) {
				currentCar.removeExpenseCategory((ExpenseCategory) entity);
			} else {
				currentExpenseCategory = (ExpenseCategory) entity;
				currentCar.addExpenseCategory((ExpenseCategory) entity);
			}
			autoAssistant.save(currentCar);
			showExpenseCategories(currentCar);
		}

		if ("Expense".equals(objectType)) {
			if (actionType == ActionType.REMOVE) {
				currentExpenseCategory.removeExpense((Expense) entity);
			} else {
				currentExpense = (Expense) entity;
				currentExpenseCategory.addExpense((Expense) entity);
			}
			autoAssistant.save(currentCar);
			showExpenses(currentExpenseCategory);
		}
	}

	/**
	 * Returns object for view. It can be current object (for edit and remove
	 * action) or new
	 * 
	 * @param actionType
	 * @param objectType
	 * 
	 * @return Object
	 */
	private Entity getEntityForView(ActionType actionType, String objectType) {

		Entity entity = null;

		if ("Car".equals(objectType)) {
			if (actionType == ActionType.ADD) {
				entity = Car.newCar();
			} else {
				entity = currentCar;
			}
		}

		if ("Expense Category".equals(objectType)) {
			if (actionType == ActionType.ADD) {
				entity = currentCar.newExpenseCategory();
			} else {
				entity = currentExpenseCategory;
			}
		}

		if ("Expense".equals(objectType)) {
			if (actionType == ActionType.ADD) {
				entity = currentExpenseCategory.newExpense();
			} else {
				entity = currentExpense;
			}
		}

		return entity;
	}

	/**
	 * Class implements actions can be performed on object
	 */
	@SuppressWarnings("serial")
	private class EntityAction extends AbstractAction {

		/*
		 * public AddCarAction(String text, String desc, Integer mnemonic, ImageIcon icon) { 
		 *  super(text, icon); 
		 *  putValue(SHORT_DESCRIPTION, desc);
		 *  putValue(MNEMONIC_KEY, mnemonic); 
		 * }
		 */

		private String objectType;

		public EntityAction(String actionName, String objectType) {
			super(actionName);
			this.objectType = objectType;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String actionCommand = ((AbstractButton) e.getSource()).getActionCommand().toUpperCase();
			processAction(ActionType.valueOf(actionCommand));
		}

		private void processAction(ActionType actionType) {
			Entity entity = getEntityForView(actionType, objectType);
			if (entity != null) {
				String caption = actionType.columnName() + " " + entity.getObjectType();
				CustomDialog dialog = new CustomDialog(frmAutoAssistant, caption, ViewFactory.getView(actionType, entity));
				if (dialog.show() == JOptionPane.OK_OPTION) {
					// return real action type
					applyChanges(entity, actionType);
				}
			}
		}

	}
}
