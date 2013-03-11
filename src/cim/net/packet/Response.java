package cim.net.packet;


public class Response extends Packet {
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -7133490810600715349L;
	private final Object[] data;
	
	public Response(Object... objects) {
		this.data = objects;
	}
	public Object[] getData() {
		return this.data;
	}
}
