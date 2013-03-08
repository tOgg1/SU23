package cim.database;

import java.sql.*;

public class DatabaseHandler {

	private static String url = "jdbc:mysql://78.91.2.66:3306/cim";
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
		String sql = "SELECT email, password FROM account WHERE email = ";
		email = email;
		sql = sql + email;
		ResultSet rs = executeQuery(sql);
		System.out.println(rs);
		try {
			rs.next();
			return password.equals(rs.getString("password"));
		} catch (SQLException | NullPointerException e) {
			return false;
		}
	}
	
	


	private ResultSet executeQuery(String sql){
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			con.close();
			stmt.close();
			return rs;
		}
		catch (SQLException e){
			return null;
		}		
	}


}
