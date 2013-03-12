package cim.database;

import cim.models.Account;


public class Hawktest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DatabaseHandlerHawk db = new DatabaseHandlerHawk();
			Account acc = new Account("Håkon", "Åmdal", "hakon@aamdal.com", "123");
			acc.setId(1);
			db.saveAccount(acc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
