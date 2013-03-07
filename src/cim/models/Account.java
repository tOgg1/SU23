package cim.models;

import java.util.ArrayList;

import cim.util.AuthenticatorInterface;

public class Account extends CalendarObject implements Attendable
{
	private String email;
	private String name;
	private ArrayList<CalendarObject> calendars;
	
	public Account(String name, String email)
	{
		this.name = name;
		this.email = email;
		this.calendars = new ArrayList<CalendarObject>();
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

    @Override
    public int getData() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
