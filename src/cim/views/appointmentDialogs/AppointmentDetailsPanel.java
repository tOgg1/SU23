package cim.views.appointmentDialogs;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JList;

import cim.models.Account;
import cim.models.Appointment;
import cim.models.Calendar;
import cim.models.Room;
import cim.net.Client;
import cim.util.CloakedIronManException;
import cim.util.Helper;

public class AppointmentDetailsPanel extends JPanel implements ActionListener {
	private JTextField txtDescription;
	private JTextField txtLocation;

	private JComboBox comBoxMonth;
	private JComboBox comBoxHours;
	private JComboBox comBoxMinutes;
	private JComboBox comBoxEndHours;
	private JComboBox comBoxEndMinutes;
	private JComboBox comBoxRoomSize;
	private JComboBox comBoxYear;
	private JComboBox comBoxDays;

	private JComboBox<Calendar> comBoxCalendars;

	private final ButtonGroup buttonGroupLocation = new ButtonGroup();
	private JRadioButton rbtnLocation;
	private JRadioButton rbtnRoomReservation;

	private JList listAvailableRooms;
	private ArrayList<Room> arrayListRooms;
	private DefaultListModel<Room> roomListModel;
	private JLabel lblCalendar;
	
	private Room room;

	public AppointmentDetailsPanel() {
		this.setSize(470, 340);
		setLayout(null);

		JLabel lblDescription = new JLabel("Beskrivelse");
		lblDescription.setBounds(10, 24, 71, 14);
		add(lblDescription);

		txtDescription = new JTextField();
		txtDescription.setColumns(30);
		txtDescription.setBounds(91, 21, 176, 20);
		add(txtDescription);

		JLabel lblDate = new JLabel("Dato");
		lblDate.setBounds(10, 50, 53, 14);
		add(lblDate);

		comBoxMonth = new JComboBox();
		int[] monthArray = new int[12];
		for(int i = 0; i < 12; i++)
		{
			monthArray[i] = i;
			comBoxMonth.addItem(monthArray[i]+1);
		}
		comBoxMonth.setToolTipText("");
		comBoxMonth.setBounds(142, 49, 48, 20);
		comBoxMonth.addActionListener(this);
		add(comBoxMonth);
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setBounds(10, 76, 54, 14);
		add(lblStart);

		comBoxHours = new JComboBox();
		int[] hours = new int[24];
		for(int i = 0; i < 24; i++)
		{
			hours[i] = i;
			comBoxHours.addItem(hours[i]+1);
		}

		comBoxHours.setBounds(71, 76, 48, 20);
		comBoxHours.addActionListener(this);
		add(comBoxHours);

		JLabel lblColon = new JLabel(":");
		lblColon.setBounds(129, 76, 12, 14);
		add(lblColon);

		comBoxMinutes = new JComboBox();
		int[] minutes = {00,15,30,45};
		for(int i = 0; i < 4; i++)
		{
			comBoxMinutes.addItem(minutes[i]);
		}
		comBoxMinutes.setBounds(142, 76, 48, 20);
		comBoxMinutes.addActionListener(this);
		add(comBoxMinutes);

		JLabel lblEnd = new JLabel("Slutt");
		lblEnd.setBounds(10, 104, 53, 14);
		add(lblEnd);

		comBoxEndHours = new JComboBox();
		for(int i = 0; i < 24; i++)
		{
			hours[i] = i;
			comBoxEndHours.addItem(hours[i]+1);
		}
		comBoxEndHours.setBounds(71, 101, 48, 20);
		comBoxEndHours.addActionListener(this);
		add(comBoxEndHours);

		JLabel lblColon2 = new JLabel(":");
		lblColon2.setBounds(128, 104, 4, 14);
		add(lblColon2);

		comBoxEndMinutes = new JComboBox();
		for(int i = 0; i < 4; i++)
		{
			comBoxEndMinutes.addItem(minutes[i]);
		}
		comBoxEndMinutes.setBounds(142, 101, 48, 20);
		comBoxEndMinutes.addActionListener(this);
		add(comBoxEndMinutes);

		rbtnLocation = new JRadioButton("Sted");
		buttonGroupLocation.add(rbtnLocation);
		rbtnLocation.setSelected(true);
		rbtnLocation.setBounds(278, 20, 67, 23);
		add(rbtnLocation);

		rbtnRoomReservation = new JRadioButton("Reserver rom");
		buttonGroupLocation.add(rbtnRoomReservation);
		rbtnRoomReservation.setBounds(278, 46, 141, 23);
		add(rbtnRoomReservation);

		txtLocation = new JTextField();
		txtLocation.setBounds(351, 21, 109, 20);
		add(txtLocation);
		txtLocation.setColumns(10);

		JLabel lblRoomSize = new JLabel("Romst\u00F8rrelse");
		lblRoomSize.setBounds(278, 76, 109, 14);
		add(lblRoomSize);

		comBoxRoomSize = new JComboBox();
		comBoxRoomSize.setBounds(401, 73, 59, 20);
		add(comBoxRoomSize);

		listAvailableRooms = new JList();
		roomListModel = new DefaultListModel<Room>();
		arrayListRooms = Client.register.getRooms();
		for(Room room : arrayListRooms)
		{
			roomListModel.addElement(room);
		}
		listAvailableRooms.setModel(roomListModel);
		listAvailableRooms.setBounds(278, 142, 182, 80);
		add(listAvailableRooms);

		JLabel lblAvailableRooms = new JLabel("Ledige rom (st\u00F8rrelse)");
		lblAvailableRooms.setBounds(278, 104, 160, 14);
		add(lblAvailableRooms);

		comBoxDays = new JComboBox();
		int[] days = new int[31];
		for(int i = 0; i < 31; i++)
		{
			days[i] = i;
			comBoxDays.addItem(days[i]+1);
		}
		comBoxDays.setBounds(71, 49, 48, 20);
		comBoxDays.addActionListener(this);
		add(comBoxDays);

		comBoxYear = new JComboBox();
		int[] years = new int[10];
		for(int i = 0; i < 10; i++)
		{
			years[i] = i+2013; 
			comBoxYear.addItem(years[i]);
		}
		comBoxYear.setBounds(210, 49, 54, 20);
		comBoxYear.addActionListener(this);
		add(comBoxYear);

		lblCalendar = new JLabel("Kalender");
		lblCalendar.setBounds(10, 165, 71, 14);
		add(lblCalendar);

		comBoxCalendars = new JComboBox();
		comBoxCalendars.setBounds(71, 162, 193, 20);
		try {
			for(Calendar c : Client.register.getAllCalendars()) {
				comBoxCalendars.addItem(c);
			}
			comBoxCalendars.setSelectedItem(Client.register.getCalendarByAccount(Client.register.getAccount()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 30; i+= 5){
			comBoxRoomSize.addItem(i);
			
		}


		add(comBoxCalendars);
		comBoxCalendars.setSelectedIndex(0);



	}
	
	public AppointmentDetailsPanel(Account account, Appointment appointment) {
		this();
		String[] dateSplit = appointment.getDate().toString().split("-");
		comBoxMonth.setSelectedItem(Integer.parseInt(dateSplit[1]));
		comBoxDays.setSelectedItem(Integer.parseInt(dateSplit[2]));
		comBoxYear.setSelectedItem(Integer.parseInt(dateSplit[0]));
		
		String[] startSplit = appointment.getStart().toString().split(":");
		
		
		comBoxHours.setSelectedItem(Integer.parseInt(startSplit[0]));
		comBoxMinutes.setSelectedItem(Integer.parseInt(startSplit[1]));
		
		String[] endSplit = appointment.getEnd().toString().split(":");
		
		comBoxEndHours.setSelectedItem(Integer.parseInt(endSplit[0]));
		comBoxEndMinutes.setSelectedItem(Integer.parseInt(endSplit[1]));
		
		
		txtDescription.setText(appointment.getName());
		txtLocation.setText(appointment.getPlace());
		
	}

	public int getDays()
	{
		return (int)comBoxDays.getSelectedItem();
	}
	public int getHours()
	{
		return (int)comBoxHours.getSelectedItem();
	}
	public int getYears()
	{
		return (int)comBoxYear.getSelectedItem();
	}
	public int getMinutes()
	{
		return (int)comBoxMinutes.getSelectedItem();
	}
	public int getEndHours()
	{
		return (int)comBoxEndHours.getSelectedItem();
	}
	public int getEndMinutes()
	{
		return (int)comBoxEndMinutes.getSelectedItem();
	}
	public int getMonths()
	{
		return (int)comBoxMonth.getSelectedItem();
	}
	
	public String getPlace(){
		return txtLocation.getText();
		
	}
	public Room getRoom(){
		return (Room)listAvailableRooms.getSelectedValue();
	}

	public Calendar getCalendar() {
		return (Calendar)this.comBoxCalendars.getSelectedItem();
	}
	public String getDescription()
	{
		return txtDescription.getText();
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		roomListModel = new DefaultListModel<Room>();
		ArrayList<Room> availableRooms;
		try {
			availableRooms = Client.register.getAvailableRooms(Helper.getDate(getYears(), getMonths(), getDays()), Helper.getTime(getHours(), getMinutes()), Helper.getTime(getEndHours(),getEndMinutes()));
			for(Room room : availableRooms)
			{
					roomListModel.addElement(room);
			}
			listAvailableRooms.setModel(roomListModel);
			
		} catch (CloakedIronManException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
