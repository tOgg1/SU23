package cim.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import cim.models.CalendarRegister;
import cim.net.Client;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

public class AlertsView extends JPanel{
//	Client.register <--- objektet vi kaller metoder p�.
	JList alertsList;
	JButton btnRemoveAlert;
	
	public AlertsView() {
		setLayout(null);
		
		alertsList = new JList();
		alertsList.setBounds(66, 75, 352, 132);
		add(alertsList);
		alertsList.setModel(Client.register.getRejectMessages());
		
		
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
