package cim.views;

import cim.models.Calendar;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * Panel to display information about a specific calendar
 * User: Tormod
 * Date: 3/17/13
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarPanel extends JPanel
{
    private Calendar model;
    private boolean isDisplayed;

    public CalendarPanel(Calendar model)
    {
        this.model = model;
        init();
    }

    public void init()
    {
        setSize(new Dimension(300, 50));
        isDisplayed = true;
        JLabel text = new JLabel(model.getOwner() +"'s calendar");
        text.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        super.add(text);
    }

    public Calendar getModel()
    {
        return model;
    }

    public boolean isDisplayed()
    {
        return isDisplayed;
    }

    public void setDisplayed(boolean flag)
    {
        this.isDisplayed = flag;
    }

    public void toggleDisplayed()
    {
        this.isDisplayed = !this.isDisplayed;
    }

    @Override
    public String toString() {
        return "Model: " + model.toString();
    }
}
