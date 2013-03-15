package cim.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Group extends CalendarObject implements Attendable, Serializable
{
	private int attendableId = -1;
	String name;
	Account owner;
	Calendar calendar;
	ArrayList<Attendable> members;
	
	public Group(String name, Account owner) {
		this.owner = owner;
		this.name = name;
	}
	
	public void addMember(Attendable member){
		if(!this.members.contains(member))
		this.members.add(member);
	}
	
	public void removeMember(Attendable member){
		//kan ikke slette eieren
		if (this.owner != member){
			this.members.remove(member);
		}
	}

    public boolean isMember(Attendable att)
    {
        return members.contains(att);
    }
	
	public void receiveInvite(Meeting meeting) {
	
	}
	
	public void changeOwner(Account owner){
		this.owner = owner;
		//Hva skjer med den forrige eieren ? 
	}
	
	public String getName() {
		return this.name;
	}
	
	public Account getOwner() {
		return this.owner;
	}
	
	public void setAttendableId(int id) {
		this.pcs.firePropertyChange("attendableId", this.attendableId, attendableId);
		this.attendableId = id;
	}
	
	public int getAttendableId() {
		return this.attendableId;
	}
}
