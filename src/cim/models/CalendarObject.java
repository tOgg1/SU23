package cim.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public abstract class CalendarObject implements Serializable
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
    
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
        return this.getId() == ((CalendarObject)obj).getId();
    }
}
