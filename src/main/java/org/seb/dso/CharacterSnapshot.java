package org.seb.dso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seb.dso.model.CharacterPower;
import org.seb.dso.model.Item;
import org.seb.dso.model.Modifier;
import org.seb.dso.model.SetConfig;
import org.seb.dso.ui.Messages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Sebastian
 *
 */
public class CharacterSnapshot implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * CharacterPower object for given snapshot.
	 */
	private CharacterPower cp = new CharacterPower();

	/**
	 * Amulet item used in the snapshot.
	 */
	private Item amulet;
	/**
	 * Belt item used in the snapshot.
	 */
	private Item belt;
	/**
	 * Cloak item used in the snapshot.
	 */
	private Item cloak;
	/**
	 * First ring item used in the snapshot.
	 */
	private Item ring1;
	/**
	 * Second ring item used in the snapshot.
	 */
	private Item ring2;
	/**
	 * Weapon adornment item used in the snapshot.
	 */
	private Item crystal;
	/**
	 * Mainhand item used in the snapshot. Might be null for 2handed setups.
	 */
	private Item mainhand;
	/**
	 * Twohand item used in the snapshot. Might be null for 1handed setups.
	 */
	private Item twohand;
	/**
	 * Offhand item used in the snapshot. Might be null for 2handed non-archer
	 * setups
	 */
	private Item offhand;
	/**
	 * Helmet item used in the snapshot.
	 */
	private Item helmet;
	/**
	 * Pauldrons item used in the snapshot.
	 */
	private Item pauldrons;
	/**
	 * Torso item used in the snapshot.
	 */
	private Item torso;
	/**
	 * Gloves item used in the snapshot.
	 */
	private Item gloves;
	/**
	 * Boots item used in the snapshot.
	 */
	private Item boots;
	/**
	 * The map of ItemSets used in the selection, if any.
	 */
	private Map<String, Integer> sets = new HashMap<String, Integer>();

	public final Item getAmulet() {
		return amulet;
	}

	public final void setAmulet(final Item vamulet) {
		this.amulet = vamulet;
	}

	public final Item getBelt() {
		return belt;
	}

	public final void setBelt(final Item vbelt) {
		this.belt = vbelt;
	}

	public final Item getCloak() {
		return cloak;
	}

	public final void setCloak(final Item vcloak) {
		this.cloak = vcloak;
	}

	public final Item getRing1() {
		return ring1;
	}

	public final void setRing1(final Item vring1) {
		this.ring1 = vring1;
	}

	public final Item getRing2() {
		return ring2;
	}

	public final void setRing2(final Item vring2) {
		this.ring2 = vring2;
	}

	public final Item getCrystal() {
		return crystal;
	}

	/**
	 * Sets the weapon adornment. Test javadoc for checkstyle
	 * 
	 * @param c
	 *            Item to set
	 */
	public final void setCrystal(final Item c) {
		this.crystal = c;
	}

	/**
	 * @return Item mainhand weapon if any
	 */
	public final Item getMainhand() {
		return mainhand;
	}

	public final void setMainhand(final Item vmainhand) {
		this.mainhand = vmainhand;
	}

	public final Item getTwohand() {
		return twohand;
	}

	public final void setTwohand(final Item vtwohand) {
		this.twohand = vtwohand;
	}

	public final Item getOffhand() {
		return offhand;
	}

	public final void setOffhand(final Item voffhand) {
		this.offhand = voffhand;
	}

	public final Item getHelmet() {
		return helmet;
	}

	public final void setHelmet(final Item vhelmet) {
		this.helmet = vhelmet;
	}

	public final Item getPauldrons() {
		return pauldrons;
	}

	public final void setPauldrons(final Item vpauldrons) {
		this.pauldrons = vpauldrons;
	}

	public final Item getTorso() {
		return torso;
	}

	public final void setTorso(final Item vtorso) {
		this.torso = vtorso;
	}

	public final Item getGloves() {
		return gloves;
	}

	public final void setGloves(final Item vgloves) {
		this.gloves = vgloves;
	}

	public final Item getBoots() {
		return boots;
	}

	public final void setBoots(final Item vboots) {
		this.boots = vboots;
	}

	public final Map<String, Integer> getSets() {
		return sets;
	}

	public final void setSets(final Map<String, Integer> vsets) {
		this.sets = vsets;
	}

	@Override
	public final String toString() {
		return "CharacterSnapshot [\namulet=" + amulet + "\nbelt=" + belt + "\ncloak=" + cloak + "\nring1=" + ring1 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ "\nring2=" + ring2 + "\ncrystal=" + crystal + "\nmainhand=" + mainhand + "\ntwohand=" + twohand //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ "\noffhand=" + offhand + "\nhelmet=" + helmet + "\npauldrons=" + pauldrons + "\ntorso=" + torso //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				+ "\ngloves=" + gloves + "\nboots=" + boots + "\n]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * @return a Clone of the CharacterSnapshot besides the embedded
	 *         CharacterPower
	 */
	public final CharacterSnapshot copy() {
		CharacterSnapshot cs = new CharacterSnapshot();
		cs.setAmulet(this.getAmulet());
		cs.setBelt(this.getBelt());
		cs.setCloak(this.getCloak());
		cs.setRing1(this.getRing1());
		cs.setRing2(this.getRing2());
		cs.setCrystal(this.getCrystal());
		cs.setMainhand(this.getMainhand());
		cs.setTwohand(this.getTwohand());
		cs.setOffhand(this.getOffhand());
		cs.setHelmet(this.getHelmet());
		cs.setPauldrons(this.getPauldrons());
		cs.setTorso(this.getTorso());
		cs.setGloves(this.getGloves());
		cs.setBoots(this.getBoots());
		// cs.setCp(this.getCharacterPowerCopy());
		return cs;
	}

	/**
	 * @return CharacterPower copy
	 */
	@Deprecated
	public final CharacterPower getCharacterPowerCopy() {
		return new CharacterPower(this.getCp().getDmg(), this.getCp().getCrit(), this.getCp().getHp(),
				this.getCp().getArmor(), this.getCp().getResist(), this.getCp().getCd(), this.getCp().getMindmg(),
				this.getCp().getMaxdmg(), this.getCp().getPdmg(), this.getCp().getPcrit(), this.getCp().getPhp(),
				this.getCp().getParmor(), this.getCp().getPresist(), this.getCp().getPmaxdmg(),
				this.getCp().getTspeed(), this.getCp().getAspeed(), this.getCp().getWdmg(), this.getCp().getPwdmg(),
				this.getCp().getBc(), this.getCp().getBr(), this.getCp().getPwde(), this.getCp().getWmindmg(),
				this.getCp().getWmaxdmg(), this.getCp().getWaspeed());
	}

	/**
	 * @return List of all modifiers of all items
	 */
	public final List<Modifier> getModifiersAsList() {
		List<Modifier> list = new ArrayList<Modifier>();
		list.addAll(this.getAmulet().getMods());
		list.addAll(this.getBelt().getMods());
		list.addAll(this.getCloak().getMods());
		list.addAll(this.getRing1().getMods());
		list.addAll(this.getRing2().getMods());
		list.addAll(this.getCrystal().getMods());
		list.addAll(this.getHelmet().getMods());
		list.addAll(this.getPauldrons().getMods());
		list.addAll(this.getTorso().getMods());
		list.addAll(this.getGloves().getMods());
		list.addAll(this.getBoots().getMods());

		if (null != this.getMainhand()) {
			list.addAll(this.getMainhand().getMods());
		}
		if (null != this.getTwohand()) {
			list.addAll(this.getTwohand().getMods());
		}
		if (null != this.getOffhand()) {
			list.addAll(this.getOffhand().getMods());
		}

		return list;
	}

	/**
	 * @return Items as List<Item>
	 */
	public final List<Item> getItemsAsList() {
		List<Item> list = new ArrayList<Item>();
		list.add(this.getAmulet());
		list.add(this.getBelt());
		list.add(this.getCloak());
		list.add(this.getRing1());
		list.add(this.getRing2());
		list.add(this.getCrystal());
		list.add(this.getHelmet());
		list.add(this.getPauldrons());
		list.add(this.getTorso());
		list.add(this.getGloves());
		list.add(this.getBoots());

		if (null != this.getMainhand()) {
			list.add(this.getMainhand());
		}
		if (null != this.getTwohand()) {
			list.add(this.getTwohand());
		}
		if (null != this.getOffhand()) {
			list.add(this.getOffhand());
		}

		return list;
	}
	
	public final ObservableList<Item> getItemsAsObservableList(){
		return FXCollections.observableArrayList(this.getItemsAsList());
	}

	public final CharacterPower getCp() {
		return cp;
	}

	public final void setCp(final CharacterPower vcp) {
		this.cp = vcp;
	}

	/**
	 * Processes all modifiers of this.
	 */
	public final void processModifiers() {
		this.getCp().processModifiers(this.getModifiersAsList());
	}

	/**
	 * Cleans the sets and the CharacterPower for the specified snapshot.
	 */
	public final void clean() {
		this.cp = new CharacterPower();
		this.sets.clear();
	}

	/**
	 * Processes the set bonus modifiers if any for given item combination.
	 * 
	 * @throws Exception
	 *             Might throw the Set is not configured exception
	 */
	public final void processSets() throws Exception {
		List<Item> list = this.getItemsAsList();
		List<Modifier> mods = new ArrayList<Modifier>();
		SetConfig cs = SetConfig.getSetConfig();
		for (Iterator<Item> iterator = list.iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			if (item.isSetItem()) {
				String setName = item.getItemSet();
				if (sets.containsKey(setName)) {
					sets.put(setName, sets.get(setName) + 1);
				} else {
					sets.put(setName, 1);
				}
				Map<Integer, Modifier[]> map = cs.getSetMap().get(setName);
				if (null == map) {
					throw new Exception("'" + setName + "' " + Messages.getString("UI.ERROR.SET.NOT.CONFIGURED")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				Modifier[] m = map.get(sets.get(setName));
				if (null != m) {
					mods.addAll(Arrays.asList(m));
				}
			}
		}
		for (Iterator<Modifier> iterator = mods.iterator(); iterator.hasNext();) {
			Modifier modifier = (Modifier) iterator.next();
			Double ds = modifier.getValue();
			switch (modifier.getType()) {
			case DAMAGE:
				cp.setDmg(cp.getDmg() + ds);
				break;
			case PDAMAGE:
				cp.setPdmg(cp.getPdmg() + ds);
				break;
			case MAXIMUM_DAMAGE:
				cp.setMaxdmg(cp.getMaxdmg() + ds);
				break;
			case PMAXIMUM_DAMAGE:
				cp.setPmaxdmg(cp.getPmaxdmg() + ds);
				break;
			case PCRITICAL_DAMAGE:
				cp.setCd(cp.getCd() + ds);
				break;
			case PATTACK_SPEED:
				cp.setAspeed(cp.getAspeed() + ds);
				break;
			case PTRAVEL_SPEED:
				cp.setTspeed(cp.getTspeed() + ds);
				break;
			case ARMOR:
				cp.setArmor(cp.getArmor() + ds);
				break;
			case PARMOR:
				cp.setParmor(cp.getParmor() + ds);
				break;
			case HP:
				cp.setHp(cp.getHp() + ds);
				break;
			case PHP:
				cp.setPhp(cp.getPhp() + ds);
				break;
			case RESIST:
				cp.setResist(cp.getResist() + ds);
				break;
			case PRESIST:
				cp.setPresist(cp.getPresist() + ds);
				break;
			case CRITICAL_HIT:
				cp.setCrit(cp.getCrit() + ds);
				break;
			case PCRITICAL_HIT:
				cp.setPcrit(cp.getPcrit() + ds);
				break;
			case WEAPON_DAMAGE:
				cp.setWdmg(cp.getWdmg() + ds);
				break;
			case PWEAPON_DAMAGE:
				cp.setPwdmg(cp.getPwdmg() + ds);
				break;
			case PEXTRA_WEAPON_DMG:
				cp.setPwde(cp.getPwde() + ds);
				break;
			default:
				break;
			}
		}
	}
}
