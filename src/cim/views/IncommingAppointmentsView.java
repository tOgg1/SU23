package cim.views;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import cim.models.MeetingResponse;

import java.awt.GridLayout;
import java.util.ArrayList;

public class IncommingAppointmentsView extends JPanel{
	
	// Det eksisterer en plan i hodet til Beate p� hvordan den her kan bygges.
	// H�kon har ogs� lyst til � pr�ve
	
	
	private ArrayList<MeetingResponse> model;
	
	
	public IncommingAppointmentsView() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	}
	
	public void setModel(ArrayList<MeetingResponse> m) {
		this.model = m;
		this.refresh();
	}
	
	
	public int getIncommingAppointments(){
	
		//TODO: Hente ut antall avtaler som ligger p� vent.
		// Dette tallet vil brukes til � lage griddet stort nok.
	
		return 10;
	}
	private void refresh() {
		this.removeAll();
		for(MeetingResponse m: this.model) {
			MeetingResponsePanel mp = new MeetingResponsePanel();
			mp.setModel(m);
			this.add(mp);
		}
	}
	
}
