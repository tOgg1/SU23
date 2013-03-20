package cim.database;

import cim.models.*;
import cim.models.MeetingResponse.Response;
import cim.net.Server;
import cim.net.packet.Event;
import cim.net.packet.Event.Type;
import cim.util.CloakedIronManException;
import cim.util.Helper;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {

	private static String url = cim.util.PersonalSettings.JDBC_URL;
	private static String user = cim.util.PersonalSettings.MYSQL_USER;
	private static String password = cim.util.PersonalSettings.MYSQL_PW;
	private Connection con;
	private Server server;


	public DatabaseHandler(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
		}
		catch (ClassNotFoundException c)
		{
			c.printStackTrace();
		}
		catch(SQLException s)
		{
			s.printStackTrace();

		}
		catch(InstantiationException i)
		{
			i.printStackTrace();

		}
		catch(IllegalAccessException o)
		{
			o.printStackTrace();

		}
	}
	
	public void setServer(Server s) {
		this.server = s;
	}
	
	public void broadcast(String method, Type type, Object... args) {
		
		if(this.server == null) {
			System.out.println("No server attached, but broadcasting: " + method + ", " + type.toString());
			return;
		}
		Event e = new Event(method, type);
		e.setArgs(args);
		this.server.broadcast(e);
	}


	public boolean requestLogin(String email, String password){
		String sql = "SELECT password FROM account WHERE email = ";
		sql = sql + "'" + email + "'";
		ResultSet rs = executeQuery(sql);
		try {
			rs.next();
			return password.equals(rs.getString("password"));
		}catch (SQLException e) {
			return false;
		}catch (NullPointerException e){
			return false;
		}
	}

	private ResultSet executeQuery(String sql){
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
		catch (SQLException e){
			return null;
		}		
	}

	private boolean executeUpdate(String sql){
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		}
		catch (SQLException e){
			return false;
		}		
	}

    public Attendable getAttendable(int attendableId) throws CloakedIronManException
    {
        try
        {
            PreparedStatement st = this.con.prepareStatement("SELECT group_id, user_id FROM attendable WHERE attendable_id = ?");
            st.setInt(1,attendableId);
            ResultSet rs = st.executeQuery();
            if(!rs.next())
            {
                throw new CloakedIronManException("Can't find attendable_id in the database");
            }
           
            int groupId = rs.getInt("group_id");
            if (rs.wasNull()) {
            	int userId = rs.getInt("user_id");
            	rs.close();
            	return getAccount(userId);
            } else {
            	// its a gorup
            	rs.close();
            	return getGroup(groupId);
            }

            
        }
        catch(SQLException e)
        {
        	throw new CloakedIronManException("Could not execute query.", e);
        }
    }
	
	public Calendar getCalendar2(int id) throws CloakedIronManException {
		try {
			
			Calendar c;
			
			/*
			 * Calendar info and owner
			 */
			PreparedStatement st = this.con.prepareStatement("SELECT calendar_id, owner_attendable_id FROM calendar WHERE calendar_id=?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (!rs.next()) {
				throw new CloakedIronManException("Calendar ID not found in database");
			}
			int iAttendableID = rs.getInt("owner_attendable_id");
			Attendable a = this.getAttendable(iAttendableID);
			c = new Calendar(a);
			c.setId(rs.getInt("calendar_id"));
			st.close();
			rs.close();
			
			/*
			 * Fill up appointments. Cancelled meetings will also be added.
			 */
			st = this.con.prepareStatement("SELECT appointment_id FROM appointment WHERE calendar_id=?");
			st.setInt(1, c.getId());
			rs = st.executeQuery();
			while(rs.next()) {
				c.addAppointment(this.getAppointment2(rs.getInt("appointment_id")));
			}
			st.close();
			rs.close();
			/*
			 *  Add oppointments where the user has said yes 
			 */
			if(a instanceof Account) {
				
				st = this.con.prepareStatement("SELECT meeting_appointment_id FROM meeting_response WHERE account_user_id=? AND status='attending'");
				st.setInt(1, a.getId());
				rs = st.executeQuery();
				while(rs.next()) {
					c.addAppointment(this.getAppointment2(rs.getInt("meeting_appointment_id")));
				}
			}
			
			
			return c;
			
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not execute query.", e);
		}
		
		
	}
	
	public Calendar getCalendar(int calendar_id) throws CloakedIronManException{
		String sql = 
				"SELECT owner_attendable_id " +
						"FROM Calendar " +
						"WHERE calendar_id = ";
		sql += calendar_id;
		ResultSet rs = executeQuery(sql);
		try {
			rs.next();
		} catch (SQLException e2) {
			System.out.println(e2);
			return null;
		}

		Account owner;
		try {
			owner = getAccount(rs.getInt("owner_attendable_id"));
		} catch (SQLException e1) {
			System.out.println(e1);
			return null;

		}
		sql = 
				"SELECT appointment_id " +
						"FROM appointment " +
						"WHERE calendar_id = ";
		sql += calendar_id;
		rs = executeQuery(sql);
		Calendar c = new Calendar(owner);
		c.setId(calendar_id);
		try {
			while(rs.next()){
				c.addAppointment(this.getAppointment2(rs.getInt("appointment_id")));
			}
			return c;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * Returns all meeting rooms
	 * @return
	 * @throws CloakedIronManException
	 */
	public ArrayList<Room> getAllRooms() throws CloakedIronManException {
		try {
			ArrayList<Room> rooms = new ArrayList<Room>();
			PreparedStatement st = this.con.prepareStatement("SELECT meeting_room_id FROM meeting_room");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				rooms.add(this.getRoom(rs.getInt("meeting_room_id")));
			}
			return rooms;
		} catch (Exception e) {
			throw new CloakedIronManException("Could not get all rooms", e);
		}
	}
	/**
	 * Returns all calendars associated with the current account, the accounts personal calendar and the calendars from his groups
	 * @param acc
	 * @return
	 */
    public ArrayList<Calendar> getAllCalendarsToAccount(Account acc) throws CloakedIronManException
    {
    	try {
    		ArrayList<Group> groups = getAllGroupsToAccount(acc);

            ArrayList<Calendar> calendars = new ArrayList<Calendar>();
            int accCalendarId, groupCalendarId;

            PreparedStatement st = this.con.prepareStatement("SELECT calendar_id FROM calendar WHERE owner_attendable_id = ?");
            st.setInt(1, getAttendableId(acc));
            ResultSet rs = st.executeQuery();

            if(!rs.next())
            {
                throw new CloakedIronManException("Account " + acc.getId() + "  is not registered as an attendable");
            }
            accCalendarId = rs.getInt("calendar_id");
            calendars.add(getCalendar2(accCalendarId));
            rs.close();
            st.close();
            for(Group group : groups)
            {
                st = this.con.prepareStatement("SELECT calendar_id FROM calendar where owner_attendable_id = ?");
                st.setInt(1, getAttendableId(group));
                rs = st.executeQuery();

                if(!rs.next())
                {
                    throw new CloakedIronManException("Group " + group.getId() + " is not registered as an attendable");
                }

                groupCalendarId = rs.getInt("calendar_id");
                calendars.add(getCalendar2(groupCalendarId));
                st.close();
                rs.close();
            }

            return calendars;
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not process query", e);
		}
    }

    public ArrayList<RejectMessage> getAllRejectMessagesToAccount(Account acc) throws CloakedIronManException
    {
        try
        {
            ArrayList<RejectMessage> rejectMessages = new ArrayList<RejectMessage>();
            PreparedStatement st = this.con.prepareStatement("SELECT * from reject_message WHERE to_account = ?");
            st.setInt(1, acc.getId());
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                RejectMessage rMessage = new RejectMessage(acc, getAppointment2(rs.getInt("meeting_id")).toMeeting());
                rMessage.setWhoRejected(this.getAccount(rs.getInt("who_rejected")));
                rejectMessages.add(rMessage);
            }
            return rejectMessages;
        }
        catch(Exception e)
        {
            throw new CloakedIronManException("Couldn't fetch reject messages to accont " + acc.toString(), e);
        }
    }


    public ArrayList<Group> getAllGroupsToAccount(Account acc) throws CloakedIronManException
    {
        try
        {
            ArrayList<Integer> groupIds = getGroupIdsFromUserid(acc.getId());
            ArrayList<Group> groups = new ArrayList<Group>();

            for(int groupId : groupIds)
            {
                groups.add(getGroup(groupId));
            }
            return groups;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
	 * Returns all calendars in the system.
	 * @return
	 */
    public ArrayList<Calendar> getAllCalendars() throws CloakedIronManException {
        String sql = "SELECT calendar_id from calendar";
        ResultSet rs = executeQuery(sql);
        ArrayList<Calendar> returnCalendars = new ArrayList<Calendar>();
        try
        {
            while(rs.next())
            {
                returnCalendars.add(getCalendar2(rs.getInt("calendar_id")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return returnCalendars;
    }

	public boolean deleteAppointment(int appointmentId){
		String sql = "DELETE from appointment WHERE appointment_id = ";
		sql += appointmentId;
		return executeUpdate(sql);
	}
	/**
	 * Saves a calendar into the database.
	 * @param c
	 * @return The new id.
	 * @throws CloakedIronManException
	 */
	public Calendar saveCalendar(Calendar c) throws CloakedIronManException {
		try {
			if(c.getId() == -1) {
				c.setId(this.getNextAutoIncrease("calendar", "calendar_id"));
			}
			if(c.getOwner() == null) {
				throw new CloakedIronManException("Calendar owner not set");
			}
			
			int iAttendableId = this.getAttendableId(c.getOwner());

			// Create statement for the calendar
			PreparedStatement st = this.con.prepareStatement("INSERT INTO calendar " +
					"(calendar_id, owner_attendable_id)" +
					"VALUES (?,?)" +
					"ON DUPLICATE KEY UPDATE " +
					"owner_attendable_id=?");
			st.setInt(1, c.getId());
			st.setInt(2, iAttendableId);
			st.setInt(3, iAttendableId);
			st.executeUpdate();
			st.close();
			
			// Delete all appointments not in the calendars list
			
			ArrayList<Integer> ids = c.getAllAppointmentIds();
			if (ids.size() > 0) {
				String joinedString = Helper.join(ids, ",");
				st = this.con.prepareStatement("DELETE FROM appointment WHERE appointment_id NOT IN (" + joinedString + ") AND calendar_id=?");
				st.setInt(1, c.getId());
				st.executeUpdate();
			}
			st.close();
			for(Appointment a : c.getAppointments()) {
				this.saveAppointment(a, c);
			}
			this.broadcast("CALENDAR", Type.UPDATED, c);
			
			
			return c;
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not handle query.", e);
		}
	}
	
	/**
	 * Saves a meeting response to the database
	 * @param mr
	 * @return
	 * @throws CloakedIronManException
	 */
	
	public MeetingResponse saveMeetingResponse(MeetingResponse mr) throws CloakedIronManException {
        System.out.println("Saving: " + mr);
        try {
			Meeting m = mr.getMeeting();
			Account a = mr.getAccount();
			if (a == null) {
				throw new CloakedIronManException("Meeting Response account not set.");
			}
			if (m == null) {
				throw new CloakedIronManException("Meeting response meeting not set.");
			}
			if(a.getId() == -1) {
				throw new CloakedIronManException("Account not saved in database.");
			}
			if (m.getId() == -1) {
				throw new CloakedIronManException("Meeting not saved in database.");
			}
			
			PreparedStatement st = this.con.prepareStatement("INSERT INTO meeting_response (meeting_appointment_id, account_user_id, status) " +
					"VALUES (?,?,?) " +
					"ON DUPLICATE KEY UPDATE " +
					"status=?");
			st.setInt(1, m.getId());
			st.setInt(2, a.getId());
			st.setString(3, mr.getResponseString());
			st.setString(4, mr.getResponseString());
			st.execute();
			this.broadcast("MEETING_RESPONSE", Type.UPDATED, mr);
			
			
			/*
			 * If the meeting response is rejected, we need to add it to reject Message
			 */
			if(mr.getResponse() == Response.NOT_ATTENDING) {
				this.createRejectMessages(m,a);
			}
			
			/*
			 * If the meeting response is accepted, the calendar is changed
			 */
			if(mr.getResponse() == Response.ATTENDING) {
				Calendar c = this.getCalendarToAttendable(mr.getAccount());
				this.broadcast("CALENDAR", Type.UPDATED, c);
			}
			
			
			
			return mr;
		} catch (Exception e) {
			throw new CloakedIronManException("Could not save meeting response", e);
		}
		
	}
	
	
	public Calendar getCalendarToAttendable(Attendable a) throws CloakedIronManException {
		try {
			PreparedStatement st = this.con.prepareStatement("SELECT calendar_id FROM calendar WHERE owner_attendable_id=?");
			st.setInt(1, a.getAttendableId());
			ResultSet rs = st.executeQuery();
			if(!rs.next()) {
				throw new CloakedIronManException("Attendable has no calendar to it, which it must have.");
			}
			return this.getCalendar2(rs.getInt("calendar_id"));
		} catch (Exception e) {
			throw new CloakedIronManException("Could not get calendar to attendable", e);
		}
	}
	
	/**
	 * This method adds reject messages to the current meeting from the account who rejected
	 * @param m The meeting that was rejected
	 * @param a The account that rejected
	 */
	private void createRejectMessages(Meeting m, Account a) throws CloakedIronManException {
		try {
			ArrayList<Account> accounts = this.getAccountsToMeeting(m);
			for (Account accountTo : accounts) {
				if (accountTo.equals(a)) {
					continue; // No need to send message to oneself
				}
				RejectMessage rm = new RejectMessage(accountTo, m);
				rm.setWhoRejected(a);
				this.saveRejectMessage(rm);
			}
		} catch (Exception e) {
			throw new CloakedIronManException("Could not create reject messages", e);
		}
		
	}
	
	private RejectMessage saveRejectMessage(RejectMessage rm) throws CloakedIronManException {
		try {
			if(rm.getId() == -1) {
				rm.setId(this.getNextAutoIncrease("reject_message", "reject_message_id"));
			}
			if(rm.getMeeting() == null) {
				throw new CloakedIronManException("Reject Message meeting not set.");
			}
			if(rm.getMeeting().getId() == -1) {
				throw new CloakedIronManException("Reject Message meeing id not set, meeting not saved in database");
			}
			
			if(rm.getRecipient() == null) {
				throw new CloakedIronManException("Reject Message recipient not set");
			}
			
			if(rm.getRecipient().getId() == -1) {
				throw new CloakedIronManException("Reject Message recipient id not set, recipient not saved in database");
			}
			if (rm.getWhoRejected() != null) {
				if(rm.getWhoRejected().getId() == -1) {
					throw new CloakedIronManException("Who Rejected is set, but its id is not set. Save the WhoRejected account to database first.");
				}
			};
			
			PreparedStatement st = this.con.prepareStatement("INSERT INTO reject_message " +
					"(reject_message_id, date, to_account, who_rejected, meeting_id) " +
					"VALUES " +
					"(?,?,?,?,?) " +
					"ON DUPLICATE KEY UPDATE " +
					"date=?," +
					"to_account=?," +
					"who_rejected=?," +
					"meeting_id=?");
			st.setInt(1, rm.getId());
			st.setTimestamp(2, rm.getDate());
			st.setInt(3, rm.getRecipient().getId());
			if(rm.getWhoRejected() != null) {
				st.setInt(4, rm.getWhoRejected().getId());
			} else {
				st.setNull(4, Types.INTEGER);
			}
			st.setInt(5, rm.getMeeting().getId());
			
			st.setTimestamp(6, rm.getDate());
			st.setInt(7, rm.getRecipient().getId());
			if(rm.getWhoRejected() != null) {
				st.setInt(8, rm.getWhoRejected().getId());
			} else {
				st.setNull(8, Types.INTEGER);
			}
			st.setInt(9, rm.getMeeting().getId());
			
			st.executeUpdate();
			
			this.broadcast("REJECT_MESSAGE", Type.UPDATED, rm);
			return rm;
		} catch (Exception e) {
			throw new CloakedIronManException("Could not save reject message.", e);
		}
	}
	
	/**
	 * Returns all accounts associated with a meeting
	 * @param m
	 * @return
	 */
	private ArrayList<Account> getAccountsToMeeting(Meeting m) throws CloakedIronManException {
		try {
			ArrayList<Account> accounts = new ArrayList<Account>();
			int id = m.getId();
			if(id == -1) {
				throw new CloakedIronManException("Meeting ID not set, meeting not saved in database.");
			}
			
			PreparedStatement st = this.con.prepareStatement("SELECT account_user_id FROM meeting_response WHERE meeting_appointment_id=?");
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				accounts.add(this.getAccount(rs.getInt("account_user_id")));
			}
			return accounts;
		} catch (Exception e) {
			throw new CloakedIronManException("Could not get all accounts to meeting.", e);
		}
	}
	/**
	 *  Saven the appointment and returns the saved version
	 * @param a
	 * @return
	 */
	private Appointment saveAppointment(Appointment a, Calendar c) throws CloakedIronManException {
		try {
			if(c.getId() == -1) {
				throw new CloakedIronManException("Calendar not saved in the database");
			}
			if(a.getId() == -1) {
				a.setId(this.getNextAutoIncrease("appointment", "appointment_id"));
			}
			if(a.getOwner() == null) {
				throw new CloakedIronManException("Owner not set");
			}
			if(a.getOwner().getId() == -1) {
				throw new CloakedIronManException("Owner id not set (owner not in database)");
			}
			if(a.getRoom() != null) {
				if (a.getRoom().getId() == -1) {
					throw new CloakedIronManException("Room not saved in database");
				}
			}
			PreparedStatement st = this.con.prepareStatement("INSERT INTO appointment " +
					"(appointment_id, name, date, start, end, info, calendar_id, place, meeting_room_id, appointment_owner) " +
					"VALUES " +
					"(?,?,?,?,?,?,?,?,?,?) " +
					"ON DUPLICATE KEY UPDATE " +
					"name=?, date=?,start=?, end=?, info=?, calendar_id=?, place=?, meeting_room_id=?, appointment_owner=?");
			st.setInt(1, a.getId());
			st.setString(2, a.getName());
			st.setDate(3, a.getDate());
			st.setTime(4, a.getStart());
			st.setTime(5, a.getEnd());
			st.setString(6, a.getInfo());
			st.setInt(7, c.getId());
			st.setString(8, a.getPlace());
			if(a.getRoom() != null) {
				st.setInt(9, a.getRoom().getId());
			} else {
				st.setNull(9, Types.INTEGER);
			}
			st.setInt(10, a.getOwner().getId());
			
			st.setString(11, a.getName());
			st.setDate(12, a.getDate());
			st.setTime(13, a.getStart());
			st.setTime(14, a.getEnd());
			st.setString(15, a.getInfo());
			st.setInt(16, c.getId());
			st.setString(17, a.getPlace());
			if(a.getRoom() != null) {
				st.setInt(18, a.getRoom().getId());
			} else {
				st.setNull(18, Types.INTEGER);
			}
			st.setInt(19, a.getOwner().getId());
			
			st.execute();
			st.close();
			
			// If meeting
			if(a instanceof Meeting) {
				Meeting m = (Meeting)a;
				System.out.println(m.isCancelled());
				st = this.con.prepareStatement("INSERT INTO meeting " +
						"(appointment_id,is_cancelled) " +
						"VALUES " +
						"(?,?) " +
						"ON DUPLICATE KEY UPDATE " +
						"is_cancelled=?");
				st.setInt(1, m.getId());
				st.setBoolean(2, m.isCancelled());
				st.setBoolean(3, m.isCancelled());
				st.execute();
				st.close();
			}
			
			this.broadcast("APPOINTMENT", Type.UPDATED, a);
			
			return a;
			
			
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not handle query.", e);
		}
	}
	
	public Alert saveAlert(Alert a) throws CloakedIronManException {
		try {
			if(a.getOwner() == null) {
				throw new CloakedIronManException("Owner not set in alert");
			}
			if(a.getOwner().getId() == -1) {
				throw new CloakedIronManException("Owner ID not set, owner not saved in database.");
			}
			
			if(a.getAppointment() == null) {
				throw new CloakedIronManException("Appointment in alert not set.");
			}
			if(a.getAppointment().getId() == -1) {
				throw new CloakedIronManException("Appointment id not set, appointment not saved in database.");
			}
			
			// All good, lets save
			PreparedStatement st = this.con.prepareStatement("INSERT INTO alarm " +
					"(appointment_id, user_id, when, is_seen) " +
					"VALUES " +
					"(?,?,?,?) " +
					"ON DUPLICATE KEY UPDATE " +
					"when=?, is_seen=?");
			st.setInt(1, a.getAppointment().getId());
			st.setInt(2, a.getOwner().getId());
			st.setTimestamp(3, a.getWhen());
			st.setBoolean(4, a.isSeen());
			st.setTimestamp(5, a.getWhen());
			st.setBoolean(6, a.isSeen());
			
			this.broadcast("ALERT", Type.UPDATED, a);
			return a;
			
			
			
		} catch (Exception e) {
			throw new CloakedIronManException("Could not save alert.", e);
		}
	}

	// getAppointment(id)
	// get
	public Account saveAccount(Account acc) throws CloakedIronManException {
		try {
			if(acc.getId() == -1) {
				acc.setId(this.getNextAutoIncrease("account", "user_id"));
			}

			// Create statement
			PreparedStatement st = this.con.prepareStatement("INSERT INTO account " +
					"(user_id, first_name, last_name, password,email)" +
					"VALUES (?,?,?,?,?)" +
					"ON DUPLICATE KEY UPDATE " +
					"first_name=?, last_name=?,password=?,email=?");
			st.setInt(1, acc.getId());
			st.setString(2, acc.getFirstName());
			st.setString(3, acc.getLastName());
			st.setString(4, acc.getPassword());
			st.setString(5, acc.getEmail());
			st.setString(6, acc.getFirstName());
			st.setString(7, acc.getLastName());
			st.setString(8, acc.getPassword());
			st.setString(9, acc.getEmail());
			st.executeUpdate();
			try {
				this.addAttendable("user_id", acc.getId());
			} catch (Exception e) {

			}
			this.broadcast("ACCOUNT", Type.UPDATED, acc);
			return acc;
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not handle database query.", e);
		}

	}
	/**
	 * Get an account by its email. Returns null if not found.
	 * @param email
	 * @return
	 */
	public Account getAccountByEmail(String email) throws CloakedIronManException {
		try {
			PreparedStatement st = this.con.prepareStatement("SELECT user_id FROM account WHERE email=?");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if(!rs.next()) {
				return null;
			}
			return this.getAccount(rs.getInt("user_id"));
		} catch (Exception e) {
			throw new CloakedIronManException("Could not get account by email.", e);
		}
	}
	
	public Appointment getAppointment2(int id) throws CloakedIronManException {
		try {
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM appointment LEFT JOIN meeting ON appointment.appointment_id=meeting.appointment_id WHERE appointment.appointment_id=?");
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			if (!rs.next()) {
				throw new CloakedIronManException("Could not find appointment.");
			}
			Appointment a;
			if(rs.getObject("meeting.appointment_id")== null) {
				// It's not a meeting
				a = fillAppointment2(rs);
			} else {
				a = fillMeeting2(rs);
			}
			// Appointment/meeting saved, now load room
			int roomId = rs.getInt("meeting_room_id");
			if(!rs.wasNull()) {
				a.setRoom(this.getRoom(roomId));
			}
			return a;
			
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not get appointment.", e);
		}
	}
	/*
	public Appointment getAppointment(int appointment_id) throws CloakedIronManException
	{
		try {
			PreparedStatement st = this.con.prepareStatement("SELECT meeting.is_cancelled FROM appointment LEFT JOIN meeting ON appointment.appointment_id=meeting.appointment_id WHERE appointment.appointment_id=?");
			st.setInt(1,appointment_id);
			PreparedStatement st2 = this.con.prepareStatement("SELECT * FROM appointment WHERE appointment_id=?");
			st2.setInt(1, appointment_id);
			ResultSet rs = st.executeQuery();
			ResultSet rs2 = st2.executeQuery();

			if(rs.next())
			{
				if(rs.getObject("meeting.is_cancelled") == null)
				{
					return fillAppointment(rs2);
				}
				else
				{
					return fillMeeting(rs2, appointment_id);
				}
			}
			return null;
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not get appointment", e);
		}
		
	} */


	
	/*public Account getAccount(String email) {

		String sql = "SELECT * FROM account WHERE email = ";
		sql += email;
		ResultSet rs = executeQuery(sql);
		try {
			rs.next();
			return getAccount(rs.getInt("user_id"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}*/
	
	
	


	public Account getAccount(int id) throws CloakedIronManException {
		try {
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM account WHERE user_id=?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (!rs.next()) {
				throw new CloakedIronManException("No account found with id " + id);
				
			}
			Account a = this.fillAccount(rs);
			st.close();
			rs.close();
			
			st = this.con.prepareStatement("SELECT attendable_id FROM attendable WHERE user_id=?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(!rs.next()) {
				throw new CloakedIronManException("Account not saved with attendable ID");
			}
			a.setAttendableId(rs.getInt("attendable_id"));
			return a;
		} catch (Exception e) {
			throw new CloakedIronManException("Could not get account.", e);
		}
		
	}

	public Group getGroup(int id) throws CloakedIronManException {
		try {
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM cim.group WHERE group_id=?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			if (!rs.next()) {
				throw new CloakedIronManException("Could not find a group with id " + id);
				
			}
			Group g = fillGroup(rs);
			st.close();
			rs.close();
			
			// Finding attendable id
			st = this.con.prepareStatement("SELECT attendable_id FROM attendable WHERE group_id=?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(!rs.next()) {
				throw new CloakedIronManException("Group has no attendable ID which is must have");
			}
			g.setAttendableId(rs.getInt("attendable_id"));
			
			
			
			// Must find all of its members
			st = this.con.prepareStatement("SELECT user_id FROM member_of WHERE group_id=?");
			st.setInt(1, id);
			rs = st.executeQuery();
			while(rs.next()) {
				g.addMember(this.getAccount(rs.getInt("user_id")));
			}
			return g;
		} catch (SQLException | CloakedIronManException e) {
			throw new CloakedIronManException("Could not get group.", e);
		}
		
	}
	/**
	 * Saves a group into the database. If it has key, it will be updated. Elsewise created
	 * @param group
	 * @return
	 * @throws CloakedIronManException 
	 */
	public Group saveGroup(Group group) throws CloakedIronManException {
		try {
			if(group.getId() == -1) {
				group.setId(this.getNextAutoIncrease("cim.group", "group_id"));
			}
			// Create statement
			PreparedStatement st = this.con.prepareStatement("INSERT INTO cim.group " +
					"(group_id, name, group_owner)" +
					"VALUES (?,?,?)" +
					"ON DUPLICATE KEY UPDATE " +
					"name=?, group_owner=?");
			st.setInt(1, group.getId());
			st.setString(2, group.getName());
			st.setInt(3, group.getOwner().getId());
			st.setString(4, group.getName());
			st.setInt(5, group.getOwner().getId());
			st.executeUpdate();
			try {
				this.addAttendable("group_id", group.getId());
			} catch (Exception e) {

			}
			this.broadcast("GROUP", Type.UPDATED, group);
			return group;
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not handle database query.", e);
		}
	}

	public void addGroupMember(Group group, Account acc) throws SQLException {
		PreparedStatement st = this.con.prepareStatement("INSERT IGNORE INTO member_of (group_id, user_id) VALUES (?,?)");
		st.setInt(1, group.getId());
		st.setInt(2, acc.getId());
		st.execute();
	}

	private void addAttendable(String column, int id) throws SQLException {
		PreparedStatement st = this.con.prepareStatement("SELECT COUNT(*) as has_att FROM attendable WHERE " + column +"=?");
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		rs.next();
		if (rs.getInt("has_att") < 1) {
			// Must add to attendable
			st = this.con.prepareStatement("INSERT INTO attendable ("+ column + ") VALUES (?)");
			st.setInt(1, id);
			st.execute();
		}
	}

	private int getNextAutoIncrease(String table, String column)throws SQLException {
		String sql = "SELECT MAX(" + column + ") as max FROM " + table;
		PreparedStatement st = this.con.prepareStatement(sql);

		ResultSet rs = st.executeQuery();
		rs.next();
		return rs.getInt("max") + 1;
	}
	/**
	 * Fills a new account with data. The ResultSet pointer must be at the correct position
	 * @return
	 * @throws SQLException 
	 */
	private Account fillAccount(ResultSet rs) throws SQLException {
		Account acc = new Account(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("password"));
		acc.setId(rs.getInt("user_id"));
		return acc;
	}

	/**
	 * Fils a new group with data from a resultset. The resultset must be at the correct position.
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Group fillGroup(ResultSet rs) throws CloakedIronManException {
		try {
			Group g = new Group(rs.getString("name"), this.getAccount(rs.getInt("group_owner")));
			g.setId(rs.getInt("group_id"));
			return g;
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not fill group.", e);
		}
		
	}
	
	/**
	 * ResultSet should be a joined query of appintment and meeting.
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	/*private Meeting fillMeeting(ResultSet rs,int appointment_id) throws CloakedIronManException
	{
		try {
			PreparedStatement st = this.con.prepareStatement("SELECT * " +
					"FROM meeting_response WHERE meeting_appointment_id = ?");
			st.setInt(1, appointment_id);
			ResultSet rs2 = st.executeQuery();
			
			Meeting m;
			if(rs.next())
			{
				ArrayList<MeetingResponse> meetingResponses = new ArrayList<MeetingResponse>();
				while(rs2.next()){
					MeetingResponse meeting = new MeetingResponse(getAccount(rs2.getInt("account_user_id")), rs2.getString("status"));
					meetingResponses.add(meeting);
				}
				m = new Meeting(
					rs.getString("name"),
					rs.getDate("date"),
					rs.getTime("start"),
					rs.getTime("end"),
					this.getAccount(rs.getInt("appointment_owner")),
					rs.getBoolean("is_cancelled"),
					meetingResponses
				);
				m.setInfo(rs.getString("info"));
				m.setPlace(rs.getString("place"));
				return m;				
			}
			return null;
		} catch (CloakedIronManException |SQLException e) {
			throw new CloakedIronManException("Could not fill meeting.", e);
		}
		


	}*/
	
	/**
	 * ResultSet should be a joined query of appintment and meeting. RS pointer should be at the correct position.
	 * @param rs
	 * @return
	 * @throws CloakedIronManException
	 */
	private Meeting fillMeeting2(ResultSet rs) throws CloakedIronManException {
		try {
			Meeting m = new Meeting(
				rs.getString("name"),
				rs.getDate("date"),
				rs.getTime("start"),
				rs.getTime("end"),
				this.getAccount(rs.getInt("appointment_owner")),
				rs.getBoolean("is_cancelled")
			);
			m.setId(rs.getInt("appointment_id"));
			m.setInfo(rs.getString("info"));
			m.setPlace(rs.getString("place"));
			return m;
		} catch (CloakedIronManException |SQLException e) {
			throw new CloakedIronManException("Could not fill meeting.", e);
		}
	}
	
	private Appointment fillAppointment(ResultSet rs) throws CloakedIronManException
	{
		Appointment a;
		try {
			if(rs.next())
			{
				a = new Appointment(
					rs.getString("name"),
					rs.getDate("date"),
					rs.getTime("start"),
					rs.getTime("end"),
					this.getAccount(rs.getInt("appointment_owner")) 
				);
				a.setId(rs.getInt("appointment_id"));
				a.setInfo(rs.getString("info"));
				a.setPlace(rs.getString("place"));
				return a;
			}
			return null;
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not fill appointment", e);
		}
	}
	/**
	 * Fills an appointment with data. Should be a joined query of appointment and meeting.
	 * RS pointer should be at the correct position
	 * @param rs
	 * @return
	 * @throws CloakedIronManException
	 */
	private Appointment fillAppointment2(ResultSet rs) throws CloakedIronManException {
		try {
			Appointment a = new Appointment(
				rs.getString("name"),
				rs.getDate("date"),
				rs.getTime("start"),
				rs.getTime("end"),
				this.getAccount(rs.getInt("appointment_owner"))
			);
			a.setId(rs.getInt("appointment_id"));
			a.setInfo(rs.getString("info"));
			a.setPlace(rs.getString("place"));
			return a;
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not fill appointment", e);
		}
	}
	
	public Room getRoom(int id) throws CloakedIronManException {
		try {
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM meeting_room WHERE meeting_room_id=?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(!rs.next()) {
				throw new CloakedIronManException("Room id not found in database");
			}
			Room r = new Room(rs.getString("name"), rs.getInt("size"));
			r.setId(rs.getInt("meeting_room_id"));
			return r;
		} catch (Exception e) {
			throw new CloakedIronManException("Could not fetch room.", e);
		}
	}
	/*
	public Room getRoom(int meeting_room_id) throws SQLException
	{
		PreparedStatement st;
		st = this.con.prepareStatement("SELECT * FROM meeting_room WHERE meeting_room_id = ?");

		st.setInt(1, meeting_room_id);

		
		ResultSet rs = st.executeQuery();
		
		if(rs.next())
		{
			return new Room(rs.getInt("meeting_room_id"),rs.getInt("size"));
		}
		return null;
	}*/
	
	public ArrayList<Room> getAvailableRooms(Date date, Time start, Time end) throws CloakedIronManException{
		try{
		PreparedStatement st;
		st = this.con.prepareStatement("SELECT * FROM appointment WHERE date = ?");
		st.setDate(1, date);
		ResultSet rs = st.executeQuery();
		ArrayList<Room> notAvailable = new ArrayList<Room>();
		if (!rs.wasNull()){
			while (rs.next()){
				//System.out.println(start.compareTo(rs.getDate("end")) <= 0);
				//System.out.println(rs.getDate("start").compareTo(end) >= 0);
				//System.out.println();

				if (overlap(start, rs.getTime("start"), end, rs.getTime("end"))){
					notAvailable.add(getRoom(rs.getInt("meeting_room_id")));
				}
			}
			ArrayList<Room> available = getAllRooms();
			for (Room a : notAvailable){
				available.remove(a);
			}			
			return available;
		}else{
			return getAllRooms();
		}

		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
}

    private ArrayList<Integer> getGroupIdsFromUserid(int userId) throws SQLException
    {
        PreparedStatement st;
        st = this.con.prepareStatement("SELECT group_id FROM member_of where user_id = ?");

        st.setInt(1, userId);
        ResultSet rs = st.executeQuery();
        ArrayList<Integer> groupIds = new ArrayList<Integer>();
        while(rs.next())
        {
            groupIds.add(rs.getInt("group_id"));
        }
        return groupIds;
    }
	
	private boolean overlap(Time start, Time start2, Time end, Time end2){
		if (start.compareTo(end2) <= 0 && start2.compareTo(end) >= 0){
			return true;
		}
		return false;
	}
	
	
	
	private int getAttendableId(Attendable a) throws SQLException, CloakedIronManException {
		PreparedStatement st;
		if (a instanceof Account) {
            st = this.con.prepareStatement("SELECT attendable_id FROM attendable WHERE user_id=?");
		} else{
			//Its a group
            st = this.con.prepareStatement("SELECT attendable_id FROM attendable WHERE group_id=?");
		}
		st.setInt(1, a.getId());
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			return rs.getInt("attendable_id");
		}
		throw new CloakedIronManException("Could not find attendable id");
	}
	
	public ArrayList<MeetingResponse> getMeetingResponsesToAccount(Account a) throws CloakedIronManException {
		try {
			if(a.getId() == -1) {
				throw new CloakedIronManException("Account ID not set, Account not saved in database?");
			}
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM meeting_response WHERE account_user_id = ?" );
			st.setInt(1, a.getId());
			ResultSet rs = st.executeQuery();
			ArrayList<MeetingResponse> meetingResponses = new ArrayList<MeetingResponse>();
			MeetingResponse m;
			while(rs.next()) {
				m = new MeetingResponse(
					a,
					(Meeting)this.getAppointment2(rs.getInt("meeting_appointment_id")),
					rs.getString("status")
				);
				meetingResponses.add(m);
			}
			return meetingResponses;
		} catch (Exception e) {
			throw new CloakedIronManException("Could not get meeting response", e);
		}
	}
	/*
	public ArrayList<Alert> getAllUnseenAlertsToAccount(Account a){
		PreparedStatement st;
		try{
			st = this.con.prepareStatement("SELECT * FROM alarm WHERE user_id = ? AND is_seen = 0");
			st.setInt(1, a.getId());
			ResultSet rs = st.executeQuery();
			ArrayList<Alert> alerts = new ArrayList<Alert>();
			while(rs.next()){
				Alert alert = new Alert(rs.getDate("when"));
				alert.setId(rs.getInt("alarm_id"));
				alerts.add(alert);
				
			}
			return alerts;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean seenAlert(Alert a){
		PreparedStatement st;
		try{
			st = this.con.prepareStatement("INSERT INTO cim.group " +
					"(is_seen)" +
					"VALUES (?) WHERE alarm_id = ? ");
			st.setBoolean(1, true);
			st.setInt(2, a.getId());
			st.executeQuery();
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
		
	} */
	public ArrayList<Account> getAllUsers() throws CloakedIronManException
	{
		ArrayList<Account> allUsers = new ArrayList<Account>();
		try {
			
			PreparedStatement st = this.con.prepareStatement("SELECT user_id FROM account");
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				allUsers.add(getAccount(rs.getInt("user_id")));
			}
			return allUsers;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void cancelAppointment(Appointment ap) throws CloakedIronManException{
		
		PreparedStatement st;
		try {
			if (ap instanceof Appointment){
				st = this.con.prepareStatement("DELETE FROM appointment WHERE appointment_id = ?");
				st.setInt(1, ap.getId());
				st.executeUpdate();
			}
		
		else if (ap instanceof Meeting){
			st = this.con.prepareStatement("UPDATE meeting SET is_cancelled = 1 WHERE appointment_id = ?");
			st.setInt(1, ap.getId());
			
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void cancelMeeting(Meeting meeting) throws CloakedIronManException, SQLException{
		PreparedStatement st;
		ArrayList<Account> accList = this.getAccountsToMeeting(meeting);
		for (Account ac : accList){
			RejectMessage rej = new RejectMessage(ac, meeting);
			this.saveRejectMessage(rej);
		}
	}

	public ArrayList<Group> getAllGroups() throws CloakedIronManException {
		try {
			PreparedStatement st = this.con.prepareStatement("SELECT group_id FROM cim.group");
			ResultSet rs = st.executeQuery();
			ArrayList<Group> groups = new ArrayList<Group>();
			while(rs.next()) {
				groups.add(this.getGroup(rs.getInt("group_id")));
			}
			return groups;
		} catch (Exception e) {
			throw new CloakedIronManException("Could not get all groups.", e);
		}
	}

	public ArrayList<Alert> getAlertsToAccount(Account account) throws CloakedIronManException {
		try {
			if(account.getId() == -1) {
				throw new CloakedIronManException("Account ID not set, account not saved in database.");
			}
			PreparedStatement st = this.con.prepareStatement("SELECT appointment_id FROM alarm WHERE user_id=?");
			st.setInt(1, account.getId());
			ResultSet rs = st.executeQuery();
			ArrayList<Alert> alerts = new ArrayList<Alert>();
			while(rs.next()) {
				Appointment app = this.getAppointment2(rs.getInt("appointment_id"));
				alerts.add(this.getAlert(app, account));
			}
			return alerts;
			
		} catch (Exception e) {
			throw new CloakedIronManException("Could not get all alerts to account.", e);
		}
	}

	private Alert getAlert(Appointment app, Account account) throws CloakedIronManException {
		try {
			if(app.getId() == -1) {
				throw new CloakedIronManException("Appointment id not set in database, appointment not saved in database");
			}
			if(account.getId() == -1) {
				throw new CloakedIronManException("Account id not set, account not saved in database");
			}
			
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM alarm WHERE appointment_id=? AND user_id=?");
			st.setInt(1, app.getId());
			st.setInt(2, account.getId());
			
			ResultSet rs = st.executeQuery();
			if(!rs.next()) {
				throw new CloakedIronManException("Alert not found.");
			}
			return new Alert(app, account, rs.getTimestamp("when"), rs.getBoolean("is_seen"));
			
			
		} catch (Exception e) {
			throw new CloakedIronManException("Could not get alert", e);
		}
	}
    
    



}
