package cim.views;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JLabel;

import cim.models.MeetingResponse;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;

public class IncomingAppointmentsView extends JPanel {

	/**
	 * Create the panel.
	 */
	public IncomingAppointmentsView() {
		setBackground(SystemColor.activeCaption);
		setLayout(null);
		setPreferredSize(new Dimension(866, 456));
		
		JLabel lblMeetings = new JLabel("M\u00F8ter til godkjenning");
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
		lblWhere.setBounds(390, 54, 46, 14);
		lblWhere.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		add(lblWhere);
		
		JLabel lblFrom = new JLabel("Avsender");
		lblFrom.setBounds(542, 54, 46, 14);
		lblFrom.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		add(lblFrom);
		
		JLabel lblRSVP = new JLabel("RSVP");
		lblRSVP.setBounds(745, 54, 46, 14);
		lblRSVP.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		add(lblRSVP);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 69, 811, 21);
		add(separator);

	}
	
	public void setModel(ArrayList<MeetingResponse> m) {
		
	}
}
