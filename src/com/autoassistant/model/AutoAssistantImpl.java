package com.autoassistant.model;

import java.util.Set;

import com.autoassistant.db.DataProvider;

public class AutoAssistantImpl implements AutoAssistant {

	private DataProvider dataProvider;

	public AutoAssistantImpl(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	@Override
	public Set<Car> getCars() {
		return dataProvider.getCars();
	}

	@Override
	public void save(Object object) {
		dataProvider.save(object);
	}

	@Override
	public void remove(Object object) {
		dataProvider.remove(object);
	}

	@Override
	public void dispose() {
		dataProvider.close();
	}
}
