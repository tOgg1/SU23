package cim.views;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JLabel;

import cim.models.MeetingResponse;
import cim.util.Fonts;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.JSeparator;

public class IncomingAppointmentsView extends JPanel implements PropertyChangeListener {

	/**
	 * Needed for somewhat reason
	 */
	private static final long serialVersionUID = 6617273164387983209L;
	/**
	 * Create the panel.
	 */
	
	MeetingResponsesFlowPanel flowpanel;
	
	private JLabel lblMeetings;
	
	public IncomingAppointmentsView() {
		try {
			setBackground(SystemColor.control);
			setLayout(null);
			setPreferredSize(new Dimension(1039, 456));
			
			lblMeetings = new JLabel("M\u00F8ter til godkjenning");
			lblMeetings.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
			lblMeetings.setBounds(31, 11, 249, 32);
			add(lblMeetings);
			
			JLabel lblDate = new JLabel("Dato");
			lblDate.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblDate.setBounds(31, 54, 46, 14);
			add(lblDate);
			
			JLabel lblWhen = new JLabel("Tid");
			lblWhen.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblWhen.setBounds(143, 54, 46, 14);
			add(lblWhen);
			
			JLabel lblWhat = new JLabel("Hva");
			lblWhat.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblWhat.setBounds(275, 54, 46, 14);
			add(lblWhat);
			
			JLabel lblWhere = new JLabel("Hvor");
			lblWhere.setBounds(425, 54, 46, 14);
			lblWhere.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			add(lblWhere);
			
			JLabel lblFrom = new JLabel("Avsender");
			lblFrom.setBounds(612, 54, 68, 14);
			lblFrom.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			add(lblFrom);
			
			JLabel lblRSVP = new JLabel("RVSP");
			lblRSVP.setBounds(907, 47, 59, 21);
			lblRSVP.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			add(lblRSVP);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(31, 69, 998, 21);
			add(separator);
			
			flowpanel = new MeetingResponsesFlowPanel();
			flowpanel.addPropertyChangeListener(new MeetingResponsePropertyChangeListener());
			flowpanel.setBounds(31,79,998,366);
			add(flowpanel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	public void setModel(ArrayList<MeetingResponse> m) {
		flowpanel.setModel(m);
	}
	
	private class MeetingResponsePropertyChangeListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// Throwing right out
			firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
		}
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if(prop.equals("meetingResponse")) {
			this.setModel((ArrayList<MeetingResponse>)evt.getNewValue());
		}
		
	}
}
