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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;

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

	
	private final JFrame application;
	
	public AddAppointmentDialog(JFrame application){
		super(application);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		
		this.application = application;
		setTitle("Ny avtale");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 645, 716);
		
		mainPanel = new JPanel();
		mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		getContentPane().add(mainPanel, BorderLayout.NORTH);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] {400, 0, 0, 0, 0, 30};
		gbl_mainPanel.rowHeights = new int[] {225, 30, 225, 30, 100, 30, 30};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		mainPanel.setLayout(gbl_mainPanel);
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
		
		addDetailsPanel = new AppointmentDetailsPanel();
		addDetailsPanel.setSize(470, 240);
		GridBagConstraints gbc_addDetailsPanel = new GridBagConstraints();
		gbc_addDetailsPanel.gridwidth = 5;
		gbc_addDetailsPanel.fill = GridBagConstraints.BOTH;
		gbc_addDetailsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_addDetailsPanel.gridx = 0;
		gbc_addDetailsPanel.gridy = 0;
		mainPanel.add(addDetailsPanel, gbc_addDetailsPanel);
		GridBagConstraints gbc_chckbxAddParticipants = new GridBagConstraints();
		gbc_chckbxAddParticipants.fill = GridBagConstraints.BOTH;
		gbc_chckbxAddParticipants.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAddParticipants.gridx = 0;
		gbc_chckbxAddParticipants.gridy = 1;
		mainPanel.add(chckbxAddParticipants, gbc_chckbxAddParticipants);
		
		
		addParticipantsPanel = new ParticipantsPanel();
		GridBagConstraints gbc_addParticipantsPanel = new GridBagConstraints();
		gbc_addParticipantsPanel.gridwidth = 5;
		gbc_addParticipantsPanel.fill = GridBagConstraints.BOTH;
		gbc_addParticipantsPanel.insets = new Insets(0, 0, 5, 0);
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
		gbc_addAlarmPanel.insets = new Insets(0, 0, 5, 0);
		gbc_addAlarmPanel.gridx = 0;
		gbc_addAlarmPanel.gridy = 4;
		mainPanel.add(addAlarmPanel, gbc_addAlarmPanel);

		mainPanel.add(chckbxAddParticipants);


		addParticipantsPanel = new ParticipantsPanel();
		addParticipantsPanel.setVisible(false);
		mainPanel.add(addParticipantsPanel);

		chckbxLeggTilPersonlig = new JCheckBox("Legg til personlig alarm");
		chckbxLeggTilPersonlig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxLeggTilPersonlig.isSelected())
				{
					System.out.println("checked");
					addAlarmPanel.setVisible(true);
				}
				else
				{
					System.out.println("not checked");
					addAlarmPanel.setVisible(false);
				}
			}
		});
		mainPanel.add(chckbxLeggTilPersonlig);

		addAlarmPanel = new AlarmPanel();
		addAlarmPanel.setVisible(false);
		mainPanel.add(addAlarmPanel);


		btnCancel = new JButton("Avbryt");
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				disposeFrame();
			}
		});

		
		btnSave = new JButton("Legg til i kalender");
		
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.fill = GridBagConstraints.VERTICAL;
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 6;
		mainPanel.add(btnSave, gbc_btnSave);
		
		btnCancel = new JButton("      Avbryt      ");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.fill = GridBagConstraints.VERTICAL;
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 6;
		mainPanel.add(btnCancel, gbc_btnCancel);

		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
		});

		mainPanel.add(btnSave);
//		pack();

	}
	
	public Appointment getAppointment() {
		return this.appointment;
	}
	
	
}
