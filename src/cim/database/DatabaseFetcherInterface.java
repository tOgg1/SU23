package cim.database;
import java.util.ArrayList;

import cim.models.*;

public interface DatabaseFetcherInterface {
	/**
	 * This method returns all the calendars associated with the current user (to group, self)
	 * @param user
	 * @return ArrayList of calendars to the given user.
	 */
	//public Calendar getCalendars(Account acc) ArrayList<Calendar>;
	
	/**
	 * This method return all room objects in database
	 * @return ArrayList of all rooms
	 */
	public ArrayList<Room> getAllRooms();
	
	/**
	 * 
	 * @param email Email (primary key) to the user to find)
	 * @return the account if email found, null if not found.
	 */
	public Account getAccount(String email);
	
	
}
