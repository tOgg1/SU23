package cim.models;

import java.util.ArrayList;

public class Account extends CalendarObject implements Attendable 
{
	private String email;
	private String name;
	private ArrayList<CalendarObject> calendars;
	
	public Account(String name, String email)
	{
		this.name = name;
		this.email = email;
	}
	
	@Override
	public void receiveInvite(Meeting meeting) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
