package org.seb.dso.model.enumeration;

import org.seb.dso.ui.Messages;

/**
 * @author sebastian_fl
 *
 */
public enum ItemType {
	AMULET,
	BELT,
	CLOAK,
	RING,
	CRYSTAL,
	MAINHAND,
	TWOHAND,
	OFFHAND,
	HELMET,
	PAULDRONS,
	TORSO,
	GLOVES,
	BOOTS;

	private final static String KEY_BASE = "UI.ITEMTYPE.";

	public String getMessage() {
		return Messages.getString(KEY_BASE + this.name().toUpperCase());
	}

	@Override
	public String toString() {
		return this.getMessage();
	}
}