package cim.views;

import cim.models.Appointment;
import cim.net.Client;
import cim.util.CloakedIronManException;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Mayacat
 * Date: 3/15/13
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppointmentPanel extends JPanel implements Comparable
{
    private Appointment base;
    private PropertyChangeSupport pcs;
    public AppointmentPanel(Appointment base)
    {
        this.base = base;
        JTextField text = new JTextField(base.getName());
        text.setEditable(false);
        this.add(text);
        
        JTextPane textPane = new JTextPane();
        textPane.addMouseListener(new deleteListener());
        textPane.setText("x");
        add(textPane);
        pcs = new PropertyChangeSupport(this);
    }

    public Appointment getBase() {
        return base;
    }
    
    public int getBaseId(){
    	return this.base.getId();
    }

    /**
     * Comparing AppointmentPanel for sorting purposes.
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o)
    {
        //TODO: Make the compare function more complex if necessary.
        AppointmentPanel other = (AppointmentPanel)o;
        return getBase().getStart().compareTo(other.getBase().getStart());
    }
    
    public void addPropertyChangeListener (PropertyChangeListener listener){
    	this.pcs.addPropertyChangeListener(listener);
    }
    
    public class deleteListener extends MouseAdapter{
    	public void mouseReleased(MouseEvent e) {
				pcs.firePropertyChange("delbase", base, null);
				base = null;}
    	}
	}
    
    

