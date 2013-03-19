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
		Account a = db.getAccount(6);
		System.out.println(db.getAlertsToAccount(a));
		
	}

}
