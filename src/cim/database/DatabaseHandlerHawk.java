package cim.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import cim.models.Account;
import cim.models.Group;
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
	 * If a email that is in use is passed, the method will appear to work, but its not actually saved.
	 * @param acc
	 * @return ID of the current user;
	 */
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
	public Account getAccountByEmail(String email) throws SQLException {
		PreparedStatement st = this.con.prepareStatement("SELECT * FROM account WHERE email=?");
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			return fillAccount(rs);
		}
		return null;
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
	
	private Group fillGroup(ResultSet rs) throws SQLException {
		Group g = new Group(rs.getString("name"), this.getAccount(rs.getInt("group_owner")));
		return g;
	}
	
	
}
