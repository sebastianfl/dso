package org.seb.dso.model;

import java.io.Serializable;

/**
 * @author Sebastian
 *
 */
public class Modifier implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Type {
		DAMAGE, CRITICAL_HIT, HP, ARMOR, RESIST, RESISTANCE_COLD, RESISTANCE_FIRE, RESISTANCE_POISON,
		RESISTANCE_ANDERMAGIC, RESISTANCE_LIGHT, BLOCK_REDUCTION, MAXIMUM_DAMAGE, WEAPON_DAMAGE, WEAPON_DAMAGE_MAX,
		WEAPON_DAMAGE_MIN, MANA, PDAMAGE, PCRITICAL_HIT, PCRITICAL_DAMAGE, PHP, PARMOR, PRESIST, PTRAVEL_SPEED,
		PATTACK_SPEED, PWEAPON_ATTACK_SPEED, PBLOCK_STRENGTH, PRESISTANCE_COLD, PRESISTANCE_FIRE, PRESISTANCE_POISON,
		PRESISTANCE_ANDERMAGIC, PRESISTANCE_LIGHT, PMAXIMUM_DAMAGE, PWEAPON_DAMAGE, PEXTRA_WEAPON_DMG, PMANA,
		MINIMUM_DAMAGE, PMINIMUM_DAMAGE

	}

	public Type getType() {
		return type;
	}

	// public void setType(Type type) {
	// this.type = type;
	// }

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public boolean isAbsolute() {
		return isAbsolute;
	}

	public void setAbsolute(boolean isAbsolute) {
		this.isAbsolute = isAbsolute;
	}

	private Type type;
	private Double value;
	// TODO Consider polymorphism
	private boolean isAbsolute = true;

	@Override
	public String toString() {
		return this.getFormattedString();
	}

	public void setType(Type t) {
		this.type = t;
	}

	public void setTypeByCode(String codeStr, boolean isAbsolute) {
		ModifierCode code = ModifierCode.getByCodeStr(codeStr);
		switch (code) {
		case ARMOR: {
			if (isAbsolute) {
				this.type = Type.ARMOR;
			} else {
				this.type = Type.PARMOR;
			}
			break;
		}
		case DAMAGE: {
			if (isAbsolute) {
				this.type = Type.DAMAGE;
			} else {
				this.type = Type.PDAMAGE;
			}
			break;
		}
		case WEAPON_DAMAGE_MIN: {
			this.type = Type.WEAPON_DAMAGE_MIN;
			break;
		}
		case WEAPON_DAMAGE_MAX: {
			this.type = Type.WEAPON_DAMAGE_MAX;
			break;
		}
		case CRITICAL_DAMAGE: {
			this.type = Type.PCRITICAL_DAMAGE;
			break;
		}
		case CRITICAL_HIT: {
			if (isAbsolute) {
				this.type = Type.CRITICAL_HIT;
			} else {
				this.type = Type.PCRITICAL_HIT;
			}
			break;
		}
		case WEAPON_DAMAGE: {
			if (isAbsolute) {
				this.type = Type.WEAPON_DAMAGE;
			} else {
				this.type = Type.PWEAPON_DAMAGE;
			}
			break;
		}
		case EXTRA_WEAPON_DMG: {
			this.type = Type.PEXTRA_WEAPON_DMG;
			break;
		}
		case TRAVEL_SPEED: {
			this.type = Type.PTRAVEL_SPEED;
			break;
		}
		case ATTACK_SPEED: {
			this.type = Type.PATTACK_SPEED;
			break;
		}
		case WEAPON_ATTACK_SPEED: {
			this.type = Type.PWEAPON_ATTACK_SPEED;
			break;
		}
		case HP: {
			if (isAbsolute) {
				this.type = Type.HP;
			} else {
				this.type = Type.PHP;
			}
			break;
		}
		case RESIST: {
			if (isAbsolute) {
				this.type = Type.RESIST;
			} else {
				this.type = Type.PRESIST;
			}
			break;
		}
		case RESISTANCE_ANDERMAGIC: {
			if (isAbsolute) {
				this.type = Type.RESISTANCE_ANDERMAGIC;
			} else {
				this.type = Type.PRESISTANCE_ANDERMAGIC;
			}
			break;
		}
		case RESISTANCE_COLD: {
			if (isAbsolute) {
				this.type = Type.RESISTANCE_COLD;
			} else {
				this.type = Type.PRESISTANCE_COLD;
			}
			break;
		}
		case RESISTANCE_FIRE: {
			if (isAbsolute) {
				this.type = Type.RESISTANCE_FIRE;
			} else {
				this.type = Type.PRESISTANCE_FIRE;
			}
			break;
		}
		case RESISTANCE_LIGHT: {
			if (isAbsolute) {
				this.type = Type.RESISTANCE_LIGHT;
			} else {
				this.type = Type.PRESISTANCE_LIGHT;
			}
			break;
		}
		case RESISTANCE_POISON: {
			if (isAbsolute) {
				this.type = Type.RESISTANCE_POISON;
			} else {
				this.type = Type.PRESISTANCE_POISON;
			}
			break;
		}
		case BLOCK_REDUCTION: {
			this.type = Type.BLOCK_REDUCTION;
			break;
		}
		case BLOCK_STRENGTH: {
			this.type = Type.PBLOCK_STRENGTH;
			break;
		}
		case MANA: {
			if (isAbsolute) {
				this.type = Type.MANA;
			} else {
				this.type = Type.PMANA;
			}
			break;
		}
		case MAXIMUM_DAMAGE: {
			if (isAbsolute) {
				this.type = Type.MAXIMUM_DAMAGE;
			} else {
				this.type = Type.PMAXIMUM_DAMAGE;
			}
			break;
		}
		case MINIMUM_DAMAGE: {
			if (isAbsolute) {
				this.type = Type.MINIMUM_DAMAGE;
			} else {
				this.type = Type.PMINIMUM_DAMAGE;
			}
			break;
		}
		}
	}

	private String getFormattedString() {
		String s = "", a = "";
		switch (type) {
		case ARMOR:
			s = ModifierCode.ARMOR.getCode();
			break;
		case BLOCK_REDUCTION:
			s = ModifierCode.BLOCK_REDUCTION.getCode();
			break;
		case CRITICAL_HIT:
			s = ModifierCode.CRITICAL_HIT.getCode();
			break;
		case DAMAGE:
			s = ModifierCode.DAMAGE.getCode();
			break;
		case HP:
			s = ModifierCode.HP.getCode();
			break;
		case MANA:
			s = ModifierCode.MANA.getCode();
			break;
		case MAXIMUM_DAMAGE:
			s = ModifierCode.MAXIMUM_DAMAGE.getCode();
			break;
		case MINIMUM_DAMAGE:
			s = ModifierCode.MINIMUM_DAMAGE.getCode();
			break;
		case WEAPON_DAMAGE_MIN:
			s = ModifierCode.WEAPON_DAMAGE_MIN.getCode();
			break;
		case WEAPON_DAMAGE_MAX:
			s = ModifierCode.WEAPON_DAMAGE_MAX.getCode();
			break;
		case RESIST:
			s = ModifierCode.RESIST.getCode();
			break;
		case RESISTANCE_ANDERMAGIC:
			s = ModifierCode.RESISTANCE_ANDERMAGIC.getCode();
			break;
		case RESISTANCE_COLD:
			s = ModifierCode.RESISTANCE_COLD.getCode();
			break;
		case RESISTANCE_FIRE:
			s = ModifierCode.RESISTANCE_FIRE.getCode();
			break;
		case RESISTANCE_LIGHT:
			s = ModifierCode.RESISTANCE_LIGHT.getCode();
			break;
		case RESISTANCE_POISON:
			s = ModifierCode.RESISTANCE_POISON.getCode();
			break;
		case WEAPON_DAMAGE:
			s = ModifierCode.WEAPON_DAMAGE.getCode();
			break;
		case PARMOR:
			s = ModifierCode.ARMOR.getCode();
			a = "%";
			break;
		case PATTACK_SPEED:
			s = ModifierCode.ATTACK_SPEED.getCode();
			a = "%";
			break;
		case PWEAPON_ATTACK_SPEED:
			s = ModifierCode.WEAPON_ATTACK_SPEED.getCode();
			a = "%";
			break;
		case PBLOCK_STRENGTH:
			s = ModifierCode.BLOCK_STRENGTH.getCode();
			a = "%";
			break;
		case PCRITICAL_DAMAGE:
			s = ModifierCode.CRITICAL_DAMAGE.getCode();
			a = "%";
			break;
		case PCRITICAL_HIT:
			s = ModifierCode.CRITICAL_HIT.getCode();
			a = "%";
			break;
		case PDAMAGE:
			s = ModifierCode.DAMAGE.getCode();
			a = "%";
			break;
		case PEXTRA_WEAPON_DMG:
			s = ModifierCode.EXTRA_WEAPON_DMG.getCode();
			a = "%";
			break;
		case PHP:
			s = ModifierCode.HP.getCode();
			a = "%";
			break;
		case PMANA:
			s = ModifierCode.MANA.getCode();
			a = "%";
			break;
		case PMAXIMUM_DAMAGE:
			s = ModifierCode.MAXIMUM_DAMAGE.getCode();
			a = "%";
			break;
		case PMINIMUM_DAMAGE:
			s = ModifierCode.MINIMUM_DAMAGE.getCode();
			a = "%";
			break;
		case PRESIST:
			s = ModifierCode.RESIST.getCode();
			a = "%";
			break;
		case PRESISTANCE_ANDERMAGIC:
			s = ModifierCode.RESISTANCE_ANDERMAGIC.getCode();
			a = "%";
			break;
		case PRESISTANCE_COLD:
			s = ModifierCode.RESISTANCE_COLD.getCode();
			a = "%";
			break;
		case PRESISTANCE_FIRE:
			s = ModifierCode.RESISTANCE_FIRE.getCode();
			a = "%";
			break;
		case PRESISTANCE_LIGHT:
			s = ModifierCode.RESISTANCE_LIGHT.getCode();
			a = "%";
			break;
		case PRESISTANCE_POISON:
			s = ModifierCode.RESISTANCE_POISON.getCode();
			a = "%";
			break;
		case PTRAVEL_SPEED:
			s = ModifierCode.TRAVEL_SPEED.getCode();
			a = "%";
			break;
		case PWEAPON_DAMAGE:
			s = ModifierCode.WEAPON_DAMAGE.getCode();
			a = "%";
			break;
		default:
			break;
		}
		return s + ":" + value + a;
	}
}
