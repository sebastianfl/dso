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

/**
 * @author Sebastian
 *
 */
public class CharacterSnapshot implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CharacterPower cp = new CharacterPower();

	private Item amulet;
	private Item belt;
	private Item cloak;
	private Item ring1;
	private Item ring2;
	private Item crystal;
	private Item mainhand;
	private Item twohand;
	private Item offhand;
	private Item helmet;
	private Item pauldrons;
	private Item torso;
	private Item gloves;
	private Item boots;
	private Map<String, Integer> sets = new HashMap<String, Integer>();

	public Item getAmulet() {
		return amulet;
	}

	public void setAmulet(final Item amulet) {
		this.amulet = amulet;
	}

	public Item getBelt() {
		return belt;
	}

	public void setBelt(final Item belt) {
		this.belt = belt;
	}

	public Item getCloak() {
		return cloak;
	}

	public void setCloak(Item cloak) {
		this.cloak = cloak;
	}

	public Item getRing1() {
		return ring1;
	}

	public void setRing1(Item ring1) {
		this.ring1 = ring1;
	}

	public Item getRing2() {
		return ring2;
	}

	public void setRing2(Item ring2) {
		this.ring2 = ring2;
	}

	public Item getCrystal() {
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

	public void setMainhand(Item mainhand) {
		this.mainhand = mainhand;
	}

	public Item getTwohand() {
		return twohand;
	}

	public void setTwohand(Item twohand) {
		this.twohand = twohand;
	}

	public Item getOffhand() {
		return offhand;
	}

	public void setOffhand(Item offhand) {
		this.offhand = offhand;
	}

	public Item getHelmet() {
		return helmet;
	}

	public void setHelmet(Item helmet) {
		this.helmet = helmet;
	}

	public Item getPauldrons() {
		return pauldrons;
	}

	public void setPauldrons(Item pauldrons) {
		this.pauldrons = pauldrons;
	}

	public Item getTorso() {
		return torso;
	}

	public void setTorso(Item torso) {
		this.torso = torso;
	}

	public Item getGloves() {
		return gloves;
	}

	public void setGloves(Item gloves) {
		this.gloves = gloves;
	}

	public Item getBoots() {
		return boots;
	}

	public void setBoots(Item boots) {
		this.boots = boots;
	}

	public Map<String, Integer> getSets() {
		return sets;
	}

	public void setSets(Map<String, Integer> sets) {
		this.sets = sets;
	}

	@Override
	public String toString() {
		return "CharacterSnapshot [\namulet=" + amulet + "\nbelt=" + belt + "\ncloak=" + cloak + "\nring1=" + ring1
				+ "\nring2=" + ring2 + "\ncrystal=" + crystal + "\nmainhand=" + mainhand + "\ntwohand=" + twohand
				+ "\noffhand=" + offhand + "\nhelmet=" + helmet + "\npauldrons=" + pauldrons + "\ntorso=" + torso
				+ "\ngloves=" + gloves + "\nboots=" + boots + "\n]";
	}

	public CharacterSnapshot copy() {
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
	 * @return
	 */
	@Deprecated
	public CharacterPower getCharacterPowerCopy() {
		return new CharacterPower(this.getCp().getDmg(), this.getCp().getCrit(), this.getCp().getHp(),
				this.getCp().getArmor(), this.getCp().getResist(), this.getCp().getCd(), this.getCp().getMindmg(),
				this.getCp().getMaxdmg(), this.getCp().getPdmg(), this.getCp().getPcrit(), this.getCp().getPhp(),
				this.getCp().getParmor(), this.getCp().getPresist(), this.getCp().getPmaxdmg(),
				this.getCp().getTspeed(), this.getCp().getAspeed(), this.getCp().getWdmg(), this.getCp().getPwdmg(),
				this.getCp().getBc(), this.getCp().getBr(), this.getCp().getPwde(), this.getCp().getWmindmg(),
				this.getCp().getWmaxdmg(), this.getCp().getWaspeed());
	}

	public List<Modifier> getModifiersAsList() {
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

		if (null != this.getMainhand())
			list.addAll(this.getMainhand().getMods());
		if (null != this.getTwohand())
			list.addAll(this.getTwohand().getMods());
		if (null != this.getOffhand())
			list.addAll(this.getOffhand().getMods());

		return list;
	}

	public List<Item> getItemsAsList() {
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

		if (null != this.getMainhand())
			list.add(this.getMainhand());
		if (null != this.getTwohand())
			list.add(this.getTwohand());
		if (null != this.getOffhand())
			list.add(this.getOffhand());

		return list;
	}

	public CharacterPower getCp() {
		return cp;
	}

	public void setCp(CharacterPower cp) {
		this.cp = cp;
	}

	public void processModifiers() {
		processModifiers(this.getModifiersAsList());
	}

	public void clean() {
		this.cp = new CharacterPower();
		this.sets.clear();
	}

	/**
	 * TODO Rewrite to apply all the modifiers at once, so it could be used
	 * against the DB in a query
	 * 
	 * Processes the list of modifiers and updates the corresponding values of
	 * the Snapshot Object.
	 * 
	 * @param mods
	 */
	public void processModifiers(List<Modifier> mods) {
		for (Iterator<Modifier> iterator2 = mods.iterator(); iterator2.hasNext();) {
			Modifier modifier = (Modifier) iterator2.next();
			Double ds = modifier.getValue();
			switch (modifier.getType()) {
			case DAMAGE: {
				cp.setDmg(cp.getDmg() + ds);
				break;
			}
			case PDAMAGE: {
				cp.setPdmg(cp.getPdmg() + ds);
				break;
			}
			case MAXIMUM_DAMAGE: {
				cp.setMaxdmg(cp.getMaxdmg() + ds);
				break;
			}
			case PMAXIMUM_DAMAGE: {
				cp.setPmaxdmg(cp.getPmaxdmg() + ds);
				break;
			}
			case PCRITICAL_DAMAGE: {
				cp.setCd(cp.getCd() + ds);
				break;
			}
			case PATTACK_SPEED: {
				cp.setAspeed(cp.getAspeed() + ds);
				break;
			}
			case PWEAPON_ATTACK_SPEED: {
				cp.setWaspeed(cp.getWaspeed() + ds);
				break;
			}
			case PTRAVEL_SPEED: {
				cp.setTspeed(cp.getTspeed() + ds);
				break;
			}
			case ARMOR: {
				cp.setArmor(cp.getArmor() + ds);
				break;
			}
			case PARMOR: {
				cp.setParmor(cp.getParmor() + ds);
				break;
			}
			case HP: {
				cp.setHp(cp.getHp() + ds);
				break;
			}
			case PHP: {
				cp.setPhp(cp.getPhp() + ds);
				break;
			}
			case RESIST: {
				cp.setResist(cp.getResist() + ds);
				break;
			}
			case PRESIST: {
				cp.setPresist(cp.getPresist() + ds);
				break;
			}
			case CRITICAL_HIT: {
				cp.setCrit(cp.getCrit() + ds);
				break;
			}
			case PCRITICAL_HIT: {
				cp.setPcrit(cp.getPcrit() + ds);
				break;
			}
			case WEAPON_DAMAGE: {
				cp.setWdmg(cp.getWdmg() + ds);
				break;
			}
			case WEAPON_DAMAGE_MIN: {
				cp.setWmindmg(cp.getWmindmg() + ds);
				break;
			}
			case WEAPON_DAMAGE_MAX: {
				cp.setWmaxdmg(cp.getWmaxdmg() + ds);
				break;
			}
			case PWEAPON_DAMAGE: {
				cp.setPwdmg(cp.getPwdmg() + ds);
				break;
			}
			case PEXTRA_WEAPON_DMG: {
				cp.setPwde(cp.getPwde() + ds);
				break;
			}

			}
		}

	}

	public void processSets() throws Exception {
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
					throw new Exception("Set: " + setName + " is not properly configured. Check the property file");
				}
				Modifier[] m = map.get(sets.get(setName));
				if (null != m)
					mods.addAll(Arrays.asList(m));
			}
		}
		for (Iterator<Modifier> iterator = mods.iterator(); iterator.hasNext();) {
			Modifier modifier = (Modifier) iterator.next();
			Double ds = modifier.getValue();
			switch (modifier.getType()) {
			case DAMAGE: {
				cp.setDmg(cp.getDmg() + ds);
				break;
			}
			case PDAMAGE: {
				cp.setPdmg(cp.getPdmg() + ds);
				break;
			}
			case MAXIMUM_DAMAGE: {
				cp.setMaxdmg(cp.getMaxdmg() + ds);
				break;
			}
			case PMAXIMUM_DAMAGE: {
				cp.setPmaxdmg(cp.getPmaxdmg() + ds);
				break;
			}
			case PCRITICAL_DAMAGE: {
				cp.setCd(cp.getCd() + ds);
				break;
			}
			case PATTACK_SPEED: {
				cp.setAspeed(cp.getAspeed() + ds);
				break;
			}
			case PTRAVEL_SPEED: {
				cp.setTspeed(cp.getTspeed() + ds);
				break;
			}
			case ARMOR: {
				cp.setArmor(cp.getArmor() + ds);
				break;
			}
			case PARMOR: {
				cp.setParmor(cp.getParmor() + ds);
				break;
			}
			case HP: {
				cp.setHp(cp.getHp() + ds);
				break;
			}
			case PHP: {
				cp.setPhp(cp.getPhp() + ds);
				break;
			}
			case RESIST: {
				cp.setResist(cp.getResist() + ds);
				break;
			}
			case PRESIST: {
				cp.setPresist(cp.getPresist() + ds);
				break;
			}
			case CRITICAL_HIT: {
				cp.setCrit(cp.getCrit() + ds);
				break;
			}
			case PCRITICAL_HIT: {
				cp.setPcrit(cp.getPcrit() + ds);
				break;
			}
			case WEAPON_DAMAGE: {
				cp.setWdmg(cp.getWdmg() + ds);
				break;
			}
			case PWEAPON_DAMAGE: {
				cp.setPwdmg(cp.getPwdmg() + ds);
				break;
			}
			case PEXTRA_WEAPON_DMG: {
				cp.setPwde(cp.getPwde() + ds);
				break;
			}
			}
		}
	}
}
