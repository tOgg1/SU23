package cim.models;

public interface Attendable 
{
	public void receiveInvite(Meeting meeting);
	public String getName();
	public int getId();
	public int getAttendableId();

}
