package cim.views.appointmentDialogs;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLayeredPane;
import java.awt.GridBagConstraints;
import java.awt.Dialog.ModalityType;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.DropMode;

import cim.util.Fonts;

public class AppointmentInfo extends JDialog{
	private AlarmPanel addAlarmPanel;
	private JTextField txtTime;
	private JTextField txtPlace;
	private JTextField txtApproved;
	private JTextField txtDeclined;
	private JTextField txtWaiting;
	
	public AppointmentInfo() {
		setModalityType(ModalityType.DOCUMENT_MODAL);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[] {150, 100, 35, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel infoPanel = new JPanel();
		GridBagConstraints gbc_infoPanel = new GridBagConstraints();
		gbc_infoPanel.fill = GridBagConstraints.BOTH;
		gbc_infoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_infoPanel.gridx = 0;
		gbc_infoPanel.gridy = 0;
		getContentPane().add(infoPanel, gbc_infoPanel);
		infoPanel.setLayout(null);
		
		JLabel lblTime = new JLabel("Tid");
		lblTime.setBounds(42, 55, 46, 14);
		infoPanel.add(lblTime);
		
		JLabel lblPlace = new JLabel("Sted");
		lblPlace.setBounds(42, 80, 46, 14);
		infoPanel.add(lblPlace);
		
		txtTime = new JTextField();
		txtTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtTime.setEditable(false);
		txtTime.setText("tid");
		txtTime.setBounds(75, 52, 86, 20);
		infoPanel.add(txtTime);
		txtTime.setColumns(10);
		
		txtPlace = new JTextField();
		txtPlace.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPlace.setEditable(false);
		txtPlace.setText("m\u00F8terom");
		txtPlace.setBounds(75, 77, 86, 20);
		infoPanel.add(txtPlace);
		txtPlace.setColumns(10);
		
		JLabel lblMeetingName = new JLabel("M\u00F8tenavn");
		lblMeetingName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMeetingName.setBounds(28, 17, 133, 27);
		infoPanel.add(lblMeetingName);
		
		JLabel lblIconApproved = new JLabel("New label");
		lblIconApproved.setFont(new Font("FontAwesome", Font.PLAIN, 11));
		lblIconApproved.setText(Fonts.AwesomeIcons.ICON_OK.toString());
		lblIconApproved.setBounds(42, 108, 15, 14);
		infoPanel.add(lblIconApproved);
		
		JLabel lblIconDeclined = new JLabel("New label");
		lblIconDeclined.setFont(new Font("FontAwesome", Font.PLAIN, 11));
		lblIconDeclined.setText(Fonts.AwesomeIcons.ICON_REMOVE.toString());
		lblIconDeclined.setBounds(85, 108, 15, 14);
		infoPanel.add(lblIconDeclined);
		
		JLabel lblWaiting = new JLabel("New label");
		lblWaiting.setFont(new Font("FontAwesome", Font.PLAIN, 11));
		lblWaiting.setText(Fonts.AwesomeIcons.ICON_GROUP.toString());
		lblWaiting.setBounds(120, 108, 15, 14);
		infoPanel.add(lblWaiting);
		
		txtApproved = new JTextField();
		txtApproved.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtApproved.setEditable(false);
		txtApproved.setBounds(52, 105, 15, 20);
		infoPanel.add(txtApproved);
		txtApproved.setColumns(2);
		
		txtDeclined = new JTextField();
		txtDeclined.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDeclined.setEditable(false);
		txtDeclined.setColumns(2);
		txtDeclined.setBounds(95, 105, 15, 20);
		infoPanel.add(txtDeclined);
		
		txtWaiting = new JTextField();
		txtWaiting.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtWaiting.setEditable(false);
		txtWaiting.setColumns(2);
		txtWaiting.setBounds(135, 105, 15, 20);
		infoPanel.add(txtWaiting);
		
		addAlarmPanel = new AlarmPanel();
		GridBagConstraints gbc_addAlarmPanel_1 = new GridBagConstraints();
		gbc_addAlarmPanel_1.fill = GridBagConstraints.BOTH;
		gbc_addAlarmPanel_1.insets = new Insets(0, 0, 5, 0);
		gbc_addAlarmPanel_1.gridx = 0;
		gbc_addAlarmPanel_1.gridy = 1;
		getContentPane().add(addAlarmPanel, gbc_addAlarmPanel_1);
		
		JLabel lbladdAlarm = new JLabel("Legg til varsel");
		lbladdAlarm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbladdAlarm.setBounds(23, 0, 95, 16);
		addAlarmPanel.add(lbladdAlarm);
		
		JPanel buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 2;
		getContentPane().add(buttonPanel, gbc_buttonPanel);
		
		JButton btnCancel = new JButton("Avbryt");
		btnCancel.setBounds(233, 11, 116, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonPanel.setLayout(null);
		buttonPanel.add(btnCancel);
		
		JButton btnSaveChanges = new JButton("Lagre endringer");
		btnSaveChanges.setBounds(107, 11, 116, 23);
		buttonPanel.add(btnSaveChanges);
		GridBagConstraints gbc_addAlarmPanel_11 = new GridBagConstraints();
		gbc_addAlarmPanel_11.gridwidth = 5;
		gbc_addAlarmPanel_11.fill = GridBagConstraints.BOTH;
		gbc_addAlarmPanel_11.insets = new Insets(0, 0, 5, 0);
		gbc_addAlarmPanel_11.gridx = 0;
		gbc_addAlarmPanel_11.gridy = 4;
	}
}
