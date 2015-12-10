package org.seb.dso.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	public Item() {
		super();
		mods = new ArrayList<Modifier>();
	}

	public boolean isSetItem() {
		return (this.itemSet != null && !this.itemSet.equals(""));
	}

	public String getItemSet() {
		return itemSet;
	}

	public void setItemSet(String itemSet) {
		this.itemSet = itemSet;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	private List<Modifier> mods;

	public synchronized List<Modifier> getMods() {
		return mods;
	}

	public synchronized void setMods(List<Modifier> mods) {
		this.mods = mods;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Iterator<Modifier> iterator = mods.iterator(); iterator.hasNext();) {
			Modifier modifier = iterator.next();
			sb.append(modifier);
			sb.append(", ");
		}
		sb.append(itemSet);
		return sb.toString();
	}

	private String itemSet;
	private String itemType;
}
