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
import org.seb.dso.model.Item;
import org.seb.dso.model.Item.Type;
import org.seb.dso.model.Modifier;
import org.seb.dso.ui.Messages;

/**
 * @author Sebastian
 *
 */
public class ItemUtils {

	/**
	 * @param file
	 *            File with items
	 * @return Collection of parsed items
	 * @throws Exception
	 *             some casting exception wrapped into e.
	 */
	public static List<Item> getItems(final File file) throws Exception {
		List<Item> items = new ArrayList<Item>();

		Reader in;
		try {
			in = new FileReader(file);
			Iterable<CSVRecord> records;
			records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord().withIgnoreEmptyLines()
					.withAllowMissingColumnNames().parse(in);
			for (CSVRecord record : records) {
				Item item = recordToItemNew(record);
				if (null != item) {
					items.add(item);
				}
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

	/**
	 * Parses the CSV record into an Item object.
	 * 
	 * @param record
	 *            Record from the CSV file
	 * @return Item parsed from the the record
	 * @throws Exception
	 *             some casting exception wrapped into e.
	 */
	private static Item recordToItemNew(final CSVRecord record) throws Exception {
		Item item = new Item();

		Iterator<String> iter = record.iterator();
		String modText = iter.next();
		if (null == modText || modText.equals("")) { //$NON-NLS-1$
			return null;
		}
		Item.Type t = null;
		switch (modText.toLowerCase()) {
		case "ring": //$NON-NLS-1$
			t = Type.RING;
			break;
		case "amulet": //$NON-NLS-1$
			t = Type.AMULET;
			break;
		case "belt": //$NON-NLS-1$
			t = Type.BELT;
			break;
		case "cloak": //$NON-NLS-1$
			t = Type.CLOAK;
			break;
		case "crystal": //$NON-NLS-1$
			t = Type.CRYSTAL;
			break;
		case "adornment": //$NON-NLS-1$
			t = Type.CRYSTAL;
			break;
		case "twohand": //$NON-NLS-1$
			t = Type.TWOHAND;
			break;
		case "mainhand": //$NON-NLS-1$
			t = Type.MAINHAND;
			break;
		case "offhand": //$NON-NLS-1$
			t = Type.OFFHAND;
			break;
		case "helmet": //$NON-NLS-1$
			t = Type.HELMET;
			break;
		case "pauldrons": //$NON-NLS-1$
			t = Type.PAULDRONS;
			break;
		case "torso": //$NON-NLS-1$
			t = Type.TORSO;
			break;
		case "gloves": //$NON-NLS-1$
			t = Type.GLOVES;
			break;
		case "boots": //$NON-NLS-1$
			t = Type.BOOTS;
			break;
		}
		item.setItemType(t);

		modText = iter.next();
		item.setItemSet(modText);

		while (iter.hasNext()) {
			modText = iter.next();
			if ("".equals(modText)) { //$NON-NLS-1$
				continue;
			}
			String[] vals = modText.split(":"); //$NON-NLS-1$
			if (vals.length < 2) {
				throw new Exception(Messages.getString("UI.ERROR.INVALID.MODIFIER")); //$NON-NLS-1$
			}
			Modifier mod = new Modifier();

			if (vals[1].contains("%")) { //$NON-NLS-1$
				mod.setTypeByCode(vals[0], false);
				mod.setValue(Double.valueOf(vals[1].substring(0, vals[1].length() - 1)));
				mod.setAbsolute(false);
			} else {
				mod.setTypeByCode(vals[0], true);
				mod.setValue(Double.valueOf(vals[1]));
			}

			item.getMods().add(mod);
		}
		return item;

	}

	public static Inventory parseInventoryFromItems(Collection<Item> items) {
		Inventory inv = new Inventory();
		for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			if (!item.isSelected()) {
				continue;
			}
			switch (item.getItemType()) {
			case AMULET:
				inv.getAmulets().add(item);
				break;
			case BELT:
				inv.getBelts().add(item);
				break;
			case CLOAK:
				inv.getCloaks().add(item);
				break;
			case RING:
				inv.getRings().add(item);
				break;
			case CRYSTAL:
				inv.getCrystals().add(item);
				break;
			case MAINHAND:
				inv.getMainhands().add(item);
				break;
			case TWOHAND:
				inv.getTwohands().add(item);
				break;
			case OFFHAND:
				inv.getOffhands().add(item);
				break;
			case HELMET:
				inv.getHelmets().add(item);
				break;
			case PAULDRONS:
				inv.getPauldrons().add(item);
				break;
			case TORSO:
				inv.getTorsos().add(item);
				break;
			case GLOVES:
				inv.getGloves().add(item);
				break;
			case BOOTS:
				inv.getBoots().add(item);
				break;
			}
		}
		return inv;
	}

	/**
	 * TODO Consider H2 DB or OrientDB without in-memory operation
	 * 
	 * Recursively creates the massive collection of all possible item
	 * combinations
	 * 
	 * @param inv
	 * @param twohand
	 *            Specifies either twohanded or onehanded setups should be used
	 * @return
	 */
	public static List<CharacterSnapshot> getAllSnapshots(Inventory inv, boolean isTwohand, boolean isRanger) {
		List<CharacterSnapshot> snapshots = new ArrayList<CharacterSnapshot>();
		if (isRanger) {
			continueWithTwohandRanger(inv, snapshots);
		} else if (isTwohand) {
			continueWithTwohand(inv, snapshots);
		} else {
			continueWithMainhand(inv, snapshots);
		}
		return snapshots;
	}

	private static void continueWithAmulet(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {

		for (Iterator<Item> iterator = inv.getAmulets().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
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
			// continueWithTwohand(inv, snapshots, cs);
			snapshots.add(cs);
		}
	}

	private static void continueWithTwohand(Inventory inv, List<CharacterSnapshot> snapshots) {
		for (Iterator<Item> iterator = inv.getTwohands().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = new CharacterSnapshot();
			cs.setTwohand(item);
			// cs.processModifiers(item.getModifiersAsList());
			// continueWithGems(inv, snapshots, cs);
			continueWithAmulet(inv, snapshots, cs);
		}

	}

	private static void continueWithTwohandRanger(Inventory inv, List<CharacterSnapshot> snapshots) {
		for (Iterator<Item> iterator = inv.getTwohands().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = new CharacterSnapshot();
			cs.setTwohand(item);
			// cs.processModifiers(item.getModifiersAsList());
			// continueWithGems(inv, snapshots, cs);
			continueWithOffhand(inv, snapshots, cs);
		}

	}

	private static void continueWithMainhand(Inventory inv, List<CharacterSnapshot> snapshots) {
		for (Iterator<Item> iterator = inv.getMainhands().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = new CharacterSnapshot();
			cs.setMainhand(item);
			// cs.processModifiers(item.getModifiersAsList());
			// continueWithGems(inv, snapshots, cs);
			continueWithOffhand(inv, snapshots, cs);
		}

	}

	private static void continueWithOffhand(Inventory inv, List<CharacterSnapshot> snapshots, CharacterSnapshot tmp) {
		for (Iterator<Item> iterator = inv.getOffhands().iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			CharacterSnapshot cs = tmp.copy();
			cs.setOffhand(item);
			// cs.processModifiers(item.getModifiersAsList());
			// continueWithGems(inv, snapshots, cs);
			continueWithAmulet(inv, snapshots, cs);
		}

	}

	/**
	 * Parses the modifiers from the semicolon and comma-separated string.
	 * 
	 * @param str
	 *            Input string
	 * @return Modfier array
	 * @throws Exception
	 *             might throw some casting exception, that would be wrapped
	 *             into simple e
	 */
	public static Modifier[] parseModifiersFromString(final String str) throws Exception {
		if (null == str || str.equals("")) { //$NON-NLS-1$
			return null;
		}
		String[] modArr = str.split(","); //$NON-NLS-1$
		Modifier[] mods = new Modifier[modArr.length];
		try {
			for (int j = 0; j < modArr.length; j++) {
				String jstr = modArr[j];
				String[] modstr = jstr.split(":"); //$NON-NLS-1$
				mods[j] = new Modifier();

				if (modstr[1].contains("%")) { //$NON-NLS-1$
					mods[j].setTypeByCode(modstr[0], false);
					mods[j].setValue(Double.valueOf(modstr[1].substring(0, modstr[1].length() - 1)));
					mods[j].setAbsolute(false);
				} else {
					mods[j].setTypeByCode(modstr[0], true);
					mods[j].setValue(Double.valueOf(modstr[1]));
				}

			}
		} catch (Exception e) {
			throw new Exception(Messages.getString("UI.ERROR.INVALID.MODIFIER")); //$NON-NLS-1$
		}
		return mods;

	}

}
