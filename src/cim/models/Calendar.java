package cim.models;

import java.util.ArrayList;

public class Calendar extends CalendarObject {
	private Account owner;
	private ArrayList<Appointment> appointments;
	private ArrayList<Attendable> hasAccess;
	
	public Calendar(Account owner)
	{
		this.appointments = new ArrayList<Appointment>();
		this.hasAccess = new ArrayList<Attendable>();
	}
	
	public Attendable getOwner()
	{
		return this.owner;
	}
	public void setOwner(Attendable owner)
	{
		this.owner = (Account)owner;
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
		this.appointments.add(appointment);
	}
	public void addAccess(Attendable person)
	{
		this.hasAccess.add(person);
	}
	public void removeAppointment(Appointment appointment)
	{
		this.appointments.remove(appointment);
	}
	public void removeAccess(Attendable person)
	{
		this.hasAccess.remove(person);
	}

    @Override
    public int getData() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}