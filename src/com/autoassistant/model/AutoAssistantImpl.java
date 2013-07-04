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
	public void add(Entity entity) {
		dataProvider.add(entity);
	}

	@Override
	public void save(Entity entity) {
		dataProvider.save(entity);
	}

	@Override
	public void remove(Entity entity) {
		dataProvider.remove(entity);
	}

	@Override
	public void dispose() {
		dataProvider.close();
	}
}
