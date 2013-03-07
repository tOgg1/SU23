package cim.models;

import java.util.Date;

public class Appointment extends CalendarObject 
{
	private Account owner;
	private Date startDate;
	private Date endDate;
	private String info;
	
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
    public int getData() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
