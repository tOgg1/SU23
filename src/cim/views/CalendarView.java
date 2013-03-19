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
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JScrollPane scrollPane_4;
	private JScrollPane scrollPane_5;
	private JScrollPane scrollPane_6;
	public CalendarView(JFrame application) {
		cal = new GregorianCalendar();
		
		weekNumber = cal.get(java.util.Calendar.WEEK_OF_YEAR);
		yearNumber = cal.get(java.util.Calendar.YEAR);
		
		this.application = application;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {110, 89, 75, 103, 75, 94, 75, 102, 75, 102, 75, 98};
		gridBagLayout.rowHeights = new int[] {30, 30, 200, 30, 202};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
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
		gbc_btnForrigeUke.gridx = 0;
		gbc_btnForrigeUke.gridy = 0;
		this.add(btnForrigeUke, gbc_btnForrigeUke);
		
		txtUke = new JTextField();
		txtUke.setHorizontalAlignment(SwingConstants.CENTER);
		txtUke.setEditable(false);
		txtUke.setText("Uke " + weekNumber);
		GridBagConstraints gbc_txtUke = new GridBagConstraints();
		gbc_txtUke.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUke.insets = new Insets(0, 0, 5, 5);
		gbc_txtUke.gridx = 1;
		gbc_txtUke.gridy = 0;
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
		gbc_btnNesteUke.gridx = 2;
		gbc_btnNesteUke.gridy = 0;
		this.add(btnNesteUke, gbc_btnNesteUke);
		
		JLabel lblHoppTilUke = new JLabel("Hopp til uke:");
		GridBagConstraints gbc_lblHoppTilUke = new GridBagConstraints();
		gbc_lblHoppTilUke.anchor = GridBagConstraints.EAST;
		gbc_lblHoppTilUke.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoppTilUke.gridx = 3;
		gbc_lblHoppTilUke.gridy = 0;
		this.add(lblHoppTilUke, gbc_lblHoppTilUke);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 0;
		this.add(comboBox, gbc_comboBox);
		
		JButton btnNyAvtale = new JButton("Ny avtale");
		btnNyAvtale.addActionListener(new AddAppointmentListener());
		
		GridBagConstraints gbc_btnNyAvtale = new GridBagConstraints();
		gbc_btnNyAvtale.insets = new Insets(0, 0, 5, 5);
		gbc_btnNyAvtale.gridx = 10;
		gbc_btnNyAvtale.gridy = 0;
		this.add(btnNyAvtale, gbc_btnNyAvtale);
		
		txtMandag = new JTextField();
		txtMandag.setHorizontalAlignment(SwingConstants.CENTER);
		txtMandag.setEditable(false);
		txtMandag.setText("Mandag");
		GridBagConstraints gbc_txtMandag = new GridBagConstraints();
		gbc_txtMandag.gridwidth = 2;
		gbc_txtMandag.insets = new Insets(0, 0, 5, 5);
		gbc_txtMandag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMandag.gridx = 0;
		gbc_txtMandag.gridy = 1;
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
		gbc_txtTirsdag.gridx = 2;
		gbc_txtTirsdag.gridy = 1;
		this.add(txtTirsdag, gbc_txtTirsdag);
		
		txtOnsdag = new JTextField();
		txtOnsdag.setHorizontalAlignment(SwingConstants.CENTER);
		txtOnsdag.setEditable(false);
		txtOnsdag.setText("Onsdag");
		GridBagConstraints gbc_txtOnsdag = new GridBagConstraints();
		gbc_txtOnsdag.gridwidth = 2;
		gbc_txtOnsdag.insets = new Insets(0, 0, 5, 5);
		gbc_txtOnsdag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOnsdag.gridx = 4;
		gbc_txtOnsdag.gridy = 1;
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
		gbc_txtTorsdag.gridx = 6;
		gbc_txtTorsdag.gridy = 1;
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
		gbc_txtFredag.gridx = 8;
		gbc_txtFredag.gridy = 1;
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
		gbc_txtLrdag.gridx = 10;
		gbc_txtLrdag.gridy = 1;
		this.add(txtLrdag, gbc_txtLrdag);
		txtLrdag.setColumns(10);
		
		scrollPane_5 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_5 = new GridBagConstraints();
		gbc_scrollPane_5.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_5.gridheight = 3;
		gbc_scrollPane_5.gridwidth = 2;
		gbc_scrollPane_5.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_5.gridx = 0;
		gbc_scrollPane_5.gridy = 2;
		add(scrollPane_5, gbc_scrollPane_5);
		
		mandag = new DayList();
		scrollPane_5.setViewportView(mandag);
		
		scrollPane_6 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_6 = new GridBagConstraints();
		gbc_scrollPane_6.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_6.gridheight = 3;
		gbc_scrollPane_6.gridwidth = 2;
		gbc_scrollPane_6.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_6.gridx = 2;
		gbc_scrollPane_6.gridy = 2;
		add(scrollPane_6, gbc_scrollPane_6);
		
		tirsdag = new DayList();
		scrollPane_6.setViewportView(tirsdag);
		
		scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.gridwidth = 2;
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.gridheight = 3;
		gbc_scrollPane_4.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_4.gridx = 4;
		gbc_scrollPane_4.gridy = 2;
		add(scrollPane_4, gbc_scrollPane_4);
		
		onsdag = new DayList();
		scrollPane_4.setViewportView(onsdag);
		
		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 2;
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridheight = 3;
		gbc_scrollPane_3.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_3.gridx = 6;
		gbc_scrollPane_3.gridy = 2;
		add(scrollPane_3, gbc_scrollPane_3);
		
		torsdag = new DayList();
		scrollPane_3.setViewportView(torsdag);
		
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 2;
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridheight = 3;
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.gridx = 8;
		gbc_scrollPane_2.gridy = 2;
		add(scrollPane_2, gbc_scrollPane_2);
		
		fredag = new DayList();
		scrollPane_2.setViewportView(fredag);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.gridx = 10;
		gbc_scrollPane_1.gridy = 2;
		add(scrollPane_1, gbc_scrollPane_1);
		
		lordag = new DayList();
		scrollPane_1.setViewportView(lordag);
		
		txtSndag = new JTextField();
		txtSndag.setHorizontalAlignment(SwingConstants.CENTER);
		txtSndag.setEditable(false);
		txtSndag.setText("S\u00F8ndag");
		GridBagConstraints gbc_txtSndag = new GridBagConstraints();
		gbc_txtSndag.gridwidth = 2;
		gbc_txtSndag.insets = new Insets(0, 0, 5, 5);
		gbc_txtSndag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSndag.gridx = 10;
		gbc_txtSndag.gridy = 3;
		this.add(txtSndag, gbc_txtSndag);
		txtSndag.setColumns(10);
		
		myCalendars = Client.register.activeCalendars();
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 10;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		
		sondag = new DayList();
		scrollPane.setViewportView(sondag);
		
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
