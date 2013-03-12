package cim.database;


public class Test {
	public static void main(String[] args) {
		DatabaseHandler db = new DatabaseHandler();
		
		System.out.println(db.getAllCalendars(14));
		
	}
}
