package cim.views;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cim.models.Account;
import cim.models.Appointment;

public class EditAppointmentDialog extends JFrame{
	// Panels
	private JPanel mainPanel;
	private AppointmentDetailsPanel addDetailsPanel;
	private ParticipantsPanel addParticipantsPanel;
	private AlarmPanel addAlarmPanel;

	// Components
	private JCheckBox chckbxLeggTilPersonlig;
	private JCheckBox chckbxAddParticipants;
	private JButton btnSave;
	private JButton btnCancelDialog;
	private JButton btnCancelAppointment;
	
	public EditAppointmentDialog(Account account, Appointment appointment){
		setTitle("Ny avtale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 571);
		
		mainPanel = new JPanel();
		mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		if(account.equals(appointment.getOwner())){
		// Møteleder får endre på alt, og kan også avlyse hele shiten
			
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
			
			btnCancelDialog = new JButton("Avbryt");
			mainPanel.add(btnCancelDialog);
			
			btnSave = new JButton("Lagre endringer");
			mainPanel.add(btnSave);
			
			btnCancelAppointment = new JButton("Avlys møte");
			mainPanel.add(btnCancelAppointment);
		
		}
		else{ 
		//Vanlig møtedeltager får kun endre sin egen alarm, ingenting annet.
			
			//Her må det inn noe som henter informasjon om tid/sted osv.
			
			addAlarmPanel = new AlarmPanel();
			mainPanel.add(addAlarmPanel);
			
			btnCancelDialog = new JButton("Avbryt");
			mainPanel.add(btnCancelDialog);
			
			btnSave = new JButton("Lagre endringer");
			mainPanel.add(btnSave);
		}
	}
}
