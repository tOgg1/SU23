package cim.database;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class Test {
	public static void main(String[] args) {
		DatabaseHandler db = new DatabaseHandler();

		db.deleteAppointment(322);

		
		System.out.println(db.getAllRooms());

	}
}
