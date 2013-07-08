package com.autoassistant.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

import com.autoassistant.db.DataProvider;
import com.autoassistant.model.Car;
import com.autoassistant.model.Expense;
import com.autoassistant.model.ExpenseCategory;

public class MainView implements Runnable {

	private final DataProvider dataProvider;
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
	private boolean resultLastAction = false;

	private AddCarAction addCarAction;
	private EditCarAction editCarAction;
	private RemoveCarAction removeCarAction;

	private AddCategoryAction addCategoryAction;
	private EditCategoryAction editCategoryAction;
	private RemoveCategoryAction removeCategoryAction;

	private AddExpenseAction addExpenseAction;
	private EditExpenseAction editExpenseAction;
	private RemoveExpenseAction removeExpenseAction;

	/**
	 * Creates the main view.
	 * 
	 * @param dataProvider
	 */
	public MainView(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
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
				dataProvider.close();
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
		gbc_cbxExpenseCategories.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxExpenseCategories.insets = new Insets(0, 0, 5, 0);
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
					editExpenseAction.actionPerformed(null);
				} else {
					checkActions();
				}
			}
		});
		scrollPane.setViewportView(tblExpenses);

		// binding the table to the model
		tblExpenses.setModel(new ExpenseTable());
		tblExpenses.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					final int selectedRow = tblExpenses.getSelectedRow();
					if (selectedRow != -1) {
						currentExpense = ((ExpenseTable) tblExpenses.getModel()).getElement(selectedRow);
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

		JMenu mnCar = new JMenu("Car");
		menuBar.add(mnCar);

		JMenuItem mntmAddCar = new JMenuItem("Add");
		addCarAction = new AddCarAction();
		mntmAddCar.setAction(addCarAction);
		mntmAddCar.setActionCommand("Add");
		mnCar.add(mntmAddCar);

		JMenuItem mntmEditCar = new JMenuItem("Edit");
		editCarAction = new EditCarAction();
		mntmEditCar.setAction(editCarAction);
		mntmEditCar.setActionCommand("Edit");
		mnCar.add(mntmEditCar);

		JMenuItem mntmRemoveCar = new JMenuItem("Remove");
		removeCarAction = new RemoveCarAction();
		mntmRemoveCar.setAction(removeCarAction);
		mntmRemoveCar.setActionCommand("Remove");
		mnCar.add(mntmRemoveCar);

		JMenu mnCategory = new JMenu("Category");
		menuBar.add(mnCategory);

		JMenuItem mntmAddCategory = new JMenuItem("Add");
		addCategoryAction = new AddCategoryAction();
		mntmAddCategory.setAction(addCategoryAction);
		mntmAddCategory.setActionCommand("Add");
		mnCategory.add(mntmAddCategory);

		JMenuItem mntmEditCategory = new JMenuItem("Edit");
		editCategoryAction = new EditCategoryAction();
		mntmEditCategory.setAction(editCategoryAction);
		mntmEditCategory.setActionCommand("Edit");
		mnCategory.add(mntmEditCategory);

		JMenuItem mntmRemoveCategory = new JMenuItem("Remove");
		removeCategoryAction = new RemoveCategoryAction();
		mntmRemoveCategory.setAction(removeCategoryAction);
		mntmRemoveCategory.setActionCommand("Remove");
		mnCategory.add(mntmRemoveCategory);

		JMenu mnExpense = new JMenu("Expense");
		menuBar.add(mnExpense);

		JMenuItem mntmAddExpense = new JMenuItem("Add");
		addExpenseAction = new AddExpenseAction();
		mntmAddExpense.setAction(addExpenseAction);
		mntmAddExpense.setActionCommand("Add");
		mnExpense.add(mntmAddExpense);

		JMenuItem mntmEditExpense = new JMenuItem("Edit");
		editExpenseAction = new EditExpenseAction();
		mntmEditExpense.setAction(editExpenseAction);
		mntmEditExpense.setActionCommand("Edit");
		mnExpense.add(mntmEditExpense);

		JMenuItem mntmRemoveExpense = new JMenuItem("Remove");
		removeExpenseAction = new RemoveExpenseAction();
		mntmRemoveExpense.setAction(removeExpenseAction);
		mntmRemoveExpense.setActionCommand("Remove");
		mnExpense.add(mntmRemoveExpense);
	}

	/**
	 * Fills car list in UI
	 */
	private void showCars() {
		cbxCars.removeAllItems();
		for (Car car : dataProvider.getCars()) {
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

		((ExpenseTable) tblExpenses.getModel()).setElements(expenses);

		tblExpenses.setAutoCreateRowSorter(true);
		tblExpenses.getRowSorter().toggleSortOrder(0);
		tblExpenses.updateUI();
		checkActions();
	}

	/**
	 * Checks if actions are available or not
	 */
	private void checkActions() {
		// check car actions
		boolean enabledFlag = (cbxCars.getItemCount() != 0);
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

	private void processAction(Object object, ActionType actionType) {
		resultLastAction = false;
		if (object != null) {
			final JDialog dialog = new JDialog(frmAutoAssistant, actionType.getName(), true);
			final View view = ViewFactory.getView(actionType, object);
			final JOptionPane optionPane = new JOptionPane(view, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

			dialog.setContentPane(optionPane);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setResizable(false);
			dialog.setLocationRelativeTo(frmAutoAssistant);
			dialog.setType(Type.UTILITY);
			dialog.pack();

			optionPane.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent e) {
					if (dialog.isVisible() && JOptionPane.VALUE_PROPERTY.equals(e.getPropertyName())) {
						// if OK button was pressed
						if (((Integer) optionPane.getValue()) == 0) {
							if (!view.isValidData()) {
								return;
							}
							view.updateObject();
							resultLastAction = true;
						}

						dialog.setVisible(false);
					}
				}
			});
			dialog.setVisible(true);
		}
	}

	@SuppressWarnings("serial")
	private class EditCategoryAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			processAction(currentExpenseCategory, ActionType.EDIT);
			if (resultLastAction) {
				dataProvider.save(currentCar);
				showExpenseCategories(currentCar);
			}
		}
	}

	@SuppressWarnings("serial")
	private class AddCategoryAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			final ExpenseCategory expenceCategory = new ExpenseCategory(0, "");
			processAction(expenceCategory, ActionType.ADD);
			if (resultLastAction) {
				currentCar.addExpenseCategory(expenceCategory);
				dataProvider.save(currentCar);
				showExpenseCategories(currentCar);
			}
		}
	}

	@SuppressWarnings("serial")
	private class RemoveCategoryAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			processAction(currentExpenseCategory, ActionType.REMOVE);
			if (resultLastAction) {
				currentCar.removeExpenseCategory(currentExpenseCategory);
				dataProvider.save(currentCar);
				showExpenseCategories(currentCar);
			}
		}
	}

	@SuppressWarnings("serial")
	private class AddCarAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			final Car car = new Car(0, "", "");
			processAction(car, ActionType.ADD);
			if (resultLastAction) {
				dataProvider.save(car);
				showCars();
			}
		}
	}

	@SuppressWarnings("serial")
	private class EditCarAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			processAction(currentCar, ActionType.EDIT);
			if (resultLastAction) {
				dataProvider.save(currentCar);
				showCars();
			}
		}
	}

	@SuppressWarnings("serial")
	private class RemoveCarAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			processAction(currentCar, ActionType.REMOVE);
			if (resultLastAction) {
				dataProvider.remove(currentCar);
				showCars();
			}
		}
	}

	@SuppressWarnings("serial")
	private class AddExpenseAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			final Expense expense = new Expense();
			expense.setId(0);
			expense.setRace(0);
			expense.setExpenseDate(Calendar.getInstance().getTime());
			expense.setAmount(0d);
			expense.setText("");
			processAction(expense, ActionType.ADD);
			if (resultLastAction) {
				currentExpenseCategory.addExpense(expense);
				dataProvider.save(currentCar);
				showExpenses(currentExpenseCategory);

			}
		}
	}

	@SuppressWarnings("serial")
	private class EditExpenseAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			processAction(currentExpense, ActionType.EDIT);
			if (resultLastAction) {
				dataProvider.save(currentCar);
				showExpenses(currentExpenseCategory);

			}
		}
	}

	@SuppressWarnings("serial")
	private class RemoveExpenseAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			processAction(currentExpense, ActionType.REMOVE);
			if (resultLastAction) {
				currentExpenseCategory.removeExpense(currentExpense);
				dataProvider.save(currentCar);
				showExpenses(currentExpenseCategory);
			}
		}
	}
}
