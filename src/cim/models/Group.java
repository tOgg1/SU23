package cim.models;

import java.util.ArrayList;

public class Group extends CalendarObject implements Attendable {
	
	String name;
	Account owner;
	Calendar calendar;
	ArrayList<Attendable> members;
	
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
	
	public void receiveInvite(Meeting meeting) {
	
	}
	
	public void changeOwner(Account owner){
		this.owner = owner;
		//Hva skjer med den forrige eieren ? 
	}
	
	public String getName() {
		return this.name;
	}

    @Override
    public Object[] getData()
    {
        Object[] data = new Object[4];
        data[0] = name;
        data[1] = owner;
        data[2] = calendar;
        data[3] = members;
        return data;
    }
}
