package cim.views;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class IncommingAppointmentsView extends JPanel{
	
	// Det eksisterer en plan i hodet til Beate p� hvordan den her kan bygges. 
	
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