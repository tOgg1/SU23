package cim.net;

import java.sql.SQLException;

import cim.database.DatabaseHandlerHawk;
import cim.models.Account;
import cim.net.packet.Request;
import cim.net.packet.Response;
import cim.util.CloakedIronManException;

/**
 * This class defines the API for the entire server request
 * @author Håkon
 *
 */
public class ServerRequestAPI {
	
	private final Server server;
	private final DatabaseHandlerHawk db;
	
	public ServerRequestAPI(Server server) throws CloakedIronManException {
		this.server = server;
		this.db = new DatabaseHandlerHawk();
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
	private Response authenticate(String email, String pw) throws SQLException {
		System.out.println(email + pw);
		Account acc = db.getAccountByEmail(email);
		System.out.println(acc);
		if(acc != null){
			if(acc.isValidPassword(pw)){
				return new Response(acc);
			}
		}
		return new Response((Object)null);
	}
	
}
