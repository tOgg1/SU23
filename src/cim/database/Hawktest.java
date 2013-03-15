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
		Calendar c = db.getCalendar2(1);
		System.out.println(Helper.getTime(1,00));
		System.out.println(Helper.getDate(2013, 3, 15));
		//Appointment app1 = new Appointment("Min superavtale",Helper.getDate(2013, 3, 15), Helper.getTime(10, 00), Helper.getTime(11, 00), a);
		//Meeting m = new Meeting("Suprmøte",Helper.getDate(2013, 3, 15), Helper.getTime(13, 15), Helper.getTime(14, 00), a);
		//c.addAppointment(app1);
		//c.addAppointment(m);
		//db.saveCalendar(c);
		c.getAppointments().remove(0);
		for(Appointment app : c.getAppointments()) {
			System.out.println(app);
		}
		db.saveCalendar(c);
		System.out.println(c.getAllAppointmentIds());

	}

}
