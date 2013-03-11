package cim.database;


public class Test {
	public static void main(String[] args) {
		DatabaseHandler db = new DatabaseHandler();
		db.deleteAppointment(322);
		System.out.println(db.getAllRooms());
		db.deleteAppointment(322);
		
		System.out.println(db.getAllRooms());

		db.deleteAppointment(322);

		System.out.println(db.getAllRooms());
	}
}
