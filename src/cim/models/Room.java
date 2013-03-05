package cim.models;

public class Room extends CalendarObject {
	private String name;
	private int size;
	private String info;
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
}
