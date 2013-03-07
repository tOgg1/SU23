package cim.entry;

import cim.util.Settings;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		cim.net.Server server = new cim.net.Server(Settings.SERVER_PORT);
		server.run();
		
	}

}
