package cim.views;

import cim.models.Calendar;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * Panel to display information about a specific calendar
 * User: Tormod
 * Date: 3/17/13
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarPanel extends JPanel
{
    private Calendar model;

    public CalendarPanel(Calendar model)
    {
        this.model = model;
        init();
    }

    public void init()
    {

        JLabel text = new JLabel("Calendar " + model.getId() + " owned by " + model.getOwner().getName());
        add(text);
    }

}
