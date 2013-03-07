package cim.models;

import java.util.Date;

public class Room extends CalendarObject {
	private String name;
	private int size;
	private String info;
	private Date bookedTime;
	private Boolean isBooked;
	
	public Room(String name, int size, String info)
	{
		this.name = name;
		this.size = size;
		this.info = info;
		this.isBooked = false;
	}
	public Boolean isAvailiable()
	{
		return isBooked;
		
	}
	public void setBooked(Boolean truthValue)
	{
		this.isBooked = truthValue;
	}
    @Override
    public int getData()
    {
        return 0;
    }
}
