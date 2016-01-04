package org.seb.dso.model.enumeration;

public enum ApplicationState {
	CLEAN,
	LOADING,
	LOADED,
	GENERATING_SNAPSHOTS,
	GENERATED_SNAPSHOTS,
	PREPROCESSING,
	PREPROCESSED,
	CALCULATING,
	CALCULATED,
	ERROR;
}