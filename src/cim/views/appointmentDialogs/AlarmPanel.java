package cim.views.appointmentDialogs;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;

public class AlarmPanel extends JPanel{
	private JTextField txtAlarmYear;
	private JTextField txtAlarmDay;
	public AlarmPanel() {
		setLayout(null);
		
		JLabel lblAlarmDato = new JLabel("Dato");
		lblAlarmDato.setBounds(42, 32, 23, 14);
		add(lblAlarmDato);
		
		txtAlarmDay = new JTextField();
		txtAlarmDay.setBounds(90, 29, 45, 20);
		txtAlarmDay.setText("DD");
		add(txtAlarmDay);
		txtAlarmDay.setColumns(2);
		
		JComboBox comBoxAlarmMonth = new JComboBox();
		comBoxAlarmMonth.setBounds(140, 29, 45, 20);
		add(comBoxAlarmMonth);
		
		txtAlarmYear = new JTextField();
		txtAlarmYear.setBounds(190, 29, 45, 20);
		txtAlarmYear.setText("YYYY");
		add(txtAlarmYear);
		txtAlarmYear.setColumns(4);
		
		JLabel lblAlarmTime = new JLabel("Tid for varsel");
		lblAlarmTime.setBounds(22, 57, 63, 14);
		add(lblAlarmTime);
		
		JComboBox comBoxAlarmHH = new JComboBox();
		comBoxAlarmHH.setBounds(90, 54, 45, 20);
		add(comBoxAlarmHH);
		
		JLabel lblColon = new JLabel(":");
		lblColon.setBounds(160, 57, 4, 14);
		add(lblColon);
		
		JComboBox comBoxAlarmMM = new JComboBox();
		comBoxAlarmMM.setBounds(190, 54, 45, 20);
		add(comBoxAlarmMM);
	}
// Felter for å lege til alarm. Bruk absolute layout
// Panelet er allerede "montert" i AddAppointmentDialog
}
