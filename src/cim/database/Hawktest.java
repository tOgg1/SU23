package cim.database;

import java.util.ArrayList;

import cim.models.Account;
import cim.models.Appointment;
import cim.models.Calendar;
import cim.models.Group;
import cim.models.Meeting;
import cim.models.MeetingResponse;
import cim.models.MeetingResponse.Response;
import cim.util.CloakedIronManException;
import cim.util.Helper;

public class Hawktest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws CloakedIronManException {
		DatabaseHandler db = new DatabaseHandler();
		
		
		Calendar c = db.getCalendar2(1);
		ArrayList<Appointment> apps = c.getAppointments();
		apps.remove(0);
		System.out.println(apps);
		db.saveCalendar(c);
		
		
		/*Meeting m = (Meeting)apps.get(4);
		System.out.println(apps);
		m.setDate(Helper.getDate(2013, 2,1));
		System.out.println(apps);
		db.saveCalendar(c);*/
		/*Meeting m = (Meeting)db.getAppointment2(6);
		ArrayList<MeetingResponse> list = db.getMeetingResponsesToMeeting(m);
		System.out.println(list);*/
		
	}

}
