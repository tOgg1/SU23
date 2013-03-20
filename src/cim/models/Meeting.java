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

	private boolean isCancelled = false;
    
    public Meeting(String name, Date date, Time start, Time end, Account owner)
   	{	
   		super(name, date, start, end, owner);
   	}
    
    public Meeting(String name, Date date, Time start, Time end, Account owner, boolean isCancelled)
	{	
		this(name, date, start, end, owner);
		this.isCancelled = isCancelled;
	}
    /*
	public Meeting(String name, Date date, Time start, Time end, Account owner, boolean isCancelled, ArrayList<MeetingResponse> invitees)
	{	
		this(name, date, start, end, owner, isCancelled);
		this.invitees = invitees;		
	}*/
	
	public String toString(){
		String ret = "Møte: " + this.getName() + " " + this.getStartFormatted() + "-" + this.getEndFormatted() + ", " + this.getDateFormatted("YYYY-MM-dd");
		if (this.isCancelled) {
			ret += " (AVLYST!)";
		}
		return ret;
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
	/*
    public void setInvitees(ArrayList<MeetingResponse> invitees)
    {
        this.invitees = invitees;
    }

    public ArrayList<MeetingResponse> getInvitees()
    {
        return invitees;
    }
    */
}
