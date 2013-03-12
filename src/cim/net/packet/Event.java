package cim.net.packet;

import cim.models.CalendarObject;

public class Event extends Packet {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1123214312423L;
	
	public enum Type {
		ADDED,
		MODIFIED,
		DELETED
	}
	private final Type type; 
	private CalendarObject object;
	
	public Event(Type type) {
		this.type = type;
	}
	
	public Event(Type type, CalendarObject object) {
		this.type = type;
		this.object = object;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public CalendarObject getObject() {
		return this.object;
	}

}
