package org.seb.dso.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	public Modifier getMod1() {
		return mod1;
	}

	public void setMod1(Modifier mod1) {
		this.mod1 = mod1;
	}

	public Modifier getMod2() {
		return mod2;
	}

	public void setMod2(Modifier mod2) {
		this.mod2 = mod2;
	}

	public Modifier getMod3() {
		return mod3;
	}

	public void setMod3(Modifier mod3) {
		this.mod3 = mod3;
	}

	public Modifier getMod4() {
		return mod4;
	}

	public void setMod4(Modifier mod4) {
		this.mod4 = mod4;
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

	private Modifier mod1;
	private Modifier mod2;
	private Modifier mod3;
	private Modifier mod4;
	private String itemSet;
	private String itemType;

	@Override
	public String toString() {
		return mod1 + ", " + mod2 + ", " + mod3 + ", " + mod4 + ", " + itemSet;
	}

	public List<Modifier> getModifiersAsList() {
		List<Modifier> list = new ArrayList<Modifier>();
		if (null != this.mod1)
			list.add(this.mod1);
		if (null != this.mod2)
			list.add(this.mod2);
		if (null != this.mod3)
			list.add(this.mod3);
		if (null != this.mod4)
			list.add(this.mod4);

		return list;
	}
}
