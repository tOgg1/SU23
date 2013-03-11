package cim.entry;

import cim.util.CloakedIronManException;


public class Client
{

	/**
	 * @param args
	 */	

	public static void main(String[] args) throws CloakedIronManException
    {
		cim.net.Client client = new cim.net.Client();
		client.run();
	}

}
