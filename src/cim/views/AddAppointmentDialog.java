package cim.views;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class AddAppointmentDialog extends JFrame{
	public AddAppointmentDialog(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 375);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
	}
}
