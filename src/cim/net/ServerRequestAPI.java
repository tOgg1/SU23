package cim.net;

import java.sql.SQLException;

import cim.database.DatabaseHandler;
import cim.models.Account;
import cim.models.Calendar;
import cim.net.packet.Request;
import cim.net.packet.Response;
import cim.util.CloakedIronManException;

/**
 * This class defines the API for the entire server request
 * @author Haakon
 *
 */
public class ServerRequestAPI {
	
	private final Server server;
	private final DatabaseHandler db;
	
	public ServerRequestAPI(Server server) throws CloakedIronManException {
		this.server = server;
		this.db = new DatabaseHandler();
	}
	
	/**
	 * Returns response object to the output stream.
	 * @param req
	 * @return
	 */
	public Response getResponse(Request req) {
		try {
			String method = req.getMethod().toUpperCase();
			Object[] args = req.getArgs();
			//	Big fat-ass if
			if(method.equals("AUTHENTICATE")) {
				return authenticate((String)args[0], (String)args[1]);
			} else if (method.equals("GET_ALL_CALENDARS")) {
				return get_all_calendars();
			} else if (method.equals("GET_ALL_CALENDARS_TO_ACCOUNT")) {
				return get_all_calendars_to_account((Account)args[0]);
			} else if (method.equals("SAVE_CALENDAR")) {
				return save_calendar((Calendar)args[0]);
			} else if (method.equals("GET_ACCOUNT")) {
				return get_account((int)args[0]);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * Returns an account if the user is validated
	 * @param email
	 * @param pw
	 * @return
	 * @throws SQLException
	 */
	private Response authenticate(String email, String pw) throws CloakedIronManException {
		Account acc = db.getAccountByEmail(email);
		if(acc != null){
			if(acc.isValidPassword(pw)){
				return new Response(acc);
			}
		}
		return new Response((Object)null);
	}
	/**
	 * Returnerer alle kalendre registrert i systemet.
	 * @return
	 * @throws SQLException
	 */
	private Response get_all_calendars() throws CloakedIronManException {
		return new Response(this.db.getAllCalendars());
	}
	
	private Response get_all_calendars_to_account(Account acc) throws CloakedIronManException {
		return new Response(this.db.getAllCalendarsToAccount(acc));
	}
	
	private Response save_calendar(Calendar c) throws CloakedIronManException {
		int iID = this.db.saveCalendar(c);
		return new Response(iID);
	}
	
	private Response get_account(int id) throws CloakedIronManException {
		return new Response(this.db.getAccount(id));
	}
	
	
	
}
