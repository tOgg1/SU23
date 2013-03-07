package cim.entry;

import cim.util.CloakedIronManException;
import cim.util.Settings;

public class Server {

	/**
	 * @param args
	 * @throws CloakedIronManException 
	 */
<<<<<<< HEAD
	public static void main(String[] args) throws CloakedIronManException {
=======
	public static void main(String[] args)throws Exception {
>>>>>>> e20069bc7dce03da469b7f38c07cda575c026f6e
		cim.net.Server server = new cim.net.Server(Settings.SERVER_PORT);
		server.run();
		
	}

}
