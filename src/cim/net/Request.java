package cim.net;

public class Request extends Packet  {
	
	/**
	 * Cereal version
	 */
	private static final long serialVersionUID = 113647563L;
	private final String method;
	private final Object[] args;
	public Request(String method, Object... args){
		this.method = method;
		this.args = args;
	}
	
	public String getMethod() {
		return this.method;
	}
	public Object[] getArgs() {
		return this.args;
	}
	
}
