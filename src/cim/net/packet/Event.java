package cim.net.packet;


public class Event extends Packet {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1123214312423L;
	
	private final String method;
	private Object[] args;
	private Type type;
	
	public enum Type {
		UPDATED,
		DELETED
	}
	
	public Event(String method,Type type, Object... args){
		this.method = method;
		this.args = args;
		this.type = type;
	}
	
	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	public String getMethod() {
		return this.method;
	}
	public Object[] getArgs() {
		return this.args;
	}
	
	public Type getType() {
		return this.type;
	}

}
