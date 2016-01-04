package org.seb.dso.model;

import org.seb.dso.ui.Messages;

/**
 * @author Sebastian
 *
 */
public enum CharClass {
	MAGE("Mage", "UI.CLASS.NAME.MAGE"), DRAGONKNIGHT("DragonKnight", "UI.CLASS.NAME.DRAGONKNIGHT"),
	RANGER("Ranger", "UI.CLASS.NAME.RANGER"), DWARF("Dwarf", "UI.CLASS.NAME.DWARF");

	String name;
	private String key;

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(final String charClassName) {
		this.name = charClassName;
	}

	/**
	 * Constructor.
	 * 
	 * @param s
	 *            class name
	 * @param k
	 *            message property key
	 */
	CharClass(final String s, final String k) {
		this.name = s;
		this.key = k;
	}

	public String getMessage() {
		return Messages.getString(key);
	}

	@Override
	public String toString() {
		return this.getMessage();
	}
}