package cim.models;

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
	
	public MeetingResponse(Account a, Meeting m) {
		this.account = a;
		this.meeting = m;
		this.response = Response.NOT_SEEN;
	}
	
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
		Response old = this.response;
		this.response = r;
		this.pcs.firePropertyChange("response", old, r);
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

		return account.toString() + ", " + meeting.toString() + ", " + this.response.toString();
	}
	 public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
        MeetingResponse m = (MeetingResponse)obj;
        if(this.getAccount() == null || m.getAccount() == null) {
        	return false;
        }
        if(this.getMeeting() == null || m.getMeeting() == null) {
        	return false;
        }
        return (this.getMeeting().equals(m.getMeeting()) && this.getAccount().equals(m.getAccount()));
    }
	
	

}
