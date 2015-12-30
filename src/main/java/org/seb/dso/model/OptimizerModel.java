package org.seb.dso.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.seb.dso.Inventory;
import org.seb.dso.model.e.ModelChangeEvent;
import org.seb.dso.model.e.ModelChangeListener;

/**
 * @author sebastian_fl
 *
 */
public class OptimizerModel {

	public enum CharClass {
		MAGE("Mage"), DRAGONKNIGHT("DragonKnight"), RANGER("Ranger"), DWARF("Dwarf");

		private String charClassName;

		public synchronized String getCharClassName() {
			return charClassName;
		}

		public synchronized void setCharClassName(String charClassName) {
			this.charClassName = charClassName;
		}

		CharClass(String s) {
			charClassName = s;
		}
	}

	private List<ModelChangeListener> listeners = new ArrayList<ModelChangeListener>();

	private EnumTypes.State state;

	private CharClass charClass;

	private Inventory inventory;
	private Collection<Item> items;

	private boolean twoHanded;

	private Modifier agility;
	private Modifier attack;
	private Modifier essence;
	private Modifier[] rage;
	private Modifier[] weaponDmg;
	private Modifier[] offGems;
	private Modifier[] defGems;
	private Modifier[] petAndBuffs;

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
	 * @param vattack Value to set
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
