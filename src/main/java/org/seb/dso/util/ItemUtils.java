package org.seb.dso.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.seb.dso.CharacterSnapshot;
import org.seb.dso.Inventory;
import org.seb.dso.model.GemConfig;
import org.seb.dso.model.Item;
import org.seb.dso.model.ItemType;
import org.seb.dso.model.Modifier;

public class ItemUtils {

	Collection<Item> itemList;

	public static Collection<Item> getItems(File file) {
		List<Item> items = new ArrayList<Item>();

		Reader in;
		try {
			in = new FileReader(file);
			Iterable<CSVRecord> records;
			records = CSVFormat.EXCEL.withHeader().parse(in);
			for (CSVRecord record : records) {
				items.add(recordToItem(record));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}

	private static Item recordToItem(CSVRecord record) {
		Item item = new Item();

		String modText = record.get("Modifier1");
		String[] vals = modText.split(":");
		if (vals.length < 2) {
			vals = new String[] { "", "" };
		}
		Modifier mod = new Modifier();
		mod.setType(vals[0]);
		mod.setValue(vals[1]);
		item.setMod1(mod);

		modText = record.get("Modifier2");
		vals = modText.split(":");
		if (vals.length < 2) {
			vals = new String[] { "", "" };
		}
		mod = new Modifier();
		mod.setType(vals[0]);
		mod.setValue(vals[1]);
		item.setMod2(mod);

		modText = record.get("Modifier3");
		vals = modText.split(":");
		if (vals.length < 2) {
			vals = new String[] { "", "" };
		}
		mod = new Modifier();
		mod.setType(vals[0]);
		mod.setValue(vals[1]);
		item.setMod3(mod);

		modText = record.get("Modifier4");
		vals = modText.split(":");
		if (vals.length < 2) {
			vals = new String[] { "", "" };
		}
		mod = new Modifier();
		mod.setType(vals[0]);
		mod.setValue(vals[1]);
		item.setMod4(mod);

		modText = record.get("ItemType");
		item.setItemType(modText);

		modText = record.get("SetName");
		item.setItemSet(modText);

		return item;

	}

	public static Inventory parseInventoryFromItems(Collection<Item> items) {
		Inventory inv = new Inventory();
		for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			switch (item.getItemType().toLowerCase()) {
			case ItemType.AMULET:
				inv.getAmulets().add(item);
				break;
			case ItemType.BELT:
				inv.getBelts().add(item);
				break;
			case ItemType.CLOAK:
				inv.getCloaks().add(item);
				break;
			case ItemType.RING:
				inv.getRings().add(item);
				break;
			case ItemType.CRYSTAL:
				inv.getCrystals().add(item);
				break;
			case ItemType.MAINHAND:
				inv.getMainhands().add(item);
				break;
			case ItemType.TWOHAND:
				inv.getTwohands().add(item);
				break;
			case ItemType.OFFHAND:
				inv.getOffhands().add(item);
				break;
			case ItemType.HELMET:
				inv.getHelmets().add(item);
				break;
			case ItemType.PAULDRONS:
				inv.getPauldrons().add(item);
				break;
			case ItemType.TORSO:
				inv.getTorsos().add(item);
				break;
			case ItemType.GLOVES:
				inv.getGloves().add(item);
				break;
			case ItemType.BOOTS:
				inv.getBoots().add(item);
				break;
			}
		}
		return inv;
	}

	public static List<CharacterSnapshot> getAllSnapshots(Inventory inv) {
		List<CharacterSnapshot> snapshots = new ArrayList<CharacterSnapshot>();
		continueWithAmulet(inv, snapshots);
		return snapshots;
	}

	/**
	 * The recursion entry method. All the amulets will be taken 1 by 1 and
	 * passed into the following structure for the snapshot generation
	 * 
	 * @param inv
	 *            - represents inventory, the list of items
	 * @param snapshots
	 *            - the resulting collection of snapshots
	 */
	private static void continueWithAmulet(Inventory inv, List<CharacterSnapshot> snapshots) {

		for (Iterator<Item> iterator = inv.getAmulets().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = new CharacterSnapshot();
			cs.setAmulet(item);
			// cs.processModifiers(item.getModifiersAsList());
			continueWithBelt(inv, snapshots, cs);
		}

	}

	private static void continueWithBelt(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Item> iterator = inv.getBelts().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
			cs.setBelt(item);
			// cs.processModifiers(item.getModifiersAsList());
			continueWithRing1(inv, snapshots, cs);
		}
	}

	private static void continueWithRing1(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Item> iterator = inv.getRings().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
			cs.setRing1(item);
			// cs.processModifiers(item.getModifiersAsList());
			continueWithRing2(inv, snapshots, cs);
		}
	}

	private static void continueWithRing2(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		boolean flag = false;
		for (Iterator<Item> iterator = inv.getRings().iterator(); iterator.hasNext();) {
			Item item = iterator.next();

			if (!flag && tmp.getRing1() != item) {
				continue;
			}
			flag = true;
			if (tmp.getRing1() != item) {
				CharacterSnapshot cs = tmp.copy();
				cs.setRing2(item);
				// cs.processModifiers(item.getModifiersAsList());
				continueWithCrystal(inv, snapshots, cs);
			}
		}

	}

	private static void continueWithCrystal(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Item> iterator = inv.getCrystals().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
			cs.setCrystal(item);
			// cs.processModifiers(item.getModifiersAsList());
			continueWithHelmet(inv, snapshots, cs);
		}

	}

	private static void continueWithHelmet(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Item> iterator = inv.getHelmets().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
			cs.setHelmet(item);
			// cs.processModifiers(item.getModifiersAsList());
			continueWithPauldrons(inv, snapshots, cs);
		}

	}

	private static void continueWithPauldrons(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Item> iterator = inv.getPauldrons().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
			cs.setPauldrons(item);
			// cs.processModifiers(item.getModifiersAsList());
			continueWithTorso(inv, snapshots, cs);
		}
	}

	private static void continueWithTorso(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Item> iterator = inv.getTorsos().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
			cs.setTorso(item);
			// cs.processModifiers(item.getModifiersAsList());
			continueWithGloves(inv, snapshots, cs);
		}
	}

	private static void continueWithGloves(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Item> iterator = inv.getGloves().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
			cs.setGloves(item);
			// cs.processModifiers(item.getModifiersAsList());
			continueWithBoots(inv, snapshots, cs);
		}
	}

	private static void continueWithBoots(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Item> iterator = inv.getBoots().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
			cs.setBoots(item);
			// cs.processModifiers(item.getModifiersAsList());
			continueWithCloak(inv, snapshots, cs);
		}
	}

	private static void continueWithCloak(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Item> iterator = inv.getCloaks().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
			cs.setCloak(item);
			// cs.processModifiers(item.getModifiersAsList());
			continueWithTwohand(inv, snapshots, cs);
		}
	}

	private static void continueWithTwohand(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Item> iterator = inv.getTwohands().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
			cs.setTwohand(item);
			// cs.processModifiers(item.getModifiersAsList());
			// continueWithGems(inv, snapshots, cs);
			snapshots.add(cs);
		}

	}

	private static void continueWithGems(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Modifier[]> iterator = GemConfig.getGemConfig().getOffensiveGemConfigs().iterator(); iterator
				.hasNext();) {
			Modifier[] mods = iterator.next();
			Modifier[] dmods = GemConfig.getGemConfig().getDefensiveGemConfigs().get(0);
			CharacterSnapshot cs = tmp.copy();
			cs.setGems(mods);
			// cs.processModifiers(Arrays.asList(mods));
			// cs.processModifiers(Arrays.asList(dmods));
			// cs.processSets();
			snapshots.add(cs);
		}

	}

	public static Modifier[] parseModifiersFromString(String str) {
		if (str.equals("") || null == str)
			return null;
		String[] modArr = str.split(",");
		Modifier[] mods = new Modifier[modArr.length];
		for (int j = 0; j < modArr.length; j++) {
			String jstr = modArr[j];
			String[] modstr = jstr.split(":");
			mods[j] = new Modifier();
			mods[j].setType(modstr[0]);
			mods[j].setValue(modstr[1]);
		}
		return mods;

	}

}
