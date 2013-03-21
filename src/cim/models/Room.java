package cim.models;

import java.util.Date;

public class Room extends CalendarObject
{
	private String name;
	
	private int size;
	private String info;
	private boolean isBooked;
	
	
	public Room(int size)
	{
		this.size = size;
	}
	
	public Room(String name, int size)
	{
		this.name = name;
		this.size = size;
		this.isBooked = false;
	}
	public boolean isAvailiable()
	{
		return isBooked;
		
	}
	public void setBooked(Boolean truthValue)
	{	Boolean oldValue = this.isBooked;
		this.isBooked = truthValue;
		pcs.firePropertyChange("booked", oldValue , truthValue);


	}
    public String toString()
    {
    	return "Navn: " + this.name + "\nStorrelse: " + this.size + "\nInfo: " + this.info;
    }
    
    public String getName() {
		return name;
	}
    
    public int getSize(){
    	return this.size;
    }
}
