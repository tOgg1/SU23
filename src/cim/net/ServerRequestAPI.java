package cim.net;

import cim.net.packet.Request;
import cim.net.packet.Response;

/**
 * This class defines the API for the entire server request
 * @author Håkon
 *
 */
public class ServerRequestAPI {
	
	private final Server server;
	
	public ServerRequestAPI(Server server) {
		this.server = server;
	}
	
	/**
	 * Returns response object to the output stream.
	 * @param req
	 * @return
	 */
	public Response getResponse(Request req) {
		String method = req.getMethod().toUpperCase();
		Object[] args = req.getArgs();
		//	Big fat-ass if
		if(method.equals("AUTHENTICATE")) {
			return authenticate((String)args[0], (String)args[1]);
		}
		
		return null;
	}
	
	private Response authenticate(String email, String pw) {
		return new Response(true);
	}
	
}
