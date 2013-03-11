package cim.entry;

import cim.util.CloakedIronManException;


public class Client
{

	/**
	 * @param args
	 */	
	public static cim.net.Client instance;
	
	
	public static void main(String[] args) throws CloakedIronManException
    {
		instance = new cim.net.Client();
		instance.run();
	}

}
