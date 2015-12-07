package org.seb.dso.model;

import java.util.HashMap;
import java.util.Map;

import org.seb.dso.util.PropertyManager;

public class SetConfig {

	private static SetConfig sc  = new SetConfig();
	private Map<String, Map<Integer, Modifier[]>> setMap;

	public synchronized Map<String, Map<Integer, Modifier[]>> getSetMap() {
		return setMap;
	}

	public synchronized void setSetMap(Map<String, Map<Integer, Modifier[]>> setMap) {
		this.setMap = setMap;
	}

	public SetConfig() {
		super();
		init();
	}

	public static SetConfig getSetConfig (){
		return sc;
	}
	private void init() {
		String setList = PropertyManager.getPropertyManager().getProperty("set.list");
		String[] sets = setList.split(",");
		setMap = new HashMap<String, Map<Integer, Modifier[]>>();
		for (int i = 0; i < sets.length; i++) {
			String string = sets[i];
			Map<Integer, Modifier[]> map = new HashMap<Integer, Modifier[]>();
			setMap.put(string, map);
			for (int j = 2; j < 5; j++) {
				String tmp = "set.bonus." + string + "." + j;
				String modstr = PropertyManager.getPropertyManager().getProperty(tmp);
				if (null == modstr) continue;
				String[] mods = modstr.split(",");
				Modifier[] modifiers = new Modifier[mods.length];
				for (int k = 0; k < mods.length; k++) {
					String mod = mods[k];
					String[] m = mod.split(":");
					modifiers[k] = new Modifier();
					modifiers[k].setType(m[0]);
					modifiers[k].setValue(m[1]);

				}
				map.put(j, modifiers);
			}
		}
	}

}