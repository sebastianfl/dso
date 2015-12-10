package org.seb.dso.model;

import java.io.Serializable;

public class Modifier implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Type {
		DAMAGE, CRITICAL_HIT, HP, ARMOR, RESIST, RESISTANCE_COLD, RESISTANCE_FIRE, RESISTANCE_POISON,
		RESISTANCE_ANDERMAGIC, RESISTANCE_LIGHT, BLOCK_REDUCTION, MAXIMUM_DAMAGE, WEAPON_DAMAGE, MANA, PDAMAGE,
		PCRITICAL_HIT, PCRITICAL_DAMAGE, PHP, PARMOR, PRESIST, PTRAVEL_SPEED, PATTACK_SPEED, PBLOCK_STRENGTH,
		PRESISTANCE_COLD, PRESISTANCE_FIRE, PRESISTANCE_POISON, PRESISTANCE_ANDERMAGIC, PRESISTANCE_LIGHT,
		PMAXIMUM_DAMAGE, PWEAPON_DAMAGE, PEXTRA_WEAPON_DMG, PMANA, MINIMUM_DAMAGE, PMINIMUM_DAMAGE

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
		return type + "=" + value + (isAbsolute ? "" : "%");
	}

	public void setType(Type t) {
		this.type = type;
	}

	public void setTypeByCode(String codeStr, boolean isAbsolute) {
		ModifierCode code = ModifierCode.getByCodeString(codeStr);
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
}
