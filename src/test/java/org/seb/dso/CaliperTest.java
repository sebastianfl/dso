package org.seb.dso;

import com.google.caliper.SimpleBenchmark;

public class CaliperTest extends SimpleBenchmark {
	// @Param
	// int size;

	public static void main(String[] args) {
		com.google.caliper.Runner.main(args);
	}

	// @Override
	// protected void setUp() {
	// for (int i = 0; i < smods.length; i++) {
	// String modText = smods[i];
	//
	// String[] vals = modText.split(":");
	// Modifier mod = new Modifier();
	//
	// if (vals[1].contains("%")) {
	// mod.setType(vals[0] + "%");
	// mod.setValue(Double.valueOf(vals[1].substring(0, vals[1].length() - 1)));
	// } else {
	// mod.setType(vals[0]);
	// mod.setValue(Double.valueOf(vals[1]));
	// }
	// list2.add(mod);
	// }
	// }
	//
	// public void timeNanoTime(int reps) {
	// for (int i = 0; i < reps; i++) {
	// System.nanoTime();
	// }
	//
	// }

	public void timeList1(int reps) {
		// Collection<Modifier> list1 = new ArrayList<Modifier>();
		// String str = "c:12,c:12%,d:23,d:25%,d:12,d:11,d:15%,a:100,a:12%";
		// String str = "c:12";

	}

}