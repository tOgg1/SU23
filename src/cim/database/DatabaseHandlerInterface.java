package cim.database;

public interface DatabaseHandlerInterface {
	/**
	 * This method returns all the calendars associated with the current user (to group, self)
	 * @param user
	 * @return ArrayList of calendars to the given user.
	 */
	public Calendar getCalendars(Account acc) ArrayList<Calendar>;
}
