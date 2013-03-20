package cim.views.appointmentDialogs;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cim.models.Account;
import cim.models.Appointment;
import cim.util.CloakedIronManException;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAppointmentDialog extends JDialog implements ActionListener{
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
	public EditAppointmentDialog(Account account, Appointment appointment) throws CloakedIronManException {
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setTitle("Endre avtale");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 690, 713);
		
		mainPanel = new JPanel();
		mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		getContentPane().add(mainPanel, BorderLayout.NORTH);
		
//		if(account.equals(appointment.getOwner())){ //Bytt til denne n�r GUI er koblet mot resten
		
		if(true){ //Ninjatriks: endre denne til false n�r man vil jobbe (i Windowbuilder) med det som hentes i 'else'
			GridBagLayout gbl_mainPanel = new GridBagLayout();
			gbl_mainPanel.columnWidths = new int[] {400, 0, 0, 0, 0, 0, 0, 0, 30, 30};
			gbl_mainPanel.rowHeights = new int[] {225, 30, 225, 30, 100, 30, 30};
			gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			mainPanel.setLayout(gbl_mainPanel);
			
			// M�teleder f�r endre p� alt, og kan ogs� avlyse hele shiten
				
				addDetailsPanel = new AppointmentDetailsPanel(account, appointment);
				addDetailsPanel.setSize(470, 240);
				GridBagConstraints gbc_addDetailsPanel = new GridBagConstraints();
				gbc_addDetailsPanel.gridwidth = 7;
				gbc_addDetailsPanel.fill = GridBagConstraints.BOTH;
				gbc_addDetailsPanel.insets = new Insets(0, 0, 5, 5);
				gbc_addDetailsPanel.gridx = 0;
				gbc_addDetailsPanel.gridy = 0;
				mainPanel.add(addDetailsPanel, gbc_addDetailsPanel);
			
			chckbxAddParticipants = new JCheckBox("Legg til personer/grupper");
			GridBagConstraints gbc_chckbxAddParticipants = new GridBagConstraints();
			gbc_chckbxAddParticipants.fill = GridBagConstraints.BOTH;
			gbc_chckbxAddParticipants.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxAddParticipants.gridx = 0;
			gbc_chckbxAddParticipants.gridy = 1;
			mainPanel.add(chckbxAddParticipants, gbc_chckbxAddParticipants);
			
			addParticipantsPanel = new ParticipantsPanel();
			GridBagConstraints gbc_addParticipantsPanel = new GridBagConstraints();
			gbc_addParticipantsPanel.gridwidth = 7;
			gbc_addParticipantsPanel.fill = GridBagConstraints.BOTH;
			gbc_addParticipantsPanel.insets = new Insets(0, 0, 5, 5);
			gbc_addParticipantsPanel.gridx = 0;
			gbc_addParticipantsPanel.gridy = 2;
			mainPanel.add(addParticipantsPanel, gbc_addParticipantsPanel);
			
			chckbxLeggTilPersonlig = new JCheckBox("Legg til personlig alarm");
			GridBagConstraints gbc_chckbxLeggTilPersonlig = new GridBagConstraints();
			gbc_chckbxLeggTilPersonlig.fill = GridBagConstraints.BOTH;
			gbc_chckbxLeggTilPersonlig.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxLeggTilPersonlig.gridx = 0;
			gbc_chckbxLeggTilPersonlig.gridy = 3;
			mainPanel.add(chckbxLeggTilPersonlig, gbc_chckbxLeggTilPersonlig);
			
			addAlarmPanel = new AlarmPanel();
			GridBagConstraints gbc_addAlarmPanel = new GridBagConstraints();
			gbc_addAlarmPanel.gridwidth = 5;
			gbc_addAlarmPanel.fill = GridBagConstraints.BOTH;
			gbc_addAlarmPanel.insets = new Insets(0, 0, 5, 5);
			gbc_addAlarmPanel.gridx = 0;
			gbc_addAlarmPanel.gridy = 4;
			mainPanel.add(addAlarmPanel, gbc_addAlarmPanel);
			
			btnCancelAppointment = new JButton("   Avlys m\u00F8te   ");
			GridBagConstraints gbc_btnCancelAppointment = new GridBagConstraints();
			gbc_btnCancelAppointment.insets = new Insets(0, 0, 0, 5);
			gbc_btnCancelAppointment.fill = GridBagConstraints.BOTH;
			gbc_btnCancelAppointment.gridx = 1;
			gbc_btnCancelAppointment.gridy = 6;
			mainPanel.add(btnCancelAppointment, gbc_btnCancelAppointment);
			
			btnSave = new JButton("Lagre endringer");
			GridBagConstraints gbc_btnSave = new GridBagConstraints();
			gbc_btnSave.fill = GridBagConstraints.BOTH;
			gbc_btnSave.insets = new Insets(0, 0, 0, 5);
			gbc_btnSave.gridx = 2;
			gbc_btnSave.gridy = 6;
			mainPanel.add(btnSave, gbc_btnSave);
			
			btnCancelDialog = new JButton("      Avbryt      ");
			btnCancelDialog.addActionListener(this);
			GridBagConstraints gbc_btnCancelDialog = new GridBagConstraints();
			gbc_btnCancelDialog.fill = GridBagConstraints.BOTH;
			gbc_btnCancelDialog.insets = new Insets(0, 0, 0, 5);
			gbc_btnCancelDialog.gridx = 3;
			gbc_btnCancelDialog.gridy = 6;
			mainPanel.add(btnCancelDialog, gbc_btnCancelDialog);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
