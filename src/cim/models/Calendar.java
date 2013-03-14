package cim.models;

import java.util.ArrayList;

public class Calendar extends CalendarObject
{
	private Account owner;
	private ArrayList<Appointment> appointments;
	private ArrayList<Attendable> hasAccess;
	
	public Calendar(Account owner)
	{
		super();
		this.appointments = new ArrayList<Appointment>();
		this.hasAccess = new ArrayList<Attendable>();
		this.owner = owner;
	}
	
	public ArrayList<Integer> getAllAppointmentIds() {
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (Appointment a : this.getAppointments()) {
			l.add(a.getId());
		}
		return l;
	}
	
	public Attendable getOwner()
	{
		return this.owner;
	}
	public void setOwner(Attendable owner)
	{
		Account oldValue = this.owner;
		this.owner = (Account)owner;
		pcs.firePropertyChange("owner", oldValue, this.owner);

	}
	
	public ArrayList<Appointment> getAppointments() {
		return this.appointments;
	}
	
	public Boolean hasAccess(Attendable person)
	{
		return this.hasAccess.contains(person);
	}
	
	
	public Boolean hasAppointment(Appointment appointment)
	{
		return this.appointments.contains(appointment);
	}
	public void addAppointment(Appointment appointment)
	{
		if(!this.appointments.contains(appointment))
		this.appointments.add(appointment);
		pcs.firePropertyChange("appointment", null, appointment);

	}
	public void addAccess(Attendable person)
	{
		if(!hasAccess(person))
		this.hasAccess.add(person);
		pcs.firePropertyChange("hasaccess", null, person);

		
	}
	public void removeAppointment(Appointment appointment)
	{
		if(!this.appointments.isEmpty())
		this.appointments.remove(appointment);
		pcs.firePropertyChange("appointment", appointment, null);

	}
	public void removeAccess(Attendable person)
	{
		this.hasAccess.remove(person);
		pcs.firePropertyChange("hasacsess", person, null);

	}
   
    
    public String toString(){
    	String returnString = this.owner.getName() + "\n";
    	for (Appointment x : appointments){
    		returnString += "\n" + x;
    	}
    	return returnString;
    	
    }

}
