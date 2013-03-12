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
			if(accountExists(acc)){
				PreparedStatement st = this.con.prepareStatement("UPDATE account SET first_name=?, last_name=?, password=?, email=? WHERE user_id=?");
				st.setString(0, acc.getFirstName());
				st.setString(1, acc.getLastName());
				st.setString(2, acc.getPassword());
				st.setString(3, acc.getEmail());
				st.setInt(4, acc.getId());
				st.execute();
				return acc.getId();
			} else {
				// Fetch net id
				if(acc.getId == -1) {
					acc.setId(this.getNextAutoIncrease("account", "user_id"));
				}
				int id = 
				// Create statement
				acc.
			}
			return acc.getId();
		} catch (SQLException e) {
			throw new CloakedIronManException("Could not handle database query.", e);
		}
		
	}
	
	private boolean accountExists(Account acc) throws SQLException {
		if(acc.getId() == -1) {
			return false;
		}
		PreparedStatement statement = this.con.prepareStatement("SELECT COUNT(*) as has_user FROM account WHERE user_id=?");
		statement.setInt(0, acc.getId());
		ResultSet rs = statement.executeQuery();
		rs.next();
		return rs.getInt("has_user") == 1;
	}
	
	private int getNextAutoIncrease(String table, String column)throws SQLException {
		PreparedStatement st = this.con.prepareStatement("SELECT MAX(?) as max FROM ?");
		st.setString(0, column);
		st.setString(1, table);
		
		ResultSet rs = st.executeQuery();
		rs.next();
		return rs.getInt("max") + 1;
	}
	
	
}
