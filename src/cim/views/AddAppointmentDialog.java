package cim.views;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Component;

public class AddAppointmentDialog extends JFrame{
	private AddAppointmentDetailsPanel addDetailsPanel;
	private AddParticipantsPanel addParticipantsPanel;
	private AddAlarmPanel addAlarmPanel;
	private JPanel mainPanel;
	
	public AddAppointmentDialog(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 571);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		mainPanel = new JPanel();
		mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new GridLayout(6, 4, 0, 0));
		
		addDetailsPanel = new AddAppointmentDetailsPanel();
		addDetailsPanel.setSize(470, 240);
		mainPanel.add(addDetailsPanel);
		
		addParticipantsPanel = new AddParticipantsPanel();
		mainPanel.add(addParticipantsPanel);
		
		addAlarmPanel = new AddAlarmPanel();
		mainPanel.add(addAlarmPanel);
	}
}
