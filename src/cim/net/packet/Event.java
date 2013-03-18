package cim.net.packet;


public class Event extends Packet {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1123214312423L;
	
	private final String method;
	private Object[] args;
	public Event(String method, Object... args){
		this.method = method;
		this.args = args;
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

}
