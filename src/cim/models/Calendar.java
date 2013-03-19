package cim.models;

import java.util.ArrayList;

public class Calendar extends CalendarObject
{
	private Attendable owner;
	private ArrayList<Appointment> appointments;
	private ArrayList<Attendable> hasAccess;
	
	public Calendar(Attendable owner)
	{
		super();
		this.appointments = new ArrayList<Appointment>();
		this.hasAccess = new ArrayList<Attendable>();
		this.owner = owner;
	}
	/**
	 * Returns a list of all appointment ids which is set. Not saved appointments are not returned.
	 * @return
	 */
	public ArrayList<Integer> getAllAppointmentIds() {
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (Appointment a : this.getAppointments()) {
			if (a.getId() != -1) {
				l.add(a.getId());
			}
			
		}
		return l;
	}
	
	
	public String toString() {
		return "Calendar (" + this.getId() +") (" + this.owner.getName() + ")";
	}
	
	public Attendable getOwner()
	{
		return this.owner;
	}
	public void setOwner(Attendable owner)
	{
		this.pcs.firePropertyChange("owner", this.owner, owner);
		this.owner = owner;

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
		if(!this.appointments.contains(appointment)) {
			this.appointments.add(appointment);
		}
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
   

}
