package org.seb.dso.model;

public enum ModifierCode {

	DAMAGE("d"), CRITICAL_HIT("c"), HP("h"), ARMOR("a"), RESIST("r"), RESISTANCE_COLD("rc"), RESISTANCE_FIRE("rf"),
	RESISTANCE_POISON("rp"), RESISTANCE_ANDERMAGIC("ra"), RESISTANCE_LIGHT("rl"), BLOCK_REDUCTION("br"),
	MAXIMUM_DAMAGE("dmax"), MINIMUM_DAMAGE("dmin"), WEAPON_DAMAGE("wd"), MANA("m"), CRITICAL_DAMAGE("cd"),
	TRAVEL_SPEED("ts"), ATTACK_SPEED("as"), BLOCK_STRENGTH("bs"), EXTRA_WEAPON_DMG("wde");

	private String code;

	private ModifierCode(String s) {
		this.code = s;
	}

	public String getCode() {
		return this.code;
	}

	public static ModifierCode getByCodeString(String s) {
		for (ModifierCode c : ModifierCode.values()) {
			if (s.equals(c.code)) {
				return c;
			}
		}
		return null;
	}

}
