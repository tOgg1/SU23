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
    public int getData() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
