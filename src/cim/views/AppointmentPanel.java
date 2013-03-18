package cim.views;

import cim.models.Appointment;
import cim.net.Client;
import cim.util.CloakedIronManException;
import cim.util.Fonts;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
    private PropertyChangeSupport pcs;
    private JTextField txtTime;
    private JTextField txtPlace;
    private JTextField txtOkNum;
    private JTextField txtDeclinedNum;
    private JTextField txtGruopNum;
    public JLabel lblOK;
    public JLabel lblArrow;
    public JLabel lblGroup;
    public JLabel lblDeclined;
    
    public AppointmentPanel(Appointment base)
    {
        this.base = base;
        setLayout(null);
        JTextField txtName = new JTextField("Navn");
        txtName.setColumns(20);
        txtName.setBounds(10, 39, 114, 20);
        txtName.setEditable(false);
        txtName.setText(base.getName());
        this.add(txtName);
        
        JLabel lblDelete = new JLabel();
        lblDelete.setBounds(134, 11, 18, 20);
        lblDelete.setFont(new Font("FontAwesome", Font.PLAIN, 14));
        lblDelete.addMouseListener(new deleteListener());
        lblDelete.setText(Fonts.AwesomeIcons.ICON_REMOVE.toString());
        add(lblDelete);
        pcs = new PropertyChangeSupport(this);
        
        JLabel lblEdit = new JLabel("New label");
        lblEdit.setFont(new Font("FontAwesome", Font.PLAIN, 14));
        lblEdit.setBounds(106, 14, 18, 14);
        lblEdit.setText(Fonts.AwesomeIcons.ICON_PENCIL.toString());
        add(lblEdit);
        
        txtTime = new JTextField();
        txtTime.setText(base.getDateFormatted("hh:mm") + "-" + base.getDateFormatted("hh:mm"));
        txtTime.setEditable(false);
        txtTime.setBounds(10, 12, 86, 20);
        add(txtTime);
        txtTime.setColumns(12);
        
        lblArrow = new JLabel("New label");
        lblArrow.setFont(new Font("FontAwesome", Font.PLAIN, 14));
        lblArrow.setBounds(134, 41, 11, 14);
        lblArrow.setText(Fonts.AwesomeIcons.ICON_CARET_DOWN.toString());
        lblArrow.addMouseListener(new showInfoListener());
        add(lblArrow);
        
        txtPlace = new JTextField();
        txtPlace.setText("Sted");
        txtPlace.setVisible(false);
        txtPlace.setEditable(false);
        txtPlace.setBounds(10, 70, 114, 20);
        add(txtPlace);
        txtPlace.setColumns(15);
        
        lblGroup = new JLabel("New label");
        lblGroup.setVisible(false);
        lblGroup.setFont(new Font("FontAwesome", Font.PLAIN, 11));
        lblGroup.setBounds(91, 101, 18, 14);
        lblGroup.setText(Fonts.AwesomeIcons.ICON_GROUP.toString());
        add(lblGroup);
        
        lblOK = new JLabel("New label");
        lblOK.setVisible(false);
        lblOK.setFont(new Font("FontAwesome", Font.PLAIN, 11));
        lblOK.setBounds(10, 101, 18, 14);
        lblOK.setText(Fonts.AwesomeIcons.ICON_OK.toString());
        add(lblOK);
        
        lblDeclined = new JLabel("New label");
        lblDeclined.setVisible(false);
        lblDeclined.setFont(new Font("FontAwesome", Font.PLAIN, 11));
        lblDeclined.setBounds(53, 101, 18, 14);
        lblDeclined.setText(Fonts.AwesomeIcons.ICON_REMOVE.toString());
        add(lblDeclined);
        
        txtOkNum = new JTextField();
        txtOkNum.setVisible(false);
        txtOkNum.setEditable(false);
        txtOkNum.setBounds(28, 98, 15, 20);
        add(txtOkNum);
        txtOkNum.setColumns(2);
        
        txtDeclinedNum = new JTextField();
        txtDeclinedNum.setVisible(false);
        txtDeclinedNum.setEditable(false);
        txtDeclinedNum.setBounds(66, 98, 15, 20);
        add(txtDeclinedNum);
        txtDeclinedNum.setColumns(2);
        
        txtGruopNum = new JTextField();
        txtGruopNum.setVisible(false);
        txtGruopNum.setEditable(false);
        txtGruopNum.setColumns(2);
        txtGruopNum.setBounds(106, 98, 15, 20);
        add(txtGruopNum);
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

    public class showInfoListener extends MouseAdapter{
    	public void mouseReleased(MouseEvent e){
    		if (lblArrow.getText() == Fonts.AwesomeIcons.ICON_CARET_DOWN.toString()){
    			lblArrow.setText(Fonts.AwesomeIcons.ICON_CARET_UP.toString());
            	lblGroup.setVisible(true);
            	txtTime.setVisible(true);
            	txtPlace.setVisible(true);
            	txtOkNum.setVisible(true);
            	txtDeclinedNum.setVisible(true);
            	txtGruopNum.setVisible(true);
            	lblOK.setVisible(true);
            	lblDeclined.setVisible(true);
    		}
    		else if(lblArrow.getText() == Fonts.AwesomeIcons.ICON_CARET_UP.toString()){
    			lblArrow.setText(Fonts.AwesomeIcons.ICON_CARET_DOWN.toString());
            	lblGroup.setVisible(false);
            	txtTime.setVisible(false);
            	txtPlace.setVisible(false);
            	txtOkNum.setVisible(false);
            	txtDeclinedNum.setVisible(false);
            	txtGruopNum.setVisible(false);
            	lblOK.setVisible(false);
            	lblDeclined.setVisible(false);
    		}
    		}
		}
	}
    
    

