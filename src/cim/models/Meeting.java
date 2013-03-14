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

	public Meeting(String info, ArrayList<MeetingResponse> response, Room room, Time startDate, Time endDate, Date date)
	{	
		super(startDate, endDate, info, date);
		this.invitees = response;		
	}
	
	public String toString(){
		String returnString = "";
		for (MeetingResponse r : invitees){
			returnString += r.account + "\n" + r.response + "\n";
		}
		return returnString;
		
		
		
	}

	
	

}
