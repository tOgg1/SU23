package cim.views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

public class AddParticipantsPanel extends JPanel{
	private JTextField txtSearch;
	public AddParticipantsPanel() {
		setLayout(null);
		
		JLabel lblSearch = new JLabel("Navn p\u00E5 person eller gruppe");
		lblSearch.setBounds(10, 11, 170, 14);
		add(lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(10, 36, 86, 20);
		add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("S\u00F8k");
		btnSearch.setBounds(106, 36, 89, 23);
		add(btnSearch);
		
		JLabel lblSketreff = new JLabel("S\u00F8ketreff");
		lblSketreff.setBounds(10, 72, 46, 14);
		add(lblSketreff);
		
		JList listSearchResult = new JList();
		listSearchResult.setBounds(10, 97, 162, 103);
		add(listSearchResult);
		
		JList listParticipants = new JList();
		listParticipants.setBounds(281, 97, 162, 103);
		add(listParticipants);
		
		JButton btnAddParticipant = new JButton("Legg til");
		btnAddParticipant.setBounds(182, 114, 89, 23);
		add(btnAddParticipant);
		
		JButton btnRemoveParticipant = new JButton("Fjern");
		btnRemoveParticipant.setBounds(182, 148, 89, 23);
		add(btnRemoveParticipant);
		
		JLabel lblMtedeltagere = new JLabel("M\u00F8tedeltagere");
		lblMtedeltagere.setBounds(279, 72, 130, 14);
		add(lblMtedeltagere);
		
		
		
		
	}
}
