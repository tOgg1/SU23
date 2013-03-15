package cim.views;

import cim.models.Appointment;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mayacat
 * Date: 3/15/13
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppointmentPanel extends JPanel implements Comparable
{
    private Appointment base;

    public AppointmentPanel(Appointment base)
    {
        this.base = base;
        JTextField text = new JTextField(base.getName());
        text.setEditable(false);
        this.add(text);
    }

    public Appointment getBase() {
        return base;
    }

    /**
     * Comparing AppointmentPanel for sorting purposes.
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o)
    {
        //TODO: Make the compare function more complex if necessary.
        AppointmentPanel other = (AppointmentPanel)o;
        return getBase().getStart().compareTo(other.getBase().getStart());
    }
}
