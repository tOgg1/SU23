package cim.database;

import cim.models.Account;


public class Hawktest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DatabaseHandlerHawk db = new DatabaseHandlerHawk();
			Account acc = db.getAccountByEmail("hakon@aamdal.com");
			
			//db.saveAccount(acc);
			System.out.println(acc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
