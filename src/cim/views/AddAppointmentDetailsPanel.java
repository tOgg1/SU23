package cim.views;

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

public class AddAppointmentDetailsPanel extends JPanel {
	private JTextField txtDescription;
	private JTextField txtDay;
	private JTextField txtYear;
	private JTextField txtLocation;
	private final ButtonGroup buttonGroupLocation = new ButtonGroup();
	public AddAppointmentDetailsPanel() {
		setLayout(null);
		
		JLabel lblDescription = new JLabel("Beskrivelse");
		lblDescription.setBounds(10, 24, 53, 14);
		add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(30);
		txtDescription.setBounds(73, 21, 176, 20);
		add(txtDescription);
		
		JLabel lblDate = new JLabel("Dato");
		lblDate.setBounds(40, 50, 23, 14);
		add(lblDate);
		
		txtDay = new JTextField();
		txtDay.setText("DD");
		txtDay.setColumns(2);
		txtDay.setBounds(73, 47, 48, 20);
		add(txtDay);
		
		JComboBox comBoxMonth = new JComboBox();
		comBoxMonth.setToolTipText("");
		comBoxMonth.setBounds(131, 47, 49, 20);
		add(comBoxMonth);
		
		txtYear = new JTextField();
		txtYear.setText("YYYY");
		txtYear.setColumns(4);
		txtYear.setBounds(190, 47, 59, 20);
		add(txtYear);
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setBounds(40, 76, 24, 14);
		add(lblStart);
		
		JComboBox comBoxStartHH = new JComboBox();
		comBoxStartHH.setBounds(74, 73, 48, 20);
		add(comBoxStartHH);
		
		JLabel lblColon = new JLabel(":");
		lblColon.setBounds(129, 76, 4, 14);
		add(lblColon);
		
		JComboBox comBoxStartMM = new JComboBox();
		comBoxStartMM.setBounds(142, 73, 59, 20);
		add(comBoxStartMM);
		
		JLabel lblEnd = new JLabel("Slutt");
		lblEnd.setBounds(41, 104, 22, 14);
		add(lblEnd);
		
		JComboBox comBoxEndHH = new JComboBox();
		comBoxEndHH.setBounds(73, 101, 48, 20);
		add(comBoxEndHH);
		
		JLabel lblColon2 = new JLabel(":");
		lblColon2.setBounds(128, 104, 4, 14);
		add(lblColon2);
		
		JComboBox comBoxEndMM = new JComboBox();
		comBoxEndMM.setBounds(141, 101, 59, 20);
		add(comBoxEndMM);
		
		JRadioButton rbtnLocation = new JRadioButton("Sted");
		buttonGroupLocation.add(rbtnLocation);
		rbtnLocation.setSelected(true);
		rbtnLocation.setBounds(278, 20, 47, 23);
		add(rbtnLocation);
		
		JRadioButton rbtnRoomReservation = new JRadioButton("Reserver rom");
		buttonGroupLocation.add(rbtnRoomReservation);
		rbtnRoomReservation.setBounds(278, 46, 91, 23);
		add(rbtnRoomReservation);
		
		txtLocation = new JTextField();
		txtLocation.setBounds(331, 21, 109, 20);
		add(txtLocation);
		txtLocation.setColumns(10);
		
		JLabel lblRoomSize = new JLabel("Romst\u00F8rrelse");
		lblRoomSize.setBounds(306, 76, 63, 14);
		add(lblRoomSize);
		
		JComboBox comBoxRoomSize = new JComboBox();
		comBoxRoomSize.setBounds(388, 70, 59, 20);
		add(comBoxRoomSize);
		
		JList listAvailableRooms = new JList();
		listAvailableRooms.setBounds(306, 129, 141, 80);
		add(listAvailableRooms);
		
		JLabel lblAvailableRooms = new JLabel("Ledige rom (st\u00F8rrelse)");
		lblAvailableRooms.setBounds(306, 104, 105, 14);
		add(lblAvailableRooms);
		

		
		
	}
}
