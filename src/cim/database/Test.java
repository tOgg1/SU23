package cim.database;

import java.sql.SQLException;

import cim.models.Appointment;
import cim.util.CloakedIronManException;



public class Test {
	public static void main(String[] args) throws CloakedIronManException {
		DatabaseHandler db = new DatabaseHandler();
		
		Appointment a = db.getAppointment(123);
		System.out.println(a);
		
	}
}
