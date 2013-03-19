package cim.database;

import cim.models.Account;
import cim.models.Calendar;
import cim.models.Group;
import cim.models.Meeting;
import cim.models.MeetingResponse;
import cim.models.MeetingResponse.Response;
import cim.util.CloakedIronManException;

public class Hawktest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws CloakedIronManException {
		DatabaseHandler db = new DatabaseHandler();
<<<<<<< HEAD
=======
		
		Account a = db.getAccount(6);
		
		Calendar c = db.getCalendarToAttendable(a);
		System.out.println(c.getAppointments());
		Meeting m = (Meeting)db.getAppointment2(16);
		System.out.println(m);
		c.removeAppointment(m);
		System.out.println(c.getAppointments());
		db.saveCalendar(c);
>>>>>>> 71f75574c037322a7c92df3339bd3dda2867469b
		
	}

}
