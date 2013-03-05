package cim.models;

import java.util.Date;
import java.util.Map;

public class Meeting extends Appointment
{
	private String info;
	private Map<Integer, Attendable> invitees;
	private Room room;
	private Date startDate;
	private Date endDate;
	
	public Meeting(String info, Map<Integer, Attendable> invitees, Room room, Date startDate, Date endDate)
	{	
		super(startDate, endDate, info);
		this.info = info;
		this.invitees = invitees;		
	}

}
