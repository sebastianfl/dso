package org.seb.dso;

import com.google.caliper.SimpleBenchmark;

public class CaliperTest extends SimpleBenchmark{

	public static void main(String[] args) {
		com.google.caliper.Runner.main(args);
	}

	public void timeNanoTime(int reps) {
		for (int i = 0; i < reps; i++) {
			System.nanoTime();
		}

	}

}