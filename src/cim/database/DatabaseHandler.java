package cim.database;

import java.sql.*;


import cim.models.Account;
import cim.models.Appointment;
import cim.models.Calendar;

public class DatabaseHandler {

	private static String url = "jdbc:mysql://78.91.3.54:3306/cim";
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
		System.out.println(sql);

		
		try {
			rs.next();
			return new Account(rs.getString("first_name"),
							   rs.getString("last_name"),
							   rs.getString("email"));
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
				"SELECT name, date, start, end , info, place " +
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
												rs.getDate("date"));
				c.addAppointment(a);
			}
			return c;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	



}
