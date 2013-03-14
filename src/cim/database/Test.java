package cim.database;

import java.sql.SQLException;

import cim.models.Appointment;



public class Test {
	public static void main(String[] args) {
		DatabaseHandler db = new DatabaseHandler();
		
		try {
			Appointment a = db.getAppointment(123);
			System.out.println(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
