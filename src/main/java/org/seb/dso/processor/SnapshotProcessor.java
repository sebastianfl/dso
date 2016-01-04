package org.seb.dso.processor;

import org.seb.dso.fx.model.CharacterModel;

import javafx.beans.property.IntegerProperty;

public interface SnapshotProcessor {

	public void process(CharacterModel model, IntegerProperty progress);

	public void loadItems(CharacterModel model);

	public void generateSnapshots(CharacterModel model, IntegerProperty progress);

}
