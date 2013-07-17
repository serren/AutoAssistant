package com.autoassistant.view;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.autoassistant.model.Expense;

public class TableModelExpenseImpl implements TableModel {

	private final Set<TableModelListener> listeners;
	protected final List<Expense> elements;
	private final String[] columnNames;
	private final Class<?>[] columnTypes;

	public TableModelExpenseImpl(final Set<Expense> elements) {
		listeners = new HashSet<TableModelListener>();
		this.elements = new LinkedList<Expense>();
		if (elements != null) {
			this.elements.addAll(elements);
		}
		columnNames = new String[] { "Date", "Race", "Amount", "Comment" };
		columnTypes = new Class[] { Object.class, Integer.class, Double.class, String.class };
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case -1:
			return elements.get(rowIndex);
		case 0:
			return elements.get(rowIndex).getExpenseDate();
		case 1:
			return elements.get(rowIndex).getRace();
		case 2:
			return elements.get(rowIndex).getAmount();
		case 3:
			return elements.get(rowIndex).getText();
		default:
			return "";
		}
	}

	@Override
	public int getRowCount() {
		return elements.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int index) {
		if (index >= 0 && index < columnNames.length) {
			return columnNames[index];
		}
		return "";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}
}