package org.seb.dso.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.seb.dso.CharacterSnapshot;
import org.seb.dso.Inventory;
import org.seb.dso.model.e.ModelChangeEvent;
import org.seb.dso.model.e.ModelChangeListener;
import org.seb.dso.model.e.ProgressChangeListener;
import org.seb.dso.ui.Messages;
import org.seb.dso.util.ItemUtils;
import org.seb.dso.util.PropertyManager;

/**
 * @author sebastian_fl
 *
 */
public class OptimizerModel {
	private static final Logger fLogger = Logger.getLogger(OptimizerModel.class.getPackage().getName());

	public enum CharClass {
		MAGE("Mage", "UI.CLASS.NAME.MAGE"), DRAGONKNIGHT("DragonKnight", "UI.CLASS.NAME.DRAGONKNIGHT"), RANGER("Ranger",
				"UI.CLASS.NAME.RANGER"), DWARF("Dwarf", "UI.CLASS.NAME.DWARF");

		private String name;
		private String key;

		public synchronized String getName() {
			return name;
		}

		public synchronized void setName(String charClassName) {
			this.name = charClassName;
		}

		CharClass(String s, String k) {
			this.name = s;
			this.key = k;
		}
		
		public String getMessage() {
			return Messages.getString(key);
		}

		@Override
		public String toString() {
			return this.getMessage();
		}
	}

	private List<ModelChangeListener> listeners = new ArrayList<ModelChangeListener>();

	private EnumTypes.State state;

	private CharClass charClass;

	private Inventory inventory;
	private Collection<Item> items;
	private CharacterPower inputPower;
	private List<CharacterSnapshot> snapshots;
	private boolean twoHanded;

	private Modifier agility;
	private Modifier attack;
	private Modifier essence;
	private Modifier[] rage;
	private Modifier[] weaponDmg;
	private Modifier[] offGems;
	private Modifier[] defGems;
	private Modifier[] petAndBuffs;

	private CharacterSnapshot bestSnapshot;

	private CharacterPower bestPower;

	private ProgressChangeListener progressListener;

	public OptimizerModel() {
		this.setState(EnumTypes.State.CLEAN);
	}

	public synchronized EnumTypes.State getState() {
		return state;
	}

	public synchronized void setState(EnumTypes.State state, String message) {
		if (this.state != state) {
			this.state = state;
			processModelChange(new ModelChangeEvent(ModelChangeEvent.EventType.STATE, state, message));
		}
	}

	public void setState(EnumTypes.State state) {
		this.setState(state, null);
	}

	public synchronized CharClass getCharClass() {
		return charClass;
	}

	public synchronized void setCharClass(CharClass charClass) {

		if (this.charClass != charClass) {
			this.charClass = charClass;
			// processModelChange(new
			// ModelChangeEvent(ModelChangeEvent.EventType.CHARCLASS,
			// charClass.charClassName));
			this.setState(EnumTypes.State.LOADED);
		}
	}

	public synchronized Inventory getInventory() {
		return inventory;
	}

	public synchronized void setInventory(Inventory inventory) {
		if (this.inventory != inventory) {
			this.inventory = inventory;
			// processModelChange(new
			// ModelChangeEvent(ModelChangeEvent.EventType.INVETORY, ""));
			this.setState(EnumTypes.State.LOADED);

		}
	}

	public synchronized boolean isTwoHanded() {
		return twoHanded;
	}

	public synchronized void setTwoHanded(boolean twoHanded) {
		if (this.twoHanded != twoHanded) {
			this.twoHanded = twoHanded;
			this.setState(EnumTypes.State.LOADED);
			// processModelChange(new
			// ModelChangeEvent(ModelChangeEvent.EventType.MODIFIER,
			// "twoHanded"));
		}
	}

	public final Modifier getAgility() {
		return agility;
	}

	/**
	 * @param vagility
	 *            value to set
	 */
	public final synchronized void setAgility(final Modifier vagility) {
		if (this.agility != vagility) {
			this.agility = vagility;
		}
	}

	public final Modifier getAttack() {
		return attack;
	}

	/**
	 * @param vattack
	 *            Value to set
	 */
	public final synchronized void setAttack(final Modifier vattack) {
		if (this.attack != vattack) {
			this.attack = vattack;
		}
	}

	public synchronized Modifier getEssence() {
		return essence;
	}

	public synchronized void setEssence(Modifier essence) {
		if (this.essence != essence) {
			this.essence = essence;
			// this.setState(State.PREPROCESSED);
			// processModelChange(new
			// ModelChangeEvent(ModelChangeEvent.EventType.MODIFIER,
			// "essence"));
		}
	}

	public synchronized Modifier[] getRage() {
		return rage;
	}

	public synchronized void setRage(Modifier[] rage) {
		if (this.rage != rage) {
			this.rage = rage;
			// this.setState(State.PREPROCESSED);
			// processModelChange(new
			// ModelChangeEvent(ModelChangeEvent.EventType.MODIFIER, "rage"));
		}
	}

	public synchronized Modifier[] getWeaponDmg() {
		return weaponDmg;
	}

	public synchronized void setWeaponDmg(Modifier[] weaponDmg) {
		if (this.weaponDmg != weaponDmg) {
			this.weaponDmg = weaponDmg;
			// this.setState(State.PREPROCESSED);
			// processModelChange(new
			// ModelChangeEvent(ModelChangeEvent.EventType.MODIFIER,
			// "weaponDmg"));
		}
	}

	public synchronized Modifier[] getOffGems() {
		return offGems;
	}

	public synchronized void setOffGems(Modifier[] offGems) {
		if (this.offGems != offGems) {
			this.offGems = offGems;
			// this.setState(State.PREPROCESSED);
			// processModelChange(new
			// ModelChangeEvent(ModelChangeEvent.EventType.MODIFIER,
			// "offGems"));
		}
	}

	public synchronized Modifier[] getDefGems() {
		return defGems;
	}

	public synchronized void setDefGems(Modifier[] defGems) {
		if (this.defGems != defGems) {
			this.defGems = defGems;
			// this.setState(State.PREPROCESSED);
			// processModelChange(new
			// ModelChangeEvent(ModelChangeEvent.EventType.MODIFIER,
			// "defGems"));
		}
	}

	public synchronized Modifier[] getPetAndBuffs() {
		return petAndBuffs;
	}

	public synchronized void setPetAndBuffs(Modifier[] petAndBuffs) {
		if (this.petAndBuffs != petAndBuffs) {
			this.petAndBuffs = petAndBuffs;
			// this.setState(State.PREPROCESSED);
			// processModelChange(new
			// ModelChangeEvent(ModelChangeEvent.EventType.MODIFIER,
			// "petAndBuffs"));
		}
	}

	public synchronized Collection<Item> getItems() {
		return items;
	}

	public synchronized void setItems(Collection<Item> items) {
		this.items = items;
	}

	public synchronized CharacterPower getInputPower() {
		return inputPower;
	}

	public synchronized void setInputPower(CharacterPower inputPower) {
		this.inputPower = inputPower;
	}

	public synchronized List<CharacterSnapshot> getSnapshots() {
		return snapshots;
	}

	public synchronized void setSnapshots(List<CharacterSnapshot> snapshots) {
		this.snapshots = snapshots;
	}

	public synchronized final ProgressChangeListener getProgressListener() {
		return progressListener;
	}

	public synchronized final void setProgressListener(ProgressChangeListener progressListener) {
		this.progressListener = progressListener;
	}

	public synchronized final CharacterSnapshot getBestSnapshot() {
		return bestSnapshot;
	}

	public synchronized final void setBestSnapshot(CharacterSnapshot bestSnapshot) {
		this.bestSnapshot = bestSnapshot;
	}

	public synchronized final CharacterPower getBestPower() {
		return bestPower;
	}

	public synchronized final void setBestPower(CharacterPower bestPower) {
		this.bestPower = bestPower;
	}

	public List<ModelChangeListener> getListeners() {
		return listeners;
	}

	public void setListeners(List<ModelChangeListener> listeners) {
		this.listeners = listeners;
	}

	public synchronized void addModelChangeListener(ModelChangeListener listener) {
		if (!this.listeners.contains(listener)) {
			this.listeners.add(listener);
		}
	}

	private void processModelChange(ModelChangeEvent e) {
		synchronized (this) {
			if (listeners.size() == 0)
				return;

			for (ModelChangeListener listener : this.listeners) {
				listener.modelChanged(e);

			}
		}
	}

	public final void processItems() throws Exception {
		this.setState(EnumTypes.State.CALCULATING);

		// off gem mods, def gem mods, attack mods, agility mods, essence mods,
		// wp mod, rage mod

		Modifier[] additionalMods = { this.attack, this.agility, this.essence };

		double max = 0;
		CharacterSnapshot bestSnapshot = null;
		CharacterPower bestPower = null;
		// user input modifiers will go here
		this.inputPower = new CharacterPower();
		if (null != this.offGems) {
			this.inputPower.processModifiers(Arrays.asList(this.offGems));
		}
		if (null != this.defGems) {
			this.inputPower.processModifiers(Arrays.asList(this.defGems));
		}
		if (null != this.weaponDmg) {
			this.inputPower.processModifiers(Arrays.asList(this.weaponDmg));
		}
		if (null != this.rage) {
			this.inputPower.processModifiers(Arrays.asList(this.rage));
		}
		if (null != this.petAndBuffs) {
			this.inputPower.processModifiers(Arrays.asList(this.petAndBuffs));
		}
		this.inputPower.processModifiers(Arrays.asList(additionalMods));
		// Now model contains the CharacterPower object with all the modifiers
		// processed. Now need to apply that to every character power from the
		// snapshot list

		int i = 0;
		for (Iterator<CharacterSnapshot> iterator = this.snapshots.iterator(); iterator.hasNext();) {
			CharacterSnapshot cs = iterator.next();
			CharacterPower power = cs.getCharacterPowerCopy();
			cs.getCp().append(this.inputPower);
			double cmd = cs.getCp().calculateEffectiveDamage();
			if (cmd > max) {
				max = cmd;
				bestSnapshot = cs;
				bestPower = cs.getCp();
			}
			++i;
			cs.setCp(power);
			if (null != this.progressListener) {
				progressListener.progressChanged(i);
			}
		}
		this.setState(EnumTypes.State.CALCULATED);
		this.bestSnapshot = bestSnapshot;
		this.bestPower = bestPower;
		fLogger.log(Level.INFO, "Best snapshot: " + bestSnapshot.toString());

	}

	/**
	 * Generates all possible snapshots given the list of the items
	 * 
	 * @throws Exception
	 */
	public final void generateSnapshots() throws Exception {

		PropertyManager.getPropertyManager().setCurrentClass(this.charClass.name);

		SetConfig.getSetConfig().reinitialize();

		// Number of snapshots to be generated
		int size = calculateSize();

		if (size == 0) {
			throw new Exception("There should be at least one item of each kind.");
		}

		this.setState(EnumTypes.State.GENERATING_SNAPSHOTS, String.valueOf(size));

		List<CharacterSnapshot> snapshots = ItemUtils.getAllSnapshots(this.getInventory(), this.twoHanded,
				this.charClass.name.equalsIgnoreCase("ranger"));

		// Go through extra state to populate the label with the snapshot list
		// size
		this.setState(EnumTypes.State.GENERATED_SNAPSHOTS, String.valueOf(size));
		this.setState(EnumTypes.State.PREPROCESSING, this.charClass.getMessage());

		int i = 0;
		for (Iterator<CharacterSnapshot> iterator = snapshots.iterator(); iterator.hasNext();) {
			CharacterSnapshot cs = iterator.next();
			cs.clean();
			// Process all the items
			cs.processModifiers();
			// process sets if any
			cs.processSets();
			++i;
			if (null != this.progressListener) {
				progressListener.progressChanged(i);
			}
		}
		this.setSnapshots(snapshots);
		this.setState(EnumTypes.State.PREPROCESSED);
	}

	public final int calculateSize() {
		boolean isRanger = this.charClass.name.equalsIgnoreCase("ranger");
		int size;
		if (isRanger && this.twoHanded) {
			size = this.getInventory().getAmulets().size() * this.getInventory().getBelts().size()
					* this.getInventory().getCloaks().size() * this.getInventory().getCrystals().size()
					* this.getInventory().getTwohands().size() * this.getInventory().getOffhands().size()
					* this.getInventory().getHelmets().size() * this.getInventory().getPauldrons().size()
					* this.getInventory().getTorsos().size() * this.getInventory().getGloves().size()
					* this.getInventory().getBoots().size() * (this.getInventory().getRings().size())
					* (this.getInventory().getRings().size() - 1) / 2;

		} else if (this.twoHanded) {
			size = this.getInventory().getAmulets().size() * this.getInventory().getBelts().size()
					* this.getInventory().getCloaks().size() * this.getInventory().getCrystals().size()
					* this.getInventory().getTwohands().size() * this.getInventory().getHelmets().size()
					* this.getInventory().getPauldrons().size() * this.getInventory().getTorsos().size()
					* this.getInventory().getGloves().size() * this.getInventory().getBoots().size()
					* (this.getInventory().getRings().size()) * (this.getInventory().getRings().size() - 1) / 2;
		} else {
			size = this.getInventory().getAmulets().size() * this.getInventory().getBelts().size()
					* this.getInventory().getCloaks().size() * this.getInventory().getCrystals().size()
					* this.getInventory().getMainhands().size() * this.getInventory().getOffhands().size()
					* this.getInventory().getHelmets().size() * this.getInventory().getPauldrons().size()
					* this.getInventory().getTorsos().size() * this.getInventory().getGloves().size()
					* this.getInventory().getBoots().size() * (this.getInventory().getRings().size())
					* (this.getInventory().getRings().size() - 1) / 2;
		}
		return size;
	}

	@Override
	public String toString() {
		return "OptimizerModel [" + (listeners != null ? "listeners=" + listeners + ", " : "")
				+ (state != null ? "state=" + state + ", " : "")
				+ (charClass != null ? "charClass=" + charClass + ", " : "")
				+ (inventory != null ? "inventory=" + inventory + ", " : "") + "twoHanded=" + twoHanded + ", "
				+ (agility != null ? "agility=" + agility + ", " : "")
				+ (attack != null ? "attack=" + attack + ", " : "")
				+ (essence != null ? "essence=" + essence + ", " : "")
				+ (rage != null ? "rage=" + Arrays.toString(rage) + ", " : "")
				+ (weaponDmg != null ? "weaponDmg=" + Arrays.toString(weaponDmg) + ", " : "")
				+ (offGems != null ? "offGems=" + Arrays.toString(offGems) + ", " : "")
				+ (defGems != null ? "defGems=" + Arrays.toString(defGems) + ", " : "")
				+ (petAndBuffs != null ? "petAndBuffs=" + Arrays.toString(petAndBuffs) : "") + "]";
	}

}
