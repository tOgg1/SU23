package cim.models;

import java.sql.Date;
import java.sql.Time;

public class Appointment extends CalendarObject 
{
	/*
	 * Must be set
	 */
	private String name;
	private Date date;
	private Time start;
	private Time end;
	private Account owner;
	
	/*
	 * Must not be set.
	 */
	private String info;
	private String place;
	protected Room room;
	
	
	
	public Appointment(String name, Date date, Time start, Time end, Account owner)
	{
		this.name = name;
		this.date = date;
		this.start = start;
		this.end = end;
		this.owner = owner;
	}

	public String toString() {
		return "Appointment: " + this.name;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.pcs.firePropertyChange("name", this.name, name);
		this.name = name;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.pcs.firePropertyChange("date", this.date, date);
		this.date = date;
	}



	public Time getStart() {
		return start;
	}
	
	public String getStartFormatted() {
		return this.formatTime(this.start);
	}


	public void setStart(Time start) {
		this.pcs.firePropertyChange("start", this.start, start);
		this.start = start;
	}



	public Time getEnd() {
		return end;
	}
	
	public String getEndFormatted() {
		return this.formatTime(this.end);
	}



	public void setEnd(Time end) {
		this.pcs.firePropertyChange("end", this.end, end);
		this.end = end;
	}



	public Account getOwner() {
		return owner;
	}



	public void setOwner(Account owner) {
		this.pcs.firePropertyChange("owner", this.owner, owner);
		this.owner = owner;
	}



	public String getInfo() {
		return info;
	}



	public void setInfo(String info) {
		this.pcs.firePropertyChange("info", this.info, info);
		this.info = info;
	}



	public String getPlace() {
		return place;
	}



	public void setPlace(String place) {
		this.pcs.firePropertyChange("place", this.place, place);
		this.place = place;
	}



	public Room getRoom() {
		return room;
	}



	public void setRoom(Room room) {
		this.pcs.firePropertyChange("room",this.room, room);
		this.room = room;
	}
	
	private String formatTime(Time t) {
		return t.getHours() + ":" + t.getMinutes();
	}
	
}
