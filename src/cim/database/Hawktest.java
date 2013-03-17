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
		Account a = db.getAccountByEmail("rømme@grøtesen.no");
		Calendar c = db.getCalendar2(54);
		c.addAppointment(new Meeting("M�te1", Helper.getDate(2013, 3, 16), Helper.getTime(16, 00), Helper.getTime(17,00), a));
		c.addAppointment(new Meeting("M�te2", Helper.getDate(2013, 3, 16), Helper.getTime(18, 00), Helper.getTime(18,15), a));
		c.addAppointment(new Meeting("M�te3", Helper.getDate(2013, 3, 17), Helper.getTime(16, 00), Helper.getTime(17,00), a));
		db.saveCalendar(c);
	}

}
