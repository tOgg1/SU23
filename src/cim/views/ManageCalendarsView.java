package cim.views;

import cim.models.Calendar;
import cim.util.Helper;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ManageCalendarsView extends JPanel
{
    private ArrayList<CalendarPanel> allCalendars;
    private ApplicationWindow application;
    private JCheckBox displayCurrent;
    private int selected;
    private boolean changedTroughLoad;
    private Color defaultColor;

    public ManageCalendarsView(ArrayList<Calendar> myModels, ArrayList<Calendar> allModels, ApplicationWindow application)
    {
        this.application = application;
        this.setLayout(null);
        this.defaultColor = UIManager.getColor("Panel.background");
        this.init(myModels, allModels);
        System.out.println(allModels.size());
        System.out.println(myModels.size());
    }

    public void init(ArrayList<Calendar> myModels, ArrayList<Calendar> allModels)
    {
        selected = -1;
        changedTroughLoad = false;

        setPreferredSize(new Dimension(1039, 456));

        JPanel calendarContainer = new JPanel();
        calendarContainer.setLayout(null);
        calendarContainer.setBounds(31, 11, 300, 456 - 20);
        calendarContainer.setBorder(new LineBorder(Color.GRAY, 3));
        calendarContainer.setPreferredSize(new Dimension(300,456-20));
        allCalendars = new ArrayList<CalendarPanel>();

        ModelClickListener modelClickListener = new ModelClickListener();

        int index = 0;
        for(Calendar cal : myModels)
        {
            CalendarPanel model = new CalendarPanel(cal);
            model.setBorder(new LineBorder(Color.GRAY, 2));
            model.addMouseListener(modelClickListener);
            model.setBounds(15,25+35*index++,270,30);
            allCalendars.add(model);
            calendarContainer.add(model);
        }

        for(Calendar cal : allModels)
        {
            if(!Helper.containsById(myModels, cal))
            {
                CalendarPanel model = new CalendarPanel(cal);
                model.setBorder(new LineBorder(Color.GRAY, 2));
                model.addMouseListener(modelClickListener);
                model.setBounds(15, 25+35*index++, 270, 30);
                allCalendars.add(model);
                calendarContainer.add(model);
            }
        }

        //JSeparator separator

        super.add(calendarContainer);

        displayCurrent = new JCheckBox("Display selected calendar");
        displayCurrent.addItemListener(new ChangeDisplayListener());
        displayCurrent.setBounds(350,20,150,50);
        super.add(displayCurrent);
    }

    private class ChangeDisplayListener implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
            if(selected == -1 || changedTroughLoad == false)
                return;
            allCalendars.get(selected).toggleDisplayed();
        }
    }

    private class ModelClickListener implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {
            CalendarPanel selectedPanel = (CalendarPanel)e.getSource();
            if(selected == allCalendars.indexOf(selectedPanel))
            {
                selectedPanel.setBackground(defaultColor);
                selected = -1;
                displayCurrent.setSelected(false);
                changedTroughLoad = false;
            }
            else
            {
                if(selected != -1)
                {
                    allCalendars.get(selected).toggleDisplayed();
                    allCalendars.get(selected).setBackground(defaultColor);
                }
                selectedPanel.setBackground(Color.lightGray);
                selected = allCalendars.indexOf(selectedPanel);
                displayCurrent.setSelected(selectedPanel.isDisplayed());
                changedTroughLoad = true;
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}
