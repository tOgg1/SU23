package cim.database;

import cim.models.Account;
import cim.models.Group;


public class Hawktest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DatabaseHandlerHawk db = new DatabaseHandlerHawk();
			Account acc = db.getAccountByEmail("hakon@aamdal.com");
			Group g = db.getGroup(1);
			
			db.addGroupMember(g, acc);
			//db.saveAccount(acc);
			System.out.println(acc);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
