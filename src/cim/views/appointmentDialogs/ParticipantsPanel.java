package cim.views.appointmentDialogs;

import cim.database.DatabaseHandler;
import cim.models.Account;
import cim.models.Attendable;
import cim.models.Group;
import cim.net.Client;
import cim.util.CloakedIronManException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ParticipantsPanel extends JPanel implements ActionListener{
	private DatabaseHandler db;
	private JTextField txtSearch;

	DefaultListModel<Attendable> modelSearch;
	DefaultListModel<Attendable> modelAttending;
	DefaultListModel<Attendable> modelNewSearch;
	DefaultListModel<Attendable> fullList;
	JLabel lblSearch, lblMtedeltagere, lblSketreff;

	JList listSearchResult, listParticipants;

	JButton btnAddParticipant, btnRemoveParticipant,btnSearch;

	public ParticipantsPanel() throws CloakedIronManException {
		setLayout(null);

		lblSearch = new JLabel("Navn p\u00E5 person eller gruppe");
		lblSearch.setBounds(10, 11, 170, 14);
		add(lblSearch);

		txtSearch = new JTextField();
		txtSearch.setBounds(10, 36, 86, 20);
		add(txtSearch);
		txtSearch.setColumns(10);

		btnSearch = new JButton("S\u00F8k");
		btnSearch.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				String search = txtSearch.getText();
				try {
					if(search.equals(""))
					{
						fullList = new DefaultListModel<Attendable>();
						ArrayList<Account> accList = Client.register.getAllUsers();
						ArrayList<Attendable> attList = new ArrayList<Attendable>();
						for(Account acc : accList)
						{
							attList.add((Attendable)acc);
						}

						for(Attendable att : attList)
						{
							fullList.addElement(att);
						}
						listSearchResult.setModel(fullList);

					}
				} catch (CloakedIronManException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!txtSearch.getText().equals(""))
				{
					modelNewSearch = new DefaultListModel<Attendable>();
					for(int i = 0; i < modelSearch.size(); i++)
					{

						for(String name : modelSearch.get(i).getName().split(" "))
						{
							if(name.equals(search))
							{
								modelNewSearch.addElement(modelSearch.get(i));
							}
						}

					}
					listSearchResult.setModel(modelNewSearch);
				}
			}
		});
		btnSearch.setBounds(106, 36, 89, 23);
		add(btnSearch);

		lblSketreff = new JLabel("S\u00F8ketreff");
		lblSketreff.setBounds(10, 72, 46, 14);
		add(lblSketreff);

		listSearchResult = new JList();
		ArrayList<Account> accountList = Client.register.getAllUsers();
		ArrayList<Group> groupList = Client.register.getAllGroups();
		modelSearch = new DefaultListModel<Attendable>();
		for(Account a : accountList){
			modelSearch.addElement(a);
		}
		for(Group g : groupList)
		{
			modelSearch.addElement(g);
		}
		listSearchResult.setModel(modelSearch);
		listSearchResult.setSelectedIndex(0);
		listSearchResult.setBounds(10, 97, 162, 103);
		add(listSearchResult);

		modelAttending = new DefaultListModel<Attendable>();
		listParticipants = new JList();
		listParticipants.setModel(modelAttending);
		listParticipants.setBounds(281, 97, 162, 103);
		add(listParticipants);

		btnAddParticipant = new JButton("Legg til");
		btnAddParticipant.setBounds(182, 114, 89, 23);
		add(btnAddParticipant);

		btnRemoveParticipant = new JButton("Fjern");
		btnRemoveParticipant.setBounds(182, 148, 89, 23);
		add(btnRemoveParticipant);

		lblMtedeltagere = new JLabel("M\u00F8tedeltagere");
		lblMtedeltagere.setBounds(279, 72, 130, 14);
		add(lblMtedeltagere);

		btnAddParticipant.addActionListener(this);
		btnRemoveParticipant.addActionListener(this);
	}

	public ArrayList<Attendable> getInvitees()
	{
		ArrayList<Attendable> attendables = new ArrayList<Attendable>();
		Enumeration<Attendable> modelContent = modelAttending.elements();
		while(modelContent.hasMoreElements())
		{
			attendables.add(modelContent.nextElement());
		}
		System.out.println(attendables.size());
		return attendables;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnAddParticipant)
		{
			Attendable att = modelSearch.get(listSearchResult.getSelectedIndex());
			modelSearch.remove(listSearchResult.getSelectedIndex());
			modelAttending.addElement(att);
		}
		else if(e.getSource() == btnRemoveParticipant)
		{
			Attendable att = modelAttending.get(listParticipants.getSelectedIndex());
			modelAttending.remove(listParticipants.getSelectedIndex());
			//TODO: Only if attendable matches search query
			modelSearch.addElement(att);
		}
	}
}
