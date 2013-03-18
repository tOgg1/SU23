package cim.database;

import java.sql.SQLException;

import cim.models.Account;
import cim.models.Appointment;
import cim.util.CloakedIronManException;



public class Test {
	public static void main(String[] args) throws CloakedIronManException {
<<<<<<< HEAD
		DatabaseHandler db = new DatabaseHandler();
		db.cancelAppointment(db.getAppointment(323));
		
=======
		/*DatabaseHandler db = new DatabaseHandler();
		System.out.println(db.getAllCalendarsToAccount(db.getAccount(564)).size());
		*/
>>>>>>> 1b464de42e04bce70072b1c67c13ba7eade8d3f1
	}
}
