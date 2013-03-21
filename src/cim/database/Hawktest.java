package cim.database;

import java.util.ArrayList;

import cim.models.Account;
import cim.models.Appointment;
import cim.models.Calendar;
import cim.models.Group;
import cim.models.Meeting;
import cim.models.MeetingResponse;
import cim.models.RejectMessage;
import cim.models.MeetingResponse.Response;
import cim.util.CloakedIronManException;
import cim.util.Helper;

public class Hawktest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws CloakedIronManException {
		DatabaseHandler db = new DatabaseHandler();

		RejectMessage rm1 = db.getRejectMessage(1);
		RejectMessage rm2 = db.getRejectMessage(3);

		System.out.println(rm1.getWhoRejected());
		System.out.println(rm2.getWhoRejected());

		System.out.println(rm1.isSeen());
		System.out.println(rm1.getMeeting());
		
		
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
