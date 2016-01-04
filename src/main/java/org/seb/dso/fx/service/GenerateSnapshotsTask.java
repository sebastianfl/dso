package org.seb.dso.fx.service;

import java.util.Iterator;
import java.util.List;

import org.seb.dso.CharacterSnapshot;
import org.seb.dso.Inventory;
import org.seb.dso.fx.model.CharacterModel;
import org.seb.dso.model.SetConfig;
import org.seb.dso.util.ItemUtils;
import org.seb.dso.util.PropertyManager;

import javafx.concurrent.Task;

public class GenerateSnapshotsTask extends Task<Void> {

	private CharacterModel model;

	public GenerateSnapshotsTask(CharacterModel model) {
		super();
		this.model = model;
	}

	@Override
	protected Void call() throws Exception {
		PropertyManager.getPropertyManager().setCurrentClass(model.getCharClass().getName());
		SetConfig.getSetConfig().reinitialize();

		Inventory inv = ItemUtils.parseInventoryFromItems(model.getItems());
		model.setInventory(inv);

		List<CharacterSnapshot> snapshots = ItemUtils.getAllSnapshots(model.getInventory(), model.getTwohanded(),
				model.getCharClass().getName().equalsIgnoreCase("ranger"));
		int size = snapshots.size();
		int i = 0;
		for (Iterator<CharacterSnapshot> iterator = snapshots.iterator(); iterator.hasNext();) {
			CharacterSnapshot cs = iterator.next();
			cs.clean();
			// Process all the items
			cs.processModifiers();
			// process sets if any
			try {
				cs.processSets();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			++i;

			this.updateProgress(i, size);
		}
		model.setSnapshots(snapshots);

		return null;
	}

}
