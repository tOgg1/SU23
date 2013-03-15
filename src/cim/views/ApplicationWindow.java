package cim.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cim.models.*;
import cim.net.packet.Response;

import cim.models.Account;
import cim.net.Client;
import cim.net.packet.Request;
import cim.util.CloakedIronManException;

import javax.swing.JTabbedPane;

public class ApplicationWindow extends JFrame {

	/**
	 * Because dunno
	 */
	private static final long serialVersionUID = -1769196092599686176L;

	
	private JTabbedPane tabbedPane;
	private CalendarView calendarView;
	private IncommingAppointmentsView incommingAppointmentsView;
	private AlertsView alertsView;
	private ManageCalendarsView manageCalendarsView;
	
	private final Account account;
	private final Client client;
	
	private ArrayList<Calendar> allCalendars;
	private ArrayList<Alert> alerts;
	private ArrayList<Calendar> myCalendars;
	
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
	public ApplicationWindow(Client c, Account a) throws CloakedIronManException {
//	public ApplicationWindow() {
		this.client = c;
		this.account = a;
		
		/*
		 * CREATE LAYOUT
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 375);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setVisible(true);
		this.setContentPane(tabbedPane);
		
		calendarView = new CalendarView(this);
		tabbedPane.addTab("Kalender", null, calendarView, null);
		
		incommingAppointmentsView = new IncommingAppointmentsView();
		// Møter til godkjenning burde ha en hjelpeklasse som bygger strengen og 
		// legger til eventuelle "(n)" som kan representere ant. ubehandlede innkallelser.
		tabbedPane.addTab("Møter til godkjenning", null, incommingAppointmentsView, null);
		
		alertsView = new AlertsView();
		tabbedPane.addTab("Varsler", null, alertsView, null);
		
		manageCalendarsView = new ManageCalendarsView();
		tabbedPane.addTab("Administrer kalendere", null, manageCalendarsView, null);
		
		Response response = client.request(new Request("GET_ALL_CALENDARS"));
		allCalendars = (ArrayList<Calendar>) response.getData()[0];
		
		response = client.request(new Request("GET_ALL_CALENDARS_TO_ACCOUNT", account));
				
		myCalendars = (ArrayList<Calendar>) response.getData()[0];
		
		response = client.request(new Request("GET_ALL_ALARMS_TO_ACCOUNT", account));
		
		System.out.println(myCalendars.size());
		
		System.out.println(allCalendars);
		
		this.setResizable(false);
		this.pack();
		

	}
	
	public Response request(Request req) throws CloakedIronManException {
		return this.client.request(req);
	}

}
