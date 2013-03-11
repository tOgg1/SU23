package cim.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class CalendarObject
{
	
	PropertyChangeSupport pcs;
	
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

    public abstract int getId();
}
