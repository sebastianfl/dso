package org.seb.dso.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seb.dso.model.enumeration.ItemType;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * TODO Replace by polymorphism. Item should encapsulate its own specific properties, e.g. Weapon should
 * know what to do with gems, Torso should be aware of special gems, etc.
 * 
 * @author Sebastian
 *
 */
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	private String itemSet;
	private ItemType itemType;
	private BooleanProperty selected;
	private List<Modifier> mods;

	public Item() {
		super();
		mods = new ArrayList<Modifier>();
		selected = new SimpleBooleanProperty();
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

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType t) {
		this.itemType = t;
	}

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

	public final BooleanProperty selected() {
		return this.selected;
	}

	public final boolean isSelected() {
		return this.selected.get();
	}

	public final void setSelected(boolean s) {
		this.selected.set(s);
	}
}
