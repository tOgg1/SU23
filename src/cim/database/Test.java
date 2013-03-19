package cim.database;

import java.util.ArrayList;

import cim.models.Room;
import cim.util.CloakedIronManException;
import cim.util.Helper;



public class Test {
	public static void main(String[] args) throws CloakedIronManException {
		DatabaseHandler db = new DatabaseHandler();
		
		ArrayList<Room> a = db.getAvailableRooms(Helper.getDate(2013, 10, 5), Helper.getTime(6, 45), Helper.getTime(9 ,55));
		System.out.println(a);
		//System.out.println(db.get);
		//db.cancelAppointment(db.getAppointment(323));

		/*DatabaseHandler db = new DatabaseHandler();
		System.out.println(db.getAllCalendarsToAccount(db.getAccount(564)).size());
		*/
	}
}
