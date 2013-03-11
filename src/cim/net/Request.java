package cim.net;

public class Request {
	private final String method;
	private final Object[] args;
	public Request(String method, Object... args){
		this.method = method;
		this.args = args;
	}
}
