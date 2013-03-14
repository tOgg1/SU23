package cim.views;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class AddAppointmentDialog extends JFrame{
	private AddAppointmentDetailsPanel addDetailsPanel;
	private AddParticipantsPanel addParticipantsPanel;
	private AddAlarmPanel addAlarmPanel;
	private JPanel mainPanel;
	
	public AddAppointmentDialog(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 571);
//		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		mainPanel = new JPanel();
		mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		addDetailsPanel = new AddAppointmentDetailsPanel();
		addDetailsPanel.setSize(470, 240);
		mainPanel.add(addDetailsPanel);
		
		JCheckBox chckbxAddParticipants = new JCheckBox("Legg til personer/grupper");
		mainPanel.add(chckbxAddParticipants);
		
		addParticipantsPanel = new AddParticipantsPanel();
		mainPanel.add(addParticipantsPanel);
		
		JCheckBox chckbxLeggTilPersonlig = new JCheckBox("Legg til personlig alarm");
		mainPanel.add(chckbxLeggTilPersonlig);
		
		addAlarmPanel = new AddAlarmPanel();
		mainPanel.add(addAlarmPanel);
		
		JButton btnCancel = new JButton("Avbryt");
		mainPanel.add(btnCancel);
		
		JButton btnSave = new JButton("Legg til i kalender");
		mainPanel.add(btnSave);
	}
}
