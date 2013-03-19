package cim.views;

import cim.models.RejectMessage;
import cim.net.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertsView extends JPanel{
//	Client.register <--- objektet vi kaller metoder p�.
	private JList alertsList;
	private JButton btnRemoveAlert;
	private DefaultListModel<RejectMessage> rejectMessageList;
	
	public AlertsView() {
		setLayout(null);
		
		alertsList = new JList();
		alertsList.setBounds(66, 75, 352, 132);
		add(alertsList);
		try{
            rejectMessageList = new DefaultListModel<RejectMessage>();
            for(RejectMessage rm : Client.register.getRejectMessages()){
            	rejectMessageList.addElement(rm);
            }
            alertsList.setModel(rejectMessageList);
		} 
		catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		btnRemoveAlert = new JButton("Fjern");
		btnRemoveAlert.setBounds(329, 228, 89, 23);
		add(btnRemoveAlert);
		btnRemoveAlert.addActionListener(new RemoveAlertButtonListener());
		
		JLabel lblAlerts = new JLabel("Varsler");
		lblAlerts.setBounds(22, 31, 46, 14);
		add(lblAlerts);
	}
	public class RemoveAlertButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			alertsList.getSelectedValue();
			
		}
		
	}
}
