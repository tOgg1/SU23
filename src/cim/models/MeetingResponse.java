package cim.models;

import java.io.Serializable;

public class MeetingResponse extends CalendarObject {
	
	/**
	 * 
	 */
	public Account account;
	public Response response;
	
	public MeetingResponse(Account a, String resp){
		this.account = a;
		this.response = handleResponse(resp);
	}
	
	public enum Response{
		not_seen, not_attending, attending;
	}
	
	public Response handleResponse(String a){
		if (a.equals("not_seen")){
			return Response.not_seen;
		}
		else if (a.equals("attending")){
			return Response.attending;
		}
		else if (a.equals("not_attending")){
			return Response.not_attending;
		}
		return null;
	}
	public String toString()
	{
		return account.toString();
	}
	

}
