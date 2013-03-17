package cim.views;

import cim.models.Calendar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManageCalendarsView extends JPanel
{
    private ArrayList<CalendarPanel> calendars;
    private ApplicationWindow application;

    public ManageCalendarsView(ArrayList<Calendar> models, ApplicationWindow application)
    {
        this.application = application;
        this.setLayout(new GridBagLayout());
        init(models);
    }

    public void init(ArrayList<Calendar> models)
    {
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.FIRST_LINE_START;

        JPanel calendarContainer = new JPanel();
        calendarContainer.setLayout(new BoxLayout(calendarContainer, BoxLayout.PAGE_AXIS));
        calendars = new ArrayList<CalendarPanel>();
        for(Calendar cal : models)
        {
            CalendarPanel model = new CalendarPanel(cal);
            model.setBackground(Color.WHITE);
            calendarContainer.add(model);
        }
        g.gridx = 0;
        g.gridy = 0;
        g.weightx = 0;
        g.weighty = 0;
        g.gridheight = 1;
        super.add(calendarContainer, g);

        g.gridx = 1;
        JLabel label = new JLabel("lololo");
        super.add(label, g);
    }
}
