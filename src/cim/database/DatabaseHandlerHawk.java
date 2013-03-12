package cim.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	 */
	public void saveAccount(Account acc) {
		
	}
	
	private boolean accountExists(Account acc) {
		if(acc.getId() == -1) {
			return false;
		}
	}
	
}
