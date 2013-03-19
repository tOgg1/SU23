package cim.views;

import cim.models.RejectMessage;
import cim.net.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertsView extends JPanel{
//	Client.register <--- objektet vi kaller metoder pï¿½.
	private JList rejectionMessagesList;
	private DefaultListModel<RejectMessage> rejectMessageListModel;
	
	private JList alertList;
//	private DefaultListModel<Al>

	
	private JButton btnRemoveAlarm;
	private JButton btnRemoveAlert;
	
	private JLabel lblMessages;
	private JLabel lblAlerts;
	
	public AlertsView() {
		setLayout(null);
		
		rejectionMessagesList = new JList();
		rejectionMessagesList.setBounds(10, 252, 639, 150);
		add(rejectionMessagesList);
		generateRejectMessageList();
		
		btnRemoveAlert = new JButton("Marker som lest1");
		btnRemoveAlert.setBounds(499, 413, 150, 23);
		add(btnRemoveAlert);
		btnRemoveAlert.addActionListener(new RemoveRejectionMessageBTNListener());
		
		lblAlerts = new JLabel("Varsler");
		lblAlerts.setBounds(10, 11, 97, 14);
		add(lblAlerts);
		
		alertList = new JList();
		alertList.setBounds(10, 36, 639, 150);
		add(alertList);
		
		btnRemoveAlarm = new JButton("Marker som lest2");
		btnRemoveAlarm.setBounds(499, 197, 150, 23);
		add(btnRemoveAlarm);
		
		lblMessages = new JLabel("Meldinger");
		lblMessages.setBounds(10, 227, 97, 14);
		add(lblMessages);
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
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	public class RemoveRejectionMessageBTNListener implements ActionListener{

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
}
