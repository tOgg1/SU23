package cim.database;

import cim.models.Account;
import cim.models.Calendar;
import cim.util.CloakedIronManException;

public class Hawktest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws CloakedIronManException {
		DatabaseHandler db = new DatabaseHandler();
		Account a = db.getAccountByEmail("hakon@aamdal.com");
		Calendar c = new Calendar(a);
		db.saveCalendar(c);
		

	}

}
