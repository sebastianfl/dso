package org.seb.dso.fx.service;

import org.seb.dso.fx.model.CharacterModel;

import javafx.beans.property.IntegerProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Button;

public class SnapshotService extends Service<Void> {
	private CharacterModel model = new CharacterModel();
	private Button triggerButton;
	private IntegerProperty progress;

	@Override
	protected Task<Void> createTask() {
		// TODO Auto-generated method stub
		return null;
	}

}
