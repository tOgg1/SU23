package cim.database;

import java.sql.*;

public class DatabaseHandler {

	private static String url = "jdbc:mysql://78.91.2.66:3306/cim";
	private static String user = "Petter";
	private static String password = "123456";
	private static Connection con;


	public static void main(String[] args) {
		DatabaseHandler db = new DatabaseHandler();
		String sql = "SELECT email FROM account WHERE first_name = 'Petter'";
		System.out.println("running");
		System.out.println(db.executeQuery(sql));
	}

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


	public ResultSet executeQuery(String sql){
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
		catch (SQLException e){
			return null;
		}		
	}


}
