package cim.models;

import cim.net.Client;
import cim.net.packet.Request;
import cim.net.packet.Response;
import cim.util.CloakedIronManException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.ListModel;

/**
 * A register containing all information currently in use by the client.
 * Contains a lot of functions for storing and retreiving data. This class also takes cares
 * of relaying information between a client and gui.
 * The functions flagged with "P.S ONLY CALL WHEN CHANGES OCCUR IN GUI!" are flagged as such
 * because they don't send updates to the GUI. Any external occuring update adding new objects must send this update to the GUI, and will therefore
 * be given another function.
 */
public class CalendarRegister
{
	/**
	 * Reference to all calendars
	 */
	private ArrayList<Calendar> calendars;

    /**
     * Reference to personal calendars
     */
    private ArrayList<Calendar> personalCalendars;
	
	/**
	 * Reference to all active calendars
	 */
	private ArrayList<Calendar> activeCalendars;
	
	/**
	 * reference to all groups
	 */
	private ArrayList<Group> groups;
	/**
	 * Reference to all accounts
	 */
	private ArrayList<Account> accounts;
	// Trenger vi denne her?
	/**
	 * Reference to all rooms
	 */
	
	/**
	 * Reference to all Meeting responses to the current users
	 */
	private ArrayList<MeetingResponse> meetingResponses;
	
	
	private ArrayList<Room> rooms;
	Client parent;

	/**
	 * The currently logged in account.
	 */
	private Account account; 
	
	/**
	 * Property change support
	 */
	private PropertyChangeSupport pcs;
	
	public CalendarRegister(Client parent)
	{
		this.pcs = new PropertyChangeSupport(this);
		this.parent = parent;
		calendars = new ArrayList<Calendar>();
		accounts = new ArrayList<Account>();
		this.activeCalendars = new ArrayList<Calendar>();
		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		this.pcs.addPropertyChangeListener(listener);
	}
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		this.pcs.removePropertyChangeListener(listener);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Calendar> getAllCalendarsToCurrentUser(){
		try{
			Response res = parent.request(new Request("GET_ALL_CALENDARS_TO_ACCOUNT", account));
			this.personalCalendars = (ArrayList<Calendar>) res.getData()[0];
			return this.personalCalendars;
		}catch(Exception e){
			System.out.println("hello");
			return new ArrayList<Calendar>();
		}
	}

	public void initialize(Account acc) throws CloakedIronManException
	{
		this.account = acc;
	}

	/**
	 * Adds a calendar to the register and sends it to the server.
	 * P.S ONLY CALL WHEN CHANGES OCCUR IN GUI!
	 * @param cal
	 * @return
	 */
	public boolean addCalendarFromGUI(Calendar cal)
	{
		try
		{
			this.registerCalendar(cal);
			Request req = new Request("SAVE_CALENDAR", cal);
			Response res = parent.request(req);
			return true;
		}
		catch (CloakedIronManException e)
		{
			return false;
		}
	}

	/**
	 * Adds a group to the register and sends it to the server.
	 * P.S ONLY CALL WHEN CHANGES OCCUR IN GUI! I AM SUPER SERIAL
	 * @param group
	 * @return
	 */
	// Is this needed?
	public boolean addGroupFromGUI(Group group)
	{
		//TODO: Server support to add new group if this function is needed
		try
		{
			this.registerGroup(group);
			Request req = new Request("SAVE_GROUP", group);
			Response res = parent.request(req);
			return true;
		}
		catch(CloakedIronManException e)
		{
			return false;
		}
	}

	/**
	 * Adds an appointment to the register and send the parent-calendar update to the server
	 * P.S ONLY CALL WHEN CHANGES OCCUR IN GUI!
	 * @param app
	 * @param cal
	 * @return
	 */
	public boolean addAppointmentFromGUI(Appointment app, Calendar cal)
	{
		//TODO: Save appointment to cal in this function?
		try
		{
			//??cal.addAppointment(app);
			Request req = new Request("SAVE_CALENDAR", cal);
			Response res = parent.request(req);
			return true;
		}
		catch(CloakedIronManException e)
		{
			return false;
		}
	}

	/**
	 * Takes an id and returns the corresponding calendar
	 * @param calendarId
	 * @return
	 */
	public Calendar getCalendarById(int calendarId)
	{
		for(Calendar cal : this.calendars)
		{
			if(calendarId == cal.getId())
			{
				return cal;
			}
		}
		return null;
	}

	/**
	 * Returns the passed-in groups primary calendar
	 * @param group
	 * @return
	 */
	public Calendar getCalendarByGroup(Group group)
	{
		// Can a group have several calendars?
		for(Calendar cal : this.calendars)
		{
			if(cal.getOwner() == group)
			{
				return cal;
			}
		}
		return null;
	}

	/**
	 * Returns the passed-in accounts primary calendar
	 * @param acc
	 * @return
	 */
	public Calendar getCalendarByAccount(Account acc)
	{
		// Can an account have several calendars?
		for(Calendar cal : this.calendars)
		{
			if(cal.getOwner() == acc)
			{
				return cal;
			}
		}
		return null;
	}

	/**
	 * Get all group the passed-in account is a member of
	 * @param acc
	 * @return
	 */
	public ArrayList<Group> getAllAttendingGroups(Account acc)
	{
		ArrayList<Group> tempGroups = new ArrayList<Group>();
		for(Group group : this.groups)
		{
			if(group.isMember(acc))
			{
				tempGroups.add(group);
			}
		}
		return tempGroups;
	}

	/**
	 * Takes a groupId and returns the corresponding group
	 * @param groupId
	 * @return
	 */
	public Group getGroupById(int groupId)
	{
		for(Group group : this.groups)
		{
			if(groupId == group.getId())
			{
				return group;
			}
		}
		return null;
	}


	/**
	 * Takes an accountid and returns the corresponding account
	 * @param accId
	 * @return
	 */
	public Account getAccountById(int accId)
	{
		for(Account acc : this.accounts)
		{
			if(accId == acc.getId())
			{
				return acc;
			}
		}
		return null;
	}

	/**
	 * Returns all groups managed by either passed-in account (personal calendar) or by one of the accounts groups
	 * @param acc
	 * @return
	 */
	public ArrayList<Calendar> getManagedCalendars(Account acc)
	{
		ArrayList<Calendar> tempCals = new ArrayList<Calendar>();
		ArrayList<Group> tempGroups = new ArrayList<Group>();

		//Add all groups being managed by acc to tempGroups
		for(Group group : this.groups)
		{
			if(group.getOwner() == acc)
			{
				tempGroups.add(group);
			}
		}

		for(Calendar cal : this.calendars)
		{
			if(cal.getOwner() == acc)
			{
				tempCals.add(cal);
			}

			for(Group group : tempGroups)
			{
				if(cal.getOwner() == group)
				{
					tempCals.add(cal);
				}
			}
		}

		return tempCals;
	}

	/**
	 * Returns all groups owned by passed-in account
	 * @return
	 */
	public ArrayList<Group> getManagedGroups(Account acc)
	{
		ArrayList<Group> tempGroups = new ArrayList<Group>();

		for(Group group : this.groups)
		{
			if(group.getOwner() == acc)
			{
				tempGroups.add(group);
			}
		}
		return tempGroups;
	}

	/**
	 * Returns all accounts in register
	 * @return
	 */
	public ArrayList<Account> getAllAccounts()
	{
		return this.accounts;
	}


	/**
	 * Returns all accounts in register
	 * @return
	 */
	public ArrayList<Calendar> getAllCalendars() throws CloakedIronManException
	{
        try
        {
            Request req = new Request("GET_ALL_CALENDARS");
            Response res = parent.request(req);
            this.calendars = (ArrayList<Calendar>)res.getData()[0];
        }
        catch(Exception e)
        {
            throw new CloakedIronManException("Error initializing calendar register", e);
        }
        return this.calendars;
	}

	public ArrayList<Account> getAllUsers() throws CloakedIronManException
	{
		try
		{
			Request req = new Request("GET_ALL_USERS");
			Response res = parent.request(req);
			ArrayList<Account> accountList = (ArrayList<Account>)res.getData()[0];
			return accountList;
		}
		catch(Exception e)
		{
			throw new CloakedIronManException("Error getting users from database", e);
		}
	}

	public ArrayList<Group> getAllGroups()
	{
		if (this.groups == null) {
			
		}
		return this.groups;
	}

	// All functions below should only be used on
	// initialize() and when fetching new objects
	// from server

	public void registerCalendar(Calendar calendar)
	{
		if(!containsById(calendars, calendar))
			calendars.add(calendar);
	}

	public void registerGroup(Group group)
	{
		if(!containsById(groups, group))
			groups.add(group);
	}


	public void registerAccount(Account account)
	{
		if(!containsById(accounts, account))
			accounts.add(account);
	}

	public void unRegisterCalendar(Calendar calendar)
	{
		if(containsById(calendars, calendar))
			calendars.remove(calendar);
	}

	public void unRegisterGroup(Group group)
	{
		if(containsById(groups, group))
			groups.remove(group);
	}


	public void unRegisterAccount(Account account)
	{
		if(containsById(accounts, account))
			accounts.add(account);
	}

	//Checking if an arraylist consisting of a calendarobject-subclass already contains a similar calendar (same id)
	public boolean containsById(ArrayList<? extends CalendarObject> list, CalendarObject object)
	{
		for(CalendarObject listObject : list)
		{
			if(listObject.getId() == object.getId())
				return true;
		}
		return false;
	}
	/**
	 * Returns all meeting response objects to the currently logged in user
	 * @return
	 */
	
	/**
	 * Saves the given meeting response to the database.
	 * @param mr
	 * @throws CloakedIronManException
	 */
	public void saveMeetingResponse(MeetingResponse mr) throws CloakedIronManException {
		for(MeetingResponse mri : this.meetingResponses) {
			if(mri.equals(mr)) {
				if(mri.getResponse() != mr.getResponse()) {
					// Save only if changed
					this.parent.request(new Request("SAVE_MEETING_RESPONSE", mr));
				}
				break;
			}
		}
		// The meeting response is new, should be saved.
		this.parent.request(new Request("SAVE_MEETING_RESPONSE", mr));
		
	}
	public Account getAccount()
	{
		return this.account;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<MeetingResponse> getMeetingResponses() throws CloakedIronManException{
		if(this.meetingResponses == null) {
			Response r = this.parent.request(new Request("GET_MEETINGRESPONSESS_TO_ACCOUNT", this.account));
			System.out.println(r.getData()[0]);
			this.setMeetingResponses((ArrayList<MeetingResponse>)r.getData()[0]);
		}
		return this.meetingResponses;
	}
	
	public void setMeetingResponses(ArrayList<MeetingResponse> list) {
		this.meetingResponses = list;
		this.pcs.firePropertyChange("meetingResponses", null, list);
	}
	
	public void registerMeetingResponse(MeetingResponse mr) {
		this.meetingResponses.remove(mr);
		if(mr.getAccount().equals(this.account)) {
			this.meetingResponses.add(mr);
		}
		this.pcs.firePropertyChange("meetingResponses", null, this.meetingResponses);
	}
		
	public void cancelAppointment(Appointment appointment) throws CloakedIronManException{
		this.parent.request(new Request("CANCEL_APPOINTMENT", appointment));

	}

	public ArrayList<RejectMessage> getRejectMessages() {
		
		// TODO Auto-generated method stub
		return null;
	}
	
	public void saveCalendar(Calendar c) {
		
	}
	
	public void saveAlert(Alert a) {
		
	}

}
