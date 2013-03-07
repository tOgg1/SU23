package cim.models;

import java.util.Date;

public class Room extends CalendarObject {
	private String name;
	private int size;
	private String info;
	private Date bookedTime;
	
	public Room(String name, int size, String info)
	{
		this.name = name;
		this.size = size;
		this.info = info;
	}
	public Boolean isAvailiable()
	{
		return null;
		
	}

    @Override
    public int getData()
    {
        return 0;
    }
}
