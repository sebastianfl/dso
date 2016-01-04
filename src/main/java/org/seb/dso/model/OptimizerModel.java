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
import org.seb.dso.model.enumeration.ApplicationState;
import org.seb.dso.model.enumeration.CharClass;
import org.seb.dso.ui.Messages;
import org.seb.dso.util.ItemUtils;
import org.seb.dso.util.PropertyManager;

/**
 * @author sebastian_fl
 *
 */
public class OptimizerModel {
	private static final Logger FLOGGER = Logger.getLogger(OptimizerModel.class.getPackage().getName());

	private List<ModelChangeListener> listeners = new ArrayList<ModelChangeListener>();

	private ApplicationState state;

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

	/**
	 * Default constructor. Start with the clean State
	 */
	public OptimizerModel() {
		this.setState(ApplicationState.CLEAN);
	}

	public final synchronized ApplicationState getState() {
		return state;
	}

	/**
	 * @param s
	 *            State
	 * @param message
	 *            Extra message that might be communicated to observers
	 */
	public final synchronized void setState(final ApplicationState s, final String message) {
		if (this.state != s) {
			this.state = s;
			processModelChange(new ModelChangeEvent(ModelChangeEvent.EventType.STATE, s, message));
		}
	}

	/**
	 * @param s
	 *            State
	 */
	public final void setState(final ApplicationState s) {
		this.setState(s, null);
	}

	public final synchronized CharClass getCharClass() {
		return charClass;
	}

	/**
	 * @param charClazz
	 *            CharacterClass
	 */
	public final synchronized void setCharClass(final CharClass charClazz) {

		if (this.charClass != charClazz) {
			this.charClass = charClazz;

			this.setState(ApplicationState.LOADED);
		}
	}

	public final synchronized Inventory getInventory() {
		return inventory;
	}

	/**
	 * Juset a setter for Inventory object.
	 * 
	 * @param inv
	 *            Inventory object
	 */
	public final synchronized void setInventory(final Inventory inv) {
		if (this.inventory != inv) {
			this.inventory = inv;
			this.setState(ApplicationState.LOADED);

		}
	}

	public synchronized boolean isTwoHanded() {
		return twoHanded;
	}

	public synchronized void setTwoHanded(boolean twoHanded) {
		if (this.twoHanded != twoHanded) {
			this.twoHanded = twoHanded;
			this.setState(ApplicationState.LOADED);
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

		}
	}

	public synchronized Modifier[] getWeaponDmg() {
		return weaponDmg;
	}

	public synchronized void setWeaponDmg(Modifier[] weaponDmg) {
		if (this.weaponDmg != weaponDmg) {
			this.weaponDmg = weaponDmg;

		}
	}

	public synchronized Modifier[] getOffGems() {
		return offGems;
	}

	public synchronized void setOffGems(Modifier[] offGems) {
		if (this.offGems != offGems) {
			this.offGems = offGems;

		}
	}

	public synchronized Modifier[] getDefGems() {
		return defGems;
	}

	public synchronized void setDefGems(Modifier[] defGems) {
		if (this.defGems != defGems) {
			this.defGems = defGems;

		}
	}

	public synchronized Modifier[] getPetAndBuffs() {
		return petAndBuffs;
	}

	public synchronized void setPetAndBuffs(Modifier[] petAndBuffs) {
		if (this.petAndBuffs != petAndBuffs) {
			this.petAndBuffs = petAndBuffs;

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
		this.setState(ApplicationState.CALCULATING);

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
		this.setState(ApplicationState.CALCULATED);
		this.bestSnapshot = bestSnapshot;
		this.bestPower = bestPower;
		FLOGGER.log(Level.INFO, "Best snapshot: " + bestSnapshot.toString());

	}

	/**
	 * Generates all possible snapshots given the list of the items.
	 * 
	 * @throws Exception
	 *             some Exception, perhaps casting or whatever
	 */
	public final void generateSnapshots() throws Exception {

		PropertyManager.getPropertyManager().setCurrentClass(this.charClass.getName());

		SetConfig.getSetConfig().reinitialize();

		// Number of snapshots to be generated
		int size = calculateSize();

		if (size == 0) {
			throw new Exception(Messages.getString("UI.MESSAGE.ITEM.OF.EACH.KIND.REQUIRED"));
		}

		this.setState(ApplicationState.GENERATING_SNAPSHOTS, String.valueOf(size));
		
		this.snapshots = ItemUtils.getAllSnapshots(this.inventory, this.twoHanded,
				this.charClass.getName().equalsIgnoreCase("ranger"));

		// Go through extra state to populate the label with the snapshot list
		// size
		this.setState(ApplicationState.GENERATED_SNAPSHOTS, String.valueOf(size));
		this.setState(ApplicationState.PREPROCESSING, this.charClass.getMessage());

		int i = 0;
		for (Iterator<CharacterSnapshot> iterator = snapshots.iterator(); iterator.hasNext();) {
			CharacterSnapshot cs = iterator.next();
			cs.clean();
			// Process all the items
			cs.processModifiers();
			// process sets if any
			cs.processSets();
			++i;
			if (null != this.progressListener && i%10 == 0) {
				progressListener.progressChanged(i);
			}
		}
		this.setState(ApplicationState.PREPROCESSED);
	}

	public final int calculateSize() {
		boolean isRanger = this.charClass.getName().equalsIgnoreCase("ranger");
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
