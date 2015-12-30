package org.seb.dso.model;

/**
 * @author Sebastian
 *
 */
public enum ModifierCode {

	DAMAGE("d"), CRITICAL_HIT("c"), HP("h"), ARMOR("a"), RESIST("r"), RESISTANCE_COLD("rc"), RESISTANCE_FIRE("rf"),
	RESISTANCE_POISON("rp"), RESISTANCE_ANDERMAGIC("ra"), RESISTANCE_LIGHT("rl"), BLOCK_REDUCTION("br"),
	MAXIMUM_DAMAGE("dmax"), MINIMUM_DAMAGE("dmin"), WEAPON_DAMAGE("wd"), MANA("m"), CRITICAL_DAMAGE("cd"),
	TRAVEL_SPEED("ts"), ATTACK_SPEED("as"), WEAPON_ATTACK_SPEED("was"), BLOCK_STRENGTH("bs"), EXTRA_WEAPON_DMG("wde"), WEAPON_DAMAGE_MIN("wdmin"),
	WEAPON_DAMAGE_MAX("wdmax");

	private String code;

	private ModifierCode(String s) {
		this.code = s;
	}

	public String getCode() {
		return this.code;
	}

	public static ModifierCode getByCodeStr(String s) {
		for (ModifierCode c : ModifierCode.values()) {
			if (s.equals(c.code)) {
				return c;
			}
		}
		return null;
	}

}
