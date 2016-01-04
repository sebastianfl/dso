package org.seb.dso.fx.model;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.seb.dso.CharacterSnapshot;
import org.seb.dso.Inventory;
import org.seb.dso.model.CharacterPower;
import org.seb.dso.model.Item;
import org.seb.dso.model.Modifier;
import org.seb.dso.model.enumeration.CharClass;
import org.seb.dso.model.enumeration.EssenceType;
import org.seb.dso.model.enumeration.ModifierType;
import org.seb.dso.util.ItemUtils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CharacterModel {
	private static final Logger FLOGGER = Logger.getLogger(CharacterModel.class.getPackage().getName());

	// User input properties bound to UI components
	private StringProperty defensiveGemsProperty;
	private StringProperty offensiveGemsProperty;
	private StringProperty petAndBuffsProperty;
	private IntegerProperty attackProperty;
	private IntegerProperty agilityProperty;
	private BooleanProperty weaponDamageProperty;
	private BooleanProperty rageProperty;
	private BooleanProperty twohandedProperty;
	private ObjectProperty<CharClass> charClassProperty;
	private ObjectProperty<EssenceType> essenceTypeProperty;

	// The modifiers representing user inputs. Calculated properties
	private Modifier agilityModifier;

	private Modifier attackModifier;
	private Modifier essenceModifier;
	private Modifier[] rageModifiers;
	private Modifier[] weaponDmgModifiers;
	private Modifier[] offGemsModifiers;
	private Modifier[] defGemsModifiers;
	private Modifier[] petAndBuffsModifiers;

	// calculated character items and stats
	private CharacterSnapshot bestSnapshot;
	private CharacterPower bestPower;

	/**
	 * File containing items.
	 */
	private File itemsFile;
	/**
	 * List of <Item>.
	 */
	private List<Item> items;
	/**
	 * Inventory object, parsed from selected items
	 */
	private Inventory inventory;
	/**
	 * List, the database of all possible snapshots assuming selected items.
	 */
	private List<CharacterSnapshot> snapshots;

	public CharacterModel() {
		defensiveGemsProperty = new SimpleStringProperty();
		offensiveGemsProperty = new SimpleStringProperty();
		petAndBuffsProperty = new SimpleStringProperty();
		attackProperty = new SimpleIntegerProperty();
		agilityProperty = new SimpleIntegerProperty();
		weaponDamageProperty = new SimpleBooleanProperty();
		rageProperty = new SimpleBooleanProperty();
		twohandedProperty = new SimpleBooleanProperty();
		charClassProperty = new SimpleObjectProperty<CharClass>();
		essenceTypeProperty = new SimpleObjectProperty<EssenceType>();
	}

	public String getDefensiveGems() {
		return defensiveGemsProperty.get();
	}

	public void setDefensiveGems(String s) {
		defensiveGemsProperty.set(s);
	}

	public StringProperty defensiveGemsProperty() {
		return defensiveGemsProperty;
	}

	public String getOffensiveGems() {
		return offensiveGemsProperty.get();
	}

	public void setOffensiveGems(String s) {
		offensiveGemsProperty.set(s);
	}

	public StringProperty offensiveGemsProperty() {
		return offensiveGemsProperty;
	}

	public String getPetAndBuffs() {
		return petAndBuffsProperty.get();
	}

	public void setPetAndBuffs(String s) {
		petAndBuffsProperty.set(s);
	}

	public StringProperty petAndBuffsProperty() {
		return petAndBuffsProperty;
	}

	public int getAttack() {
		return attackProperty.get();
	}

	public void setAttack(int a) {
		attackProperty.set(a);
	}

	public IntegerProperty attackProperty() {
		return attackProperty;
	}

	public int getAgility() {
		return agilityProperty.get();
	}

	public void setAgility(int a) {
		agilityProperty.set(a);
	}

	public IntegerProperty agilityProperty() {
		return agilityProperty;
	}

	public boolean getWeaponDamage() {
		return weaponDamageProperty.get();
	}

	public void setWeaponDamage(boolean b) {
		weaponDamageProperty.set(b);
	}

	public BooleanProperty weaponDamageProperty() {
		return weaponDamageProperty;
	}

	public boolean getRage() {
		return rageProperty.get();
	}

	public void setRage(boolean b) {
		rageProperty.set(b);
	}

	public BooleanProperty rageProperty() {
		return rageProperty;
	}

	public boolean getTwohanded() {
		return twohandedProperty.get();
	}

	public void setTwohanded(boolean b) {
		twohandedProperty.set(b);
	}

	public BooleanProperty twohandedProperty() {
		return twohandedProperty;
	}

	public CharClass getCharClass() {
		return charClassProperty.get();
	}

	public void setCharClass(CharClass c) {
		charClassProperty.set(c);
	}

	public ObjectProperty<CharClass> charClassProperty() {
		return charClassProperty;
	}

	public EssenceType getEssenceType() {
		return this.essenceTypeProperty.get();
	}

	public void setEssenceType(EssenceType c) {
		this.essenceTypeProperty.set(c);
	}

	public ObjectProperty<EssenceType> essenceTypeProperty() {
		return this.essenceTypeProperty;
	}

	/**
	 * Lazy getter for <code>Modifier</code> representation of Agility.
	 * 
	 * @return <code>Modifier</code> representing Agility
	 */
	public Modifier getAgilityModifier() {
		synchronized (this) {
			if (null == this.agilityModifier) {
				Modifier a = new Modifier();
				a.setType(ModifierType.PATTACK_SPEED);
				a.setValue(this.getAgility() * 1.6);
				a.setAbsolute(false);
				this.agilityModifier = a;
			}
		}
		return this.agilityModifier;
	}

	/**
	 * Lazy getter for <code>Modifier</code> representation of Attack.
	 * 
	 * @return <code>Modifier</code> representing Attack
	 */
	public Modifier getAttackModifier() {
		synchronized (this) {
			if (null == this.attackModifier) {
				Modifier a = new Modifier();
				a.setType(ModifierType.PDAMAGE);
				a.setValue(this.getAttack() * 2.0);
				a.setAbsolute(false);
				this.attackModifier = a;
			}
		}

		return this.attackModifier;
	}

	public Modifier getEssenceModifier() {
		synchronized (this) {
			if (null == this.essenceModifier) {
				this.essenceModifier = this.getEssenceType().getModifier();
			}
		}
		return this.essenceModifier;
	}

	public Modifier[] getRageModifiers() {
		synchronized (this) {
			if (null == this.rageModifiers) {
				Modifier[] rage = null;
				if (this.getRage()) {
					rage = new Modifier[2];
					rage[0] = new Modifier();
					rage[1] = new Modifier();
					rage[0].setType(ModifierType.PMANA);
					rage[0].setValue(25.0);
					rage[0].setAbsolute(false);

					rage[1].setType(ModifierType.PTRAVEL_SPEED);
					rage[1].setValue(-5.0);
					rage[1].setAbsolute(false);
				}
				this.rageModifiers = rage;
			}
		}
		return this.rageModifiers;
	}

	public Modifier[] getWeaponDmgModifiers() {
		synchronized (this) {
			if (null == this.weaponDmgModifiers) {
				Modifier[] weaponDmg = null;
				if (this.getWeaponDamage()) {
					weaponDmg = new Modifier[2];
					weaponDmg[0] = new Modifier();
					weaponDmg[1] = new Modifier();
					weaponDmg[0].setType(ModifierType.PEXTRA_WEAPON_DMG);
					weaponDmg[0].setValue(50.0);
					weaponDmg[0].setAbsolute(false);
					weaponDmg[1].setType(ModifierType.PATTACK_SPEED);
					weaponDmg[1].setValue(-10.0);
					weaponDmg[1].setAbsolute(false);
				}
				this.weaponDmgModifiers = weaponDmg;
			}
		}
		return this.weaponDmgModifiers;
	}

	public Modifier[] getOffGemsModifiers() {
		synchronized (this) {
			if (null == this.offGemsModifiers) {
				// TODO Do i need to catch it here?
				try {
					this.offGemsModifiers = ItemUtils.parseModifiersFromString(this.getOffensiveGems());
				} catch (Exception e) {
					FLOGGER.warning("ERROR: Wrong Modifier: Offensive Gems");
					e.printStackTrace();
				}
			}
		}
		return this.offGemsModifiers;
	}

	public Modifier[] getDefGemsModifiers() {
		synchronized (this) {
			if (null == this.defGemsModifiers) {
				// TODO Do i need to catch it here?
				try {
					this.defGemsModifiers = ItemUtils.parseModifiersFromString(this.getDefensiveGems());
				} catch (Exception e) {
					FLOGGER.warning("ERROR: Wrong Modifier: Defensive Gems");
					e.printStackTrace();
				}
			}
		}
		return this.defGemsModifiers;
	}

	public Modifier[] getPetAndBuffsModifiers() {
		synchronized (this) {
			if (null == this.petAndBuffsModifiers) {
				// TODO Do i need to catch it here?
				try {
					this.petAndBuffsModifiers = ItemUtils.parseModifiersFromString(this.getPetAndBuffs());
				} catch (Exception e) {
					FLOGGER.warning("ERROR: Wrong Modifier: Defensive Gems");
					e.printStackTrace();
				}
			}
		}
		return this.petAndBuffsModifiers;
	}

	public CharacterSnapshot getBestSnapshot() {
		return bestSnapshot;
	}

	public void setBestSnapshot(CharacterSnapshot bestSnapshot) {
		this.bestSnapshot = bestSnapshot;
	}

	public CharacterPower getBestPower() {
		return bestPower;
	}

	public void setBestPower(CharacterPower bestPower) {
		this.bestPower = bestPower;
	}

	public File getItemsFile() {
		return itemsFile;
	}

	public void setItemsFile(File itemsFile) {
		this.itemsFile = itemsFile;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<CharacterSnapshot> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<CharacterSnapshot> snapshots) {
		this.snapshots = snapshots;
	}

	@Override
	public String toString() {
		return "CharacterModel [defensiveGemsProperty=" + defensiveGemsProperty + ", offensiveGemsProperty="
				+ offensiveGemsProperty + ", petAndBuffsProperty=" + petAndBuffsProperty + ", attackProperty="
				+ attackProperty + ", agilityProperty=" + agilityProperty + ", weaponDamageProperty="
				+ weaponDamageProperty + ", rageProperty=" + rageProperty + ", twohandedProperty=" + twohandedProperty
				+ ", charClassProperty=" + charClassProperty + ", essenceTypeProperty=" + essenceTypeProperty
				+ ", agilityModifier=" + getAgilityModifier() + ", attackModifier=" + getAttackModifier()
				+ ", essenceModifier=" + getEssenceModifier() + ", rageModifiers=" + Arrays.toString(getRageModifiers())
				+ ", weaponDmgModifiers=" + Arrays.toString(getWeaponDmgModifiers()) + ", offGemsModifiers="
				+ Arrays.toString(getOffGemsModifiers()) + ", defGemsModifiers="
				+ Arrays.toString(getDefGemsModifiers()) + ", petAndBuffsModifiers="
				+ Arrays.toString(getPetAndBuffsModifiers()) + ", bestSnapshot=" + bestSnapshot + ", bestPower="
				+ bestPower + ", itemsFile=" + itemsFile + ", items=" + items + ", inventory=" + inventory
				+ ", snapshots=" + snapshots + "]";
	}

}
