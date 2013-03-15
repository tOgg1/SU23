package cim.models;

import java.sql.*;

public class Alert extends CalendarObject {
	
	Account owner;
	Date when;
	
	public Alert(Date date){
		this.when = date;
	}
	
}
