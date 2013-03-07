package cim.net;

import java.net.ServerSocket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server {
	
	private final short port;
    private ServerSocket server;
    ConcurrentLinkedQueue<Bucket> bucketQueue;
	
	/**
	 * Initiates a server with the given port number
	 * @param port
	 */
	public Server(short port)
    {
		this.port = port;

	}
	
	/**
	 * Starts the server.
	 */
	public void run()
    {
		System.out.println("Server is runnnig on " + this.port + ".");
	}
}
