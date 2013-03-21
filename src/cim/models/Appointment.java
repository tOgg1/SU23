package cim.models;

import cim.util.Helper;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

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
	private Alert alert;
	
	/*
	 * Must not be set.
	 */
	private String place;
	protected Room room;
	private String info;
	
	
	
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
	
	public String getDateFormatted(String format) {
		return Helper.formatDate(this.date, format);
	}
	
	public String getDateFormatted() {
		return this.getDateFormatted("d/M-yy");
	}


	public void setDate(Date date) {
		this.pcs.firePropertyChange("date", this.date, date);
		this.date = date;
	}



	public Time getStart() {
		return start;
	}
	
	
	
	public String getStartFormatted() {
		return this.getStartFormatted("HH:mm");
	}
	
	public String getStartFormatted(String format) {
		return Helper.formatTime(this.start, format);
	}


	public void setStart(Time start) {
		this.pcs.firePropertyChange("start", this.start, start);
		this.start = start;
	}



	public Time getEnd() {
		return end;
	}
	
	public String getEndFormatted(String format) {
		return Helper.formatTime(this.end, format);
	}
	
	public String getEndFormatted() {
		return this.getEndFormatted("HH:mm");
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
	
	/**
	 * Returns the room name if that is set, returns place if not;
	 * @return
	 */
	public String getWhere() {
		if(this.room != null) {
			return this.room.getName();
		}
		if (this.place != null) {
			return this.place;
		}
		return "Ukjent";
	}

    public Meeting toMeeting()
    {
    	Meeting m = new Meeting(getName(), getDate(), getStart(), getEnd(), getOwner());
    	m.setId(this.getId());
    	m.setRoom(this.room);
    	m.setAlert(this.alert);
    	m.setPlace(this.place);
    	return m;
    }

    public Meeting toMeeting(ArrayList<Attendable> invitees)
    {
        Meeting meeting = this.toMeeting();
        return meeting;
    }
    public Alert getAlert()
    {
    	return this.alert;
    }
    public void setAlert(Alert alert)
    {
    	this.alert = alert;
    }
    
    public void setInfo(String info) {
    	this.info = info;
    }
    
    public String getInfo() {
    	return this.info;
    }
}
