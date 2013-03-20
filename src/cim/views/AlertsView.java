package cim.views;

import cim.models.Alert;
import cim.models.CalendarRegister;
import cim.models.RejectMessage;
import cim.net.Client;
import cim.util.CloakedIronManException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AlertsView extends JPanel implements PropertyChangeListener{
	/*
	 * 
	 * CalendarRegister fyrer av "alerts" når det kommer oppdateringer fra 
	 * serveren med en ny alarm. Du kan da hente de nye alertsene med Client.register.getAlerts()
CalendarRegister fyrer av "rejectMessages" når det kommer oppdateringer fra serveren 
med en ny reject message. Du kan da hente de nye meldingene med Client.register.getRejectMessages()


	 */
//	Client.register <--- objektet vi kaller metoder pï¿½.
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
	private PropertyChangeListener alertListListener;
	private PropertyChangeListener rejectMessageListener;
	
	public AlertsView() {
//		Client.register.addPropertyChangeListener(new CalendarRegistryListener());
		setLayout(null);
		
		lblAlerts = new JLabel("Alarmer/varsler");
		lblAlerts.setBounds(10, 11, 97, 14);
		add(lblAlerts);
		
		alertList = new JList();
		alertList.setBounds(10, 36, 639, 150);
		add(alertList);
		generateAlertList();
		alertList.addPropertyChangeListener(alertListListener);
		
		btnMarkAlarmAsRead = new JButton("Fjern alarm"); //Alarm (alerts)
		btnMarkAlarmAsRead.setBounds(499, 197, 150, 23);
		add(btnMarkAlarmAsRead);
		btnMarkAlarmAsRead.addActionListener(new BTNMarkAlertAsReadListener());
		
		lblMessages = new JLabel("Beskjeder");
		lblMessages.setBounds(10, 227, 97, 14);
		add(lblMessages);
		
		rejectionMessagesList = new JList();
		rejectionMessagesList.setBounds(10, 252, 639, 150);
		add(rejectionMessagesList);
		generateRejectMessageList();
		rejectionMessagesList.addPropertyChangeListener(rejectMessageListener);
		
		btnMarkAlertAsRead = new JButton("Fjern beskjed"); //RejectMessages
		btnMarkAlertAsRead.setBounds(499, 413, 150, 23);
		add(btnMarkAlertAsRead);
		btnMarkAlertAsRead.addActionListener(new BTNMarkRejectionAsReadListener());

	}
	public void countUnreadAlerts(){
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
	}
	private void generateRejectMessageList() {
		try{
            rejectMessageListModel = new DefaultListModel<RejectMessage>();
            for(RejectMessage rm : Client.register.getRejectMessages()){
            	if(!rm.isSeen()){ // Add only unseen elements
            		rejectMessageListModel.addElement(rm);
            	}
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
				if(!alert.isSeen()){ // Add only unseen elements
					alertListModel.addElement(alert);
				}
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
//	public class CalendarRegistryListener implements PropertyChangeListener{
//
//		@Override
//		public void propertyChange(PropertyChangeEvent evt) {
//			System.out.println("ALERTVIEW, propChanged: "+ evt.getPropertyName());
////			
////			generateAlertList();
////			generateRejectMessageList();
//			
//		}
//		
//	}
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(propertyName.equals("alerts")){
			generateAlertList(); 
			// Setter modell på nytt og god stemning
		}
		else if(propertyName.equals("rejectMessages")){
			generateRejectMessageList(); 
			//Samme som over. Ny modell og gode greier.
		}
		
	}

}
