package cim.models;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import cim.util.AuthenticatorInterface;

public class Account extends CalendarObject implements Attendable 
{
	private String email;
	private String name;
	private ArrayList<CalendarObject> calendars;
	private ArrayList<Group> groups;
<<<<<<< HEAD
	private String password;
=======

	private String password;

>>>>>>> e20069bc7dce03da469b7f38c07cda575c026f6e
	private static Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+"
            + "(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*"
            + "(\\.[A-Za-z]{2,})$");
	private static Matcher matcher;
<<<<<<< HEAD
=======

>>>>>>> e20069bc7dce03da469b7f38c07cda575c026f6e
	
	public Account(String name, String email)
	{
		this.name = name;
		this.email = email;
		this.calendars = new ArrayList<CalendarObject>();
		this.groups = new ArrayList<Group>();
	}
	
	public void addCalendar(CalendarObject calendar)
	{
		if(!this.calendars.contains(calendar))
		{
			this.calendars.add(calendar);
		}
	}
	
	public void removeCalendar(CalendarObject calendar)
	{
		this.calendars.remove(calendar);
	}
	
	public void addGroup(Group group)
	{
		if(!this.groups.contains(group))
		{
			this.groups.add(group);
		}
	}
	
	public void removeGroup(Group group)
	{
		this.groups.remove(group);
	}
	
	private static boolean validate(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
	
	public void changeAccount(String newName, String newEmail)
	{
		this.name = newName;
		if(validate(newEmail))
		{
			this.email = newEmail;
		}
	
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
<<<<<<< HEAD
=======

>>>>>>> e20069bc7dce03da469b7f38c07cda575c026f6e

	public String getPassword() {
		return password;
	}

	public boolean isValidPassword(String password){
		return this.password == password;
	}
<<<<<<< HEAD
    
=======

    

>>>>>>> e20069bc7dce03da469b7f38c07cda575c026f6e
}
