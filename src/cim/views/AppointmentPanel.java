package cim.views;

import cim.models.Appointment;
import cim.net.Client;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    public AppointmentPanel(Appointment base)
    {
        this.base = base;
        JTextField text = new JTextField(base.getName());
        text.setEditable(false);
        this.add(text);
        
        JTextPane textPane = new JTextPane();
        textPane.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		System.out.println(getBaseId());
        	}
        });
        textPane.setText("x");
        add(textPane);
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
}
