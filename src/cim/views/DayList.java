package cim.views;

import cim.models.Account;
import cim.models.Appointment;

import javax.swing.*;

import java.awt.Dimension;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;

/**
 * Created with IntelliJ IDEA.
 * User: Mayacat
 * Date: 3/15/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DayList extends JPanel
{
    //PriorityQueue orders our elements for us, \o/
    public PriorityQueue<AppointmentPanel> children;
    

    public DayList()
    {
        this.children = new PriorityQueue<AppointmentPanel>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
    }

    public boolean add(AppointmentPanel appointment)
    {
        if(!children.offer(appointment))
            return false;

        int index = 0;
        Iterator it = children.iterator();

        //find index of appointment in children
        while(it.hasNext())
        {
            if(it.next() == appointment)
            {
                break;
            }
            index++;
        }
        super.add(appointment, index);
        return true;
    }
    
    public void clear(){
    	this.children.clear();
    	this.removeAll();
    }

    public boolean remove(AppointmentPanel appointment)
    {
        if(!children.contains(appointment))
        {
            //Child does not exist in dayList, removing it is therefore already done.. or something
            return true;
        }

        super.remove(appointment);
        return children.remove(appointment);
    }
    
    

    //For testing
}
