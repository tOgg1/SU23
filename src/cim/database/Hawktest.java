package cim.database;

import cim.models.Group;
import cim.util.CloakedIronManException;

public class Hawktest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws CloakedIronManException {
		DatabaseHandler db = new DatabaseHandler();
		Group g = db.getGroup(1);
		System.out.println(g);
		System.out.println(g.getMembers());
	}

}
