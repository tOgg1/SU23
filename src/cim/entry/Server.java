package cim.entry;

import cim.util.CloakedIronManException;
import cim.util.Settings;

public class Server {

	/**
	 * @param args
	 * @throws CloakedIronManException 
	 */

	public static void main(String[] args) throws CloakedIronManException {

		cim.net.Server server = new cim.net.Server(Settings.SERVER_EVENT_PORT, Settings.SERVER_REQUEST_PORT);
		server.run();
		
	}
}
