package cim.database;

import java.sql.Time;

import cim.models.Account;
import cim.models.Appointment;
import cim.models.Calendar;
import cim.models.Meeting;
import cim.util.CloakedIronManException;
import cim.util.Helper;

public class Hawktest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws CloakedIronManException {
		DatabaseHandler db = new DatabaseHandler();
		Account a = db.getAccountByEmail("hakon@aamdal.com");
		System.out.println(db.getAllCalendarsToAccount(a));
	}

}
