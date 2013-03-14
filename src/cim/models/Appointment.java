package cim.models;

import java.sql.Date;
import java.sql.Time;

public class Appointment extends CalendarObject 
{
	private Account owner;
	private Time start;
	private Time end;
	private Date date;
	private String info;
	private String name;
	private String place;
	
	protected Room room;
	
	public Appointment(Time startDate, Time endDate, String info, Date date)
	{
		this.start = startDate;
		this.date = date;
		this.end = endDate;
		this.info = info;
	}
	public void modifyStart(Time newStartDate)
	{
		this.start = newStartDate;
	}
	public void modifyEnd(Time newEndDate)
	{
		this.end = newEndDate;
	}
	public void modifyTime(Time newStartDate, Time newEndDate)
	{
		this.start = newStartDate;
		this.end = newEndDate;
	}
	public void modifyDate(Date newDate)
	{
		this.date = newDate;
	}
	
	public Date getDate(){
		return this.date;
	}

    public String toString(){
    	return "startDate: " + start + " endDate: " + end;
    }
    
    public Account getOwner() {
    	return this.owner;
    }
    public Time getStart() {
		return start;
	}
	public Time getEnd() {
		return end;
	}
	public Date getDate() {
		return date;
	}
	public String getInfo() {
		return info;
	}
	public Room getRoom() {
		return room;
	}
	public String getName() {
		return this.name;
	}
	public String getPlace() {
		return place;
	}
}
