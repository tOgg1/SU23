package cim.models;

import java.util.Date;

public class Room extends CalendarObject
{
    private int id;
	private String name;
	private int size;
	private String info;
	private Date bookedTime;
	private boolean isBooked;
	
	
	public Room(int id, int size)
	{
		this.id = id;
		this.size = size;
	}
	
	public Room(int id, String name, int size, String info)
	{
		this.id = id;
		this.name = name;
		this.size = size;
		this.info = info;
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
    @Override
    public int getId()
    {
        return id;
    }
    public String toString()
    {
    	return "Navn: " + this.name + "\nStorrelse: " + this.size + "\nInfo: " + this.info;
    }
}
