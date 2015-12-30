package org.seb.dso.model;

/**
 * @author Sebastian
 *
 */
public class EnumTypes {
	public enum State {
		CLEAN, LOADING, LOADED, GENERATING_SNAPSHOTS, GENERATED_SNAPSHOTS, PREPROCESSING, PREPROCESSED, CALCULATING, CALCULATED, ERROR;
	}
}
