package cim.views;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AlertPanel extends JPanel{
	public AlertPanel() {
		setLayout(null);
		
//		JLabel lblAlertString = new JLabel(buildAlertString(Alert alert));
		JLabel lblAlertString = new JLabel(buildAlertString());

		lblAlertString.setBounds(10, 11, 46, 14);
		add(lblAlertString);
		
		JButton btnEditMeeting = new JButton("Endre m\u00F8te");
		btnEditMeeting.setBounds(509, 7, 111, 23);
		add(btnEditMeeting);
	}
	
	private String buildAlertString(){
		String s = "";
		/*
		 * Message from
		 * "...avlyste møtet den DATO KLOKKA: "
		 * "Møtebeskrivelse"
		 */
		return s;
	}
}
