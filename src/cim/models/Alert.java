package cim.models;

import java.sql.*;

public class Alert extends CalendarObject {
	
	Account owner;
	Date when;
	Appointment appointment;
	
	public Alert(Date date)
	{
		this.when = date;
	}
	public Alert(Date date, Appointment appointment, Account owner){
		this.appointment = appointment;
		this.owner = owner;
		this.when = date;
	}
	
}
