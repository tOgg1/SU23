package cim.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cim.models.Appointment;
import cim.views.appointmentDialogs.AddAppointmentDialog;

public class CalendarView extends JPanel {
	private static JTextField txtMandag;
	private static JTextField txtTirsdag;
	private static JTextField txtOnsdag;
	private static JTextField txtTorsdag;
	private static JTextField txtFredag;
	private static JTextField txtLrdag;
	private static JTextField txtSndag;
	private static JTextField txtUke;
	/**
	 * Create the panel.
	 */
	public CalendarView() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 30};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 150, 30, 150, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		this.setLayout(gridBagLayout);
		
		JButton btnForrigeUke = new JButton("Forrige uke");
		GridBagConstraints gbc_btnForrigeUke = new GridBagConstraints();
		gbc_btnForrigeUke.insets = new Insets(0, 0, 5, 5);
		gbc_btnForrigeUke.gridx = 1;
		gbc_btnForrigeUke.gridy = 1;
		this.add(btnForrigeUke, gbc_btnForrigeUke);
		
		txtUke = new JTextField();
		txtUke.setHorizontalAlignment(SwingConstants.CENTER);
		txtUke.setEditable(false);
		txtUke.setText("Uke 10");
		GridBagConstraints gbc_txtUke = new GridBagConstraints();
		gbc_txtUke.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUke.insets = new Insets(0, 0, 5, 5);
		gbc_txtUke.gridx = 2;
		gbc_txtUke.gridy = 1;
		this.add(txtUke, gbc_txtUke);
		txtUke.setColumns(10);
		
		JButton btnNesteUke = new JButton("Neste uke");
		GridBagConstraints gbc_btnNesteUke = new GridBagConstraints();
		gbc_btnNesteUke.insets = new Insets(0, 0, 5, 5);
		gbc_btnNesteUke.gridx = 3;
		gbc_btnNesteUke.gridy = 1;
		this.add(btnNesteUke, gbc_btnNesteUke);
		
		JLabel lblHoppTilUke = new JLabel("Hopp til uke:");
		GridBagConstraints gbc_lblHoppTilUke = new GridBagConstraints();
		gbc_lblHoppTilUke.anchor = GridBagConstraints.EAST;
		gbc_lblHoppTilUke.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoppTilUke.gridx = 4;
		gbc_lblHoppTilUke.gridy = 1;
		this.add(lblHoppTilUke, gbc_lblHoppTilUke);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 1;
		this.add(comboBox, gbc_comboBox);
		
		JButton btnNyAvtale = new JButton("Ny avtale");
		btnNyAvtale.addActionListener(new AddAppointmentListener());
		GridBagConstraints gbc_btnNyAvtale = new GridBagConstraints();
		gbc_btnNyAvtale.insets = new Insets(0, 0, 5, 5);
		gbc_btnNyAvtale.gridx = 12;
		gbc_btnNyAvtale.gridy = 1;
		this.add(btnNyAvtale, gbc_btnNyAvtale);
		
		txtMandag = new JTextField();
		txtMandag.setHorizontalAlignment(SwingConstants.CENTER);
		txtMandag.setEditable(false);
		txtMandag.setText("Mandag");
		GridBagConstraints gbc_txtMandag = new GridBagConstraints();
		gbc_txtMandag.gridwidth = 2;
		gbc_txtMandag.insets = new Insets(0, 0, 5, 5);
		gbc_txtMandag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMandag.gridx = 1;
		gbc_txtMandag.gridy = 2;
		this.add(txtMandag, gbc_txtMandag);
		txtMandag.setColumns(10);
		
		txtTirsdag = new JTextField();
		txtTirsdag.setHorizontalAlignment(SwingConstants.CENTER);
		txtTirsdag.setEditable(false);
		txtTirsdag.setText("Tirsdag");
		txtTirsdag.setColumns(10);
		GridBagConstraints gbc_txtTirsdag = new GridBagConstraints();
		gbc_txtTirsdag.gridwidth = 2;
		gbc_txtTirsdag.insets = new Insets(0, 0, 5, 5);
		gbc_txtTirsdag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTirsdag.gridx = 3;
		gbc_txtTirsdag.gridy = 2;
		this.add(txtTirsdag, gbc_txtTirsdag);
		
		txtOnsdag = new JTextField();
		txtOnsdag.setHorizontalAlignment(SwingConstants.CENTER);
		txtOnsdag.setEditable(false);
		txtOnsdag.setText("Onsdag");
		GridBagConstraints gbc_txtOnsdag = new GridBagConstraints();
		gbc_txtOnsdag.gridwidth = 2;
		gbc_txtOnsdag.insets = new Insets(0, 0, 5, 5);
		gbc_txtOnsdag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOnsdag.gridx = 5;
		gbc_txtOnsdag.gridy = 2;
		this.add(txtOnsdag, gbc_txtOnsdag);
		txtOnsdag.setColumns(10);
		
		txtTorsdag = new JTextField();
		txtTorsdag.setHorizontalAlignment(SwingConstants.CENTER);
		txtTorsdag.setEditable(false);
		txtTorsdag.setText("Torsdag");
		GridBagConstraints gbc_txtTorsdag = new GridBagConstraints();
		gbc_txtTorsdag.gridwidth = 2;
		gbc_txtTorsdag.insets = new Insets(0, 0, 5, 5);
		gbc_txtTorsdag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTorsdag.gridx = 7;
		gbc_txtTorsdag.gridy = 2;
		this.add(txtTorsdag, gbc_txtTorsdag);
		txtTorsdag.setColumns(10);
		
		txtFredag = new JTextField();
		txtFredag.setHorizontalAlignment(SwingConstants.CENTER);
		txtFredag.setEditable(false);
		txtFredag.setText("Fredag");
		GridBagConstraints gbc_txtFredag = new GridBagConstraints();
		gbc_txtFredag.gridwidth = 2;
		gbc_txtFredag.insets = new Insets(0, 0, 5, 5);
		gbc_txtFredag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFredag.gridx = 9;
		gbc_txtFredag.gridy = 2;
		this.add(txtFredag, gbc_txtFredag);
		txtFredag.setColumns(10);
		
		txtLrdag = new JTextField();
		txtLrdag.setHorizontalAlignment(SwingConstants.CENTER);
		txtLrdag.setEditable(false);
		txtLrdag.setText("L\u00F8rdag");
		GridBagConstraints gbc_txtLrdag = new GridBagConstraints();
		gbc_txtLrdag.gridwidth = 2;
		gbc_txtLrdag.insets = new Insets(0, 0, 5, 5);
		gbc_txtLrdag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLrdag.gridx = 11;
		gbc_txtLrdag.gridy = 2;
		this.add(txtLrdag, gbc_txtLrdag);
		txtLrdag.setColumns(10);
		
		JList mandag = new JList();
		GridBagConstraints gbc_mandag = new GridBagConstraints();
		gbc_mandag.gridheight = 3;
		gbc_mandag.gridwidth = 2;
		gbc_mandag.insets = new Insets(0, 0, 5, 5);
		gbc_mandag.fill = GridBagConstraints.BOTH;
		gbc_mandag.gridx = 1;
		gbc_mandag.gridy = 3;
		this.add(mandag, gbc_mandag);
		
		JList tirsdag = new JList();
		GridBagConstraints gbc_tirsdag = new GridBagConstraints();
		gbc_tirsdag.gridheight = 3;
		gbc_tirsdag.gridwidth = 2;
		gbc_tirsdag.insets = new Insets(0, 0, 5, 5);
		gbc_tirsdag.fill = GridBagConstraints.BOTH;
		gbc_tirsdag.gridx = 3;
		gbc_tirsdag.gridy = 3;
		this.add(tirsdag, gbc_tirsdag);
		
		JList onsdag = new JList();
		GridBagConstraints gbc_onsdag = new GridBagConstraints();
		gbc_onsdag.gridheight = 3;
		gbc_onsdag.gridwidth = 2;
		gbc_onsdag.insets = new Insets(0, 0, 5, 5);
		gbc_onsdag.fill = GridBagConstraints.BOTH;
		gbc_onsdag.gridx = 5;
		gbc_onsdag.gridy = 3;
		this.add(onsdag, gbc_onsdag);
		
		JList torsdag = new JList();
		GridBagConstraints gbc_torsdag = new GridBagConstraints();
		gbc_torsdag.gridheight = 3;
		gbc_torsdag.gridwidth = 2;
		gbc_torsdag.insets = new Insets(0, 0, 5, 5);
		gbc_torsdag.fill = GridBagConstraints.BOTH;
		gbc_torsdag.gridx = 7;
		gbc_torsdag.gridy = 3;
		this.add(torsdag, gbc_torsdag);
		
		JList fredag = new JList();
		GridBagConstraints gbc_fredag = new GridBagConstraints();
		gbc_fredag.gridheight = 3;
		gbc_fredag.gridwidth = 2;
		gbc_fredag.insets = new Insets(0, 0, 5, 5);
		gbc_fredag.fill = GridBagConstraints.BOTH;
		gbc_fredag.gridx = 9;
		gbc_fredag.gridy = 3;
		this.add(fredag, gbc_fredag);
		
		JList lordag = new JList();
		GridBagConstraints gbc_lordag = new GridBagConstraints();
		gbc_lordag.gridwidth = 2;
		gbc_lordag.insets = new Insets(0, 0, 5, 5);
		gbc_lordag.fill = GridBagConstraints.BOTH;
		gbc_lordag.gridx = 11;
		gbc_lordag.gridy = 3;
		this.add(lordag, gbc_lordag);
		
		txtSndag = new JTextField();
		txtSndag.setHorizontalAlignment(SwingConstants.CENTER);
		txtSndag.setEditable(false);
		txtSndag.setText("S\u00F8ndag");
		GridBagConstraints gbc_txtSndag = new GridBagConstraints();
		gbc_txtSndag.gridwidth = 2;
		gbc_txtSndag.insets = new Insets(0, 0, 5, 5);
		gbc_txtSndag.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSndag.gridx = 11;
		gbc_txtSndag.gridy = 4;
		this.add(txtSndag, gbc_txtSndag);
		txtSndag.setColumns(10);
		
		JList sondag = new JList();
		GridBagConstraints gbc_sondag = new GridBagConstraints();
		gbc_sondag.insets = new Insets(0, 0, 5, 5);
		gbc_sondag.gridwidth = 2;
		gbc_sondag.fill = GridBagConstraints.BOTH;
		gbc_sondag.gridx = 11;
		gbc_sondag.gridy = 5;
		this.add(sondag, gbc_sondag);
	}
	
	private class AddAppointmentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AddAppointmentDialog ad = new AddAppointmentDialog();
			ad.setVisible(true);
			Appointment a = ad.getAppointment();
			if(a != null) {
				System.out.println("Appointment set in dialog.");
			} else {
				System.out.println("Appointment not set in dialog.");
			}
		
		}
		
	}

}
