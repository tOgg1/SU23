package cim.models;

import java.util.ArrayList;

public class Group extends CalendarObject implements Attendable
{
	private int attendableId = -1;
	String name;
	Account owner;
	
	ArrayList<Account> members;
	
	public Group(String name, Account owner) {
		this.owner = owner;
		this.name = name;
		this.members = new ArrayList<Account>();
	}
	
	public void addMember(Account a) {
		this.members.add(a);
	}
	
    public boolean isMember(Account a)
    {
        return members.contains(a);
    }
    
    public ArrayList<Account> getMembers() {
    	return this.members;
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
	
	public String toString() {
		return this.name;
	}
}
