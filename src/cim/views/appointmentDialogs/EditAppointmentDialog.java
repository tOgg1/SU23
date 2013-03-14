package cim.views.appointmentDialogs;

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
	private AppointmentInfo appointmentInfo;

	// Components
	private JCheckBox chckbxLeggTilPersonlig;
	private JCheckBox chckbxAddParticipants;
	private JButton btnSave;
	private JButton btnCancelDialog;
	private JButton btnCancelAppointment;
	
//	public EditAppointmentDialog(Account account, Appointment appointment){ //Bytt til denne n�r GUI er koblet mot resten
	public EditAppointmentDialog(){
		setTitle("Ny avtale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 571);
		
		mainPanel = new JPanel();
		mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
//		if(account.equals(appointment.getOwner())){ //Bytt til denne n�r GUI er koblet mot resten
		
		if(true){ //Ninjatriks: endre denne til false n�r man vil jobbe (i Windowbuilder) med det som hentes i 'else'
		
		// M�teleder f�r endre p� alt, og kan ogs� avlyse hele shiten
			
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
			
			btnCancelAppointment = new JButton("Avlys m�te");
			mainPanel.add(btnCancelAppointment);
		
		}
		else{ 
		//Vanlig m�tedeltager f�r kun endre sin egen alarm, ingenting annet.
			
			//Her m� det inn noe som henter informasjon om tid/sted osv.
			appointmentInfo = new AppointmentInfo();
			mainPanel.add(appointmentInfo);
			
			addAlarmPanel = new AlarmPanel();
			mainPanel.add(addAlarmPanel);
			
			btnCancelDialog = new JButton("Avbryt");
			mainPanel.add(btnCancelDialog);
			
			btnSave = new JButton("Lagre endringer");
			mainPanel.add(btnSave);
		}
	}
}
