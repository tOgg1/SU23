package cim.views;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;


public class SaveChangesDialog extends JFrame{
	
	public SaveChangesDialog(){
		setResizable(false);
		pack();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 30, 30, 30, 30, 5};
		gridBagLayout.rowHeights = new int[] {30, 30, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblVilDuLagre = new JLabel("Vil du lagre endringene dine?");
		GridBagConstraints gbc_lblVilDuLagre = new GridBagConstraints();
		gbc_lblVilDuLagre.gridheight = 2;
		gbc_lblVilDuLagre.gridwidth = 3;
		gbc_lblVilDuLagre.insets = new Insets(0, 0, 5, 5);
		gbc_lblVilDuLagre.gridx = 1;
		gbc_lblVilDuLagre.gridy = 0;
		getContentPane().add(lblVilDuLagre, gbc_lblVilDuLagre);
		
		JButton btnLagre = new JButton("Lagre");
		GridBagConstraints gbc_btnLagre = new GridBagConstraints();
		gbc_btnLagre.anchor = GridBagConstraints.EAST;
		gbc_btnLagre.insets = new Insets(0, 0, 0, 5);
		gbc_btnLagre.gridx = 3;
		gbc_btnLagre.gridy = 2;
		getContentPane().add(btnLagre, gbc_btnLagre);
		
		JButton btnAvbryt = new JButton("Avbryt");
		GridBagConstraints gbc_btnAvbryt = new GridBagConstraints();
		gbc_btnAvbryt.insets = new Insets(0, 0, 0, 5);
		gbc_btnAvbryt.gridx = 4;
		gbc_btnAvbryt.gridy = 2;
		getContentPane().add(btnAvbryt, gbc_btnAvbryt);
	}

}
