package cim.views;

import cim.net.Client;
import cim.util.CloakedIronManException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ApplicationWindow extends JFrame implements ChangeListener {

	/**
	 * Because dunno
	 */
	private static final long serialVersionUID = -1769196092599686176L;

	
	private JTabbedPane tabbedPane;
	private CalendarView calendarView;
	private IncomingAppointmentsView incomingAppointmentsView;
	private AlertsView alertsView;
	private ManageCalendarsView manageCalendarsView;
	
	
//	private static JTextField txtMandag;
//	private static JTextField txtTirsdag;
//	private static JTextField txtOnsdag;
//	private static JTextField txtTorsdag;
//	private static JTextField txtFredag;
//	private static JTextField txtLrdag;
//	private static JTextField txtSndag;
//	private static JTextField txtUke;
	/**
	 * Main methd for testing purposes.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ApplicationWindow frame = new ApplicationWindow();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 * @throws CloakedIronManException 
	 */
	public ApplicationWindow() throws CloakedIronManException {
//	public ApplicationWindow() {
		
		/*
		 * Fetch references
		 */
		
		/*
		 * CREATE LAYOUT
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 375);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setVisible(true);
		this.setContentPane(tabbedPane);
		
		calendarView = new CalendarView(this);
		Client.register.addPropertyChangeListener(calendarView);
		tabbedPane.addTab("Kalender", null, calendarView, null);
		
		incomingAppointmentsView = new IncomingAppointmentsView();
		incomingAppointmentsView.addPropertyChangeListener(new MeetingResponsePropertyChangeListener());
		
		// Møter til godkjenning burde ha en hjelpeklasse som bygger strengen og 
		// legger til eventuelle "(n)" som kan representere ant. ubehandlede innkallelser.
		// Det er laget. @hawk
		
		tabbedPane.addTab("Møter til godkjenning", null, incomingAppointmentsView, null);
		incomingAppointmentsView.setModel(Client.register.getMeetingResponsesToAccount());
		
		alertsView = new AlertsView();
		alertsView.addPropertyChangeListener(new AlertViewPropertyChangeListener());
		tabbedPane.addTab("Varsler", null, alertsView, null);
		
		manageCalendarsView = new ManageCalendarsView(Client.register.activeCalendars(), Client.register.getAllCalendars(), this);
		tabbedPane.addTab("Administrer kalendere", null, manageCalendarsView, null);

        tabbedPane.addChangeListener(this);

        System.out.println(Client.register.getAlerts());

		/*
		Response response = client.request(new Request("GET_ALL_CALENDARS"));
		allCalendars = (ArrayList<Calendar>) response.getData()[0];
		
		response = client.request(new Request("GET_ALL_CALENDARS_TO_ACCOUNT", account));
				
		myCalendars = (ArrayList<Calendar>) response.getData()[0];
		
		response = client.request(new Request("GET_ALL_ALARMS_TO_ACCOUNT", account));
		
		System.out.println(myCalendars.size());
		
		System.out.println(allCalendars); */
		
		this.setResizable(false);
		this.pack();
		

	}
	
	public CalendarView getCalendarView()
    {
        return calendarView;
    }

    @Override
    public void requestFocus() {
        tabbedPane.requestFocus();
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        if(tabbedPane.getSelectedComponent() == manageCalendarsView)
        {
            manageCalendarsView.requestFocus();
            manageCalendarsView.reload(Client.register.getManagedCalendars(), Client.register.activeCalendars());
        }
    }

    private class MeetingResponsePropertyChangeListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName() == "numMeetingResponses"){
				int iNewVal = (int)evt.getNewValue();
				String text = "Møter til godkjenning";
				if (iNewVal > 0) {
					text = "Møter til godkjenning (" + iNewVal + ")";
				}
				tabbedPane.setTitleAt(1, text);
			}
			
		}
		
	}
    
    private class AlertViewPropertyChangeListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
//			String propertyName = evt.getPropertyName();
//			if(propertyName.equals("unreadElementsCount")){
			if(evt.getPropertyName() == "unreadElementsCount"){
				int newValue = (int)evt.getNewValue();
				String varsler = "Varsler";
				if(newValue > 0){
					varsler += " ("+newValue+")";
				}
			}
		}
    	
    }

}
