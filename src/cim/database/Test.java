package cim.database;

import cim.util.CloakedIronManException;



public class Test {
	public static void main(String[] args) throws CloakedIronManException {
		DatabaseHandler db = new DatabaseHandler();
		db.cancelAppointment(db.getAppointment(323));

		/*DatabaseHandler db = new DatabaseHandler();
		System.out.println(db.getAllCalendarsToAccount(db.getAccount(564)).size());
		*/
	}
}
