package cim.models;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends CalendarObject implements Attendable 
{
	private String email;
	private String name;
	private ArrayList<CalendarObject> calendars;
	private ArrayList<Group> groups;

	private String password;

	private static Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+"
            + "(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*"
            + "(\\.[A-Za-z]{2,})$");
	private static Matcher matcher;

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
	
	public void changeAccount(String newName, String newEmail, String newPassword)
	{
		this.name = newName;
		if(validate(newEmail))
		{
			this.email = newEmail;
		}
		this.password = newPassword;
	
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

	public String getPassword() {
		return password;
	}

	public boolean isValidPassword(String password){
		return this.password == password;
	}
}
