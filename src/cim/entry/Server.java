package cim.entry;

import cim.util.CloakedIronManException;
import cim.util.Settings;

public class Server {

	/**
	 * @param args
	 * @throws CloakedIronManException 
	 */

	public static void main(String[] args) throws CloakedIronManException {

		cim.net2.Server server = new cim.net2.Server(Settings.SERVER_PORT);
		server.run();
		
	}
}
