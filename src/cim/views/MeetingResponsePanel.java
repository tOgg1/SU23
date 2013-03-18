package cim.views;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import cim.models.Meeting;
import cim.models.MeetingResponse;
import cim.models.MeetingResponse.Response;
import cim.util.CloakedIronManException;
import cim.util.Fonts;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MeetingResponsePanel extends JPanel {

	/**
	 * Needed for somewhat reason
	 */
	private static final long serialVersionUID = -3583894885082933803L;
	
	
	private MeetingResponse model;
	private ModelListener ml = new ModelListener();
	private JLabel lblDate;
	private JLabel lblWhen;
	private JLabel lblWhat;
	private JLabel lblWhere;
	private JLabel lblFrom;
	private JButton btnDecline;
	private JButton btnAccept;
	private ButtonGroup buttonGroup;
	
	public MeetingResponsePanel() throws CloakedIronManException {
		try {
			setBackground(Color.RED);
			setLayout(null);
			
			lblDate = new JLabel("time");
			lblDate.setBounds(0, 11, 87, 14);
			add(lblDate);
			
			lblWhen = new JLabel("when");
			lblWhen.setBounds(111, 11, 111, 14);
			add(lblWhen);
			
			lblWhat = new JLabel("what");
			lblWhat.setBounds(245, 11, 46, 14);
			add(lblWhat);
			
			lblWhere = new JLabel("where");
			lblWhere.setBounds(394, 11, 110, 14);
			add(lblWhere);
			
			lblFrom = new JLabel("from");
			lblFrom.setBounds(578, 11, 344, 14);
			add(lblFrom);
			
			this.buttonGroup = new ButtonGroup();
			
			btnAccept = new JButton("");
			btnAccept.setToolTipText("Godkjenn m\u00F8teinnkalling");
			btnAccept.setFont(Fonts.getFontAwesome());
			btnAccept.setText(Fonts.AwesomeIcons.ICON_ARROW_DOWN.toString());
			btnAccept.addActionListener(new AcceptListener());
			btnAccept.setBounds(933, 7, 23, 23);
			buttonGroup.add(btnAccept);
			add(btnAccept);
			
			btnDecline = new JButton("\uF007");
			btnDecline.setToolTipText("Avsl\u00E5 m\u00F8teinkalling");
			btnDecline.setFont(Fonts.getFontAwesome());
			btnDecline.addActionListener(new DeclineListener());
			btnDecline.setBounds(965, 7, 23, 23);
			buttonGroup.add(btnDecline);
			add(btnDecline);
			setSize(967,35);
			setPreferredSize(new Dimension(998, 35));
		} catch(Exception e) {
			throw new CloakedIronManException("Could not create meeting response panel.", e);
		}
		
		
	}
	
	public void setModel(MeetingResponse model) {
		if(this.model != null) {
			this.model.removePropertyChangeListener(ml);
		}
		this.model = model;
		this.model.addPropertyChangeListener(ml);
		this.refresh();
		
	}
	
	private void refresh() {
		Meeting m = this.model.getMeeting();
		
		this.lblDate.setText(m.getDateFormatted());
		this.lblWhen.setText(m.getStartFormatted() + "-" + m.getEndFormatted());
		this.lblWhere.setText(m.getWhere());
		this.lblWhat.setText(m.getName());
		this.lblFrom.setText(m.getOwner().getName());
		if(this.model.getResponse() == Response.ATTENDING) {
			this.buttonGroup.setSelected(this.btnAccept.getModel(), true);
			this.buttonGroup.setSelected(this.btnDecline.getModel(), false);
		} else {

			this.buttonGroup.setSelected(this.btnAccept.getModel(), false);
			this.buttonGroup.setSelected(this.btnDecline.getModel(), true);
		}
	}
	
	private class ModelListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			MeetingResponsePanel.this.refresh();
			
		}
		
	}
	
	private class AcceptListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MeetingResponsePanel.this.model.setResponse(Response.ATTENDING);
		}
	}
	
	private class DeclineListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MeetingResponsePanel.this.model.setResponse(Response.NOT_ATTENDING);
			
		}
	}
}
