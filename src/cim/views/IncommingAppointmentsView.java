package cim.views;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import cim.models.MeetingResponse;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.Color;

public class IncommingAppointmentsView extends JPanel{
	
	// Det eksisterer en plan i hodet til Beate på hvordan den her kan bygges.
	// Håkon har også lyst til å prøve
	
	
	private ArrayList<MeetingResponse> model;
	
	
	public IncommingAppointmentsView() {
		setBackground(Color.YELLOW);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	}
	
	public void setModel(ArrayList<MeetingResponse> m) {
		this.model = m;
		this.refresh();
	}
	
	
	public int getIncommingAppointments(){
	
		//TODO: Hente ut antall avtaler som ligger på vent.
		// Dette tallet vil brukes til å lage griddet stort nok.
	
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
