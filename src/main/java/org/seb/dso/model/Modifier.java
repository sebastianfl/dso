package org.seb.dso.model;

import java.io.Serializable;

import org.seb.dso.model.enumeration.ModifierCode;
import org.seb.dso.model.enumeration.ModifierType;

/**
 * @author Sebastian
 *
 */
public class Modifier implements Serializable {

	private static final long serialVersionUID = 1L;

	public ModifierType getType() {
		return type;
	}

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

	private ModifierType type;
	private Double value;
	// TODO Consider polymorphism
	private boolean isAbsolute = true;

	@Override
	public String toString() {
		return this.getFormattedString();
	}

	public void setType(ModifierType t) {
		this.type = t;
	}

	public void setTypeByCode(String codeStr, boolean isAbsolute) {
		ModifierCode code = ModifierCode.getByCodeStr(codeStr);
		switch (code) {
		case ARMOR: {
			if (isAbsolute) {
				this.type = ModifierType.ARMOR;
			} else {
				this.type = ModifierType.PARMOR;
			}
			break;
		}
		case DAMAGE: {
			if (isAbsolute) {
				this.type = ModifierType.DAMAGE;
			} else {
				this.type = ModifierType.PDAMAGE;
			}
			break;
		}
		case WEAPON_DAMAGE_MIN: {
			this.type = ModifierType.WEAPON_DAMAGE_MIN;
			break;
		}
		case WEAPON_DAMAGE_MAX: {
			this.type = ModifierType.WEAPON_DAMAGE_MAX;
			break;
		}
		case CRITICAL_DAMAGE: {
			this.type = ModifierType.PCRITICAL_DAMAGE;
			break;
		}
		case CRITICAL_HIT: {
			if (isAbsolute) {
				this.type = ModifierType.CRITICAL_HIT;
			} else {
				this.type = ModifierType.PCRITICAL_HIT;
			}
			break;
		}
		case WEAPON_DAMAGE: {
			if (isAbsolute) {
				this.type = ModifierType.WEAPON_DAMAGE;
			} else {
				this.type = ModifierType.PWEAPON_DAMAGE;
			}
			break;
		}
		case EXTRA_WEAPON_DMG: {
			this.type = ModifierType.PEXTRA_WEAPON_DMG;
			break;
		}
		case TRAVEL_SPEED: {
			this.type = ModifierType.PTRAVEL_SPEED;
			break;
		}
		case ATTACK_SPEED: {
			this.type = ModifierType.PATTACK_SPEED;
			break;
		}
		case WEAPON_ATTACK_SPEED: {
			this.type = ModifierType.PWEAPON_ATTACK_SPEED;
			break;
		}
		case HP: {
			if (isAbsolute) {
				this.type = ModifierType.HP;
			} else {
				this.type = ModifierType.PHP;
			}
			break;
		}
		case RESIST: {
			if (isAbsolute) {
				this.type = ModifierType.RESIST;
			} else {
				this.type = ModifierType.PRESIST;
			}
			break;
		}
		case RESISTANCE_ANDERMAGIC: {
			if (isAbsolute) {
				this.type = ModifierType.RESISTANCE_ANDERMAGIC;
			} else {
				this.type = ModifierType.PRESISTANCE_ANDERMAGIC;
			}
			break;
		}
		case RESISTANCE_COLD: {
			if (isAbsolute) {
				this.type = ModifierType.RESISTANCE_COLD;
			} else {
				this.type = ModifierType.PRESISTANCE_COLD;
			}
			break;
		}
		case RESISTANCE_FIRE: {
			if (isAbsolute) {
				this.type = ModifierType.RESISTANCE_FIRE;
			} else {
				this.type = ModifierType.PRESISTANCE_FIRE;
			}
			break;
		}
		case RESISTANCE_LIGHT: {
			if (isAbsolute) {
				this.type = ModifierType.RESISTANCE_LIGHT;
			} else {
				this.type = ModifierType.PRESISTANCE_LIGHT;
			}
			break;
		}
		case RESISTANCE_POISON: {
			if (isAbsolute) {
				this.type = ModifierType.RESISTANCE_POISON;
			} else {
				this.type = ModifierType.PRESISTANCE_POISON;
			}
			break;
		}
		case BLOCK_REDUCTION: {
			this.type = ModifierType.BLOCK_REDUCTION;
			break;
		}
		case BLOCK_STRENGTH: {
			this.type = ModifierType.PBLOCK_STRENGTH;
			break;
		}
		case MANA: {
			if (isAbsolute) {
				this.type = ModifierType.MANA;
			} else {
				this.type = ModifierType.PMANA;
			}
			break;
		}
		case MAXIMUM_DAMAGE: {
			if (isAbsolute) {
				this.type = ModifierType.MAXIMUM_DAMAGE;
			} else {
				this.type = ModifierType.PMAXIMUM_DAMAGE;
			}
			break;
		}
		case MINIMUM_DAMAGE: {
			if (isAbsolute) {
				this.type = ModifierType.MINIMUM_DAMAGE;
			} else {
				this.type = ModifierType.PMINIMUM_DAMAGE;
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
