package org.seb.dso.processor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.seb.dso.CharacterSnapshot;
import org.seb.dso.fx.model.CharacterModel;
import org.seb.dso.model.CharacterPower;
import org.seb.dso.model.Item;
import org.seb.dso.model.Modifier;
import org.seb.dso.model.SetConfig;
import org.seb.dso.util.ItemUtils;
import org.seb.dso.util.PropertyManager;

import javafx.beans.property.IntegerProperty;

public class MostOffenceSnapshotProcessor implements SnapshotProcessor {

	@Override
	public void process(CharacterModel model, IntegerProperty progress) {
		// off gem mods, def gem mods, attack mods, agility mods, essence mods,
		// wp mod, rage mod

		Modifier[] additionalMods = { model.getAttackModifier(), model.getAgilityModifier(),
				model.getEssenceModifier() };

		double max = 0;
		CharacterSnapshot bestSnapshot = null;
		CharacterPower bestPower = null;
		// user input modifiers will go here
		CharacterPower inputPower = new CharacterPower();
		inputPower.processModifiers(Arrays.asList(model.getOffGemsModifiers()));
		inputPower.processModifiers(Arrays.asList(model.getDefGemsModifiers()));
		inputPower.processModifiers(Arrays.asList(model.getWeaponDmgModifiers()));
		inputPower.processModifiers(Arrays.asList(model.getRageModifiers()));
		inputPower.processModifiers(Arrays.asList(model.getPetAndBuffsModifiers()));
		inputPower.processModifiers(Arrays.asList(additionalMods));

		// Now, inputPower, the CharacterPower object, contains all the
		// modifiers
		// processed. Now need to apply that to every character power from the
		// snapshot list

		int i = 0;
		for (Iterator<CharacterSnapshot> iterator = model.getSnapshots().iterator(); iterator.hasNext();) {
			CharacterSnapshot cs = iterator.next();
			CharacterPower power = cs.getCharacterPowerCopy();
			cs.getCp().append(inputPower);
			double cmd = cs.getCp().calculateEffectiveDamage();
			if (cmd > max) {
				max = cmd;
				bestSnapshot = cs;
				bestPower = cs.getCp();
			}
			cs.setCp(power);
			progress.set(i);
		}
		model.setBestSnapshot(bestSnapshot);
		model.setBestPower(bestPower);
	}

	@Override
	public void loadItems(CharacterModel model) {
		try {
			List<Item> items = ItemUtils.getItems(model.getItemsFile());
			model.setItems(items);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void generateSnapshots(CharacterModel model, IntegerProperty progress) {
		PropertyManager.getPropertyManager().setCurrentClass(model.getCharClass().getName());

		SetConfig.getSetConfig().reinitialize();

		// Number of snapshots to be generated

		List<CharacterSnapshot> snapshots = ItemUtils.getAllSnapshots(model.getInventory(), model.getTwohanded(),
				model.getCharClass().getName().equalsIgnoreCase("ranger"));

		// Go through extra state to populate the label with the snapshot list
		// size

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
			progress.set(i);
		}
	}

}
