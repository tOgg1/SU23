package cim.views;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import cim.models.MeetingResponse;
import cim.net.Client;
import cim.net.packet.Response;
import cim.util.CloakedIronManException;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MeetingResponsesFlowPanel extends JPanel {
	
	// Det eksisterer en plan i hodet til Beate på hvordan den her kan bygges.
	// Håkon har også lyst til å prøve
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2690042605018985496L;
	private ArrayList<MeetingResponse> model;
	
	
	public MeetingResponsesFlowPanel() {
		setBackground(Color.YELLOW);
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(715, 403));
		

	}
	
	public void setModel(ArrayList<MeetingResponse> m) {
		this.model = m;
		for(MeetingResponse mr : this.model) {
			mr.addPropertyChangeListener(new MeetingResponseListener(mr));
		}
		this.refresh();
	}
	
	private void refresh() {
		this.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		for(MeetingResponse m: this.model) {
			if(m.getResponse() != MeetingResponse.Response.NOT_SEEN) {
				continue;
			}
			MeetingResponsePanel mp = new MeetingResponsePanel();
			mp.setModel(m);
			this.add(mp, c);
			System.out.println(c.gridy);
			++c.gridy;
		}
	}

	private class MeetingResponseListener implements PropertyChangeListener {
		private MeetingResponse mr;
		public MeetingResponseListener(MeetingResponse mr) {
			this.mr = mr;
		}
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			try {
				Client.register.saveMeetingResponse(this.mr);
				MeetingResponsesFlowPanel.this.refresh();
				// The current response has been modified
				System.out.println(mr.getMeeting().getName() + " has been modified");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
