package cim.views;

import cim.models.Account;
import cim.models.Appointment;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Iterator;
import java.util.PriorityQueue;

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
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
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
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DayList list = new DayList();
        AppointmentPanel panel1 = new AppointmentPanel(new Appointment("Appointment1", new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()+0xFFFF), new Time(System.currentTimeMillis() + 0xFFFFF),new Account("Beist", "Birgerson","Beist@Birger.no", "hei")));
        AppointmentPanel panel2 = new AppointmentPanel(new Appointment("Appointment2", new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()+0xFFFFF), new Time(System.currentTimeMillis() + 0xFFFFF),new Account("Beist", "Birgerson","Beist@Birger.no", "hei")));
        AppointmentPanel panel3 = new AppointmentPanel(new Appointment("Appointment3", new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()+0xFF), new Time(System.currentTimeMillis() + 0xFFFFF),new Account("Beist", "Birgerson","Beist@Birger.no", "hei")));
        log(list.add(panel1));
        log(list.add(panel2));
        log(list.add(panel3));
        //log(list.remove(panel2));

        frame.add(list);
        frame.pack();
        frame.setVisible(true);
    }

    public static void log(Object s)
    {
        System.out.println(s.toString());
    }
}