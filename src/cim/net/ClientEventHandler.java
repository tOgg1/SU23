package cim.net;

import cim.models.MeetingResponse;
import cim.net.packet.Event;

public class ClientEventHandler {
	public void handleEvent(Event e) {
		String method = e.getMethod().toUpperCase();
		System.out.println(method);
		if(method.equals("MEETING_RESPONSE")) {
			this.meeting_response((MeetingResponse)e.getArgs()[0]);
		}
	}
	
	private void meeting_response(MeetingResponse mr) {
		System.out.println(mr);
	}
}
