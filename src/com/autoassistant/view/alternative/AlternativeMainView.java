package com.autoassistant.view.alternative;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.apache.log4j.Logger;

import com.autoassistant.db.DataProvider;
import com.autoassistant.model.Car;
import com.autoassistant.model.ExpenseCategory;
import com.autoassistant.view.TableModelExpenseImpl;

public class AlternativeMainView implements Runnable {
	private static final Logger log = Logger.getLogger(AlternativeMainView.class);

	private DataProvider dataProvider;

	private JFrame frmAutoAssistant;
	private JTable tblExpenses;
	private JLabel lblCarComment;
	private JLabel lblStatus;
	private JScrollPane paneTree;
	private JTree tree;

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
		frmAutoAssistant.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		paneTree = new JScrollPane();
		paneTree.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmAutoAssistant.getContentPane().add(paneTree);

		tree = new JTree();
		tree.setShowsRootHandles(true);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

				if (node == null)
					// Nothing is selected.
							return;

						Object nodeInfo = node.getUserObject();

						if (node.isLeaf() && !node.getAllowsChildren()) {
							ExpenseCategory category = (ExpenseCategory) nodeInfo;
							categoryChanged(category);
						} else if (!node.isRoot()) {
							Car car = (Car) nodeInfo;
							carChanged(car);
						}
					}
				});
		
		paneTree.setViewportView(tree);
		lblStatus = new JLabel("Status");
		
		JPanel pnlMain = new JPanel();
		frmAutoAssistant.getContentPane().add(pnlMain);
		pnlMain.setBorder(UIManager.getBorder("Button.border"));
		
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] {0};
		layout.columnWeights = new double[] { 1.0 };
		layout.rowWeights = new double[] { 0.0, 1.0 };
		layout.rowHeights = new int[] {35, 280};		
		pnlMain.setLayout(layout);
		
		lblCarComment = new JLabel("Car comment", SwingConstants.LEFT);
		lblCarComment.setVerticalAlignment(SwingConstants.TOP);
		
		GridBagConstraints gbc_lblCarComment = new GridBagConstraints();		
		gbc_lblCarComment.anchor = GridBagConstraints.WEST;
		gbc_lblCarComment.insets = new Insets(0, 0, 5, 0);
		gbc_lblCarComment.gridx = 0;
		gbc_lblCarComment.gridy = 0;		
		pnlMain.add(lblCarComment, gbc_lblCarComment);		

		tblExpenses = new JTable(new TableModelExpenseImpl(null));
		tblExpenses.getColumnModel().getColumn(0).setResizable(false);
		tblExpenses.getColumnModel().getColumn(0).setMinWidth(140);
		tblExpenses.getColumnModel().getColumn(0).setMaxWidth(140);
		tblExpenses.getColumnModel().getColumn(1).setResizable(false);
		tblExpenses.getColumnModel().getColumn(1).setMaxWidth(100);
		tblExpenses.getColumnModel().getColumn(2).setResizable(false);
		tblExpenses.getColumnModel().getColumn(2).setMaxWidth(100);
		tblExpenses.setRowHeight(20);
		JScrollPane paneExpenses = new JScrollPane();
		paneExpenses.setViewportView(tblExpenses);
		
		GridBagConstraints gbc_paneExpenses = new GridBagConstraints();
		gbc_paneExpenses.fill = GridBagConstraints.BOTH;
		gbc_paneExpenses.anchor = GridBagConstraints.CENTER;
		gbc_paneExpenses.insets = new Insets(0, 0, 5, 0);
		gbc_paneExpenses.gridx = 0;
		gbc_paneExpenses.gridy = 1;		
		pnlMain.add(paneExpenses, gbc_paneExpenses);
		
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.WEST;
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 2;		
		pnlMain.add(lblStatus, gbc_lblStatus);	

		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(10, 10, 10, 10));
		frmAutoAssistant.setJMenuBar(menuBar);

		@SuppressWarnings("serial")
		AbstractAction addAction = new AbstractAction("Add") {

			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("Add action");
			}
		};

		@SuppressWarnings("serial")
		AbstractAction editAction = new AbstractAction("Edit") {

			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("Edit action");
			}
		};

		@SuppressWarnings("serial")
		AbstractAction deleteAction = new AbstractAction("Delete") {

			@Override
			public void actionPerformed(ActionEvent e) {
				log.info("Delete action");
			}
		};

		JMenu operationsMenu = menuBar.add(new JMenu("Operations"));
		operationsMenu.add(new JMenuItem(addAction));
		operationsMenu.add(new JMenuItem(editAction));
		operationsMenu.add(new JMenuItem(deleteAction));
	}

	private void carChanged(Car car) {
		if (car != null) {
			lblCarComment.setText(car.getComment());
		}
	}

	private void categoryChanged(ExpenseCategory category) {
		fillExpenses(category);
	}

	private void fillCars() {
		final Set<Car> cars = dataProvider.getCars();

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Cars");
		if (cars != null) {
			for (Car car : cars) {
				DefaultMutableTreeNode carNode = new DefaultMutableTreeNode(car);
				carNode.setAllowsChildren(true);
				rootNode.add(carNode);

				Set<ExpenseCategory> expenseCategories = car.getExpenseCategories();

				if (expenseCategories != null) {
					for (ExpenseCategory expenseCategory : expenseCategories) {
						DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(expenseCategory);
						categoryNode.setAllowsChildren(false);
						carNode.add(categoryNode);
					}
				}
			}
		}
		tree.setModel(new DefaultTreeModel(rootNode));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	}

	private void fillExpenses(ExpenseCategory category) {
		tblExpenses.setModel(new TableModelExpenseImpl(category != null ? category.getExpenses() : null));
		tblExpenses.setAutoCreateRowSorter(true);
		tblExpenses.getRowSorter().toggleSortOrder(0);
		tblExpenses.updateUI();
	}
}
