package org.seb.dso.model;

import java.io.Serializable;

public class Modifier implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public synchronized boolean isAbsolute() {
		return isAbsolute;
	}

	public synchronized void setAbsolute(boolean isAbsolute) {
		this.isAbsolute = isAbsolute;
	}

	private String type;
	private Double value;
	// TODO Consider polymorphism
	private boolean isAbsolute = true;

	@Override
	public String toString() {
		return type + "=" + value + (isAbsolute ? "" : "%");
	}

}
