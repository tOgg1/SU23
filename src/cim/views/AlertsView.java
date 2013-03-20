package cim.views;

import cim.models.Alert;
import cim.models.RejectMessage;
import cim.net.Client;
import cim.util.CloakedIronManException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertsView extends JPanel{
//	Client.register <--- objektet vi kaller metoder p�.
	private JList rejectionMessagesList;
	private DefaultListModel<RejectMessage> rejectMessageListModel;
	
	private JList alertList;
	private DefaultListModel<Alert> alertListModel;

	
	private JButton btnMarkAlarmAsRead;
	private JButton btnMarkAlertAsRead;
	
	private JLabel lblMessages;
	private JLabel lblAlerts;
	
	private int unReadAlerts; 
	//Kombinerer begge listene sine uleste elementer
	
	public AlertsView() {
		setLayout(null);
		
		lblAlerts = new JLabel("Varsler");
		lblAlerts.setBounds(10, 11, 97, 14);
		add(lblAlerts);
		
		alertList = new JList();
		alertList.setBounds(10, 36, 639, 150);
		add(alertList);
		generateAlertList();
		
		btnMarkAlarmAsRead = new JButton("Marker som lest"); //Alarm (alerts)
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
		
		btnMarkAlertAsRead = new JButton("Marker som lest"); //RejectMessages
		btnMarkAlertAsRead.setBounds(499, 413, 150, 23);
		add(btnMarkAlertAsRead);
		btnMarkAlertAsRead.addActionListener(new BTNMarkRejectionAsReadListener());

	}
	public int getUnreadAlerts(){
		int count = 0;
		
		// Count all unseen alerts
		try { 
			for(Alert alert : Client.register.getAlerts()){
				if(!alert.isSeen()){
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Count all unseen rejectMessages
		try {
			for(RejectMessage rejectMessage : Client.register.getRejectMessages()){
				if(!rejectMessage.isSeen()){
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Return count of total unseen objects
		return count;
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
			RejectMessage rm = (RejectMessage) rejectionMessagesList.getSelectedValue();
			if(!rm.isSeen()){
				rm.changeIsSeen(true);
				//TODO mekke save.RejectMessage
			}
			
		}
	}
	
	public class BTNMarkAlertAsReadListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Alert alert = (Alert) alertList.getSelectedValue();
			if(!alert.isSeen()){
				alert.changeIsSeen(true);
				try {
					Client.register.saveAlert(alert);
				} catch (CloakedIronManException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

}
