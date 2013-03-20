package cim.views;

import cim.models.Appointment;
import cim.models.Calendar;
import cim.net.Client;
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
    private JPanel calendarInformation;
    JScrollPane scrollText;
    private int selected;
    private boolean changedTroughLoad;
    private Color defaultColor;

    public ManageCalendarsView(ArrayList<Calendar> myModels, ArrayList<Calendar> allModels, ApplicationWindow application)
    {
        this.application = application;
        this.setLayout(null);
        this.defaultColor = UIManager.getColor("Panel.background");
        this.init(myModels, allModels);
    }

    public void init(ArrayList<Calendar> myModels, ArrayList<Calendar> allModels)
    {
        selected = -1;
        changedTroughLoad = false;

        setPreferredSize(new Dimension(1039, 456));

        JPanel calendarContainer = new JPanel();
        calendarContainer.setLayout(null);
        calendarContainer.setBorder(new LineBorder(Color.GRAY, 3));
        //calendarContainer.setBounds(31,11,300,436);

        allCalendars = new ArrayList<CalendarPanel>();

        ModelClickListener modelClickListener = new ModelClickListener();

        JLabel label1 = new JLabel("My Calendars");
        label1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        label1.setBorder(new LineBorder(Color.GRAY, 1));
        label1.setBounds(50, 20, 200, 15);
        calendarContainer.add(label1);

        int index = 0;
        for(Calendar cal : myModels)
        {
            CalendarPanel model = new CalendarPanel(cal);
            model.setBorder(new LineBorder(Color.GRAY, 2));
            model.addMouseListener(modelClickListener);
            model.setBounds(15, 40 + (35 * index++), 270, 30);
            model.setDisplayed(true);
            allCalendars.add(model);
            calendarContainer.add(model);
            colorizePanel(model);
        }

        JLabel label2 = new JLabel("Other Calendars");
        label2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        label2.setBorder(new LineBorder(Color.GRAY, 1));
        label2.setBounds(50,40+(35*++index),200, 15);
        calendarContainer.add(label2);

        index++;
        for(Calendar cal : allModels)
        {
            if(!Helper.containsById(myModels, cal))
            {
                CalendarPanel model = new CalendarPanel(cal);
                model.setBorder(new LineBorder(Color.GRAY, 2));
                model.setDisplayed(false);
                model.addMouseListener(modelClickListener);
                model.setBounds(15, 25 + (35 * index++), 270, 30);
                allCalendars.add(model);
                calendarContainer.add(model);
                colorizePanel(model);

            }
        }
        calendarContainer.setPreferredSize(new Dimension(300,25+35*index));

        JScrollPane scrollContainer = new JScrollPane(calendarContainer);
        scrollContainer.setBounds(31,11,320,436);


        super.add(scrollContainer);

        displayCurrent = new JCheckBox("Display selected calendar");
        displayCurrent.addItemListener(new ChangeDisplayListener());
        displayCurrent.setBounds(750,20,150,50);

        calendarInformation = new JPanel();
        calendarInformation.setLayout(null);
        JLabel empty = new JLabel("Select a calendar");
        empty.setBounds(350,10, 100, 30);
        JSeparator sep = new JSeparator();
        sep.setBounds(30,45,740,20);
        calendarInformation.add(empty);
        calendarInformation.add(sep);
        calendarInformation.setBounds(400, 70, 800, 325);
        calendarInformation.setBorder(new LineBorder(Color.GRAY, 2));
        super.add(calendarInformation);
        super.add(displayCurrent);
    }

    private void colorizePanel(CalendarPanel panel)
    {
        System.out.println(allCalendars.indexOf(panel));
        if(allCalendars.indexOf(panel) == selected)
        {
            panel.setBackground(Color.GRAY);
            return;
        }
        panel.setBackground(panel.isDisplayed() ? Color.lightGray : defaultColor);
    }

    private void displayInformation(CalendarPanel panel)
    {
        System.out.println("panel: " + panel);
        System.out.println("count: " + calendarInformation.getComponentCount());
        calendarInformation.removeAll();
        System.out.println("count: " + calendarInformation.getComponentCount());
        if(panel == null)
        {
            JLabel empty = new JLabel("Select a calendar");
            empty.setBounds(350,10, 100, 30);
            JSeparator sep = new JSeparator();
            sep.setBounds(30,45,740,20);
            calendarInformation.add(sep);
            calendarInformation.add(empty);
            calendarInformation.revalidate();
            calendarInformation.repaint();
        }
        else
        {
            Calendar model = panel.getModel();
            ArrayList<Appointment> relatedAppointments = model.getAppointments();
            JSeparator sep = new JSeparator();
            sep.setBounds(30,45,740,20);
            final JPanel appointmentInfo = new JPanel();
            appointmentInfo.setBorder(new LineBorder(Color.GRAY, 2));
            appointmentInfo.setLayout(null);
            final JPanel appointments = new JPanel();
            appointments.setBorder(new LineBorder(Color.GRAY, 2));
            appointments.setLayout(null);
            JLabel size, name, appointmentTitle;
            int count = relatedAppointments.size();

            appointmentTitle = new JLabel("Appointments");
            name = new JLabel("Calendar owned by " + model.getOwner());
            size = new JLabel("Amount of appointments in calendar: " + count);

            int i = 0;
            for(final Appointment app : relatedAppointments)
            {
                JPanel appointment = new JPanel();
                appointment.setBorder(new LineBorder(Color.GRAY, 1));
                JLabel nameApp = new JLabel(app.getName());
                nameApp.repaint();
                appointment.add(nameApp);
                appointment.setBounds(40, 10+35*i++, 225, 30);
                appointment.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Yo");
                        appointmentInfo.removeAll();
                        JLabel information1 = new JLabel();
                        JLabel information2 = new JLabel();
                        JLabel information3 = new JLabel();
                        information1.setBounds(25,10, 275,30);
                        information1.setText("Appointment: " + app.getName());
                        information1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                        information2.setText("\nDate: " + app.getDateFormatted());
                        information2.setBounds(25, 45, 275, 30);
                        information2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                        information3.setText("\nStart: " + app.getStartFormatted() + " End: " + app.getEndFormatted());
                        information3.setBounds(25, 80, 275, 30);
                        information3.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                        appointmentInfo.add(information1);
                        appointmentInfo.add(information2);
                        appointmentInfo.add(information3);
                        appointmentInfo.setBorder(new LineBorder(Color.gray, 1));
                        appointmentInfo.repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent e){}@Override
                    public void mouseReleased(MouseEvent e){}@Override
                    public void mouseEntered(MouseEvent e){} @Override
                    public void mouseExited(MouseEvent e){}
                });
                appointments.add(appointment);
            }

            name.setBounds(325,10, 200, 30);
            size.setBounds(290,50, 300,30);
            appointments.setPreferredSize(new Dimension(300,10+35*i));
            appointmentInfo.setBounds(450, 125, 325, 150);

            JScrollPane scrollAppointments = new JScrollPane(appointments);
            scrollAppointments.setBounds(50, 100, 325, 200);
            appointmentTitle.setBounds(170, 80, 100, 10);

            calendarInformation.add(name);
            calendarInformation.add(sep);
            calendarInformation.add(size);
            calendarInformation.add(scrollAppointments);
            calendarInformation.add(appointmentInfo);
            calendarInformation.add(appointmentTitle);
            calendarInformation.revalidate();
            calendarInformation.repaint();
        }
    }

    @Override
    public void requestFocus() {
        this.setFocusable(true);
        super.requestFocus();
    }

    private class ChangeDisplayListener implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
            if(selected == -1 || changedTroughLoad == true)
                return;
            CalendarPanel panelOfInterest = allCalendars.get(selected);
            panelOfInterest.toggleDisplayed();
            Client.register.setCalendarActivity(panelOfInterest.getModel(), panelOfInterest.isDisplayed());
            changedTroughLoad = false;
        }
    }

    private class ModelClickListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            CalendarPanel selectedPanel = (CalendarPanel)e.getSource();
            int oldSelected = selected;
            if(selected == allCalendars.indexOf(selectedPanel))
            {
                selected = -1;
                changedTroughLoad = false;
                displayCurrent.setSelected(false);
                displayInformation(null);
            }
            else
            {
                selected = allCalendars.indexOf(selectedPanel);
                displayInformation(allCalendars.get(selected));
                changedTroughLoad = true;
                displayCurrent.setSelected(selectedPanel.isDisplayed());
            }
            if(oldSelected != -1)
                colorizePanel(allCalendars.get(oldSelected));
            colorizePanel(selectedPanel);
            changedTroughLoad = false;
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
