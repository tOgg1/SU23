package cim.net;

/**
 * This class defines the API for the entire server request
 * @author Håkon
 *
 */
public class ServerRequestAPI {
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
