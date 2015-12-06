package org.seb.dso.model;

import org.seb.dso.util.PropertyManager;

public class CharacterPower {
	private double dmg = 0;
	private double crit = 0;
	private double hp = 0;
	private double armor = 0;
	private double resist = 0;
	private double cd = 0;
	private double mindmg = 0;
	private double maxdmg = 0;
	private double pdmg = 0;
	private double pcrit = 0;
	private double php = 0;
	private double parmor = 0;
	private double presist = 0;
	private double pmaxdmg = 0;
	private double tspeed = 0;
	private double aspeed = 0;
	private double wdmg = 0;
	private double pwdmg = 0;
	private double bc = 0;
	private double br = 0;
	private double pwde = 0;

	public double getPwde() {
		return pwde;
	}

	public void setPwde(double wde) {
		this.pwde = wde;
	}

	public CharacterPower() {
		super();
	}

	public CharacterPower(double dmg, double crit, double hp, double armor, double resist, double cd, double mindmg2,
			double maxdmg2, double pdmg, double pcrit, double php, double parmor, double presist, double pmaxdmg,
			double tspeed, double aspeed, double wdmg, double pwdmg, double bc, double br, double pwde) {
		super();
		this.dmg = dmg;
		this.crit = crit;
		this.hp = hp;
		this.armor = armor;
		this.resist = resist;
		this.cd = cd;
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
	}

	public double getDmg() {
		return dmg;
	}

	public void setDmg(double dmg) {
		this.dmg = dmg;
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

	public void setResist(double resist) {
		this.resist = resist;
	}

	public double getCd() {
		return cd;
	}

	public void setCd(double cd) {
		this.cd = cd;
	}

	public double getMindmg() {
		return mindmg;
	}

	public void setMindmg(double mindmg) {
		this.mindmg = mindmg;
	}

	public double getMaxdmg() {
		return maxdmg;
	}

	public void setMaxdmg(double maxdmg) {
		this.maxdmg = maxdmg;
	}

	public double getPdmg() {
		return pdmg;
	}

	public void setPdmg(double pdmg) {
		this.pdmg = pdmg;
	}

	public double getPcrit() {
		return pcrit;
	}

	public void setPcrit(double pcrit) {
		this.pcrit = pcrit;
	}

	public double getPhp() {
		return php;
	}

	public void setPhp(double php) {
		this.php = php;
	}

	public double getParmor() {
		return parmor;
	}

	public void setParmor(double parmor) {
		this.parmor = parmor;
	}

	public double getPresist() {
		return presist;
	}

	public void setPresist(double presist) {
		this.presist = presist;
	}

	public double getPmaxdmg() {
		return pmaxdmg;
	}

	public void setPmaxdmg(double pmaxdmg) {
		this.pmaxdmg = pmaxdmg;
	}

	public double getTspeed() {
		return tspeed;
	}

	public void setTspeed(double tspeed) {
		this.tspeed = tspeed;
	}

	public double getAspeed() {
		return aspeed;
	}

	public void setAspeed(double aspeed) {
		this.aspeed = aspeed;
	}

	public double getPwdmg() {
		return pwdmg;
	}

	public void setPwdmg(double pwdmg) {
		this.pwdmg = pwdmg;
	}

	public double getBc() {
		return bc;
	}

	public void setBc(double bc) {
		this.bc = bc;
	}

	public double getBr() {
		return br;
	}

	public void setBr(double br) {
		this.br = br;
	}

	public double getWdmg() {
		return wdmg;
	}

	public void setWdmg(double wdmg) {
		this.wdmg = wdmg;
	}

	public double calculateMinDamage() {
		double wmin = PropertyManager.TWOHAND_MIN_DMG;
		wmin += this.getWdmg();
		wmin += wmin * this.getPwdmg() / 100;
		wmin += wmin * this.getPwde() / 100;
		wmin += PropertyManager.BASE_DMG;
		wmin += this.getDmg();
		wmin += wmin * this.getPdmg() / 100;

		return wmin;
	}

	public double calculateMaxDamage() {
		//first calculate weapon dmg
		double wmax = PropertyManager.TWOHAND_MAX_DMG;
		wmax += this.getWdmg();
		// get % from the weapon
		wmax += wmax * this.getPwdmg() / 100;
		// get % from shit like dragan set
		wmax += wmax * this.getPwde() / 100;
		// add the char dmg
		wmax += PropertyManager.BASE_DMG;
		// add the items dmg
		wmax += this.getDmg();
		// add max dmg from items
		wmax += this.getMaxdmg();
		// add max % dmg from witch set if any
		wmax += wmax * this.getPmaxdmg() / 100;
		// add % total dmg
		wmax += wmax * this.getPdmg() / 100;

		return wmax;
	}

	public double calculateCrit() {
		double wmax = 0;
		wmax += this.getCrit();
		wmax += wmax * this.getPcrit() / 100;

		wmax = (wmax * 100 / PropertyManager.BASE_CRITICAL44);
		if (wmax > 80)
			return 80;
		return wmax;
	}

	public double calculateHP() {
		double wmax = PropertyManager.BASE_HP;
		wmax += this.getHp();
		wmax += wmax * this.getPhp() / 100;
		return wmax;
	}

	public double calculateArmor() {
		double wmax = 0;
		wmax += this.getArmor();
		wmax += PropertyManager.BASE_ARMOR_BELT;
		wmax += PropertyManager.BASE_ARMOR_CLOAK;
		wmax += PropertyManager.BASE_ARMOR_HELMET;
		wmax += PropertyManager.BASE_ARMOR_PAULDRONS;
		wmax += PropertyManager.BASE_ARMOR_TORSO;
		wmax += PropertyManager.BASE_ARMOR_GLOVES;
		wmax += PropertyManager.BASE_ARMOR_BOOTS;

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

}
