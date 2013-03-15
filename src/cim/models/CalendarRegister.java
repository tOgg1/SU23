package cim.models;

import cim.net.Client;
import cim.net.packet.Request;
import cim.net.packet.Response;
import cim.util.CloakedIronManException;

import java.util.ArrayList;

public class CalendarRegister
{

	ArrayList<Calendar> calendars;
    ArrayList<Group> groups;
    ArrayList<Appointment> appointments;
    ArrayList<Account> accounts;
    Client parent;

    public CalendarRegister(Client parent)
    {
        this.parent = parent;
        calendars = new ArrayList<Calendar>();
        groups = new ArrayList<Group>();
        appointments = new ArrayList<Appointment>();
        accounts = new ArrayList<Account>();
    }

    public void initialize() throws CloakedIronManException
    {
        try
        {
            Request req = new Request("GET_ALL_CALENDARS", null);
            Response res = parent.request(req);
            Calendar[] data = (Calendar[])res.getData();
            for(Calendar cal : data)
            {
                calendars.add(cal);
            }
        }
        catch(Exception e)
        {
            throw new CloakedIronManException("Error initializing calendar register", e);
        }
    }

    public boolean addFromGUICalendar(Calendar cal)
    {
        try
        {
            calendars.add(cal);
            Request req = new Request("SAVE_CALENDAR", cal);
            Response res = parent.request(req);
            return true;
        }
        catch (CloakedIronManException e)
        {
            return false;
        }
    }


    // Is this needed?
    public boolean addFromGUIGroup(Group group)
    {
        try
        {
            groups.add(group);
            Request req = new Request("SAVE_GROUP", group);
            Response res = parent.request(req);
            return true;
        }
        catch(CloakedIronManException e)
        {
            return false;
        }
    }

    public Calendar getCalendarById(int calendarId)
    {
        for(Calendar cal : calendars)
        {
            if(calendarId == cal.getId())
            {
                return cal;
            }
        }
        return null;
    }

    /**
     * Returns the passed-in groups primary calendar
     * @param group
     * @return
     */
    public Calendar getCalendarByGroup(Group group)
    {
        // Can a group have several calendars?
        for(Calendar cal : calendars)
        {
            if(cal.getOwner() == group)
            {
                return cal;
            }
        }
        return null;
    }

    /**
     * Returns the passed-in accounts primary calendar
     * @param acc
     * @return
     */
    public Calendar getCalendarByAccount(Account acc)
    {
        // Can an account have several calendars?
        for(Calendar cal : calendars)
        {
            if(cal.getOwner() == acc)
            {
                return cal;
            }
        }
        return null;
    }

    /**
     * Get all group the passed-in account is a member of
     * @param acc
     * @return
     */
    public ArrayList<Group> getAllAttendingGroups(Account acc)
    {

    }


    public Group getGroupById(int groupId)
    {
        for(Group group : groups)
        {
            if(groupId == group.getId())
            {
                return group;
            }
        }
        return null;
    }

    //Useless?
    public Appointment getAppointmentById(int appId)
    {
        for(Appointment app : appointments)
        {
            if(appId == app.getId())
            {
                return app;
            }
        }
        return null;
    }

    public Account getAccountById(int accId)
    {
        for(Account acc : accounts)
        {
            if(accId == acc.getId())
            {
                return acc;
            }
        }
        return null;
    }

    /**
     * Returns all groups managed by either passed-in account (personal calendar) or by one of the accounts groups
     * @param acc
     * @return
     */
    public ArrayList<Calendar> getManagedCalendars(Account acc)
    {
        ArrayList<Calendar> tempCals = new ArrayList<Calendar>();
        ArrayList<Group> tempGroups = new ArrayList<Group>();

        //Add all groups being managed by acc to tempGroups
        for(Group group : groups)
        {
            if(group.getOwner() == acc)
            {
                tempGroups.add(group);
            }
        }

        for(Calendar cal : calendars)
        {
            if(cal.getOwner() == acc)
            {
                tempCals.add(cal);
            }

            for(Group group : tempGroups)
            {
                if(cal.getOwner() == group)
                {
                    tempCals.add(cal);
                }
            }
        }

        return tempCals;
    }

    /**
     * Returns all groups owned by passed-in account
     * @return
     */
    public ArrayList<Group> getManagedGroups(Account acc)
    {
        ArrayList<Group> tempGroups = new ArrayList<Group>();

        for(Group group : groups)
        {
            if(group.getOwner() == acc)
            {
                tempGroups.add(group);
            }
        }
        return tempGroups;
    }


    public ArrayList<Account> getAccounts()
    {
        return accounts;
    }

    public ArrayList<Appointment> getAppointments()
    {
        return appointments;
    }

    public ArrayList<Calendar> getCalendars()
    {
        return calendars;
    }

    public ArrayList<Group> getGroups()
    {
        return groups;
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

    //Checking if an arraylist consisting of a calendarobject-subclass already contains a similar calendar (same id)
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
