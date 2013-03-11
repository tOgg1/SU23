package cim.net;

import java.io.Serializable;

public class Request implements Serializable {
	private Bucket bucket;
	private String request; // tab separated request string4
	private Object[] args;
	
	public String getRequest() {
		return this.request;
	}
	
	public Object[] getArgs() {
		return this.args;
	}
	
}
