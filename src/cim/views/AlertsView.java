package cim.views;

import cim.models.RejectMessage;
import cim.net.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertsView extends JPanel{
//	Client.register <--- objektet vi kaller metoder p�.
	JList alertsList;
	JButton btnRemoveAlert;
	DefaultListModel<RejectMessage> rejectList;
	
	public AlertsView() {
		setLayout(null);
		
		alertsList = new JList();
		alertsList.setBounds(66, 75, 352, 132);
		add(alertsList);
		try{
            rejectList = new DefaultListModel<RejectMessage>();
		for(RejectMessage rm : Client.register.getRejectMessages())
		{
			rejectList.addElement(rm);
		}
		alertsList.setModel(rejectList);
		} catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		
		btnRemoveAlert = new JButton("Fjern");
		btnRemoveAlert.setBounds(329, 228, 89, 23);
		add(btnRemoveAlert);
		btnRemoveAlert.addActionListener(new removeButtonListener());
		
		JLabel lblAlerts = new JLabel("Varsler");
		lblAlerts.setBounds(22, 31, 46, 14);
		add(lblAlerts);
	}
	public class removeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			alertsList.getSelectedValue();
			System.out.println(alertsList.getSelectedValue());
		}
		
	}
}
