package cim.database;

import java.sql.*;
import java.util.ArrayList;


import cim.models.Account;
import cim.models.Appointment;
import cim.models.Calendar;
import cim.models.Room;

public class DatabaseHandler {

	private static String url = "jdbc:mysql://192.168.0.104:3306/cim";
	private static String user = "Petter";
	private static String password = "123456";
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
	
	private Account getAccount(int accountId){
		String sql = 
				"SELECT * " +
				"FROM attendable " +
				"WHERE attendable_id = ";
		sql += accountId;
		ResultSet rs = executeQuery(sql);
		int accountId1 = 0;
		try {
			rs.next();
			accountId1 = rs.getInt("user_id");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		sql = "SELECT * " +
				"FROM account " +
				"WHERE user_id = ";
		sql += accountId1;		
		rs = executeQuery(sql);		
		try {
			rs.next();
			return new Account(rs.getString("first_name"),
							   rs.getString("last_name"),
							   rs.getString("email"),
							   rs.getString("password"));
		} catch (SQLException e) {
			System.out.println(e);
			return null;
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
		try {
			while(rs.next()){
				
				Appointment a = new Appointment(rs.getTime("start"), 
												rs.getTime("end"),
												rs.getString("info"),
												rs.getDate("date"),
												rs.getInt("appointment_id"));
				c.addAppointment(a);
			}
			return c;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private void getMeeting(int appointment_id){
		String sql = 
				"SELECT * " +
				"FROM meeting " +
				"WHERE appointment_id = ";
		sql += appointment_id;
		
		
				
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
	
	public ArrayList<Calendar> getAllCalendars(int user_id) {
		String sql =
				"SELECT calendar_id " +
				"FROM calendar " +
				"WHERE owner_attendable_id = ";
		sql += user_id;
		ResultSet rs = executeQuery(sql);
		ArrayList<Calendar> allCals = new ArrayList<Calendar>();
		try {
			while(rs.next()){
				allCals.add(getCalendar(rs.getInt("calendar_id")));
			}
			return allCals;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
		
				
	}
	
	
	
	
	public boolean createAppointment(int owner_id, int calendar_id, Time startTime, Time endTime, Date date, String info, String name,int room_id, String place){
		String sql = 
				"INSERT INTO appointment(name, date, start, end, info, calendar_id, place, meeting_room_id, appointment_owner) " +
				"VALUES(" + "'" + name + "'"  
						+ ", " + "'" + date + "'"
						+ ", " + "'"+ startTime + "'"
						+ ", " + "'" + endTime + "'"
						+ ", " + "'" + info + "'" 
						+ ", " + calendar_id +  ", " + 
						"'" +place + "'" + ", " + 
						"'" + room_id +"'" + ", " + 
						"'"+ owner_id + "'" + ");";
		try{
			return executeUpdate(sql);
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean deleteAppointment(int appointmentId){
		String sql = "DELETE from appointment WHERE appointment_id = ";
		sql += appointmentId;
		return executeUpdate(sql);
	}
	
	



}
