package cim.views;

import javax.swing.JPanel;

import cim.models.Meeting;
import cim.models.MeetingResponse;
import javax.swing.JLabel;
import java.awt.Color;

public class MeetingResponsePanel extends JPanel {

	private MeetingResponse model;
	
	private JLabel lblDate;
	private JLabel lblWhen;
	private JLabel lblWhat;
	private JLabel lblWhere;
	private JLabel lblFrom;
	
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
		
	}
	
	public void setModel(MeetingResponse model) {
		this.model = model;
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
}
