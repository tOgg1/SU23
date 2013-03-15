package cim.views.appointmentDialogs;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import cim.models.Appointment;

import java.awt.Dialog.ModalityType;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AddAppointmentDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7800782603404937292L;
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
	private Appointment appointment;
	
	public AddAppointmentDialog(JFrame parent){
		super(parent);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setTitle("Ny avtale");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 571);
		
		mainPanel = new JPanel();
		mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		addDetailsPanel = new AppointmentDetailsPanel();
		addDetailsPanel.setSize(470, 240);
		mainPanel.add(addDetailsPanel);
		
		chckbxAddParticipants = new JCheckBox("Legg til personer/grupper");
		chckbxAddParticipants.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				
				
			}
		});
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
	}
	
	public Appointment getAppointment() {
		return this.appointment;
	}
	
	
}
