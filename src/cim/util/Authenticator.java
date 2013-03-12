package cim.util;

import cim.database.DatabaseHandlerHawk;
import cim.models.Account;
import cim.net.Client;
import cim.net.packet.Request;
import cim.net.packet.Response;

public class Authenticator{

	private Account self;
	private final Client parent;
	
	public Authenticator(Client parent) {
		this.parent = parent;
	}
	
	public boolean isAuthenticated() {
		return (this.self != null);
	}

	public boolean authenticate(String email, String password) throws CloakedIronManException {
		Response resp = this.parent.request(new Request("AUTHENTICATE", "hakon@aamdal.com", "123"));
		this.self = (Account)resp.getData()[0];
		return this.isAuthenticated();
	}

	public Account getSelf() {
		return this.self;
	}

}
