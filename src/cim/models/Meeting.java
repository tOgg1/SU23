package cim.models;

import java.util.Date;
import java.util.Map;

public class Meeting extends Appointment
{
	private Map<Integer, Attendable> invitees;

    private final static int STATUS_INVITED = 0;
    private final static int STATUS_ACCEPTED = 1;
    private final static int STATUS_DECLINED = 2;

	public Meeting(String info, Map<Integer, Attendable> invitees, Room room, Date startDate, Date endDate)
	{	
		super(startDate, endDate, info);
		this.invitees = invitees;		
	}

    @Override
    public Object[] getData()
    {
        Object[] superData = super.getData();
        Object[] data = new Object[superData.length + 1];
        int i = 0;
        for(Object o : superData)
        {
            data[i++] = o;
        }
        data[i] = invitees;
        return data;
    }
}
