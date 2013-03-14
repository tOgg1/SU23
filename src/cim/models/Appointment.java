package cim.models;

import java.sql.Date;
import java.sql.Time;

public class Appointment extends CalendarObject 
{
	private Account owner;
	private Time startDate;
	private Time endDate;
	private Date date;
	private String info;
	protected Room room;
	
	public Appointment(Time startDate, Time endDate, String info, Date date)
	{
		this.startDate = startDate;
		this.date = date;
		this.endDate = endDate;
		this.info = info;
	}
	public void modifyStart(Time newStartDate)
	{
		this.startDate = newStartDate;
	}
	public void modifyEnd(Time newEndDate)
	{
		this.endDate = newEndDate;
	}
	public void modifyTime(Time newStartDate, Time newEndDate)
	{
		this.startDate = newStartDate;
		this.endDate = newEndDate;
	}
	public void modifyDate(Date newDate)
	{
		this.date = newDate;
	}

    public String toString(){
    	return "startDate: " + startDate + " endDate: " + endDate;
    }
    
    public Account getOwner() {
    	return this.owner;
    }
}
