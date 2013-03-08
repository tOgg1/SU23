package cim.models;

import java.util.Date;

public class Room extends CalendarObject {
	private String name;
	private int size;
	private String info;
	private Date bookedTime;
	private boolean isBooked;
	
	public Room(String name, int size, String info)
	{
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
	{
		this.isBooked = truthValue;
	}
    @Override
    public Object[] getData()
    {
        Object[] data = new Object[5];
        data[0] = name;
        data[1] = size;
        data[2] = info;
        data[3] = bookedTime;
        data[4] = isBooked;
        return data;
    }
}
