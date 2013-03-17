package cim.views.appointmentDialogs;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;

public class AlarmPanel extends JPanel{
	public AlarmPanel() {
		setLayout(null);
		
		JLabel lblAlarmDato = new JLabel("Dato");
		lblAlarmDato.setBounds(10, 32, 55, 14);
		add(lblAlarmDato);
		
		JComboBox comboBoxAlarmMonth = new JComboBox();
		comboBoxAlarmMonth.setBounds(140, 29, 45, 20);
		add(comboBoxAlarmMonth);
		
		JLabel lblAlarmTime = new JLabel("Tid for varsel");
		lblAlarmTime.setBounds(10, 57, 75, 14);
		add(lblAlarmTime);
		
		JComboBox comboBoxAlarmHours = new JComboBox();
		comboBoxAlarmHours.setBounds(90, 54, 45, 20);
		add(comboBoxAlarmHours);
		
		JLabel lblColon = new JLabel(":");
		lblColon.setBounds(160, 57, 4, 14);
		add(lblColon);
		
		JComboBox comboBoxAlarmMinutes = new JComboBox();
		comboBoxAlarmMinutes.setBounds(190, 54, 45, 20);
		add(comboBoxAlarmMinutes);
		
		JComboBox comboBoxAlarmDays = new JComboBox();
		comboBoxAlarmDays.setBounds(90, 29, 45, 20);
		add(comboBoxAlarmDays);
		
		JComboBox comboBoxAlarmYear = new JComboBox();
		comboBoxAlarmYear.setBounds(190, 29, 45, 20);
		add(comboBoxAlarmYear);
	}
}
