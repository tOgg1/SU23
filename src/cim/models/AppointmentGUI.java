package cim.models;

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

public class AppointmentGUI{

	private static JTextField textField;
	private static JTextField txtDd;
	private static JTextField txtYyyy;
	


	public static void main(String args[]){
		JFrame frame = new JFrame("Ny Avtale");
		frame.setResizable(false);
		frame.pack();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblBeskrivelse = new JLabel("Beskrivelse");
		GridBagConstraints gbc_lblBeskrivelse = new GridBagConstraints();
		gbc_lblBeskrivelse.anchor = GridBagConstraints.EAST;
		gbc_lblBeskrivelse.insets = new Insets(0, 0, 5, 5);
		gbc_lblBeskrivelse.gridx = 1;
		gbc_lblBeskrivelse.gridy = 1;
		frame.getContentPane().add(lblBeskrivelse, gbc_lblBeskrivelse);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 5;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(30);
		
		JRadioButton rdbtnSted = new JRadioButton("Sted");
		rdbtnSted.setSelected(true);
		GridBagConstraints gbc_rdbtnSted = new GridBagConstraints();
		gbc_rdbtnSted.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSted.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSted.gridx = 9;
		gbc_rdbtnSted.gridy = 1;
		frame.getContentPane().add(rdbtnSted, gbc_rdbtnSted);
		
		JLabel lblDato = new JLabel("Dato");
		GridBagConstraints gbc_lblDato = new GridBagConstraints();
		gbc_lblDato.anchor = GridBagConstraints.EAST;
		gbc_lblDato.insets = new Insets(0, 0, 5, 5);
		gbc_lblDato.gridx = 1;
		gbc_lblDato.gridy = 2;
		frame.getContentPane().add(lblDato, gbc_lblDato);
		
		txtDd = new JTextField();
		txtDd.setText("DD");
		GridBagConstraints gbc_txtDd = new GridBagConstraints();
		gbc_txtDd.insets = new Insets(0, 0, 5, 5);
		gbc_txtDd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDd.gridx = 3;
		gbc_txtDd.gridy = 2;
		frame.getContentPane().add(txtDd, gbc_txtDd);
		txtDd.setColumns(2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 2;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		
		txtYyyy = new JTextField();
		txtYyyy.setText("YYYY");
		GridBagConstraints gbc_txtYyyy = new GridBagConstraints();
		gbc_txtYyyy.insets = new Insets(0, 0, 5, 5);
		gbc_txtYyyy.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYyyy.gridx = 5;
		gbc_txtYyyy.gridy = 2;
		frame.getContentPane().add(txtYyyy, gbc_txtYyyy);
		txtYyyy.setColumns(4);
		
		JRadioButton rdbtnRom = new JRadioButton("Reserver rom");
		GridBagConstraints gbc_rdbtnRom = new GridBagConstraints();
		gbc_rdbtnRom.anchor = GridBagConstraints.WEST;
		gbc_rdbtnRom.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnRom.gridx = 9;
		gbc_rdbtnRom.gridy = 2;
		frame.getContentPane().add(rdbtnRom, gbc_rdbtnRom);
		
		JLabel lblStart = new JLabel("Start");
		GridBagConstraints gbc_lblStart = new GridBagConstraints();
		gbc_lblStart.anchor = GridBagConstraints.EAST;
		gbc_lblStart.insets = new Insets(0, 0, 5, 5);
		gbc_lblStart.gridx = 1;
		gbc_lblStart.gridy = 3;
		frame.getContentPane().add(lblStart, gbc_lblStart);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 3;
		gbc_comboBox_1.gridy = 3;
		frame.getContentPane().add(comboBox_1, gbc_comboBox_1);
		
		JLabel label = new JLabel(":");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 4;
		gbc_label.gridy = 3;
		frame.getContentPane().add(label, gbc_label);
		
		JComboBox comboBox_3 = new JComboBox();
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 5;
		gbc_comboBox_3.gridy = 3;
		frame.getContentPane().add(comboBox_3, gbc_comboBox_3);
		
		JLabel lblRomstrrelse = new JLabel("Romst\u00F8rrelse");
		GridBagConstraints gbc_lblRomstrrelse = new GridBagConstraints();
		gbc_lblRomstrrelse.anchor = GridBagConstraints.WEST;
		gbc_lblRomstrrelse.insets = new Insets(0, 0, 5, 5);
		gbc_lblRomstrrelse.gridx = 9;
		gbc_lblRomstrrelse.gridy = 3;
		frame.getContentPane().add(lblRomstrrelse, gbc_lblRomstrrelse);
		
		JComboBox comboBox_5 = new JComboBox();
		GridBagConstraints gbc_comboBox_5 = new GridBagConstraints();
		gbc_comboBox_5.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_5.gridx = 10;
		gbc_comboBox_5.gridy = 3;
		frame.getContentPane().add(comboBox_5, gbc_comboBox_5);
		
		JLabel lblSlutt = new JLabel("Slutt");
		GridBagConstraints gbc_lblSlutt = new GridBagConstraints();
		gbc_lblSlutt.anchor = GridBagConstraints.EAST;
		gbc_lblSlutt.insets = new Insets(0, 0, 5, 5);
		gbc_lblSlutt.gridx = 1;
		gbc_lblSlutt.gridy = 4;
		frame.getContentPane().add(lblSlutt, gbc_lblSlutt);
		
		JComboBox comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 3;
		gbc_comboBox_2.gridy = 4;
		frame.getContentPane().add(comboBox_2, gbc_comboBox_2);
		
		JLabel lblNewLabel = new JLabel(":");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 4;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JComboBox comboBox_4 = new JComboBox();
		GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
		gbc_comboBox_4.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_4.gridx = 5;
		gbc_comboBox_4.gridy = 4;
		frame.getContentPane().add(comboBox_4, gbc_comboBox_4);
		
		JLabel lblLedigeRomstrrelse = new JLabel("Ledige rom (st\u00F8rrelse)");
		GridBagConstraints gbc_lblLedigeRomstrrelse = new GridBagConstraints();
		gbc_lblLedigeRomstrrelse.anchor = GridBagConstraints.WEST;
		gbc_lblLedigeRomstrrelse.insets = new Insets(0, 0, 5, 5);
		gbc_lblLedigeRomstrrelse.gridx = 9;
		gbc_lblLedigeRomstrrelse.gridy = 4;
		frame.getContentPane().add(lblLedigeRomstrrelse, gbc_lblLedigeRomstrrelse);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.gridheight = 3;
		gbc_list.gridwidth = 2;
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 9;
		gbc_list.gridy = 5;
		frame.getContentPane().add(list, gbc_list);
		
		JCheckBox chckbxLeggTilPersoner = new JCheckBox("Legg til personer/grupper");
		GridBagConstraints gbc_chckbxLeggTilPersoner = new GridBagConstraints();
		gbc_chckbxLeggTilPersoner.anchor = GridBagConstraints.WEST;
		gbc_chckbxLeggTilPersoner.gridwidth = 4;
		gbc_chckbxLeggTilPersoner.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxLeggTilPersoner.gridx = 1;
		gbc_chckbxLeggTilPersoner.gridy = 10;
		frame.getContentPane().add(chckbxLeggTilPersoner, gbc_chckbxLeggTilPersoner);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Legg til personlig varsel");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox.gridwidth = 4;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 11;
		frame.getContentPane().add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("Legg til i kalender");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 9;
		gbc_btnNewButton.gridy = 11;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton btnAvbryt = new JButton("        Avbryt         ");
		btnAvbryt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnAvbryt = new GridBagConstraints();
		gbc_btnAvbryt.insets = new Insets(0, 0, 0, 5);
		gbc_btnAvbryt.gridx = 10;
		gbc_btnAvbryt.gridy = 11;
		frame.getContentPane().add(btnAvbryt, gbc_btnAvbryt);
		frame.setVisible(true);
	}
}