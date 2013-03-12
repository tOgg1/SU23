package cim.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import cim.models.Account;
import cim.util.CloakedIronManException;
import cim.util.PersonalSettings;

/**
 * This class is a temporary class so hawk can test. Some of its components might be merged with the original DatabaseHandler.
 * @author Håkon
 *
 */
public class DatabaseHandlerHawk {
	
	private Connection con;
	
	public DatabaseHandlerHawk() throws CloakedIronManException {
		try	{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(PersonalSettings.JDBC_URL, PersonalSettings.MYSQL_USER, PersonalSettings.MYSQL_PW);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			throw new CloakedIronManException("Could not connect to database.", e);
		}
		
	}
	// PUBLIC METHODS
	/**
	 * This method saves an Account. It creates a new one if not exists, updates otherwise.
	 * @param acc
	 * @return ID of the current user;
	 */
	public int saveAccount(Account acc) throws CloakedIronManException {
		try {
			boolean bNew = false;
			if(acc.getId() == -1) {
				bNew = true;
				acc.setId(this.getNextAutoIncrease("account", "user_id"));
			}
			System.out.println(acc.getId());
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
			if(st.execute()) {
				// New user added
				this.addAttendable("user_id", acc.getId());
			} else {
				if (bNew == true) {
					throw new CloakedIronManException("User not added. Is the email a duplicate?");
				}
			}
			return acc.getId();
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not handle database query.", e);
		}
		
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
		PreparedStatement st = this.con.prepareStatement("SELECT MAX(" + column + ") as max FROM " + table);
		
		ResultSet rs = st.executeQuery();
		rs.next();
		return rs.getInt("max") + 1;
	}
	
	
}
