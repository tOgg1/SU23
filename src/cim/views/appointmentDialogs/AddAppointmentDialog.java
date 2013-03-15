package cim.views.appointmentDialogs;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class AddAppointmentDialog extends JFrame{

	// Panels
	private JPanel mainPanel;
	private AppointmentDetailsPanel addDetailsPanel;
	private ParticipantsPanel addParticipantsPanel;
	private AlarmPanel addAlarmPanel;

	// Components
	private JCheckBox chckbxLeggTilPersonlig;
	private JCheckBox chckbxAddParticipants;
	private JButton btnSave;
	private JButton btnCancel;
	
	public AddAppointmentDialog(){
		setTitle("Ny avtale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 571);
		
		mainPanel = new JPanel();
		mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		addDetailsPanel = new AppointmentDetailsPanel();
		addDetailsPanel.setSize(470, 240);
		mainPanel.add(addDetailsPanel);
		
		chckbxAddParticipants = new JCheckBox("Legg til personer/grupper");
		mainPanel.add(chckbxAddParticipants);
		
		addParticipantsPanel = new ParticipantsPanel();
		mainPanel.add(addParticipantsPanel);
		
		chckbxLeggTilPersonlig = new JCheckBox("Legg til personlig alarm");
		mainPanel.add(chckbxLeggTilPersonlig);
		
		addAlarmPanel = new AlarmPanel();
		mainPanel.add(addAlarmPanel);
		
		btnCancel = new JButton("Avbryt");
		mainPanel.add(btnCancel);
		
		btnSave = new JButton("Legg til i kalender");
		mainPanel.add(btnSave);
		pack();
	}
}
