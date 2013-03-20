package cim.models;

import java.util.ArrayList;

import cim.net.Client;
import cim.util.CloakedIronManException;

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
		this.appointments.remove(appointment);
		this.appointments.add(appointment);
		pcs.firePropertyChange("appointment", null, appointment);

	}
	public void addAccess(Attendable person)
	{
		if(!hasAccess(person))
		this.hasAccess.add(person);
		pcs.firePropertyChange("hasaccess", null, person);

		
	}
	public void removeAppointment(Appointment appointment) throws CloakedIronManException
	{
		
		if (appointment instanceof Meeting){
			Meeting m = (Meeting) appointment;
			m.setCancelled(true);
			
			 // Removing and adding makes sure we get the new meeting
			this.appointments.remove(m);
			this.appointments.add(m);
		} else {
			this.appointments.remove(appointment);
		}
		pcs.firePropertyChange("appointment", appointment, null);

	}
	public void removeAccess(Attendable person)
	{
		this.hasAccess.remove(person);
		pcs.firePropertyChange("hasacsess", person, null);

	}
	public Calendar getUpdatedCalendar(){
		return Client.register.getCalendarById(this.getId());
	}
	
   

}
