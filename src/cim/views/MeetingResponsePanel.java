package cim.views;

import javax.swing.JPanel;

import cim.models.Meeting;
import cim.models.MeetingResponse;
import javax.swing.JLabel;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MeetingResponsePanel extends JPanel {

	private MeetingResponse model;
	private ModelListener ml = new ModelListener();
	private JLabel lblDate;
	private JLabel lblWhen;
	private JLabel lblWhat;
	private JLabel lblWhere;
	private JLabel lblFrom;
	private JButton btnDecline;
	
	public MeetingResponsePanel() {
		setBackground(Color.RED);
		setLayout(null);
		
		lblDate = new JLabel("time");
		lblDate.setBounds(10, 11, 87, 14);
		add(lblDate);
		
		lblWhen = new JLabel("when");
		lblWhen.setBounds(107, 11, 111, 14);
		add(lblWhen);
		
		lblWhat = new JLabel("what");
		lblWhat.setBounds(228, 11, 46, 14);
		add(lblWhat);
		
		lblWhere = new JLabel("where");
		lblWhere.setBounds(328, 11, 110, 14);
		add(lblWhere);
		
		lblFrom = new JLabel("from");
		lblFrom.setBounds(448, 11, 148, 14);
		add(lblFrom);
		
		JButton btnAccept = new JButton("Godta");
		btnAccept.addActionListener(new AcceptListener());
		btnAccept.setBounds(666, 7, 89, 23);
		add(btnAccept);
		
		btnDecline = new JButton("Forkast");
		btnDecline.setBounds(765, 7, 89, 23);
		add(btnDecline);
		
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
	}
	
	private class ModelListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			MeetingResponsePanel.this.refresh();
			
		}
		
	}
	
	private class AcceptListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
