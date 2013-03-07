package cim.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server
{
	
	private final short port;
    private ServerSocket server;
    private Thread serverThread;
    // Thread-safe
    private ConcurrentLinkedQueue<Bucket> bucketQueue;
    private Vector<ConnectionThread> connections;

	
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

    private class ConnectionThread extends Thread implements Runnable
    {
        private Socket socket;

        public ConnectionThread(Socket socket)
        {
            this.socket = socket;
            run();
        }


        @Override
        public void run()
        {

        }

    }
}
