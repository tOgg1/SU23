package cim.models;

import java.util.ArrayList;

public class CalendarRegister
{
	ArrayList<Calendar> calendars;
    ArrayList<Group> groups;
    ArrayList<Appointment> appointments;
    ArrayList<Account> accounts;

    public CalendarRegister()
    {
        calendars = new ArrayList<Calendar>();
        groups = new ArrayList<Group>();
        appointments = new ArrayList<Appointment>();
        accounts = new ArrayList<Account>();
    }

    public void registerCalendar(Calendar calendar)
    {
        if(!containsById(calendars, calendar))
            calendars.add(calendar);
    }

    public void registerGroup(Group group)
    {
        if(!containsById(groups, group))
            groups.add(group);
    }

    public void registerAppointment(Appointment appointment)
    {
        if(!containsById(appointments, appointment))
            appointments.add(appointment);
    }

    public void registerAccount(Account account)
    {
        if(!containsById(accounts, account))
            accounts.add(account);
    }

    public void unRegisterCalendar(Calendar calendar)
    {
        if(containsById(calendars, calendar))
            calendars.remove(calendar);
    }

    public void unRegisterGroup(Group group)
    {
        if(containsById(groups, group))
            groups.remove(group);
    }

    public void unRegisterAppointment(Appointment appointment)
    {
        if(containsById(appointments, appointment))
            appointments.add(appointment);
    }

    public void unRegisterAccount(Account account)
    {
        if(containsById(accounts, account))
            accounts.add(account);
    }

    public boolean containsById(ArrayList<? extends CalendarObject> list, CalendarObject object)
    {
        for(CalendarObject listObject : list)
        {
            if(listObject.getId() == object.getId())
                return true;
        }
        return false;
    }
}
