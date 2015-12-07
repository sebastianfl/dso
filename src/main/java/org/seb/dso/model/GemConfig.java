package org.seb.dso.model;

import java.util.ArrayList;
import java.util.List;

import org.seb.dso.util.PropertyManager;

public class GemConfig {
	private static GemConfig gc = new GemConfig();
	private List<Modifier[]> offensiveGems = new ArrayList<Modifier[]>();
	private List<Modifier[]> defensiveGems = new ArrayList<Modifier[]>();

	public List<Modifier[]> getDefensiveGemConfigs() {
		return defensiveGems;
	}

	public void setDefensiveGemConfigs(List<Modifier[]> defensiveGems) {
		this.defensiveGems = defensiveGems;
	}

	public synchronized List<Modifier[]> getOffensiveGemConfigs() {
		return offensiveGems;
	}

	public synchronized void setOffensiveGemConfigs(List<Modifier[]> configs) {
		this.offensiveGems = configs;
	}

	public GemConfig() {
		super();
		init();
	}

	private void init() {
		String offGemConfigList = PropertyManager.getPropertyManager().getProperty("offensive.gem.config.list");
		String defGemConfigList = PropertyManager.getPropertyManager().getProperty("defensive.gem.config.list");
		String[] configs = offGemConfigList.split(";");
		// "offensive.gem.config.list=d:75;d:64,c:185"
		for (int i = 0; i < configs.length; i++) {
			String str = configs[i];
			String[] modArr = str.split(",");
			Modifier[] mods = new Modifier[modArr.length];
			for (int j = 0; j < modArr.length; j++) {
				String jstr = modArr[j];
				String[] modstr = jstr.split(":");
				mods[j] = new Modifier();
				mods[j].setType(modstr[0]);
				mods[j].setValue(modstr[1]);
			}
			this.offensiveGems.add(mods);
		}

		configs = defGemConfigList.split(";");
		for (int i = 0; i < configs.length; i++) {
			String str = configs[i];
			String[] modArr = str.split(",");
			Modifier[] mods = new Modifier[modArr.length];
			for (int j = 0; j < modArr.length; j++) {
				String jstr = modArr[j];
				String[] modstr = jstr.split(":");
				mods[j] = new Modifier();
				mods[j].setType(modstr[0]);
				mods[j].setValue(modstr[1]);
			}
			this.defensiveGems.add(mods);
		}

	}

	public static GemConfig getGemConfig() {
		return gc;
	}
}
