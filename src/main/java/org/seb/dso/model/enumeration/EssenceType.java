package org.seb.dso.model.enumeration;

import org.seb.dso.model.Modifier;
import org.seb.dso.ui.Messages;

/**
 * @author Sebastian
 *
 */
public enum EssenceType {
	// TODO Replace by NullModifier
	NOESSENCES(ModifierType.DAMAGE, true, 0.0, "UI.ESSENCE.NOESSENCE"),
	GREEN(ModifierType.DAMAGE, true, 50.0, "UI.ESSENCE.GREEN"),
	BLUE(ModifierType.DAMAGE, false, 100.0, "UI.ESSENCE.BLUE"),
	PURPLE(ModifierType.DAMAGE, false, 200.0, "UI.ESSENCE.PURPLE"),
	RED(ModifierType.DAMAGE, false, 300.0, "UI.ESSENCE.RED");

	private String key;
	private Modifier modifier;

	public Modifier getModifier() {
		return modifier;
	}

	/**
	 * Constructor.
	 * 
	 * @param s
	 *            class name
	 * @param k
	 *            message property key
	 */
	EssenceType(final ModifierType t, final boolean abs, final double val, final String k) {
		this.modifier = new Modifier();
		this.modifier.setAbsolute(abs);
		this.modifier.setType(t);
		this.modifier.setValue(val);
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