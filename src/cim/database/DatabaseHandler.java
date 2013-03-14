package cim.database;

import cim.models.*;
import cim.util.CloakedIronManException;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler implements DatabaseFetcherInterface {

	private static String url = cim.util.PersonalSettings.JDBC_URL;
	private static String user = cim.util.PersonalSettings.MYSQL_USER;
	private static String password = cim.util.PersonalSettings.MYSQL_PW;
	private static Connection con;



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
			System.out.println(e);
			return false;
		}		
	}

	
	public Calendar getCalendar(int calendar_id){
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
				"SELECT name, date, start, end , info, place, appointment_id " +
						"FROM appointment " +
						"WHERE calendar_id = ";
		sql += calendar_id;
		rs = executeQuery(sql);
		Calendar c = new Calendar(owner);
		c.setId(calendar_id);
		try {
			while(rs.next()){

				Appointment a = new Appointment(rs.getTime("start"), 
						rs.getTime("end"),
						rs.getString("info"),
						rs.getDate("date"));
				c.addAppointment(a);
			}
			return c;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Room> getAllRooms()
	{
		ArrayList<Room> rom = new ArrayList<Room>();
		String sql = "SELECT *" +
				"FROM meeting_room;";
		ResultSet rs = executeQuery(sql);
		try {
			while(rs.next())
			{
				rom.add(new Room(rs.getInt("meeting_room_id"), "Navn", rs.getInt("size"), "Info"));
			}
			return rom;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Returns all calendars associated with the current account, the accounts personal calendar and the calendars from his groups
	 * @param acc
	 * @return
	 */
    public ArrayList<Calendar> getAllCalendarsToAccount(Account acc) throws SQLException, CloakedIronManException
    {
        ArrayList<Group> groups = getAllGroupsToAccount(acc);
        ArrayList<Calendar> calendars = new ArrayList<Calendar>();
        int accCalendarId, groupCalendarId;

        PreparedStatement st = this.con.prepareStatement("SELECT calender_id FROM calendar where owner_attendable_id = ?");
        st.setInt(1, getAttendableId(acc));
        ResultSet rs = st.executeQuery();

        if(!rs.next())
        {
            throw new CloakedIronManException("Account " + acc.getId() + "  is not registered as an attendable");
        }
        accCalendarId = rs.getInt("calendar_id");
        calendars.add(getCalendar(accCalendarId));
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
            calendars.add(getCalendar(groupCalendarId));
        }

        return calendars;
    }


    public ArrayList<Group> getAllGroupsToAccount(Account acc) throws SQLException, CloakedIronManException
    {
        ArrayList<Integer> groupIds = getGroupIdsFromUserid(acc.getId());
        ArrayList<Group> groups = new ArrayList<Group>();

        for(int groupId : groupIds)
        {
            groups.add(getGroup(groupId));
        }
        return groups;
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
                returnCalendars.add(getCalendar(rs.getInt("calendar_id")));
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
	public int saveCalendar(Calendar c) throws CloakedIronManException {
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
			
			// Delete all appointments not in the calendars list
			ArrayList<Integer> ids = c.getAllAppointmentIds();
			String[] str = new String[ids.size()];
			for(int i = 0;i<ids.size();i++) {
				str[i] = ids.get(i).toString();
			}
			System.out.println(str);
			
			
			return c.getId();
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not handle query.", e);
		}
	}
	/**
	 *  Saven the appointment and returns the saved version
	 * @param a
	 * @return
	 */
	public Appointment saveAppointment(Appointment a, Calendar c) throws CloakedIronManException {
		try {
			if(c.getId() == -1) {
				throw new CloakedIronManException("Calendar not saved in the database");
			}
			if(a.getId() == -1) {
				a.setId(this.getNextAutoIncrease("appointment", "appointment_id"));
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
			return a;
			
			
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not handle query.", e);
		}

	}

	// getAppointment(id)
	// get
	public int saveAccount(Account acc) throws CloakedIronManException {
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
			return acc.getId();
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
			PreparedStatement st = this.con.prepareStatement("SELECT * FROM account WHERE email=?");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return fillAccount(rs);
			}
			return null;
		} catch (SQLException e) {
			throw new CloakedIronManException("Unable to process query.", e);
		}
		
	}

	public Appointment getAppointment(int appointment_id) throws SQLException
	{
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
	}



	
	public Account getAccount(String email) {
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
	}
	


	public Account getAccount(int id) throws SQLException {
		PreparedStatement st = this.con.prepareStatement("SELECT * FROM account WHERE user_id=?");
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			return fillAccount(rs);
		}
		return null;
	}

	public Group getGroup(int id) throws SQLException {
		PreparedStatement st = this.con.prepareStatement("SELECT * FROM cim.group WHERE group_id=?");
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			return fillGroup(rs);
		}
		return null;
	}
	/**
	 * Saves a group into the database. If it has key, it will be updated. Elsewise created
	 * @param group
	 * @return
	 * @throws CloakedIronManException 
	 */
	public int saveGroup(Group group) throws CloakedIronManException {
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
			return group.getId();
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
		System.out.println(sql);
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
	private Group fillGroup(ResultSet rs) throws SQLException {
		Group g = new Group(rs.getString("name"), this.getAccount(rs.getInt("group_owner")));
		g.setId(rs.getInt("group_id"));
		return g;
	}

	/**
	 * ResultSet should be a joined query of appintment and meeting.
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Meeting fillMeeting(ResultSet rs,int appointment_id) throws SQLException
	{
		PreparedStatement st = this.con.prepareStatement("SELECT * " +
				"FROM meeting_response WHERE meeting_appointment_id = ?");
		st.setInt(1, appointment_id);
		ResultSet rs2 = st.executeQuery();
		
		Meeting m;
		try {
			if(rs.next())
			{
				ArrayList<MeetingResponse> meetingResponses = new ArrayList<MeetingResponse>();
				while(rs2.next()){
					MeetingResponse meeting = new MeetingResponse(getAccount(rs2.getInt("account_user_id")), rs2.getString("status"));
					meetingResponses.add(meeting);
				}
				return m = new Meeting(rs.getString("info"), meetingResponses, getRoom(rs.getInt("meeting_room_id")), rs.getTime("start"), rs.getTime("end"), rs.getDate("date"));				
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;


	}
	public Appointment fillAppointment(ResultSet rs)
	{
		Appointment a;
		try {
			if(rs.next())
			{
				a = new Appointment(rs.getTime("start") , rs.getTime("end"), rs.getString("info"), rs.getDate("date"));
				a.setId(rs.getInt("appointment_id"));
				return a;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
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
	
	private int getAttendableId(Attendable a) throws SQLException, CloakedIronManException {
		PreparedStatement st;
		if (a instanceof Account) {
			st = this.con.prepareStatement("SELECT attendable_id FROM attendable WHERE user_id=?");
		} else{
			//Its a group
			st = this.con.prepareStatement("SELECT attendable_id FROM attendable WHERE gruop_id=?");
		}
		st.setInt(1, a.getId());
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			return rs.getInt("attendable_id");
		}
		throw new CloakedIronManException("Could not find attendable id");
		
	}

}
