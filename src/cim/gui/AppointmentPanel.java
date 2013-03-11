package cim.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cim.models.Account;
import cim.models.Room;

public class AppointmentPanel extends JPanel{

//	private Account owner;
//	private Date startDate;
//	private Date endDate;
//	private String info;
//	protected Room room;
	private enum Minutes {A00,B05,C10,D15,E20,F25,G30,H35,I40,J45,K50,L55};
	private enum Month {Januar,Februar,Mars,April,Mai,Juni,Juli};
	private enum Hours {A06,B07,C08,D09,E10,F11,G12,H13,I14,J15,K16,L17,M18,N19,O20,P21};

	private JTextField info;
	private JTextField dateDay;
	private JTextField dateYear;
	private JComboBox dateMonth;
	private JComboBox startHours;
	private JComboBox startMin;
	private JComboBox endHours;
	private JComboBox endMin;
	
	public AppointmentPanel(){s
		
		info = new JTextField();
		info.setColumns(40);
		info.addActionListener();
		
		dateDay = new JTextField();
		dateDay.setColumns(2);
		dateDay.addActionListener();
		
		dateYear = new JTextField();
		dateYear.setColumns(4);
		dateYear.addActionListener();
		
		dateMonth = new JComboBox<>(Month.values());
		dateMonth.addActionListener();
		
		startHours = new JComboBox<>(Hours.values());
		startMin = new JComboBox<>(Minutes.values());
		endHours = new JComboBox<>(Hours.values());
		endMin = new JComboBox<>(Minutes.values());
	}
	


	public static void main(String args[]){
		JFrame frame = new JFrame("New Appointment");
		frame.getContentPane().add(new AppointmentPanel());
		frame.pack();
		frame.setVisible(true);
	}
}
