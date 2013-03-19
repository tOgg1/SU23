package cim.models;

import java.sql.*;

public class Alert extends CalendarObject {
	
	
	Appointment appointment;
	Account owner;
	Timestamp when;
	boolean isSeen;
	
	
	
	/*public Alert(Date date)
	{
		this.when = date;
	}*/
	
	public Alert(Appointment a, Account o, Timestamp when, boolean isSeen) {
		this.appointment = a;
		this.owner = o;
		this.when = when;
		this.isSeen = isSeen;
	}
	
	public Alert(Appointment a, Account o, Timestamp when) {
		this(a,o,when,false);
	}
	
	public Alert(Timestamp date, Appointment appointment, Account owner){
		this.appointment = appointment;
		this.owner = owner;
		this.when = date;
	}
	
	
	public Account getOwner() {
		return owner;
	}
	public Timestamp getWhen() {
		return when;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	
	public boolean isSeen() {
		return this.isSeen;
	}
	public void changeIsSeen(boolean b){
		this.isSeen = b;
	}
	
	public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
        Alert a = (Alert)obj;
        if(this.getOwner() == null || a.getOwner() == null) {
        	return false;
        }
        if(this.getAppointment() == null || a.getAppointment() == null) {
        	return false;
        }
        return (this.getAppointment().equals(a.getAppointment()) && this.getOwner().equals(a.getOwner()));
    }
	
	public String toString() {
		return "Alarm for '" + this.owner + "' til '" +  this.appointment + "'";
	}
	
	
}
