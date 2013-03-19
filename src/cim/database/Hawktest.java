package cim.database;

import cim.models.Account;
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
		Account a = db.getAccount(1);
		Meeting m = (Meeting)db.getAppointment2(5);
		System.out.println(m);
		MeetingResponse mr = new MeetingResponse(a, m);
		mr.setResponse(Response.ATTENDING);
		db.saveMeetingResponse(mr);
		System.out.println(mr);
	}

}
