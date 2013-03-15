package cim.models;

import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;

public class Meeting extends Appointment
{
	/**
	 * Needed for somewhat reason
	 */
	private static final long serialVersionUID = 2366591279198595004L;

	private ArrayList<MeetingResponse> invitees;
	private boolean isCancelled = false;

    private final static int STATUS_INVITED = 0;
    private final static int STATUS_ACCEPTED = 1;
    private final static int STATUS_DECLINED = 2;
    
    public Meeting(String name, Date date, Time start, Time end, Account owner)
   	{	
   		super(name, date, start, end, owner);
   	}
    
    public Meeting(String name, Date date, Time start, Time end, Account owner, boolean isCancelled)
	{	
		this(name, date, start, end, owner);
		this.isCancelled = isCancelled;
	}
    
	public Meeting(String name, Date date, Time start, Time end, Account owner, boolean isCancelled, ArrayList<MeetingResponse> invitees)
	{	
		this(name, date, start, end, owner, isCancelled);
		this.invitees = invitees;		
	}
	
	public String toString(){
		return "Meeting: " + this.getName();
		/*
		String returnString = "";
		for (MeetingResponse r : invitees){
			returnString += r.account + "\n" + r.response + "\n";
		}
		return returnString;*/
		
		
		
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.pcs.firePropertyChange("cancelled", this.isCancelled, isCancelled);
		this.isCancelled = isCancelled;
	}
	
	

	
	

}
