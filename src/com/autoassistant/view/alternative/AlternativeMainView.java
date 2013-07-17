package com.autoassistant.view.alternative;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.autoassistant.db.DataProvider;
import com.autoassistant.model.Car;
import com.autoassistant.model.ExpenseCategory;
import com.autoassistant.view.TableModelExpenseImpl;
import com.autoassistant.view.SortedComboBoxModel;

public class AlternativeMainView implements Runnable {
	private static final Logger log = Logger.getLogger(AlternativeMainView.class);

	private DataProvider dataProvider;

	private JFrame frmAutoAssistant;

	private JComboBox<Car> cbxCars;
	private JComboBox<ExpenseCategory> cbxExpenseCategories;
	private JTable tblExpenses;
	private JLabel lblCarComment;
	private JLabel lblStatus;	

	@Override
	public void run() {
		frmAutoAssistant.setVisible(true);
	}

	public AlternativeMainView(DataProvider dataProvider) {
		this.dataProvider = dataProvider;

		addUIComponents();
		fillCars();
	}

	private void addUIComponents() {
		frmAutoAssistant = new JFrame("Auto Assistant");
		frmAutoAssistant.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmAutoAssistant.setBounds(100, 100, 598, 414);
		frmAutoAssistant.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));

		cbxCars = new JComboBox<Car>(new SortedComboBoxModel<Car>());
		cbxCars.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				carChanged((Car) e.getItem());
			}
		});

		cbxExpenseCategories = new JComboBox<ExpenseCategory>(new SortedComboBoxModel<ExpenseCategory>());
		cbxExpenseCategories.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				categoryChanged((ExpenseCategory) e.getItem());
			}
		});

		lblCarComment = new JLabel("Car comment", SwingConstants.LEFT);
		lblStatus = new JLabel("Status");

		tblExpenses = new JTable(new TableModelExpenseImpl(null));
		tblExpenses.getColumnModel().getColumn(0).setResizable(false);
		tblExpenses.getColumnModel().getColumn(0).setMinWidth(140);
		tblExpenses.getColumnModel().getColumn(0).setMaxWidth(140);
		tblExpenses.getColumnModel().getColumn(1).setResizable(false);
		tblExpenses.getColumnModel().getColumn(1).setMaxWidth(100);
		tblExpenses.getColumnModel().getColumn(2).setResizable(false);
		tblExpenses.getColumnModel().getColumn(2).setMaxWidth(100);
		tblExpenses.setRowHeight(20);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tblExpenses);

		JPanel pnlMain = new JPanel();
		frmAutoAssistant.getContentPane().add(pnlMain);
		pnlMain.setBorder(UIManager.getBorder("Button.border"));
		pnlMain.setLayout(getPnlMainLayout());

		pnlMain.add(cbxCars, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
		pnlMain.add(cbxExpenseCategories, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
		pnlMain.add(lblCarComment, new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
		pnlMain.add(new JLabel("Expenses:"), new GridBagConstraints(0, 3, 2, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
		pnlMain.add(new JLabel("Select car:"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		pnlMain.add(new JLabel("Select category:"), new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
		pnlMain.add(lblStatus, new GridBagConstraints(0, 5, 2, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
		pnlMain.add(scrollPane, new GridBagConstraints(0, 4, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
	}

	private static LayoutManager getPnlMainLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 0, 0 };
		layout.columnWeights = new double[] { 1.0, 1.0 };
		layout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
		layout.rowHeights = new int[] { 30, 30, 0, 10, 0, 0 };
		return layout;
	}

	private void carChanged(Car car) {
		if (car != null) {
			lblCarComment.setText(car.getComment());
		}
		fillExpensesCategory(car);
	}

	private void categoryChanged(ExpenseCategory category) {
		fillExpenses(category);
	}

	private void fillCars() {
		cbxCars.removeAllItems();
		final Set<Car> cars = dataProvider.getCars();
		if (cars != null) {
			for (Car car : cars) {
				cbxCars.addItem(car);
			}
		}
	}

	private void fillExpensesCategory(final Car car) {
		cbxExpenseCategories.removeAllItems();
		if (car != null) {
			Set<ExpenseCategory> expenseCategories = car.getExpenseCategories();
			if (expenseCategories != null) {
				for (ExpenseCategory expenseCategory : expenseCategories) {
					cbxExpenseCategories.addItem(expenseCategory);
				}
			}
		}
	}

	private void fillExpenses(ExpenseCategory category) {
		tblExpenses.setModel(new TableModelExpenseImpl(category != null ? category.getExpenses() : null));
		tblExpenses.setAutoCreateRowSorter(true);
		tblExpenses.getRowSorter().toggleSortOrder(0);
		tblExpenses.updateUI();
	}
}
