package cim.database;

import java.sql.SQLException;


public class Test {
	public static void main(String[] args) {
		DatabaseHandler db = new DatabaseHandler();
		
		try {
			System.out.println(db.getAppointment(123));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
