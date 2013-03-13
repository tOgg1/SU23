package cim.models;

import java.sql.Time;
import java.sql.Date;
import java.util.Map;

public class Meeting extends Appointment
{
	private Map<Integer, Attendable> invitees;

    private final static int STATUS_INVITED = 0;
    private final static int STATUS_ACCEPTED = 1;
    private final static int STATUS_DECLINED = 2;

	public Meeting(String info, Map<Integer, Attendable> invitees, Room room, Time startDate, Time endDate, Date date)
	{	
		super(startDate, endDate, info, date);
		this.invitees = invitees;		
	}

}
