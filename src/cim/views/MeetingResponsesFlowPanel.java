package cim.views;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

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
import java.awt.ScrollPane;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.SystemColor;

public class MeetingResponsesFlowPanel extends JPanel implements PropertyChangeListener {
	
	// Det eksisterer en plan i hodet til Beate på hvordan den her kan bygges.
	// Håkon har også lyst til å prøve
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2690042605018985496L;
	private ArrayList<MeetingResponse> model;
	
	public MeetingResponsesFlowPanel() {
		setBackground(SystemColor.control);
		//setLayout(new GridBagLayout());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setMaximumSize(new Dimension(715, 403));
		
       

	}
	
	public void setModel(ArrayList<MeetingResponse> m) {
		this.model = m;
		this.refresh();
	}
	
	private void refresh() {
		this.removeAll();
		int iNumAdded = 0;
		for(MeetingResponse m: this.model) {
			if(m.getResponse() != MeetingResponse.Response.NOT_SEEN) {
				continue;
			}
			try {
				MeetingResponsePanel mp = new MeetingResponsePanel();
				mp.setModel(m);
				this.add(mp);
				++iNumAdded;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		this.revalidate();
		this.repaint();
		this.firePropertyChange("numMeetingResponses", null, iNumAdded);
		/*GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, 0);
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTH;
		c.weighty = 1.0;
		int iNumAdded = 0;
		
		for(MeetingResponse m: this.model) {
			if(m.getResponse() != MeetingResponse.Response.NOT_SEEN) {
				continue;
			}
			try {
				MeetingResponsePanel mp = new MeetingResponsePanel();
				mp.setModel(m);
				this.add(mp, c);
				++c.gridy;
				++iNumAdded;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		this.revalidate();
		this.repaint();
		this.firePropertyChange("numMeetingResponses", null, iNumAdded);
		*/
		
	}
		
	
	/**
	 * This method listens to property change from the Register object
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String strProp = evt.getPropertyName();
		if(strProp.equals("meetingResponses")) {
			try {
				this.setModel(Client.register.getMeetingResponsesToAccount());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
