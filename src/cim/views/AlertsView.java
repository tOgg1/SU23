package cim.views;

import cim.models.Alert;
import cim.models.RejectMessage;
import cim.net.Client;
import cim.util.CloakedIronManException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertsView extends JPanel{
//	Client.register <--- objektet vi kaller metoder pï¿½.
	private JList rejectionMessagesList;
	private DefaultListModel<RejectMessage> rejectMessageListModel;
	
	private JList alertList;
	private DefaultListModel<Alert> alertListModel;

	
	private JButton btnMarkAlarmAsRead;
	private JButton btnMarkAlertAsRead;
	
	private JLabel lblMessages;
	private JLabel lblAlerts;
	
	public AlertsView() {
		setLayout(null);
		
		lblAlerts = new JLabel("Varsler");
		lblAlerts.setBounds(10, 11, 97, 14);
		add(lblAlerts);
		
		alertList = new JList();
		alertList.setBounds(10, 36, 639, 150);
		add(alertList);
		generateAlertList();
		
		btnMarkAlarmAsRead = new JButton("Marker som lest2");
		btnMarkAlarmAsRead.setBounds(499, 197, 150, 23);
		add(btnMarkAlarmAsRead);
		btnMarkAlarmAsRead.addActionListener(new BTNMarkAlertAsReadListener());
		
		lblMessages = new JLabel("Meldinger");
		lblMessages.setBounds(10, 227, 97, 14);
		add(lblMessages);
		
		rejectionMessagesList = new JList();
		rejectionMessagesList.setBounds(10, 252, 639, 150);
		add(rejectionMessagesList);
		generateRejectMessageList();
		
		btnMarkAlertAsRead = new JButton("Marker som lest1");
		btnMarkAlertAsRead.setBounds(499, 413, 150, 23);
		add(btnMarkAlertAsRead);
		btnMarkAlertAsRead.addActionListener(new BTNMarkRejectionAsReadListener());

	}
	private void generateRejectMessageList() {
		try{
            rejectMessageListModel = new DefaultListModel<RejectMessage>();
            for(RejectMessage rm : Client.register.getRejectMessages()){
            	rejectMessageListModel.addElement(rm);
            }
            rejectionMessagesList.setModel(rejectMessageListModel);
		} 
		catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	private void generateAlertList() {
		try {
			alertListModel = new DefaultListModel<Alert>();
			for(Alert alert : Client.register.getAlerts()){
				alertListModel.addElement(alert);
			}
			alertList.setModel(alertListModel);
		} catch (CloakedIronManException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Listeners
	 */
	public class BTNMarkRejectionAsReadListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			RejectMessage rm = (RejectMessage) rejectionMessagesList.getSelectedValue();
			System.out.println(rm.getMeeting().getName());
			
			
			/*
			 * får tak i alert-objektet
			 * setter til seen
			 * save alert
			 */
			
		}
	}
	
	public class BTNMarkAlertAsReadListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Alert alert = (Alert) alertList.getSelectedValue();
//			alert.isSeen()
		}
		
	}

}
