package cim.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class CalendarObject
{
	
	protected PropertyChangeSupport pcs;
	
	private int id = -1; 
	
	public CalendarObject()
	{
		pcs = new PropertyChangeSupport(this);
		
	}

	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		pcs.addPropertyChangeListener(listener);
	}
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		pcs.removePropertyChangeListener(listener);
	}

    public int getId() {
    	return this.id;
    }
    
    public void setId(int id) {
    	
    	this.pcs.firePropertyChange("id", this.id, id);
    	this.id = id;
    }
}
