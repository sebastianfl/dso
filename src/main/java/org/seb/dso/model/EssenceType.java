package org.seb.dso.model;

import org.seb.dso.ui.Messages;

/**
 * @author Sebastian
 *
 */
public enum EssenceType {
	NOESSENCES(Modifier.Type.DAMAGE, true, 0.0, "UI.ESSENCE.NOESSENCE"),
	GREEN(Modifier.Type.DAMAGE, true, 50.0, "UI.ESSENCE.GREEN"),
	BLUE(Modifier.Type.DAMAGE, false, 100.0, "UI.ESSENCE.BLUE"),
	PURPLE(Modifier.Type.DAMAGE, false, 200.0, "UI.ESSENCE.PURPLE"),
	RED(Modifier.Type.DAMAGE, false, 300.0, "UI.ESSENCE.RED");

	private String key;
	private Modifier modifier;

	public Modifier getModifier() {
		return modifier;
	}

	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}

	/**
	 * Constructor.
	 * 
	 * @param s
	 *            class name
	 * @param k
	 *            message property key
	 */
	EssenceType(final Modifier.Type t, final boolean abs, final double val, final String k) {
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