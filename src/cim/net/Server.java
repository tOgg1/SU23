package cim.net;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import cim.util.CloakedIronManException;
import cim.util.Log;

public class Server {
	
	private final short eventPort;
	private final short requestPort;
    private ServerSocket eventServerSocket;
    private ServerSocket requestServerSocket;
    private boolean running = false;

	private Vector<RequestThread> requestThreads = new Vector<RequestThread>();
	private Vector<EventThread> eventThreads = new Vector<EventThread>();
	
    
	
	public Server(short eventPort, short requestPort) throws CloakedIronManException {
		// Create log file
        File file = new File("serverlog.txt");
        try
        {
            if(!file.isFile())
                file.createNewFile();
            Log.setLogFile(file);
        }
        catch(IOException e)
        {
            throw new CloakedIronManException("Log file not found, or can't be accessed!");
        }
        // //

		this.eventPort = eventPort;
		this.requestPort = requestPort;
        try
        {
            this.eventServerSocket = new ServerSocket(this.eventPort);
            this.requestServerSocket = new ServerSocket(this.requestPort);
        }
        catch(IOException e)
        {
            // Sad Panda
            throw new CloakedIronManException("Server Connection not established");
        }

        Log.d("Server", "Server running");
	}
	
	public void run() {
		this.running = true;
		Thread et = new Thread() {
			public void run() {
				while(Server.this.running){
					try {
						Socket s = Server.this.eventServerSocket.accept();
						EventThread et = new EventThread(s);
						Server.this.eventThreads.add(et);
					} catch (IOException e) {
						Log.e("Server", "Connection event accept error");
					}
					
				}
			}
		};
		et.run();
		Thread rt = new Thread() {
			public void run() {
				while(Server.this.running){
					try {
						Socket s = Server.this.requestServerSocket.accept();
						RequestThread et = new RequestThread(s);
						Server.this.requestThreads.add(et);
					} catch (IOException e) {
						Log.e("Server", "Connection request accept error");
					}
					
				}
			}
		};
		rt.run();
	}
	
	
	
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
