package cim.models;

import java.io.Serializable;

public class MeetingResponse extends CalendarObject {
	
	/**
	 * 
	 */
	
	public enum Response{
		NOT_SEEN,
		NOT_ATTENDING,
		ATTENDING;
	}
	
	
	private Account account;
	private Meeting meeting;
	private Response response;
	
	public MeetingResponse(Account a, Meeting m, String r) {
		this.account = a;
		this.meeting = m;
		this.response = this.handleResponse(r);
	}
	
	public MeetingResponse(Account a, String resp){
		this.account = a;
		this.response = handleResponse(resp);
	}
	
	public Account getAccount() {
		return account;
	}

	public Meeting getMeeting() {
		return meeting;
	}
	
	public void setResponse(Response r) {
		this.pcs.firePropertyChange("response", this.response, r);
		this.response = r;
	}

	public Response getResponse() {
		return response;
	}
	
	public String getResponseString() {
		return this.response.toString().toLowerCase();
	}

	public Response handleResponse(String a){
		if (a.equals("not_seen")){
			return Response.NOT_SEEN;
		}
		else if (a.equals("attending")){
			return Response.ATTENDING;
		}
		else if (a.equals("not_attending")){
			return Response.NOT_ATTENDING;
		}
		return null;
	}
	public String toString()
	{
		return account.toString();
	}
	

}
