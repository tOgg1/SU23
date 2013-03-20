package cim.views.appointmentDialogs;

import cim.database.DatabaseHandler;
import cim.models.Account;
import cim.models.Attendable;
import cim.models.Group;
import cim.net.Client;
import cim.util.CloakedIronManException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

public class ParticipantsPanel extends JPanel implements ActionListener, DocumentListener {
	private DatabaseHandler db;
	private JTextField txtSearch;

    private final DefaultListModel<Attendable> allModels;
    private DefaultListModel<Attendable> modelSearch;
    private DefaultListModel<Attendable> modelAttending;
    private DefaultListModel<Attendable> modelNewSearch;
	JLabel lblSearch, lblMtedeltagere, lblSketreff;

	JList listSearchResult, listParticipants;

	JButton btnAddParticipant, btnRemoveParticipant;

	public ParticipantsPanel() throws CloakedIronManException {
		setLayout(null);

		lblSearch = new JLabel("Navn p\u00E5 person eller gruppe");
		lblSearch.setBounds(10, 11, 170, 14);
		add(lblSearch);

		txtSearch = new JTextField();
		txtSearch.setBounds(10, 36, 86, 20);
		add(txtSearch);
		txtSearch.setColumns(10);
        txtSearch.getDocument().addDocumentListener(this);

		lblSketreff = new JLabel("S\u00F8ketreff");
		lblSketreff.setBounds(10, 72, 46, 14);
		add(lblSketreff);

		listSearchResult = new JList();
		ArrayList<Account> accountList = Client.register.getAllUsers();
		ArrayList<Group> groupList = Client.register.getAllGroups();
        allModels = new DefaultListModel<Attendable>();
		modelSearch = new DefaultListModel<Attendable>();
		for(Account a : accountList){
			modelSearch.addElement(a);
            allModels.addElement(a);
		}
		for(Group g : groupList)
		{
			modelSearch.addElement(g);
            allModels.addElement(g);
        }
        listSearchResult.setModel(modelSearch);
		listSearchResult.setSelectedIndex(0);
		listSearchResult.setBounds(10, 97, 162, 103);
        JScrollPane scrollSearch = new JScrollPane(listSearchResult);
        scrollSearch.setBounds(10, 97, 162, 103);

        add(scrollSearch);


		modelAttending = new DefaultListModel<Attendable>();
		listParticipants = new JList();
		listParticipants.setModel(modelAttending);
        JScrollPane scrollParticipants = new JScrollPane(listParticipants);
        scrollParticipants.setBounds(281, 97, 162, 103);
		add(scrollParticipants);

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

    public void updateSearchModel(ListModel model)
    {
        listSearchResult.setModel(model);
    }

    public void updateFromSearch(String search)
    {
        System.out.println("Updating from search\n-------------------\n------------");
        modelSearch = getSearchResult(search);
        System.out.println(modelSearch.size());
        updateSearchModel(modelSearch);
    }

    //Veldig dårlig søkealgoritme men hvem bryr seg
    public DefaultListModel<Attendable> getSearchResult(String search)
    {

        // Return full list of models that is not in the attending list
        if(search.equals(""))
        {
            DefaultListModel<Attendable> allModelsNotAttending = new DefaultListModel<Attendable>();
            Enumeration<Attendable> models = allModels.elements();
            while(models.hasMoreElements())
            {
                Attendable att = models.nextElement();
                if(!modelAttending.contains(att))
                {
                    allModelsNotAttending.addElement(att);
                }
            }
            return allModelsNotAttending;
        }

        DefaultListModel<Attendable> resultList = new DefaultListModel<Attendable>();

        for(int i = 0; i < allModels.size(); i++)
        {
            for(String s : allModels.getElementAt(i).getName().toLowerCase().split(" "))
            {
                char[] searchArray = search.toCharArray();
                char[] modelArray = s.toCharArray();
                int j = 0;
                for(Character ch : searchArray)
                {
                    ch = Character.toLowerCase(ch);
                    if(j > modelArray.length-1)
                    {
                        break;
                    }
                    if(ch == modelArray[j])
                    {
                        ++j;
                        if(j < searchArray.length)
                            continue;
                    }

                    if(j != searchArray.length)
                    {
                        break;
                    }

                    if(modelAttending.contains(allModels.elementAt(i)))
                        break;

                    resultList.addElement(allModels.elementAt(i));
                }
            }
        }
        return resultList;
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

    @Override
    public void insertUpdate(DocumentEvent e)
    {
        updateFromSearch(txtSearch.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        updateFromSearch(txtSearch.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {
        updateFromSearch(txtSearch.getText());
    }
}
