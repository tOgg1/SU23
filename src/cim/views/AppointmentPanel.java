package cim.views;

import cim.models.Appointment;
import cim.models.Calendar;
import cim.net.Client;
import cim.util.CloakedIronManException;
import cim.util.Fonts;
import cim.views.appointmentDialogs.EditAppointmentDialog;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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
    private Calendar cal;
    private PropertyChangeSupport pcs;
    private JTextField txtTime;
    private JTextField txtPlace;
    private JTextField txtOkNum;
    private JTextField txtDeclinedNum;
    private JTextField txtGroupNum;
    public JLabel lblOK;
    public JLabel lblArrow;
    public JLabel lblGroup;
    public JLabel lblDeclined;
    private JLabel lblWait;
    private JLabel lblPlace;
    
    public AppointmentPanel(Appointment base, Calendar calendar)
    {
    	setPreferredSize(new Dimension(190, 65));
    	
        this.base = base;
        setLayout(null);
        JTextField txtName = new JTextField("Navn");
        txtName.setColumns(20);
        txtName.setBounds(10, 39, 143, 20);
        txtName.setBackground(null);
        txtName.setBorder(null);
        txtName.setText(base.getName());
        this.add(txtName);
        
        JLabel lblDelete = new JLabel();
        lblDelete.setBounds(162, 11, 18, 20);
        lblDelete.setFont(new Font("FontAwesome", Font.PLAIN, 14));
        lblDelete.addMouseListener(new deleteListener());
        lblDelete.setText(Fonts.AwesomeIcons.ICON_REMOVE.toString());
        add(lblDelete);
        pcs = new PropertyChangeSupport(this);
        
        JLabel lblEdit = new JLabel("New label");
        lblEdit.setFont(new Font("FontAwesome", Font.PLAIN, 14));
        lblEdit.setBounds(135, 14, 28, 14);
        lblEdit.addMouseListener(new editListener());
        lblEdit.setText(Fonts.AwesomeIcons.ICON_PENCIL.toString());
        add(lblEdit);
        
        txtTime = new JTextField();
        txtTime.setText(base.getStartFormatted() + "-" + base.getEndFormatted());
        txtTime.setBackground(null);
        txtTime.setBorder(null);
        txtTime.setEditable(false);
        txtTime.setBounds(10, 12, 115, 20);
        txtTime.setBackground(null);
        txtTime.setBorder(null);
        txtTime.setColumns(12);
        add(txtTime);
        
        lblArrow = new JLabel("New label");
        lblArrow.setFont(new Font("FontAwesome", Font.PLAIN, 14));
        lblArrow.setBounds(163, 41, 11, 14);
        lblArrow.setText(Fonts.AwesomeIcons.ICON_CARET_DOWN.toString());
        lblArrow.addMouseListener(new showInfoListener());
        add(lblArrow);
        
        txtPlace = new JTextField();
        txtPlace.setVisible(false);
        txtPlace.setText(base.getWhere());
        txtPlace.setBounds(20, 70, 115, 20);
        txtPlace.setBackground(null);
        txtPlace.setBorder(null);
        add(txtPlace);
        txtPlace.setColumns(15);
        
        lblGroup = new JLabel("New label");
        lblGroup.setVisible(false);
        lblGroup.setFont(new Font("FontAwesome", Font.PLAIN, 11));
        lblGroup.setBounds(10, 101, 24, 14);
        lblGroup.setText(Fonts.AwesomeIcons.ICON_GROUP.toString());
        add(lblGroup);
        
        lblOK = new JLabel("New label");
        lblOK.setEnabled(false);
        lblOK.setVisible(false);
        lblOK.setFont(new Font("FontAwesome", Font.PLAIN, 11));
        lblOK.setBounds(30, 101, 29, 14);
        lblOK.setText(Fonts.AwesomeIcons.ICON_OK.toString());
        add(lblOK);
        
        lblDeclined = new JLabel("New label");
        lblDeclined.setEnabled(false);
        lblDeclined.setVisible(false);
        lblDeclined.setFont(new Font("FontAwesome", Font.PLAIN, 11));
        lblDeclined.setBounds(69, 101, 25, 14);
        lblDeclined.setText(Fonts.AwesomeIcons.ICON_REMOVE.toString());
        add(lblDeclined);
        
        txtOkNum = new JTextField();
        txtOkNum.setText("2");
        txtOkNum.setEnabled(false);
        txtOkNum.setVisible(false);
        txtOkNum.setBounds(44, 98, 15, 20);
        txtOkNum.setBackground(null);
        txtOkNum.setBorder(null);
        txtOkNum.setColumns(2);
        add(txtOkNum);
        
        txtDeclinedNum = new JTextField();
        txtDeclinedNum.setText("1");
        txtDeclinedNum.setEnabled(false);
        txtDeclinedNum.setVisible(false);
        txtDeclinedNum.setBounds(79, 98, 15, 20);
        txtDeclinedNum.setBackground(null);
        txtDeclinedNum.setBorder(null);
        txtDeclinedNum.setColumns(2);
        add(txtDeclinedNum);
        
        txtGroupNum = new JTextField();
        txtGroupNum.setText("4");
        txtGroupNum.setEnabled(false);
        txtGroupNum.setVisible(false);
        txtGroupNum.setColumns(2);
        txtGroupNum.setBounds(124, 98, 15, 20);
        txtGroupNum.setBackground(null);
        txtGroupNum.setBorder(null);
        add(txtGroupNum);
        
        this.cal = calendar;
        
        lblWait = new JLabel("New label");
        lblWait.setEnabled(false);
        lblWait.setVisible(false);
        lblWait.setFont(new Font("FontAwesome", Font.PLAIN, 14));
        lblWait.setBounds(104, 101, 35, 14);
        lblWait.setText(Fonts.AwesomeIcons.ICON_TIME.toString());
        add(lblWait);
        
        lblPlace = new JLabel("New label");
        lblPlace.setVisible(false);
        lblPlace.setFont(new Font("FontAwesome", Font.PLAIN, 12));
        lblPlace.setBounds(10, 72, 24, 14);
        lblPlace.setText(Fonts.AwesomeIcons.ICON_MAP_MARKER.toString());
        add(lblPlace);
    }

    public Appointment getBase() {
        return base;
    }
    
    public int getBaseId(){
    	return this.base.getId();
    }
    
    public Calendar getCalendar(){
    	return this.cal;
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
				pcs.firePropertyChange("delbase", AppointmentPanel.this, null);
				base = null;}
    	}
    
    public class editListener extends MouseAdapter{
    	public void mouseReleased(MouseEvent e){
    		try {
				EditAppointmentDialog edit = new EditAppointmentDialog(Client.register.getAccount(), base, cal);
			} catch (CloakedIronManException e1) {
				e1.printStackTrace();
			}
    		
    	}
    }

    public class showInfoListener extends MouseAdapter{
    	public void mouseReleased(MouseEvent e){
    		if (lblArrow.getText().equals(Fonts.AwesomeIcons.ICON_CARET_DOWN.toString())){
    			setPreferredSize(new Dimension(190,130));
    			lblArrow.setText(Fonts.AwesomeIcons.ICON_CARET_UP.toString());
            	lblGroup.setVisible(true);
            	txtPlace.setVisible(true);
            	txtOkNum.setVisible(true);
            	txtDeclinedNum.setVisible(true);
            	txtGroupNum.setVisible(true);
            	lblOK.setVisible(true);
            	lblDeclined.setVisible(true);
                lblPlace.setVisible(true);
                lblWait.setVisible(true);
    		}
    		else if(lblArrow.getText().equals(Fonts.AwesomeIcons.ICON_CARET_UP.toString())){
    			setPreferredSize(new Dimension(190,65));
    			lblArrow.setText(Fonts.AwesomeIcons.ICON_CARET_DOWN.toString());
            	lblGroup.setVisible(false);
            	txtPlace.setVisible(false);
            	txtOkNum.setVisible(false);
            	txtDeclinedNum.setVisible(false);
            	txtGroupNum.setVisible(false);
            	lblOK.setVisible(false);
            	lblDeclined.setVisible(false);
                lblPlace.setVisible(false);
                lblWait.setVisible(false);
    		}
    		}
		}
	}
    
    

