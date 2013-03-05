package cim.entry;

import cim.util.Settings;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		cim.application.Server server = new cim.application.Server(Settings.SERVER_PORT);
		server.run();
		
	}

}
