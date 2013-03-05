package cim.models;

import java.util.Map;

public class Meeting extends Account
{
	private String info;
	private Map<Integer, Attendable> invitees;
	private Room room;
	private Date startDate;
	private Date endDate;
	
	public Meeting(String info, Map<Integer, Attendable> invitees, Room room)
	{
		this.info = info;
		this.invitees = invitees;
		this.startDate = super.startDate;
		this.endDate = super.endDate;
		
	}

}
