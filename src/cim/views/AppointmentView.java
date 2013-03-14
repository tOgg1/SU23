package cim.views;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class AppointmentView{

	private static JTextField txtDescription;
	private static JTextField txtDD;
	private static JTextField txtYYYY;
	


	public static void main(String args[]){
		JFrame frame = new JFrame("Ny Avtale");
		frame.setResizable(false);
		frame.pack();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblDescription = new JLabel("Beskrivelse");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.EAST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 1;
		frame.getContentPane().add(lblDescription, gbc_lblDescription);
		
		txtDescription = new JTextField();
		GridBagConstraints gbc_txtDescription = new GridBagConstraints();
		gbc_txtDescription.gridwidth = 5;
		gbc_txtDescription.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescription.gridx = 2;
		gbc_txtDescription.gridy = 1;
		frame.getContentPane().add(txtDescription, gbc_txtDescription);
		txtDescription.setColumns(30);
		
		JRadioButton rdbtnTown = new JRadioButton("Sted");
		rdbtnTown.setSelected(true);
		GridBagConstraints gbc_rdbtnTown = new GridBagConstraints();
		gbc_rdbtnTown.anchor = GridBagConstraints.WEST;
		gbc_rdbtnTown.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnTown.gridx = 9;
		gbc_rdbtnTown.gridy = 1;
		frame.getContentPane().add(rdbtnTown, gbc_rdbtnTown);
		
		JLabel lblDate = new JLabel("Dato");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 1;
		gbc_lblDate.gridy = 2;
		frame.getContentPane().add(lblDate, gbc_lblDate);
		
		txtDD = new JTextField();
		txtDD.setText("DD");
		GridBagConstraints gbc_txtDD = new GridBagConstraints();
		gbc_txtDD.insets = new Insets(0, 0, 5, 5);
		gbc_txtDD.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDD.gridx = 3;
		gbc_txtDD.gridy = 2;
		frame.getContentPane().add(txtDD, gbc_txtDD);
		txtDD.setColumns(2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 2;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		
		txtYYYY = new JTextField();
		txtYYYY.setText("YYYY");
		GridBagConstraints gbc_txtYYYY = new GridBagConstraints();
		gbc_txtYYYY.insets = new Insets(0, 0, 5, 5);
		gbc_txtYYYY.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYYYY.gridx = 5;
		gbc_txtYYYY.gridy = 2;
		frame.getContentPane().add(txtYYYY, gbc_txtYYYY);
		txtYYYY.setColumns(4);
		
		JRadioButton rdbtnRoom = new JRadioButton("Reserver rom");
		GridBagConstraints gbc_rdbtnRoom = new GridBagConstraints();
		gbc_rdbtnRoom.anchor = GridBagConstraints.WEST;
		gbc_rdbtnRoom.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnRoom.gridx = 9;
		gbc_rdbtnRoom.gridy = 2;
		frame.getContentPane().add(rdbtnRoom, gbc_rdbtnRoom);
		
		JLabel lblStart = new JLabel("Start");
		GridBagConstraints gbc_lblStart = new GridBagConstraints();
		gbc_lblStart.anchor = GridBagConstraints.EAST;
		gbc_lblStart.insets = new Insets(0, 0, 5, 5);
		gbc_lblStart.gridx = 1;
		gbc_lblStart.gridy = 3;
		frame.getContentPane().add(lblStart, gbc_lblStart);
		
		JComboBox comboBoxStarHH = new JComboBox();
		GridBagConstraints gbc_comboBoxStarHH = new GridBagConstraints();
		gbc_comboBoxStarHH.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxStarHH.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStarHH.gridx = 3;
		gbc_comboBoxStarHH.gridy = 3;
		frame.getContentPane().add(comboBoxStarHH, gbc_comboBoxStarHH);
		
		JLabel lblColon1 = new JLabel(":");
		GridBagConstraints gbc_lblColon1 = new GridBagConstraints();
		gbc_lblColon1.insets = new Insets(0, 0, 5, 5);
		gbc_lblColon1.gridx = 4;
		gbc_lblColon1.gridy = 3;
		frame.getContentPane().add(lblColon1, gbc_lblColon1);
		
		JComboBox comboBoxStartMM = new JComboBox();
		GridBagConstraints gbc_comboBoxStartMM = new GridBagConstraints();
		gbc_comboBoxStartMM.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxStartMM.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStartMM.gridx = 5;
		gbc_comboBoxStartMM.gridy = 3;
		frame.getContentPane().add(comboBoxStartMM, gbc_comboBoxStartMM);
		
		JLabel lblRomstrrelse = new JLabel("Romst\u00F8rrelse");
		GridBagConstraints gbc_lblRomstrrelse = new GridBagConstraints();
		gbc_lblRomstrrelse.anchor = GridBagConstraints.WEST;
		gbc_lblRomstrrelse.insets = new Insets(0, 0, 5, 5);
		gbc_lblRomstrrelse.gridx = 9;
		gbc_lblRomstrrelse.gridy = 3;
		frame.getContentPane().add(lblRomstrrelse, gbc_lblRomstrrelse);
		
		JComboBox comboBoxRoom = new JComboBox();
		GridBagConstraints gbc_comboBoxRoom = new GridBagConstraints();
		gbc_comboBoxRoom.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxRoom.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRoom.gridx = 10;
		gbc_comboBoxRoom.gridy = 3;
		frame.getContentPane().add(comboBoxRoom, gbc_comboBoxRoom);
		
		JLabel lblSlutt = new JLabel("Slutt");
		GridBagConstraints gbc_lblSlutt = new GridBagConstraints();
		gbc_lblSlutt.anchor = GridBagConstraints.EAST;
		gbc_lblSlutt.insets = new Insets(0, 0, 5, 5);
		gbc_lblSlutt.gridx = 1;
		gbc_lblSlutt.gridy = 4;
		frame.getContentPane().add(lblSlutt, gbc_lblSlutt);
		
		JComboBox comboBoxEndHH = new JComboBox();
		GridBagConstraints gbc_comboBoxEndHH = new GridBagConstraints();
		gbc_comboBoxEndHH.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEndHH.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEndHH.gridx = 3;
		gbc_comboBoxEndHH.gridy = 4;
		frame.getContentPane().add(comboBoxEndHH, gbc_comboBoxEndHH);
		
		JLabel lblColon2 = new JLabel(":");
		GridBagConstraints gbc_lblColon2 = new GridBagConstraints();
		gbc_lblColon2.insets = new Insets(0, 0, 5, 5);
		gbc_lblColon2.gridx = 4;
		gbc_lblColon2.gridy = 4;
		frame.getContentPane().add(lblColon2, gbc_lblColon2);
		
		JComboBox comboBoxEndMM = new JComboBox();
		GridBagConstraints gbc_comboBoxEndMM = new GridBagConstraints();
		gbc_comboBoxEndMM.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEndMM.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEndMM.gridx = 5;
		gbc_comboBoxEndMM.gridy = 4;
		frame.getContentPane().add(comboBoxEndMM, gbc_comboBoxEndMM);
		
		JLabel lblAvailableRooms = new JLabel("Ledige rom (st\u00F8rrelse)");
		GridBagConstraints gbc_lblAvailableRooms = new GridBagConstraints();
		gbc_lblAvailableRooms.anchor = GridBagConstraints.WEST;
		gbc_lblAvailableRooms.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvailableRooms.gridx = 9;
		gbc_lblAvailableRooms.gridy = 4;
		frame.getContentPane().add(lblAvailableRooms, gbc_lblAvailableRooms);
		
		JList availableRooms = new JList();
		GridBagConstraints gbc_availableRooms = new GridBagConstraints();
		gbc_availableRooms.insets = new Insets(0, 0, 5, 5);
		gbc_availableRooms.gridheight = 3;
		gbc_availableRooms.gridwidth = 2;
		gbc_availableRooms.fill = GridBagConstraints.BOTH;
		gbc_availableRooms.gridx = 9;
		gbc_availableRooms.gridy = 5;
		frame.getContentPane().add(availableRooms, gbc_availableRooms);
		
		JCheckBox chckbxAddParticipants = new JCheckBox("Legg til personer/grupper");
		GridBagConstraints gbc_chckbxAddParticipants = new GridBagConstraints();
		gbc_chckbxAddParticipants.anchor = GridBagConstraints.WEST;
		gbc_chckbxAddParticipants.gridwidth = 4;
		gbc_chckbxAddParticipants.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAddParticipants.gridx = 1;
		gbc_chckbxAddParticipants.gridy = 10;
		frame.getContentPane().add(chckbxAddParticipants, gbc_chckbxAddParticipants);
		
		JCheckBox chckbxAddAlarm = new JCheckBox("Legg til personlig varsel");
		GridBagConstraints gbc_chckbxAddAlarm = new GridBagConstraints();
		gbc_chckbxAddAlarm.anchor = GridBagConstraints.WEST;
		gbc_chckbxAddAlarm.gridwidth = 4;
		gbc_chckbxAddAlarm.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAddAlarm.gridx = 1;
		gbc_chckbxAddAlarm.gridy = 11;
		frame.getContentPane().add(chckbxAddAlarm, gbc_chckbxAddAlarm);
		
		JButton btnSave = new JButton("Legg til i kalender");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 9;
		gbc_btnSave.gridy = 11;
		frame.getContentPane().add(btnSave, gbc_btnSave);
		
		JButton btnCancel = new JButton("        Avbryt         ");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 10;
		gbc_btnCancel.gridy = 11;
		frame.getContentPane().add(btnCancel, gbc_btnCancel);
		frame.setVisible(true);
	}
}
