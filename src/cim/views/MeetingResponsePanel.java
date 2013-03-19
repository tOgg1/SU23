package cim.views;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import cim.models.Meeting;
import cim.models.MeetingResponse;
import cim.models.MeetingResponse.Response;
import cim.net.Client;
import cim.util.CloakedIronManException;
import cim.util.Fonts;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.SystemColor;
import java.awt.Font;

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
	private JLabel lblAccept;
	private JLabel lblDecline;
	
	public MeetingResponsePanel() throws CloakedIronManException {
		try {
			setBackground(SystemColor.control);
			setLayout(null);
			
			lblDate = new JLabel("time");
			lblDate.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblDate.setBounds(0, 11, 101, 14);
			add(lblDate);
			
			lblWhen = new JLabel("when");
			lblWhen.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblWhen.setBounds(111, 11, 124, 14);
			add(lblWhen);
			
			lblWhat = new JLabel("what");
			lblWhat.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblWhat.setBounds(245, 11, 139, 14);
			add(lblWhat);
			
			lblWhere = new JLabel("where");
			lblWhere.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblWhere.setBounds(394, 11, 174, 14);
			add(lblWhere);
			
			lblFrom = new JLabel("from");
			lblFrom.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblFrom.setBounds(578, 11, 289, 14);
			add(lblFrom);
			
			
			lblAccept = new JLabel(Fonts.AwesomeIcons.ICON_HAPPY.toString());
			lblAccept.setFont(new Font("FontAwesome", Font.PLAIN, 24));
			lblAccept.setBounds(876, 7, 20, 20);
			lblAccept.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblAccept.setToolTipText("Godta møteinnkalling");
			lblAccept.addMouseListener(new AcceptListener());
			add(lblAccept);
			
			lblDecline = new JLabel(Fonts.AwesomeIcons.ICON_ANGRY.toString());
			lblDecline.setFont(new Font("FontAwesome", Font.PLAIN, 24));
			lblDecline.setBounds(905, 7, 20, 20);
			lblDecline.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblDecline.setToolTipText("Avslå møteinnkalling");
			lblDecline.addMouseListener(new DeclineListener());
			add(lblDecline);
			
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
	}
	
	private class ModelListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			MeetingResponsePanel.this.refresh();
			
		}
		
	}
	
	private class AcceptListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				MeetingResponsePanel.this.model.setResponse(Response.ATTENDING);
				Client.register.saveMeetingResponse(model);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
	
	private class DeclineListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				MeetingResponsePanel.this.model.setResponse(Response.NOT_ATTENDING);
				Client.register.saveMeetingResponse(model);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	}
}
