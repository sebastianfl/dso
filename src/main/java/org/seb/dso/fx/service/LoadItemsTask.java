package org.seb.dso.fx.service;

import java.util.List;

import org.seb.dso.fx.model.CharacterModel;
import org.seb.dso.model.Item;
import org.seb.dso.util.ItemUtils;

import javafx.concurrent.Task;

public class LoadItemsTask extends Task<Void> {

	private CharacterModel model;

	public LoadItemsTask(CharacterModel model) {
		super();
		this.model = model;
	}

	@Override
	protected Void call() throws Exception {
		List<Item> items = ItemUtils.getItems(model.getItemsFile());
		model.setItems(items);
		return null;
	}

}
