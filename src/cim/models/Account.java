package cim.models;

import java.util.ArrayList;

public class Account extends CalendarObject implements Attendable 
{
	private String email;
	private String name;
	private ArrayList<CalendarObject> calendars;
	private ArrayList<Group> groups;
	private String password;
	
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
		return this.name;
	}

    @Override
    public int getData() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

	public String getPassword() {
		return password;
	}

	public boolean isValidPassword(String password){
		return this.password == password;
	}
}
