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
		Calendar c = db.getCalendar2(2);
		c.addAppointment(new Meeting("Møte1", Helper.getDate(2013, 3, 16), Helper.getTime(16, 00), Helper.getTime(17,00), a));
		c.addAppointment(new Meeting("Møte2", Helper.getDate(2013, 3, 16), Helper.getTime(18, 00), Helper.getTime(18,15), a));
		c.addAppointment(new Meeting("Møte3", Helper.getDate(2013, 3, 17), Helper.getTime(16, 00), Helper.getTime(17,00), a));
		db.saveCalendar(c);
	}

}
