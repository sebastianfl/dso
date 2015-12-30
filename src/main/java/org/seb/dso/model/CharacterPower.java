package org.seb.dso.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.seb.dso.util.Constants;
import org.seb.dso.util.PropertyManager;

/**
 * @author Sebastian
 *
 */
public class CharacterPower implements Serializable {
	private static final long serialVersionUID = 1L;
	private double dmg = 0;
	private double crit = 0;
	private double hp = 0;
	private double armor = 0;
	private double resist = 0;
	private double cd = 0;
	private double mindmg = 0;
	private double maxdmg = 0;
	private double wmindmg = 0;
	private double wmaxdmg = 0;
	private double pdmg = 0;
	private double pcrit = 0;
	private double php = 0;
	private double parmor = 0;
	private double presist = 0;
	private double pmaxdmg = 0;
	private double tspeed = 0;
	private double aspeed = 0;
	private double waspeed = 0;
	private double wdmg = 0;
	private double pwdmg = 0;
	private double bc = 0;
	private double br = 0;
	private double pwde = 0;

	public final double getPwde() {
		return pwde;
	}

	public final void setPwde(final double v) {
		this.pwde = v;
	}

	public CharacterPower() {
		super();
	}

	public CharacterPower(double vdmg, double vcrit, double vhp, double varmor, double vresist, double vcriticaldamage,
			double mindmg2, double maxdmg2, double pdmg, double pcrit, double php, double parmor, double presist,
			double pmaxdmg, double tspeed, double aspeed, double wdmg, double pwdmg, double bc, double br, double pwde,
			double wmindmg, double wmaxdmg, double waspeed) {
		super();
		this.dmg = vdmg;
		this.crit = vcrit;
		this.hp = vhp;
		this.armor = varmor;
		this.resist = vresist;
		this.cd = vcriticaldamage;
		this.mindmg = mindmg2;
		this.maxdmg = maxdmg2;
		this.pdmg = pdmg;
		this.pcrit = pcrit;
		this.php = php;
		this.parmor = parmor;
		this.presist = presist;
		this.pmaxdmg = pmaxdmg;
		this.tspeed = tspeed;
		this.aspeed = aspeed;
		this.wdmg = wdmg;
		this.pwdmg = pwdmg;
		this.bc = bc;
		this.br = br;
		this.pwde = pwde;
		this.wmindmg = wmindmg;
		this.wmaxdmg = wmaxdmg;
		this.waspeed = waspeed;
	}

	public void append(CharacterPower cp) {
		this.dmg += cp.dmg;
		this.crit += cp.crit;
		this.hp += cp.hp;
		this.armor += cp.armor;
		this.resist += cp.resist;
		this.cd += cp.cd;
		this.mindmg += cp.mindmg;
		this.maxdmg += cp.maxdmg;
		this.pdmg += cp.pdmg;
		this.pcrit += cp.pcrit;
		this.php += cp.php;
		this.parmor += cp.parmor;
		this.presist += cp.presist;
		this.pmaxdmg += cp.pmaxdmg;
		this.tspeed += cp.tspeed;
		this.aspeed += cp.aspeed;
		this.wdmg += cp.wdmg;
		this.pwdmg += cp.pwdmg;
		this.bc += cp.bc;
		this.br += cp.br;
		this.pwde += cp.pwde;
		this.wmindmg += cp.wmindmg;
		this.wmaxdmg += cp.wmaxdmg;
		this.waspeed += cp.waspeed;
	}

	public final double getDmg() {
		return dmg;
	}

	public final void setDmg(final double v) {
		this.dmg = v;
	}

	public final double getWmindmg() {
		return wmindmg;
	}

	public final void setWmindmg(final double v) {
		this.wmindmg = v;
	}

	public double getWmaxdmg() {
		return wmaxdmg;
	}

	public void setWmaxdmg(double wmaxdmg) {
		this.wmaxdmg = wmaxdmg;
	}

	public double getCrit() {
		return crit;
	}

	public void setCrit(double crit) {
		this.crit = crit;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public double getArmor() {
		return armor;
	}

	public void setArmor(double armor) {
		this.armor = armor;
	}

	public double getResist() {
		return resist;
	}

	/**
	 * Just a setter. Checkstyle, gtfo
	 * 
	 * @param vresist
	 *            setter param
	 */
	public final void setResist(final double vresist) {
		this.resist = vresist;
	}

	/**
	 * Just a getter.
	 * 
	 * @return obvious
	 */
	public final double getCd() {
		return cd;
	}

	/**
	 * Setter.
	 * 
	 * @param v
	 *            double
	 */
	public final void setCd(final double v) {
		this.cd = v;
	}

	/**
	 * @return minimal damage
	 */
	public final double getMindmg() {
		return mindmg;
	}

	/**
	 * @param v
	 *            value to set
	 */
	public final void setMindmg(final double v) {
		this.mindmg = v;
	}

	public final double getMaxdmg() {
		return maxdmg;
	}

	public final void setMaxdmg(final double v) {
		this.maxdmg = v;
	}

	public double getPdmg() {
		return pdmg;
	}

	public void setPdmg(double _pdmg) {
		this.pdmg = _pdmg;
	}

	public double getPcrit() {
		return pcrit;
	}

	public void setPcrit(double _pcrit) {
		this.pcrit = _pcrit;
	}

	public double getPhp() {
		return php;
	}

	public void setPhp(double _php) {
		this.php = _php;
	}

	public double getParmor() {
		return parmor;
	}

	public void setParmor(double _parmor) {
		this.parmor = _parmor;
	}

	public double getPresist() {
		return presist;
	}

	public void setPresist(double _presist) {
		this.presist = _presist;
	}

	public double getPmaxdmg() {
		return pmaxdmg;
	}

	public void setPmaxdmg(double _pmaxdmg) {
		this.pmaxdmg = _pmaxdmg;
	}

	public double getTspeed() {
		return tspeed;
	}

	public void setTspeed(double _tspeed) {
		this.tspeed = _tspeed;
	}

	public double getAspeed() {
		return aspeed;
	}

	public void setAspeed(double _aspeed) {
		this.aspeed = _aspeed;
	}

	public double getWaspeed() {
		return waspeed;
	}

	public void setWaspeed(double _waspeed) {
		this.waspeed = _waspeed;
	}

	public double getPwdmg() {
		return pwdmg;
	}

	public void setPwdmg(double _pwdmg) {
		this.pwdmg = _pwdmg;
	}

	public double getBc() {
		return bc;
	}

	public void setBc(double _bc) {
		this.bc = _bc;
	}

	public double getBr() {
		return br;
	}

	public void setBr(double _br) {
		this.br = _br;
	}

	public double getWdmg() {
		return wdmg;
	}

	public void setWdmg(double wdmg) {
		this.wdmg = wdmg;
	}

	public double calculateMinDamage() {
		double wmin = this.wmindmg;
		// Double.valueOf(PropertyManager.getPropertyManager().getProperty(Constants.TWOHAND_MIN_DAMAGE));
		wmin += this.getWdmg();
		wmin += wmin * this.getPwdmg() / 100;
		wmin += wmin * this.getPwde() / 100;
		wmin += Integer.valueOf(PropertyManager.getPropertyManager().getProperty(Constants.BASE_DAMAGE));
		wmin += this.getDmg();
		wmin += wmin * this.getPdmg() / 100;

		return wmin;
	}

	public double calculateMaxDamage() {
		// first calculate weapon dmg
		double wmax = this.wmaxdmg;
		// Double.valueOf(PropertyManager.getPropertyManager().getProperty(Constants.TWOHAND_MAX_DAMAGE));
		wmax += this.getWdmg();
		// get % from the weapon
		wmax += wmax * this.getPwdmg() / 100;
		// get % from shit like dragan set
		wmax += wmax * this.getPwde() / 100;
		// add the char dmg
		wmax += Integer.valueOf(PropertyManager.getPropertyManager().getProperty(Constants.BASE_DAMAGE));
		// add the items dmg
		wmax += this.getDmg();
		// add max dmg from items
		wmax += this.getMaxdmg();
		// add max % dmg from witch set if any
		wmax += wmax * (this.getPmaxdmg() + this.getPdmg()) / 100;

		return wmax;
	}

	public final double calculateCrit() {
		double wmax = 0;
		wmax += this.getCrit();
		wmax += wmax * this.getPcrit() / 100;

		wmax = (wmax * 100 / Double.valueOf(PropertyManager.getPropertyManager().getProperty(Constants.BASE_CRITICAL)));
		if (wmax > 80) {
			return 80;
		}
		return wmax;
	}

	public final double calculateHP() {
		double wmax = Double.valueOf(PropertyManager.getPropertyManager().getProperty(Constants.BASE_HP));
		wmax += this.getHp();
		wmax += wmax * this.getPhp() / 100;
		return wmax;
	}

	public final double calculateArmor() {
		double wmax = 0;
		wmax += this.getArmor();
		wmax += wmax * this.getParmor() / 100;
		return wmax;
	}

	public double calculateResist() {
		double wmax = 0;
		wmax += this.getResist();
		wmax += wmax * this.getPresist() / 100;

		return wmax;
	}

	public double calculateEffectiveDamage() {
		double mediandmg = (calculateMinDamage() + calculateMaxDamage()) / 2;
		double crit = calculateCrit() / 100;
		double cd = (this.getCd() / 100) + 2;

		return (1 - crit) * mediandmg + crit * cd * mediandmg;
	}

	/**
	 * TODO Rewrite to apply all the modifiers at once, so it could be used
	 * against the DB in a query
	 * 
	 * Processes the list of modifiers and updates the corresponding values of
	 * the Snapshot Object.
	 * 
	 * @param mods
	 *            list of modifiers to be processed
	 */
	public final void processModifiers(final List<Modifier> mods) {
		for (Iterator<Modifier> iterator2 = mods.iterator(); iterator2.hasNext();) {
			Modifier modifier = (Modifier) iterator2.next();
			Double ds = modifier.getValue();
			switch (modifier.getType()) {
			case DAMAGE:
				this.setDmg(this.getDmg() + ds);
				break;
			case PDAMAGE:
				this.setPdmg(this.getPdmg() + ds);
				break;
			case MAXIMUM_DAMAGE:
				this.setMaxdmg(this.getMaxdmg() + ds);
				break;
			case PMAXIMUM_DAMAGE:
				this.setPmaxdmg(this.getPmaxdmg() + ds);
				break;
			case PCRITICAL_DAMAGE:
				this.setCd(this.getCd() + ds);
				break;
			case PATTACK_SPEED:
				this.setAspeed(this.getAspeed() + ds);
				break;
			case PWEAPON_ATTACK_SPEED:
				this.setWaspeed(this.getWaspeed() + ds);
				break;
			case PTRAVEL_SPEED:
				this.setTspeed(this.getTspeed() + ds);
				break;
			case ARMOR:
				this.setArmor(this.getArmor() + ds);
				break;
			case PARMOR:
				this.setParmor(this.getParmor() + ds);
				break;
			case HP:
				this.setHp(this.getHp() + ds);
				break;
			case PHP:
				this.setPhp(this.getPhp() + ds);
				break;
			case RESIST:
				this.setResist(this.getResist() + ds);
				break;
			case PRESIST:
				this.setPresist(this.getPresist() + ds);
				break;
			case CRITICAL_HIT:
				this.setCrit(this.getCrit() + ds);
				break;
			case PCRITICAL_HIT:
				this.setPcrit(this.getPcrit() + ds);
				break;
			case WEAPON_DAMAGE:
				this.setWdmg(this.getWdmg() + ds);
				break;
			case WEAPON_DAMAGE_MIN:
				this.setWmindmg(this.getWmindmg() + ds);
				break;
			case WEAPON_DAMAGE_MAX:
				this.setWmaxdmg(this.getWmaxdmg() + ds);
				break;
			case PWEAPON_DAMAGE:
				this.setPwdmg(this.getPwdmg() + ds);
				break;
			case PEXTRA_WEAPON_DMG:
				this.setPwde(this.getPwde() + ds);
				break;
			default:
				break;

			}
		}

	}

	@Override
	public final String toString() {
		return "CharacterPower [dmg=" + dmg + ", crit=" + crit + ", hp=" + hp + ", armor=" + armor + ", resist="
				+ resist + ", cd=" + cd + ", mindmg=" + mindmg + ", maxdmg=" + maxdmg + ", pdmg=" + pdmg + ", pcrit="
				+ pcrit + ", php=" + php + ", parmor=" + parmor + ", presist=" + presist + ", pmaxdmg=" + pmaxdmg
				+ ", tspeed=" + tspeed + ", aspeed=" + aspeed + ", wdmg=" + wdmg + ", pwdmg=" + pwdmg + ", bc=" + bc
				+ ", br=" + br + ", pwde=" + pwde + "]";
	}

}
