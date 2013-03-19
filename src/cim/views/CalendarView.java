package cim.views;

import cim.models.Alert;
import cim.models.Appointment;
import cim.models.Calendar;
import cim.models.Meeting;
import cim.models.MeetingResponse;
import cim.net.Client;
import cim.util.CloakedIronManException;
import cim.util.Helper;
import cim.views.appointmentDialogs.AddAppointmentDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CalendarView extends JPanel implements PropertyChangeListener {
	private static JTextField txtMandag;
	private static JTextField txtTirsdag;
	private static JTextField txtOnsdag;
	private static JTextField txtTorsdag;
	private static JTextField txtFredag;
	private static JTextField txtLrdag;
	private static JTextField txtSndag;
	private static JTextField txtUke;
	
	private ArrayList<Calendar> myCalendars;
	
	private DayList mandag;
	private DayList tirsdag;
	private DayList onsdag;
	private DayList torsdag;
	private DayList fredag;
	private DayList lordag;
	private DayList sondag;
	
	private int weekNumber;
	private int yearNumber;
	
	
	private GregorianCalendar gregCal;
	private GregorianCalendar cal;


	
	/**
	 * Create the panel.
	 */
	
	private final JFrame application;
	public CalendarView(JFrame application) {
		cal = new GregorianCalendar();
		
		weekNumber = cal.get(java.util.Calendar.WEEK_OF_YEAR);
		yearNumber = cal.get(java.util.Calendar.YEAR);
		
		this.application = application;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 110, 108, 75, 113, 75, 113, 75, 113, 75, 113, 75, 113, 30};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 150, 30, 202, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		this.setLayout(gridBagLayout);
		
		JButton btnForrigeUke = new JButton("Forrige uke");
		btnForrigeUke.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				weekNumber--;
				txtUke.setText("Uke " + weekNumber);
				renderCalendars();
			}
		});
		GridBagConstraints gbc_btnForrigeUke = new GridBagConstraints();
		gbc_btnForrigeUke.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnForrigeUke.insets = new Insets(0, 0, 5, 5);
		gbc_btnForrigeUke.gridx = 1;
		gbc_btnForrigeUke.gridy = 1;
		this.add(btnForrigeUke, gbc_btnForrigeUke);
		
		txtUke = new JTextField();
		txtUke.setHorizontalAlignment(SwingConstants.CENTER);
		txtUke.setEditable(false);
		txtUke.setText("Uke " + weekNumber);
		GridBagConstraints gbc_txtUke = new GridBagConstraints();
		gbc_txtUke.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUke.insets = new Insets(0, 0, 5, 5);
		gbc_txtUke.gridx = 2;
		gbc_txtUke.gridy = 1;
		this.add(txtUke, gbc_txtUke);
		txtUke.setColumns(10);
		
		JButton btnNesteUke = new JButton("Neste uke");
		btnNesteUke.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				weekNumber++;
				txtUke.setText("Uke " + weekNumber);
				renderCalendars();
			}
		});
		GridBagConstraints gbc_btnNesteUke = new GridBagConstraints();
		gbc_btnNesteUke.insets = new Insets(0, 0, 5, 5);
		gbc_btnNesteUke.gridx = 3;
		gbc_btnNesteUke.gridy = 1;
		this.add(btnNesteUke, gbc_btnNesteUke);
		
		JLabel lblHoppTilUke = new JLabel("Hopp til uke:");
		GridBagConstraints gbc_lblHoppTilUke = new GridBagConstraints();
		gbc_lblHoppTilUke.anchor = GridBagConstraints.EAST;
		gbc_lblHoppTilUke.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoppTilUke.gridx = 4;
		gbc_lblHoppTilUke.gridy = 1;
		this.add(lblHoppTilUke, gbc_lblHoppTilUke);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 1;
		this.add(comboBox, gbc_comboBox);
		
		JButton btnNyAvtale = new JButton("Ny avtale");
		btnNyAvtale.addActionListener(new AddAppointmentListener());
		
		GridBagConstraints gbc_btnNyAvtale = new GridBagConstraints();
		gbc_btnNyAvtale.insets = new Insets(0, 0, 5, 5);
		gbc_btnNyAvtale.gridx = 11;
		gbc_btnNyAvtale.gridy = 1;
		this.add(btnNyAvtale, gbc_btnNyAvtale);
		
		txtMandag = new JTextField();
		txtMandag.setHorizontalAlignment(SwingConstants.CENTER);
		txtMandag.setEditable(false);
		txtMandag.setText("Mandag");
		GridBagConstraints gbc_txtMandag = new GridBagConstraints();
		gbc_txtMandag.gridwidth = 2;
		gbc_txtMandag.insets = new Insets(0, 0, 5, 5);
		gbc_txtMandag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMandag.gridx = 1;
		gbc_txtMandag.gridy = 2;
		this.add(txtMandag, gbc_txtMandag);
		txtMandag.setColumns(10);
		
		txtTirsdag = new JTextField();
		txtTirsdag.setHorizontalAlignment(SwingConstants.CENTER);
		txtTirsdag.setEditable(false);
		txtTirsdag.setText("Tirsdag");
		txtTirsdag.setColumns(10);
		GridBagConstraints gbc_txtTirsdag = new GridBagConstraints();
		gbc_txtTirsdag.gridwidth = 2;
		gbc_txtTirsdag.insets = new Insets(0, 0, 5, 5);
		gbc_txtTirsdag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTirsdag.gridx = 3;
		gbc_txtTirsdag.gridy = 2;
		this.add(txtTirsdag, gbc_txtTirsdag);
		
		txtOnsdag = new JTextField();
		txtOnsdag.setHorizontalAlignment(SwingConstants.CENTER);
		txtOnsdag.setEditable(false);
		txtOnsdag.setText("Onsdag");
		GridBagConstraints gbc_txtOnsdag = new GridBagConstraints();
		gbc_txtOnsdag.gridwidth = 2;
		gbc_txtOnsdag.insets = new Insets(0, 0, 5, 5);
		gbc_txtOnsdag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOnsdag.gridx = 5;
		gbc_txtOnsdag.gridy = 2;
		this.add(txtOnsdag, gbc_txtOnsdag);
		txtOnsdag.setColumns(10);
		
		txtTorsdag = new JTextField();
		txtTorsdag.setHorizontalAlignment(SwingConstants.CENTER);
		txtTorsdag.setEditable(false);
		txtTorsdag.setText("Torsdag");
		GridBagConstraints gbc_txtTorsdag = new GridBagConstraints();
		gbc_txtTorsdag.gridwidth = 2;
		gbc_txtTorsdag.insets = new Insets(0, 0, 5, 5);
		gbc_txtTorsdag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTorsdag.gridx = 7;
		gbc_txtTorsdag.gridy = 2;
		this.add(txtTorsdag, gbc_txtTorsdag);
		txtTorsdag.setColumns(10);
		
		txtFredag = new JTextField();
		txtFredag.setHorizontalAlignment(SwingConstants.CENTER);
		txtFredag.setEditable(false);
		txtFredag.setText("Fredag");
		GridBagConstraints gbc_txtFredag = new GridBagConstraints();
		gbc_txtFredag.gridwidth = 2;
		gbc_txtFredag.insets = new Insets(0, 0, 5, 5);
		gbc_txtFredag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFredag.gridx = 9;
		gbc_txtFredag.gridy = 2;
		this.add(txtFredag, gbc_txtFredag);
		txtFredag.setColumns(10);
		
		txtLrdag = new JTextField();
		txtLrdag.setHorizontalAlignment(SwingConstants.CENTER);
		txtLrdag.setEditable(false);
		txtLrdag.setText("L\u00F8rdag");
		GridBagConstraints gbc_txtLrdag = new GridBagConstraints();
		gbc_txtLrdag.gridwidth = 2;
		gbc_txtLrdag.insets = new Insets(0, 0, 5, 5);
		gbc_txtLrdag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLrdag.gridx = 11;
		gbc_txtLrdag.gridy = 2;
		this.add(txtLrdag, gbc_txtLrdag);
		txtLrdag.setColumns(10);
		
		mandag = new DayList();
		GridBagConstraints gbc_mandag = new GridBagConstraints();
		gbc_mandag.gridheight = 3;
		gbc_mandag.gridwidth = 2;
		gbc_mandag.insets = new Insets(0, 0, 5, 5);
		gbc_mandag.fill = GridBagConstraints.BOTH;
		gbc_mandag.gridx = 1;
		gbc_mandag.gridy = 3;
		this.add(mandag, gbc_mandag);
		
		tirsdag = new DayList();
		GridBagConstraints gbc_tirsdag = new GridBagConstraints();
		gbc_tirsdag.gridheight = 3;
		gbc_tirsdag.gridwidth = 2;
		gbc_tirsdag.insets = new Insets(0, 0, 5, 5);
		gbc_tirsdag.fill = GridBagConstraints.BOTH;
		gbc_tirsdag.gridx = 3;
		gbc_tirsdag.gridy = 3;
		this.add(tirsdag, gbc_tirsdag);
		
		onsdag = new DayList();
		GridBagConstraints gbc_onsdag = new GridBagConstraints();
		gbc_onsdag.gridheight = 3;
		gbc_onsdag.gridwidth = 2;
		gbc_onsdag.insets = new Insets(0, 0, 5, 5);
		gbc_onsdag.fill = GridBagConstraints.BOTH;
		gbc_onsdag.gridx = 5;
		gbc_onsdag.gridy = 3;
		this.add(onsdag, gbc_onsdag);
		
		torsdag = new DayList();
		GridBagConstraints gbc_torsdag = new GridBagConstraints();
		gbc_torsdag.gridheight = 3;
		gbc_torsdag.gridwidth = 2;
		gbc_torsdag.insets = new Insets(0, 0, 5, 5);
		gbc_torsdag.fill = GridBagConstraints.BOTH;
		gbc_torsdag.gridx = 7;
		gbc_torsdag.gridy = 3;
		this.add(torsdag, gbc_torsdag);
		
		fredag = new DayList();
		GridBagConstraints gbc_fredag = new GridBagConstraints();
		gbc_fredag.gridheight = 3;
		gbc_fredag.gridwidth = 2;
		gbc_fredag.insets = new Insets(0, 0, 5, 5);
		gbc_fredag.fill = GridBagConstraints.BOTH;
		gbc_fredag.gridx = 9;
		gbc_fredag.gridy = 3;
		this.add(fredag, gbc_fredag);
		
		lordag = new DayList();
		GridBagConstraints gbc_lordag = new GridBagConstraints();
		gbc_lordag.gridwidth = 2;
		gbc_lordag.insets = new Insets(0, 0, 5, 5);
		gbc_lordag.fill = GridBagConstraints.BOTH;
		gbc_lordag.gridx = 11;
		gbc_lordag.gridy = 3;
		this.add(lordag, gbc_lordag);
		
		txtSndag = new JTextField();
		txtSndag.setHorizontalAlignment(SwingConstants.CENTER);
		txtSndag.setEditable(false);
		txtSndag.setText("S\u00F8ndag");
		GridBagConstraints gbc_txtSndag = new GridBagConstraints();
		gbc_txtSndag.gridwidth = 2;
		gbc_txtSndag.insets = new Insets(0, 0, 5, 5);
		gbc_txtSndag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSndag.gridx = 11;
		gbc_txtSndag.gridy = 4;
		this.add(txtSndag, gbc_txtSndag);
		txtSndag.setColumns(10);
		
		sondag = new DayList();
		GridBagConstraints gbc_sondag = new GridBagConstraints();
		gbc_sondag.insets = new Insets(0, 0, 5, 5);
		gbc_sondag.gridwidth = 2;
		gbc_sondag.fill = GridBagConstraints.BOTH;
		gbc_sondag.gridx = 11;
		gbc_sondag.gridy = 5;
		
		myCalendars = Client.register.activeCalendars();
		this.add(sondag, gbc_sondag);
		
		renderCalendars();
	}
	
	
	
	
	private void renderCalendars(){
		resetCalendar();
		DayList[] dayList = {sondag, mandag, tirsdag, onsdag, torsdag, fredag, lordag};
		for (int i = 0; i < myCalendars.size(); i++){
			for (int j = 0; j < myCalendars.get(i).getAppointments().size(); j++){
				
				gregCal = new GregorianCalendar();
				Appointment tempAppointment = myCalendars.get(i).getAppointments().get(j);
				
				Date tempDate = tempAppointment.getDate();
				gregCal.setTimeInMillis(tempDate.getTime());								
				
				
				if ((gregCal.get(java.util.Calendar.WEEK_OF_YEAR) == weekNumber) && (gregCal.get(java.util.Calendar.YEAR)) == yearNumber){
					DayList day = dayList[gregCal.get(java.util.Calendar.DAY_OF_WEEK) - 1];
					AppointmentPanel panel = new AppointmentPanel(tempAppointment);
					panel.addPropertyChangeListener(this);
					day.add(panel);
					
				}
			}
		}
		this.revalidate();
		this.repaint();
	}
	
	private void resetCalendar(){
		DayList[] dayList = {sondag, mandag, tirsdag, onsdag, torsdag, fredag, lordag};
		
		for(int i = 0; i < dayList.length; i++){
			dayList[i].clear();
		}
		
	}
	
	
	private class AddAppointmentListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				AddAppointmentDialog ad = new AddAppointmentDialog(CalendarView.this.application);
				ad.addPropertyChangeListener(CalendarView.this);
				ad.setVisible(true);
				
				
				Appointment a = ad.getAppointment();
				if (a != null) {
					// Appointment set, lets save it!
					
					//Getting data
					Calendar c = ad.getCalendar();
					Alert alert = ad.getAlert();
					ArrayList<MeetingResponse> meetingResponses = ad.getMeetingResponses();
					
					c.addAppointment(a);
					Client.register.saveCalendar(c);
					
					// Saving the actual appointment /could also be meeting
					
					// Saving the alarm
					if(alert != null) {
						Client.register.saveAlert(alert);
					}
					if (a instanceof Meeting && meetingResponses.size() > 0) {
						for(MeetingResponse mr : meetingResponses) {
							Client.register.saveMeetingResponse(mr);
						}
					}
					
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		
		}
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt.getPropertyName());
		if (evt.getPropertyName().equals("delbase")){
			try {
				Client.register.cancelAppointment(((Appointment)evt.getOldValue()));
			} catch (CloakedIronManException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Calendar cal : myCalendars){
				cal.removeAppointment((Appointment) evt.getOldValue());
			}
			//Client.register. trenger saveCalendar
		}
		
		else if (evt.getPropertyName().equals("createApp")){
			for (Calendar cal : myCalendars){
				if (Client.register.getAccount().equals(cal.getOwner())){
					cal.addAppointment((Appointment) evt.getNewValue());
					Client.register.addAppointmentFromGUI((Appointment) evt.getNewValue(), cal);
					break;
				}
			}
		}
		
		else if (evt.getPropertyName().equals("activeCalendars")){
			myCalendars = (ArrayList<Calendar>) evt.getNewValue();
		}
		renderCalendars();
	}
	
	
	public void addCalendar(Calendar cal){
		if (!Helper.containsById(myCalendars,cal)){
			this.myCalendars.add(cal);
		}
        renderCalendars();
	}
	
	public void removeCalendar(Calendar cal){
		Helper.removeById(myCalendars,cal);
        renderCalendars();
    }

}
