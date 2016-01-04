package org.seb.dso.fx.service;

import java.util.Arrays;
import java.util.Iterator;

import org.seb.dso.CharacterSnapshot;
import org.seb.dso.fx.model.CharacterModel;
import org.seb.dso.model.CharacterPower;
import org.seb.dso.model.Modifier;

import javafx.concurrent.Task;

public class ProcessTask extends Task<Void> {

	private CharacterModel model;

	public ProcessTask(CharacterModel model) {
		super();
		this.model = model;

	}

	@Override
	protected Void call() throws Exception {
		// off gem mods, def gem mods, attack mods, agility mods, essence mods,
		// wp mod, rage mod

		Modifier[] additionalMods = { model.getAttackModifier(), model.getAgilityModifier(),
				model.getEssenceModifier() };

		double max = 0;
		CharacterSnapshot bestSnapshot = null;
		CharacterPower bestPower = null;
		// user input modifiers will go here
		CharacterPower inputPower = new CharacterPower();
		if (null != model.getOffGemsModifiers()) {
			inputPower.processModifiers(Arrays.asList(model.getOffGemsModifiers()));
		}
		if (null != model.getDefGemsModifiers()) {
			inputPower.processModifiers(Arrays.asList(model.getDefGemsModifiers()));
		}
		if (null != model.getWeaponDmgModifiers()) {
			inputPower.processModifiers(Arrays.asList(model.getWeaponDmgModifiers()));
		}
		if (null != model.getRageModifiers()) {
			inputPower.processModifiers(Arrays.asList(model.getRageModifiers()));
		}
		if (null != model.getPetAndBuffsModifiers()) {
			inputPower.processModifiers(Arrays.asList(model.getPetAndBuffsModifiers()));
		}
		if (null != additionalMods) {
			inputPower.processModifiers(Arrays.asList(additionalMods));
		}

		// Now, inputPower, the CharacterPower object, contains all the
		// modifiers
		// processed. Now need to apply that to every character power from the
		// snapshot list

		int i = 0;
		int size = model.getSnapshots().size();
		System.out.println(size);
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
			updateProgress(i, size);
		}
		model.setBestSnapshot(bestSnapshot);
		model.setBestPower(bestPower);

		return null;
	}

}
