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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	private String type;
	private String value;
	@Override
	public String toString() {
		return type + "=" + value;
	}

}
