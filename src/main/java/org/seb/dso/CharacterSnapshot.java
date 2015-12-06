package org.seb.dso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seb.dso.model.CharacterPower;
import org.seb.dso.model.Item;
import org.seb.dso.model.Mod;
import org.seb.dso.model.Modifier;
import org.seb.dso.model.SetConfig;

public class CharacterSnapshot {
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
	private Modifier[] gems;

	public Item getAmulet() {
		return amulet;
	}

	public void setAmulet(Item amulet) {
		this.amulet = amulet;
	}

	public Item getBelt() {
		return belt;
	}

	public void setBelt(Item belt) {
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

	public void setCrystal(Item crystal) {
		this.crystal = crystal;
	}

	public Item getMainhand() {
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
				+ "\ngloves=" + gloves + "\nboots=" + boots + "\n]gems=" + gems[0];
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
		cs.setCp(this.getCharacterPowerCopy());
		return cs;
	}

	private CharacterPower getCharacterPowerCopy() {
		return new CharacterPower(this.getCp().getDmg(), this.getCp().getCrit(), this.getCp().getHp(),
				this.getCp().getArmor(), this.getCp().getResist(), this.getCp().getCd(), this.getCp().getMindmg(),
				this.getCp().getMaxdmg(), this.getCp().getPdmg(), this.getCp().getPcrit(), this.getCp().getPhp(),
				this.getCp().getParmor(), this.getCp().getPresist(), this.getCp().getPmaxdmg(),
				this.getCp().getTspeed(), this.getCp().getAspeed(), this.getCp().getWdmg(), this.getCp().getPwdmg(),
				this.getCp().getBc(), this.getCp().getBr(), this.getCp().getPwde());
	}

	public List<Item> getAsList() {
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

	private CharacterPower cp = new CharacterPower();

	public CharacterPower getCp() {
		return cp;
	}

	public void setCp(CharacterPower cp) {
		this.cp = cp;
	}

	public void processModifiers(List<Modifier> mods) {

		// List<Modifier> mods = item.getModifiersAsList();
		for (Iterator<Modifier> iterator2 = mods.iterator(); iterator2.hasNext();) {
			Modifier modifier = (Modifier) iterator2.next();
			String ds = modifier.getValue();
			switch (modifier.getType()) {
			case Mod.DAMAGE:
				if (!ds.contains("%")) {
					cp.setDmg(cp.getDmg() + Double.valueOf(ds));
				} else {
					cp.setPdmg(cp.getPdmg() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;
			case Mod.MAXIMUM_DAMAGE:
				if (!ds.contains("%")) {
					cp.setMaxdmg(cp.getMaxdmg() + Double.valueOf(ds));
				} else {
					cp.setPmaxdmg(cp.getPmaxdmg() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;
			case Mod.CRITICAL_DAMAGE:
				cp.setCd(cp.getCd() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				break;
			case Mod.ATTACK_SPEED:
				cp.setAspeed(cp.getAspeed() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				break;
			case Mod.TRAVEL_SPEED:
				cp.setTspeed(cp.getTspeed() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				break;
			case Mod.ARMOR:
				if (!ds.contains("%")) {
					cp.setArmor(cp.getArmor() + Double.valueOf(ds));
				} else {
					cp.setParmor(cp.getParmor() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;
			case Mod.HP:
				if (!ds.contains("%")) {
					cp.setHp(cp.getHp() + Double.valueOf(ds));
				} else {
					cp.setPhp(cp.getPhp() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;
			case Mod.RESIST:
				if (!ds.contains("%")) {
					cp.setResist(cp.getResist() + Double.valueOf(ds));
				} else {
					cp.setPresist(cp.getPresist() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;
			case Mod.CRITICAL_HIT:
				if (!ds.contains("%")) {
					cp.setCrit(cp.getCrit() + Double.valueOf(ds));
				} else {
					cp.setPcrit(cp.getPcrit() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;
			case Mod.WEAPON_DAMAGE:
				if (!ds.contains("%")) {
					cp.setWdmg(cp.getWdmg() + Double.valueOf(ds));
				} else {
					cp.setPwdmg(cp.getPwdmg() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;

			}
		}
	}

	public void processSets() {
		List<Item> list = this.getAsList();
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
				Modifier[] m = cs.getSetMap().get(setName).get(sets.get(setName));
				if (null != m)
					mods.addAll(Arrays.asList(m));
			}
		}
		for (Iterator<Modifier> iterator = mods.iterator(); iterator.hasNext();) {
			Modifier modifier = iterator.next();
			String ds = modifier.getValue();
			switch (modifier.getType()) {
			case Mod.DAMAGE:
				if (!ds.contains("%")) {
					cp.setDmg(cp.getDmg() + Double.valueOf(ds));
				} else {
					cp.setPdmg(cp.getPdmg() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;
			case Mod.MAXIMUM_DAMAGE:
				if (!ds.contains("%")) {
					cp.setMaxdmg(cp.getMaxdmg() + Double.valueOf(ds));
				} else {
					cp.setPmaxdmg(cp.getPmaxdmg() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;
			case Mod.CRITICAL_DAMAGE:
				cp.setCd(cp.getCd() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				break;
			case Mod.ATTACK_SPEED:
				cp.setAspeed(cp.getAspeed() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				break;
			case Mod.TRAVEL_SPEED:
				cp.setTspeed(cp.getTspeed() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				break;
			case Mod.ARMOR:
				if (!ds.contains("%")) {
					cp.setArmor(cp.getArmor() + Double.valueOf(ds));
				} else {
					cp.setParmor(cp.getParmor() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;
			case Mod.HP:
				if (!ds.contains("%")) {
					cp.setHp(cp.getHp() + Double.valueOf(ds));
				} else {
					cp.setPhp(cp.getPhp() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;
			case Mod.RESIST:
				if (!ds.contains("%")) {
					cp.setResist(cp.getResist() + Double.valueOf(ds));
				} else {
					cp.setPresist(cp.getPresist() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;
			case Mod.CRITICAL_HIT:
				if (!ds.contains("%")) {
					cp.setCrit(cp.getCrit() + Double.valueOf(ds));
				} else {
					cp.setPcrit(cp.getPcrit() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				}
				break;

			case Mod.EXTRA_WEAPON_DMG:
				cp.setPwde(cp.getPwde() + Double.valueOf(ds.substring(0, ds.length() - 1)));
				break;
			}

		}
	}

	public void setGems(Modifier[] mods) {
		this.gems = mods;
	}
}
