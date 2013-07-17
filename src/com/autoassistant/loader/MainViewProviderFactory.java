package com.autoassistant.loader;

import com.autoassistant.db.DataProvider;
import com.autoassistant.db.DataProviderFactory;
import com.autoassistant.view.alternative.AlternativeMainView;
import com.autoassistant.view.defaultview.MainView;

public class MainViewProviderFactory {

	public static Runnable getMainView(String typeView) {
		final DataProvider dataProvider = DataProviderFactory.getDataProvider("HIBERNATE");
		if ("Default".equals(typeView)) {
			return new MainView(dataProvider);
		}

		if ("Alternative".equals(typeView)) {
			return new AlternativeMainView(dataProvider);
		}
		return null;
	}
}
