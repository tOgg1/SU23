package cim.database;

import cim.models.Account;


public class Hawktest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DatabaseHandlerHawk db = new DatabaseHandlerHawk();
			Account acc = new Account("H�kon", "�mdal", "hakon@aamdal.com");
			db.saveAccount(acc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
