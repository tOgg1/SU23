package cim.models;

import java.util.Date;

public class Appointment extends CalendarObject 
{
	private Account owner;
	private Date startDate;
	private Date endDate;
	private String info;
	protected Room room;
	
	public Appointment(Date startDate, Date endDate, String info)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		this.info = info;
	}
	public void modifyStart(Date newStartDate)
	{
		this.startDate = newStartDate;
	}
	public void modifyEnd(Date newEndDate)
	{
		this.endDate = newEndDate;
	}
	public void modifyTime(Date newStartDate, Date newEndDate)
	{
		this.startDate = newStartDate;
		this.endDate = newEndDate;
	}


    @Override
    public Object[] getData()
    {
        Object[] data = new Object[5];
        data[0] = owner;
        data[1] = startDate;
        data[2] = endDate;
        data[3] = info;
        data[4] = room;
        return data;
    }
}
