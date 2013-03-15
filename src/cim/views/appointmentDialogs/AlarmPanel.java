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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {50, 50, 50, 50};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAlarmDato = new JLabel("Dato");
		GridBagConstraints gbc_lblAlarmDato = new GridBagConstraints();
		gbc_lblAlarmDato.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlarmDato.gridx = 0;
		gbc_lblAlarmDato.gridy = 1;
		add(lblAlarmDato, gbc_lblAlarmDato);
		
		txtAlarmDay = new JTextField();
		txtAlarmDay.setText("DD");
		GridBagConstraints gbc_txtAlarmDay = new GridBagConstraints();
		gbc_txtAlarmDay.insets = new Insets(0, 0, 5, 5);
		gbc_txtAlarmDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAlarmDay.gridx = 1;
		gbc_txtAlarmDay.gridy = 1;
		add(txtAlarmDay, gbc_txtAlarmDay);
		txtAlarmDay.setColumns(2);
		
		JComboBox comBoxAlarmMonth = new JComboBox();
		GridBagConstraints gbc_comBoxAlarmMonth = new GridBagConstraints();
		gbc_comBoxAlarmMonth.insets = new Insets(0, 0, 5, 5);
		gbc_comBoxAlarmMonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_comBoxAlarmMonth.gridx = 2;
		gbc_comBoxAlarmMonth.gridy = 1;
		add(comBoxAlarmMonth, gbc_comBoxAlarmMonth);
		
		txtAlarmYear = new JTextField();
		txtAlarmYear.setText("YYYY");
		GridBagConstraints gbc_txtAlarmYear = new GridBagConstraints();
		gbc_txtAlarmYear.insets = new Insets(0, 0, 5, 5);
		gbc_txtAlarmYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAlarmYear.gridx = 3;
		gbc_txtAlarmYear.gridy = 1;
		add(txtAlarmYear, gbc_txtAlarmYear);
		txtAlarmYear.setColumns(4);
		
		JLabel lblAlarmTime = new JLabel("Tid for varsel");
		GridBagConstraints gbc_lblAlarmTime = new GridBagConstraints();
		gbc_lblAlarmTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlarmTime.gridx = 0;
		gbc_lblAlarmTime.gridy = 2;
		add(lblAlarmTime, gbc_lblAlarmTime);
		
		JComboBox comBoxAlarmHH = new JComboBox();
		GridBagConstraints gbc_comBoxAlarmHH = new GridBagConstraints();
		gbc_comBoxAlarmHH.insets = new Insets(0, 0, 5, 5);
		gbc_comBoxAlarmHH.fill = GridBagConstraints.HORIZONTAL;
		gbc_comBoxAlarmHH.gridx = 1;
		gbc_comBoxAlarmHH.gridy = 2;
		add(comBoxAlarmHH, gbc_comBoxAlarmHH);
		
		JLabel lblColon = new JLabel(":");
		GridBagConstraints gbc_lblColon = new GridBagConstraints();
		gbc_lblColon.insets = new Insets(0, 0, 5, 5);
		gbc_lblColon.gridx = 2;
		gbc_lblColon.gridy = 2;
		add(lblColon, gbc_lblColon);
		
		JComboBox comBoxAlarmMM = new JComboBox();
		GridBagConstraints gbc_comBoxAlarmMM = new GridBagConstraints();
		gbc_comBoxAlarmMM.insets = new Insets(0, 0, 5, 5);
		gbc_comBoxAlarmMM.fill = GridBagConstraints.HORIZONTAL;
		gbc_comBoxAlarmMM.gridx = 3;
		gbc_comBoxAlarmMM.gridy = 2;
		add(comBoxAlarmMM, gbc_comBoxAlarmMM);
	}
// Felter for å lege til alarm. Bruk absolute layout
// Panelet er allerede "montert" i AddAppointmentDialog
}
