package cim.views;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class IncommingAppointmentsView extends JPanel{
	
	private int appointments;
	
	public IncommingAppointmentsView() {
		appointments = getIncommingAppointments();
		setLayout(new GridLayout(appointments, 0, 0, 0));
		

	}
	
	private int getIncommingAppointments(){
	
		//TODO: Hente ut antall avtaler som ligger p� vent.
		// Dette tallet vil brukes til � lage griddet stort nok.
	
		return 10;
	}
}
