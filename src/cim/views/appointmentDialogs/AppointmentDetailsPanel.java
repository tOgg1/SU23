package cim.views.appointmentDialogs;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JList;

public class AppointmentDetailsPanel extends JPanel {
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
	
	private final ButtonGroup buttonGroupLocation = new ButtonGroup();
	private JRadioButton rbtnLocation;
	private JRadioButton rbtnRoomReservation;
	
	private JList listAvailableRooms;
	
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
		listAvailableRooms.setBounds(278, 143, 141, 80);
		add(listAvailableRooms);
		
		JLabel lblAvailableRooms = new JLabel("Ledige rom (st\u00F8rrelse)");
		lblAvailableRooms.setBounds(278, 104, 160, 14);
		add(lblAvailableRooms);
		
		JComboBox comBoxDay = new JComboBox();
		int[] days = new int[31];
		for(int i = 0; i < 31; i++)
		{
			days[i] = i;
			comBoxDay.addItem(days[i]+1);
		}
		comBoxDay.setBounds(71, 49, 48, 20);
		add(comBoxDay);
		
		comBoxYear = new JComboBox();
		int[] years = new int[10];
		for(int i = 0; i < 10; i++)
		{
			years[i] = i+2013; 
			comBoxYear.addItem(years[i]);
		}
		comBoxYear.setBounds(210, 49, 54, 20);
		add(comBoxYear);
		

		
		
	}
	public int getDays()
	{
		return (int)comBoxDays.getSelectedItem();
	}
}
