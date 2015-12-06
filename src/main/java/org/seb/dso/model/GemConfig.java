package org.seb.dso.model;

import java.util.ArrayList;
import java.util.List;

import org.seb.dso.util.PropertyManager;

public class GemConfig {
	private static GemConfig gc = new GemConfig();
	private List<Modifier[]> configs = new ArrayList<Modifier[]>();

	public synchronized List<Modifier[]> getConfigs() {
		return configs;
	}

	public synchronized void setConfigs(List<Modifier[]> configs) {
		this.configs = configs;
	}

	public GemConfig() {
		super();
		init();
	}

	private void init() {
		String gemConfigList = PropertyManager.getPropertyManager().getProperty("offensive.gem.config.list");
		String[] configs = gemConfigList.split(";");
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
			this.configs.add(mods);
		}
	}

	public static GemConfig getGemConfig() {
		return gc;
	}
}
