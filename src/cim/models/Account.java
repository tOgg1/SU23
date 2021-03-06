package cim.models;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class Account extends CalendarObject implements Attendable, Serializable
{
	private int attendableId = -1;
	private String email;
	private String firstName;
	private String lastName;
	private ArrayList<CalendarObject> calendars;
	private ArrayList<Group> groups;
	private String password;

	private static Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+"
            + "(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*"
            + "(\\.[A-Za-z]{2,})$");
	private static Matcher matcher;

	public Account(String first_name,String last_name, String email, String password)
	{
		super();
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = email;
		this.password = password;
		this.calendars = new ArrayList<CalendarObject>();
		this.groups = new ArrayList<Group>();
	}
	
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
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
		String[] splittet = new String[3];
		splittet = newName.split(" ");
		if(!(splittet[0] == null) && !(splittet[1] == null))
		{
			this.firstName = splittet[0];
			this.lastName = splittet[1];
		}
		if(validate(newEmail))
		{
			String oldMail = this.email;
			this.email = newEmail;
			pcs.firePropertyChange("email", oldMail, newEmail);
		}
		String oldPassword = this.password;
		this.password = newPassword;
		pcs.firePropertyChange("password", oldPassword, newPassword);
	
	}
	
	public void receiveInvite(Meeting meeting) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName() {
		// For the attendable interface
		return this.getFirstName() + " " + this.getLastName();
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}

	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return this.email;
	}

	public boolean isValidPassword(String password){
		return this.password.equals(password);
	}
    
	public void setPassword(String password) {
		this.pcs.firePropertyChange("password", this.password, password);
		this.password = password;
	}
	
	public void setAttendableId(int id) {
		int oldVal = this.attendableId;
		this.attendableId = id;
		this.pcs.firePropertyChange("attendableId", oldVal, id);
	}
	
	public int getAttendableId() {
		return this.attendableId;
	}

   
}
