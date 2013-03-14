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
	
	private final Account account;
	private final Client client;
	
	private ArrayList<Calendar> allCalendars;
	
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
		
		calendarView = new CalendarView();
		tabbedPane.addTab("Kalender", null, calendarView, null);
		
		Response response = client.request(new Request("GET_ALL_CALENDARS"));
		allCalendars = (ArrayList<Calendar>) response.getData()[0];
		
		System.out.println(allCalendars);
		
		this.setResizable(false);
		this.pack();
		

	}

}
