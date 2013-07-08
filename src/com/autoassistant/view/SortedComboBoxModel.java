package com.autoassistant.view;

import javax.swing.DefaultComboBoxModel;

/*
 *  Custom model to make sure the items are stored in a sorted order.
 *  The default is to sort in the natural order of the item, but a
 *  Comparator can be used to customize the sort order.
 */
@SuppressWarnings("serial")
public class SortedComboBoxModel<E> extends DefaultComboBoxModel<E> {

	@Override
	public void addElement(E element) {
		insertElementAt(element, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insertElementAt(E element, int index) {
		int size = getSize();

		// Determine where to insert element to keep model in sorted order
		for (index = 0; index < size; index++) {
			if (((Comparable<E>) getElementAt(index)).compareTo(element) > 0) {
				break;
			}
		}

		super.insertElementAt(element, index);

		// Select an element when it is added to the beginning of the model
		if (index == 0 && element != null) {
			setSelectedItem(element);
		}
	}
}