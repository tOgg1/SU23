package cim.net;

import cim.models.MeetingResponse;
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
		} else if (type == Type.DELETED) {
			
		}
		System.out.println(method + " was not caught.");
		
	}
	
	private void meeting_response_updated(MeetingResponse mr) {
		Client.register.registerMeetingResponse(mr);
	}
}
