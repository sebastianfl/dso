package org.seb.dso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.seb.dso.model.Item;

/**
 * @author Sebastian
 *
 */
public class Inventory {
	/**
	 * All amulets provided by user
	 */
	private Collection<Item> amulets;
	private Collection<Item> belts;
	private Collection<Item> cloaks;
	private Collection<Item> rings;
	private Collection<Item> crystals;
	private Collection<Item> mainhands;
	private Collection<Item> twohands;
	private Collection<Item> offhands;

	private Collection<Item> helmets;
	private Collection<Item> pauldrons;
	private Collection<Item> torsos;
	private Collection<Item> gloves;
	private Collection<Item> boots;

	private List<Collection<Item>> itemMatrix;

	public synchronized List<Collection<Item>> getItemMatrix() {
		return itemMatrix;
	}

	public synchronized void setItemMatrix(List<Collection<Item>> itemMatrix) {
		this.itemMatrix = itemMatrix;
	}

	/**
	 * Compiles collection of collections of items for either two handed or one
	 * handed builds. This martix will replace the list of all item combinations
	 * for better performance
	 * 
	 * @param isTwohanded
	 *            boolean - determines whether user wants to use 2 handed or 1
	 *            handed setups
	 */
	public void setupItemMatrix(boolean isTwohanded) {
		this.itemMatrix = new ArrayList<Collection<Item>>();
		this.itemMatrix.add(this.amulets);
		this.itemMatrix.add(this.cloaks);
		this.itemMatrix.add(this.belts);
		this.itemMatrix.add(this.rings);
		this.itemMatrix.add(this.rings);
		this.itemMatrix.add(this.crystals);
		this.itemMatrix.add(this.helmets);
		this.itemMatrix.add(this.pauldrons);
		this.itemMatrix.add(this.torsos);
		this.itemMatrix.add(this.gloves);
		this.itemMatrix.add(this.boots);
		if (isTwohanded) {
			this.itemMatrix.add(this.twohands);
		} else {
			this.itemMatrix.add(this.mainhands);
			this.itemMatrix.add(this.offhands);
		}

	}

	public Collection<Item> getAmulets() {
		return amulets;
	}

	public void setAmulets(Collection<Item> amulets) {
		this.amulets = amulets;
	}

	public Collection<Item> getBelts() {
		return belts;
	}

	public void setBelts(Collection<Item> belts) {
		this.belts = belts;
	}

	public Collection<Item> getCloaks() {
		return cloaks;
	}

	public void setCloaks(Collection<Item> cloaks) {
		this.cloaks = cloaks;
	}

	public Collection<Item> getRings() {
		return rings;
	}

	public void setRings(Collection<Item> rings) {
		this.rings = rings;
	}

	public Collection<Item> getCrystals() {
		return crystals;
	}

	public void setCrystals(Collection<Item> crystals) {
		this.crystals = crystals;
	}

	public Collection<Item> getMainhands() {
		return mainhands;
	}

	public void setMainhands(Collection<Item> mainhands) {
		this.mainhands = mainhands;
	}

	public Collection<Item> getTwohands() {
		return twohands;
	}

	public void setTwohands(Collection<Item> twohands) {
		this.twohands = twohands;
	}

	public Collection<Item> getOffhands() {
		return offhands;
	}

	public void setOffhands(Collection<Item> offhands) {
		this.offhands = offhands;
	}

	public Collection<Item> getHelmets() {
		return helmets;
	}

	public void setHelmets(Collection<Item> helmets) {
		this.helmets = helmets;
	}

	public Collection<Item> getPauldrons() {
		return pauldrons;
	}

	public void setPauldrons(Collection<Item> pauldrons) {
		this.pauldrons = pauldrons;
	}

	public Collection<Item> getTorsos() {
		return torsos;
	}

	public void setTorsos(Collection<Item> torsos) {
		this.torsos = torsos;
	}

	public Collection<Item> getGloves() {
		return gloves;
	}

	public void setGloves(Collection<Item> gloves) {
		this.gloves = gloves;
	}

	public Collection<Item> getBoots() {
		return boots;
	}

	public void setBoots(Collection<Item> boots) {
		this.boots = boots;
	}

	public Inventory() {
		super();
		this.rings = new ArrayList<Item>();
		this.amulets = new ArrayList<Item>();
		this.belts = new ArrayList<Item>();
		this.crystals = new ArrayList<Item>();
		this.mainhands = new ArrayList<Item>();
		this.twohands = new ArrayList<Item>();
		this.offhands = new ArrayList<Item>();
		this.helmets = new ArrayList<Item>();
		this.torsos = new ArrayList<Item>();
		this.pauldrons = new ArrayList<Item>();
		this.gloves = new ArrayList<Item>();
		this.cloaks = new ArrayList<Item>();
		this.boots = new ArrayList<Item>();

	}

	@Override
	public String toString() {
		return "Inventory [amulets=" + amulets + ", belts=" + belts + ", cloaks=" + cloaks + ", rings=" + rings
				+ ", crystals=" + crystals + ", mainhands=" + mainhands + ", twohands=" + twohands + ", offhands="
				+ offhands + ", helmets=" + helmets + ", pauldrons=" + pauldrons + ", torsos=" + torsos + ", gloves="
				+ gloves + ", boots=" + boots + "]";
	}

}
