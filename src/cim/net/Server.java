package cim.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class Server {
	private Vector<RequestThread> requestThreads = new Vector<RequestThread>();
	
	/**
	 * Abstract class for managing connetions in separate threads
	 * @author Håkon
	 *
	 */
	private abstract class ConnectionThread extends Thread implements Runnable {
		Socket socket;
        ObjectInputStream input;
        ObjectOutputStream output;
        
        public ConnectionThread(Socket s) throws IOException {
        	// Sockets
        	this.socket = s;
        	//Streams
        	this.input = new ObjectInputStream(this.socket.getInputStream());
        	this.output = new ObjectOutputStream(this.socket.getOutputStream());
    	
        }
		
	}
	
	/**
	 * This class manages request coming from the clients
	 * @author Håkon
	 *
	 */	
	private class RequestThread extends ConnectionThread {
		public RequestThread(Socket s) throws IOException {
			super(s);
		}
		
		public void run() {
			System.out.println("Request handler thread running");
		}
		
	}
	/**
	 * This calss manages events to be sent out to clients
	 * @author Håkon
	 *
	 */
	private class EventThread extends ConnectionThread {
		public EventThread(Socket s) throws IOException {
			super(s);
		}

		public void run() {
			System.out.println("Request");
		}
	}

}
