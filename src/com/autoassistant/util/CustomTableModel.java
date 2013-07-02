package com.autoassistant.util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public abstract class CustomTableModel<TElement> implements TableModel {

	private final Set<TableModelListener> listeners = new HashSet<TableModelListener>();
	
	protected List<TElement> elements;
	private final int columnCount;
	private final boolean[] columnEditables;
	private final String[] columnNames;
	private final Class<?>[] columnTypes;

	CustomTableModel(Set<TElement> elements, String[] columnNames, Class<?>[] columnTypes) {
		List<TElement> list = new LinkedList<TElement>();
		list.addAll(elements);
		this.elements = list;
		this.columnNames = columnNames;
		columnCount = columnNames.length;
		columnEditables = new boolean[columnCount];

		for (int i = 0; i < columnCount; i++)
			columnEditables[i] = false;

		this.columnTypes = columnTypes;
	}

	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	public int getRowCount() {
		return elements.size();
	}

	public void setElements(Set<TElement> elements) {
		if (elements == null) {
			this.elements.clear();
		} else {
			List<TElement> list = new LinkedList<TElement>();
			list.addAll(elements);
			this.elements = list;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnEditables[columnIndex];
	}

	public int getColumnCount() {
		return columnCount;
	}

	public String getColumnName(int columnIndex) {
		if (columnIndex >= 0 && columnIndex < columnCount) {
			return columnNames[columnIndex];
		}
		return "";
	}

	public Class<?> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {

	}

	public Object getElement(int rowIndex) {
		return elements.get(rowIndex);
	}
}
