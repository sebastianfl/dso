package org.seb.dso.model.e;

import org.seb.dso.model.EnumTypes;

/**
 * @author sebastian_fl
 *
 */
public class ModelChangeEvent {
	public enum EventType {
		STATE, CHARCLASS, MODIFIER, INVETORY;
	}

	private EventType eventType;
	private EnumTypes.State command;
	private String message;

	public synchronized String getMessage() {
		return message;
	}

	public synchronized void setMessage(String message) {
		this.message = message;
	}

	public synchronized EventType getEventType() {
		return eventType;
	}

	public synchronized void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public synchronized EnumTypes.State getCommand() {
		return command;
	}

	public synchronized void setCommand(EnumTypes.State command) {
		this.command = command;
	}

	public ModelChangeEvent(EventType type, EnumTypes.State command, String message) {
		this.eventType = type;
		this.command = command;
		this.message = message;
	}
}
