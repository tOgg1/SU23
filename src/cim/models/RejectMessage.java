package cim.models;

import java.sql.Date;
import java.sql.Timestamp;

import cim.util.Helper;

public class RejectMessage extends CalendarObject {
	/**
	 * Needed for somewhat reason
	 */
	private static final long serialVersionUID = -8772863094136219918L;
	private Timestamp date;
	private final Account recipient;
	/**
	 * Can be null. If null, it is a message saying the meeting has been cancelled. If not null, it means that a person rejected the meeting
	 */
	private Account whoRejected;
	private final Meeting meeting;
	
	private boolean isSeen;
	
	public RejectMessage(Account recipient, Meeting meeting) {
		this.recipient = recipient;
		this.meeting = meeting;
		this.date = Helper.getNow();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Timestamp getDate() {
		return date;
	}

	public Account getRecipient() {
		return recipient;
	}

	public Account getWhoRejected() {
		return whoRejected;
	}
	
	public void setWhoRejected(Account a) {
		Account old = this.whoRejected;
		this.whoRejected = a;
		this.pcs.firePropertyChange("whoRejected", old, this.whoRejected);
	}

	public Meeting getMeeting() {
		return meeting;
	}
	
	public boolean isSeen(){
		return this.isSeen;
	}
	public void changeIsSeen(boolean b){
		this.isSeen = b;
	}
	public String toString() {
		//Is used in the AlertsView
		String s = "";

//		If meeting is cancelled
//		s = [møtenavn] [kl] [dato] har blitt avlyst.
		if(whoRejected == null){
			s = meeting.getName()
					+ " kl. " + meeting.getStartFormatted()
					+ " " + meeting.getDateFormatted()
					+ " har blitt avlyst.";
		}
// 		If someone rejected the invitation
//		[navn] har avvist innkallingen til [møtenavn] [kl] [dato]
		else {
			s = whoRejected.getFirstName() 
					+ " " + whoRejected.getLastName()
					+ " har avvist innkallingen til "
					+ meeting.getName()
					+ " kl. " + meeting.getStartFormatted()
					+ " " + meeting.getDateFormatted();
		}
		
		
		return s;
	}
	
	
	
}
