package cim.views;

import cim.models.RejectMessage;
import cim.net.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertsView extends JPanel{
//	Client.register <--- objektet vi kaller metoder p�.
	private JList alertsList;

	private DefaultListModel<RejectMessage> rejectMessageList;
	private DefaultListModel<String> messageList;
	private JList list;
	private JButton btnRemoveAlarm;
	private JButton btnRemoveAlert;
	private JLabel lblMessages;
	private JLabel lblAlerts;
	
	public AlertsView() {
		setLayout(null);
		
		alertsList = new JList();
		alertsList.setBounds(10, 311, 639, 200);
		add(alertsList);
		try{
//            rejectMessageList = new DefaultListModel<RejectMessage>();
				messageList = new DefaultListModel<String>();
            for(RejectMessage rm : Client.register.getRejectMessages()){
//            	rejectMessageList.addElement(rm);
            	messageList.addElement(rm.toString());
            }
            alertsList.setModel(messageList);
		} 
		catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		btnRemoveAlert = new JButton("Fjern");
		btnRemoveAlert.setBounds(560, 522, 89, 23);
		add(btnRemoveAlert);
		btnRemoveAlert.addActionListener(new RemoveAlertButtonListener());
		
		lblAlerts = new JLabel("Varsler");
		lblAlerts.setBounds(10, 286, 97, 14);
		add(lblAlerts);
		
		list = new JList();
		list.setBounds(10, 36, 639, 200);
		add(list);
		
		btnRemoveAlarm = new JButton("Fjern");
		btnRemoveAlarm.setBounds(560, 247, 89, 23);
		add(btnRemoveAlarm);
		
		lblMessages = new JLabel("Meldinger");
		lblMessages.setBounds(10, 11, 97, 14);
		add(lblMessages);
	}
	public class RemoveAlertButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			alertsList.getSelectedValue();
			
		}
		
	}
}
