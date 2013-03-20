package cim.views.appointmentDialogs;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cim.models.Account;
import cim.models.Alert;
import cim.models.Appointment;
import cim.models.Attendable;
import cim.models.Calendar;
import cim.models.Group;
import cim.models.Meeting;
import cim.models.MeetingResponse;
import cim.net.Client;
import cim.util.CloakedIronManException;
import cim.util.Helper;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class EditAppointmentDialog extends JDialog implements ActionListener{

	private Appointment appointment;
	private Account account;
	private Calendar calendar;

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
	private Alert alert;
	private ArrayList<MeetingResponse> meetingResponses;

	//	public EditAppointmentDialog(Account account, Appointment appointment){ //Bytt til denne n�r GUI er koblet mot resten
	@SuppressWarnings("unused")
	public EditAppointmentDialog(Account account, Appointment appointment2) throws CloakedIronManException {

		this.calendar = calendar;
		this.account = account;


		setModalityType(ModalityType.DOCUMENT_MODAL);
		setTitle("Endre avtale");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 690, 713);

		mainPanel = new JPanel();
		mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		getContentPane().add(mainPanel, BorderLayout.NORTH);

		//		if(account.equals(appointment.getOwner())){ //Bytt til denne n�r GUI er koblet mot resten

		//Ninjatriks: endre denne til false n�r man vil jobbe (i Windowbuilder) med det som hentes i 'else'
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] {400, 0, 0, 0, 0, 0, 0, 0, 30, 30};
		gbl_mainPanel.rowHeights = new int[] {225, 30, 225, 30, 100, 30, 30};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		mainPanel.setLayout(gbl_mainPanel);

		// M�teleder f�r endre p� alt, og kan ogs� avlyse hele shiten

		addDetailsPanel = new AppointmentDetailsPanel(account, appointment2);
		addDetailsPanel.setSize(470, 240);
		GridBagConstraints gbc_addDetailsPanel = new GridBagConstraints();
		gbc_addDetailsPanel.gridwidth = 7;
		gbc_addDetailsPanel.fill = GridBagConstraints.BOTH;
		gbc_addDetailsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_addDetailsPanel.gridx = 0;
		gbc_addDetailsPanel.gridy = 0;
		mainPanel.add(addDetailsPanel, gbc_addDetailsPanel);

		chckbxAddParticipants = new JCheckBox("Legg til personer/grupper");
		chckbxAddParticipants.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxAddParticipants.isSelected())
				{
					addParticipantsPanel.setVisible(true);
				}
				else
				{
					addParticipantsPanel.setVisible(false);
				}

			}
		});
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
		chckbxLeggTilPersonlig.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxLeggTilPersonlig.isSelected())
				{
					addAlarmPanel.setVisible(true);
				}
				else
				{
					addAlarmPanel.setVisible(false);
				}
			}
		});
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

		btnSave = new JButton("Lagre endringer");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 6;
		btnSave.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				editAppointment();

			}
		});
		mainPanel.add(btnSave, gbc_btnSave);

		btnCancelDialog = new JButton("      Avbryt      ");
		btnCancelDialog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				disposeFrame();

			}
		});
		GridBagConstraints gbc_btnCancelDialog = new GridBagConstraints();
		gbc_btnCancelDialog.fill = GridBagConstraints.BOTH;
		gbc_btnCancelDialog.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelDialog.gridx = 3;
		gbc_btnCancelDialog.gridy = 6;
		mainPanel.add(btnCancelDialog, gbc_btnCancelDialog);
		this.setVisible(true);





		//Vanlig m�tedeltager f�r kun endre sin egen alarm, ingenting annet.

		//Her m� det inn noe som henter informasjon om tid/sted osv.
		appointmentInfo = new AppointmentInfo();
		mainPanel.add(appointmentInfo);

		addAlarmPanel = new AlarmPanel();
		mainPanel.add(addAlarmPanel);

		mainPanel.add(btnCancelDialog);
	}


	public void editAppointment(){
		//TODO: Use getInvitees method in ParticipantsPanel to send invites
		int i = addDetailsPanel.getDays();
		int j = addDetailsPanel.getMonths();
		int k = addDetailsPanel.getYears();
		Date date = Helper.getDate(k,j,i);
		int a = addDetailsPanel.getHours();
		int b = addDetailsPanel.getMinutes();
		Time startTid = Helper.getTime(a,b);
		int c = addDetailsPanel.getEndHours();
		int d = addDetailsPanel.getEndMinutes();
		Time sluttTid = Helper.getTime(c,d);
		String info = addDetailsPanel.getDescription();

		// Initializes the construction of a new Appointment from the information gathered above.
		Appointment app = new Appointment(info,date,startTid,sluttTid,Client.register.getAccount());
		if(chckbxLeggTilPersonlig.isSelected())
		{
			int x = addAlarmPanel.getYear();
			int y = addAlarmPanel.getMonth();
			int z = addAlarmPanel.getDays();

			int h = addAlarmPanel.getHours();
			int m = addAlarmPanel.getMinutes();
			Timestamp time = Helper.getTime(x,y,z,h,m,0);
			Alert alert = new Alert(app,Client.register.getAccount(),time);
			setAlert(alert);

		}
		/* 
		 * Then, if the checkbox "Legg til personer/grupper" is set,
		 *  and if the addParticipantsPanel is not empty, a Meeting-instance is created and 
		 *  meeting responses will be sent.
		 */ 
		app.setOwner(Client.register.getAccount());

		/*
		 * Creating all the meeting responses
		 */
		ArrayList<Attendable> invitees = this.addParticipantsPanel.getInvitees();
		if(invitees.size() > 0)
		{
			Meeting meeting = app.toMeeting();
			setAppointment(meeting);
			meetingResponses = new ArrayList<MeetingResponse>();
			MeetingResponse mr;
			for(Attendable att : invitees) {
				if (att instanceof Account) {
					mr = new MeetingResponse((Account)att, meeting);
					meetingResponses.add(mr);
				} else if (att instanceof Group){
					for(Account member: ((Group)att).getMembers()) {
						mr = new MeetingResponse(member, meeting);
						meetingResponses.add(mr);
					}
				}
			}
		} 

		else {

			setAppointment(app);
		}



		this.disposeFrame();
	}



	public void setAlert(Alert al)
	{
		this.alert = al;
	}
	public Alert getAlert(){
		return this.alert;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	public void disposeFrame()
	{
		this.dispose();
	}
	protected void setAppointment(Appointment app) {
		this.appointment = app;
	}

	public ArrayList<MeetingResponse> getMeetingResponses(){
		return this.meetingResponses;
	}

	public Appointment getAppointment(){
		return this.appointment;
	}
}
