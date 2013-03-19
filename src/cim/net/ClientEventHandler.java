package cim.net;

import cim.models.Alert;
import cim.models.Calendar;
import cim.models.MeetingResponse;
import cim.models.RejectMessage;
import cim.net.packet.Event;
import cim.net.packet.Event.Type;

public class ClientEventHandler {
	public void handleEvent(Event e) {
		String method = e.getMethod().toUpperCase();
		Type type = e.getType();
		if(type == Type.UPDATED) {
			if(method.equals("MEETING_RESPONSE")) {
				this.meeting_response_updated((MeetingResponse)e.getArgs()[0]);
				return;
			}
			
			else if (method.equals("CALENDAR")) {
				this.calendar_updated((Calendar)e.getArgs()[0]);
				return;
			}
			else if (method.equals("ALERT")) {
				this.alert_updated((Alert)e.getArgs()[0]);
			}
			else if (method.equals("REJECT_MESSAGE")) {
				this.reject_message_updated((RejectMessage)e.getArgs()[0]);
			}
			
			
		} else if (type == Type.DELETED) {
			
		}
		System.out.println(method + " was not caught.");
		
	}
	
	private void meeting_response_updated(MeetingResponse mr) {
		Client.register.registerMeetingResponse(mr);
	}
	
	private void calendar_updated(Calendar c) {
		Client.register.registerCalendar(c);
	}
	
	private void reject_message_updated(RejectMessage rm) {
		Client.register.registerRejectMessage(rm);
	}
	
	private void alert_updated(Alert a) {
		Client.register.registerAlert(a);
	}
	
}
