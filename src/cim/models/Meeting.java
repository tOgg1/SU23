package cim.models;

import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;

public class Meeting extends Appointment
{
	private ArrayList<MeetingResponse> invitees;

    private final static int STATUS_INVITED = 0;
    private final static int STATUS_ACCEPTED = 1;
    private final static int STATUS_DECLINED = 2;

	public Meeting(String name, Date date, Time start, Time end, Account owner, ArrayList<MeetingResponse> invitees)
	{	
		super(name, date, start, end, owner);
		this.invitees = invitees;		
	}
	
	public String toString(){
		String returnString = "";
		for (MeetingResponse r : invitees){
			returnString += r.account + "\n" + r.response + "\n";
		}
		return returnString;
		
		
		
	}

	
	

}
