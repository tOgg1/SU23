package cim.views.appointmentDialogs;

import cim.models.*;
import cim.models.MeetingResponse.Response;
import cim.net.Client;
import cim.util.CloakedIronManException;
import cim.util.Helper;
import cim.views.ApplicationWindow;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AddAppointmentDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7800782603404937292L;
	// Panels
	private JPanel mainPanel;
	AppointmentDetailsPanel addDetailsPanel;
	private ParticipantsPanel addParticipantsPanel;
	private AlarmPanel addAlarmPanel;

	// Components
	private JCheckBox chckbxLeggTilPersonlig;
	private JCheckBox chckbxAddParticipants;
	private JButton btnSave;
	private JButton btnCancel;
	
	
	private Appointment appointment;
	private ArrayList<MeetingResponse> meetingResponses;
	private Alert alert;
	Calendar calendar;
	
	private PropertyChangeSupport pcs;

    ApplicationWindow application;

	public AddAppointmentDialog(ApplicationWindow application) throws CloakedIronManException{
		super(application);
		setModalityType(ModalityType.DOCUMENT_MODAL);
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
		addParticipantsPanel.setVisible(false);

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
		gbc_addAlarmPanel.insets = new Insets(0, 0, 5, 0);
		gbc_addAlarmPanel.gridx = 0;
		gbc_addAlarmPanel.gridy = 4;
		mainPanel.add(addAlarmPanel, gbc_addAlarmPanel);
		addAlarmPanel.setVisible(false);

		btnSave = new JButton("Legg til i kalender");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {

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
				String place = addDetailsPanel.getPlace();
				
				Room room = addDetailsPanel.getRoom();
				// Initializes the construction of a new Appointment from the information gathered above.
				Appointment app = new Appointment(info,date,startTid,sluttTid,Client.register.getAccount());
				if (room != null){
					app.setRoom(room);
				}
				else{
				app.setPlace(place);
				}
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
				Account loggedInAccount = Client.register.getAccount();
				app.setOwner(loggedInAccount);
				/*
				 * Creating all the meeting responses
				 */
                ArrayList<Attendable> invitees = AddAppointmentDialog.this.addParticipantsPanel.getInvitees();
                if(invitees.size() > 0)
                {
                	
                    Meeting meeting = app.toMeeting();
                    setAppointment(meeting);
                    meetingResponses = new ArrayList<MeetingResponse>();

                    MeetingResponse mr;
                    for(Attendable att : invitees) {
	                	if (att instanceof Account) {
	                		if(att.equals(loggedInAccount)) {
	                			continue; // Do not add logged in account here
	                		}
	                		mr = new MeetingResponse((Account)att, meeting);
	                		meetingResponses.add(mr);
	                	} else if (att instanceof Group){
	                		for(Account member: ((Group)att).getMembers()) {
	                			if(att.equals(loggedInAccount)) {
	                				continue;
	                			}
	                			mr = new MeetingResponse(member, meeting);
	                			meetingResponses.add(mr);
	                		}
	                	}
	                }
                    // At last, add oneself
                    MeetingResponse mr2 = new MeetingResponse(loggedInAccount, meeting);
                    mr2.setResponse(Response.ATTENDING);
                    meetingResponses.add(mr2);
                    
                } 
                
                else {
                	Alert alert = null;
                	if(chckbxLeggTilPersonlig.isSelected())
                    {
                    	int x = addAlarmPanel.getYear();
                    	int y = addAlarmPanel.getMonth();
                    	int z = addAlarmPanel.getDays();
                    	
                    	int h = addAlarmPanel.getHours();
                    	int m = addAlarmPanel.getMinutes();
                    	Timestamp time = Helper.getTime(x,y,z,h,m,0);
                    	alert = new Alert(app,Client.register.getAccount(),time);
                    	
                    }
                	app.setAlert(alert);
                	setAppointment(app);
                	
                
                }
                
                // Setting calendar
                AddAppointmentDialog.this.calendar = AddAppointmentDialog.this.addDetailsPanel.getCalendar();

                // ?? pcs.firePropertyChange("createApp", null, app);
                
				disposeFrame();
			}
		});


		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.fill = GridBagConstraints.VERTICAL;
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 6;
		mainPanel.add(btnSave, gbc_btnSave);

		btnCancel = new JButton("      Avbryt      ");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				disposeFrame();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.fill = GridBagConstraints.VERTICAL;
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 6;
		mainPanel.add(btnCancel, gbc_btnCancel);
		pcs = new PropertyChangeSupport(this);
	}

	protected void setAppointment(Appointment app) {
		this.appointment = app;
		
	}

	public Appointment getAppointment() {
		return this.appointment;
	}
	
	public Alert getAlert() {
		return this.alert;
	}
	public void setAlert(Alert al)
	{
		this.alert = al;
	}
	
	public Calendar getCalendar() {
		return this.calendar;
	}
	
	public ArrayList<MeetingResponse> getMeetingResponses() {
		return this.meetingResponses;
	}
	
	public void disposeFrame()
	{
		this.dispose();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		this.pcs.addPropertyChangeListener(listener);
	}

}
