package cim.views.appointmentDialogs;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;

public class AlarmPanel extends JPanel{
	JComboBox comboBoxAlarmMonth, comboBoxAlarmDays, comboBoxAlarmHours, comboBoxAlarmMinutes, comboBoxAlarmYear;
	public AlarmPanel() {
		setLayout(null);
		
		JLabel lblAlarmDato = new JLabel("Dato");
		lblAlarmDato.setBounds(10, 32, 55, 14);
		add(lblAlarmDato);
		
		comboBoxAlarmMonth = new JComboBox();
		for(int i = 0; i < 12; i++)
		{
			comboBoxAlarmMonth.addItem(i+1);
		}
		comboBoxAlarmMonth.setBounds(140, 29, 45, 20);
		add(comboBoxAlarmMonth);
		
		JLabel lblAlarmTime = new JLabel("Tid for varsel");
		lblAlarmTime.setBounds(10, 57, 75, 14);
		add(lblAlarmTime);
		
		comboBoxAlarmHours = new JComboBox();
		for(int i = 0; i < 24; i++)
		{
			comboBoxAlarmHours.addItem(i+1);
		}
		comboBoxAlarmHours.setBounds(90, 54, 45, 20);
		add(comboBoxAlarmHours);
		
		JLabel lblColon = new JLabel(":");
		lblColon.setBounds(160, 57, 4, 14);
		add(lblColon);
		
		comboBoxAlarmMinutes = new JComboBox();
		int[] minutes = {00,15,30,45};
		
		for(int i = 0; i < 4; i++)
		{
			comboBoxAlarmMinutes.addItem(minutes[i]);
		}
		comboBoxAlarmMinutes.setBounds(190, 54, 45, 20);
		add(comboBoxAlarmMinutes);
		
		comboBoxAlarmDays = new JComboBox();
		for(int i = 0; i < 31; i++)
		{
			comboBoxAlarmDays.addItem(i+1);
		}
		comboBoxAlarmDays.setBounds(90, 29, 45, 20);
		add(comboBoxAlarmDays);
		
		comboBoxAlarmYear = new JComboBox();
		for(int i = 2013; i < 2022; i++)
		{
			comboBoxAlarmYear.addItem(i);
		}
		comboBoxAlarmYear.setBounds(190, 29, 45, 20);
		add(comboBoxAlarmYear);
	}
	public int getDays()
	{
		return (int)this.comboBoxAlarmDays.getSelectedItem();
	}
	public int getMonth()
	{
		return (int)this.comboBoxAlarmMonth.getSelectedItem();
	}
	public int getMinutes()
	{
		return (int)this.comboBoxAlarmMinutes.getSelectedItem();
	}
	public int getYear()
	{
		return (int)this.comboBoxAlarmYear.getSelectedItem();
	}
	public int getHours()
	{
		return (int)this.comboBoxAlarmHours.getSelectedItem();
	}
}
