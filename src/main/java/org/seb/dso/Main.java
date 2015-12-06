package org.seb.dso;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.seb.dso.model.Item;
import org.seb.dso.model.SetConfig;
import org.seb.dso.util.ItemUtils;

public class Main {

	public static void main(String[] args) throws IOException {

		SetConfig sc = new SetConfig();
		Collection<Item> items = ItemUtils.getItems();

		Inventory inv = ItemUtils.parseInventoryFromItems(items);

		List<CharacterSnapshot> snapshots = ItemUtils.getAllSnapshots(inv);
		System.out.println(snapshots.size());
		double max = 0;
		CharacterSnapshot bestSnapshot = null;
		for (Iterator<CharacterSnapshot> iterator = snapshots.iterator(); iterator.hasNext();) {
			CharacterSnapshot cs = iterator.next();
			double cmd = cs.getCp().calculateEffectiveDamage();
			if (cmd > max) {
				max = cmd;
				bestSnapshot = cs;
			}
		}

		System.out.println(bestSnapshot);

		System.out.println("Min Damage: " + bestSnapshot.getCp().calculateMinDamage());
		System.out.println("Max Damage: " + bestSnapshot.getCp().calculateMaxDamage());
		System.out.println("Critical Hit: " + bestSnapshot.getCp().calculateCrit());
		System.out.println("Critical Damage: " + bestSnapshot.getCp().getCd());
		System.out.println("Travel Speed: " + bestSnapshot.getCp().getTspeed());
		System.out.println("Attack Speed: " + bestSnapshot.getCp().getAspeed());
		System.out.println("HP: " + bestSnapshot.getCp().calculateHP());
		System.out.println("Armor: " + bestSnapshot.getCp().calculateArmor());
		System.out.println("Resistance: " + bestSnapshot.getCp().calculateResist());
		System.out.println("Effective DMG: " + bestSnapshot.getCp().calculateEffectiveDamage());

	}

}
