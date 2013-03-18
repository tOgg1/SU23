package cim.models;

import java.sql.Date;

import cim.util.Helper;

public class RejectMessage extends CalendarObject {
	/**
	 * Needed for somewhat reason
	 */
	private static final long serialVersionUID = -8772863094136219918L;
	private Date date;
	private final Account recipient;
	/**
	 * Can be null. If null, it is a message saying the meeting has been cancelled. If not null, it means that a person rejected the meeting
	 */
	private Account whoRejected;
	private final Meeting meeting;
	
	public RejectMessage(Account recipient, Meeting meeting) {
		this.recipient = recipient;
		this.meeting = meeting;
		this.date = Helper.getNow();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDate() {
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
	
	
	
	
	
}
