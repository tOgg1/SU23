package cim.net;

import cim.net.packet.Event;
import cim.net.packet.Request;
import cim.net.packet.Response;
import cim.util.CloakedIronManException;
import cim.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.UUID;
import java.util.Vector;

public class Server {
	
	private final short eventPort;
	private final short requestPort;
    private ServerSocket eventServerSocket;
    private ServerSocket requestServerSocket;
    private boolean running = false;

	private Vector<RequestThread> requestThreads = new Vector<RequestThread>();
	private Vector<EventThread> eventThreads = new Vector<EventThread>();
	
	private ServerRequestAPI api;
    
	
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
        this.api = new ServerRequestAPI(this);
	}
	
	public void run() {
		this.running = true;
		Thread et = new Thread() {
			private final String tag = "Server Event Socket Listener";
			public void run() {
				Log.d(tag, "Started");
				while(Server.this.running){
					try {
						Log.d(tag, "Wating for incoming connection.");
						Socket s = Server.this.eventServerSocket.accept();
						EventThread etInner = new EventThread(s);
						Server.this.eventThreads.add(etInner);
						etInner.start();
						Log.d(tag, "Connection added");
					} catch (IOException e) {
						Log.e(tag, "Connection event accept error");
					}

				}
			}
		};
		Thread rt = new Thread() {
			private final String tag = "Server Request Socket Listener";
			public void run() {
				Log.d(tag, "Started.");
				while(Server.this.running){
					try {
						Log.d(tag, "Wating for incoming connection.");
						Socket s = Server.this.requestServerSocket.accept();
						RequestThread rtInner = new RequestThread(s);
						Server.this.requestThreads.add(rtInner);
						rtInner.start();
						Log.d(tag, "Connection added");
					} catch (IOException e) {
						Log.e(tag, "Connection request accept error");
					}
					
				}
			}
		};
		et.start();
		rt.start();
	}
	
	/**
	 * Broadcasting an event to all connected clients
	 * @param e
	 */
	public void broadcast(Event e){
		for(EventThread et : this.eventThreads) {
			et.broadcast(e);
		}
	}
	
	
	/**
	 * Abstract class for managing connetions in separate threads
	 * @author H�kon
	 *
	 */
	private abstract class ConnectionThread extends Thread implements Runnable {
		Socket socket;
        ObjectInputStream input;
        ObjectOutputStream output;
        
        UUID id = UUID.randomUUID();
        
        
        public ConnectionThread(Socket s) throws IOException {
        	// Sockets
        	this.socket = s;
        	//Streams
        	this.output = new ObjectOutputStream(this.socket.getOutputStream());
        	this.input = new ObjectInputStream(this.socket.getInputStream());
    	
        }
        void d(String message) {
        	Log.d(this.getClass().toString() + " " + this.id, message);
        }
        void e(String message) {
        	Log.e(this.getClass().toString() + " " + this.id, message);
        }
		
	}
	
	/**
	 * This class manages request coming from the clients
	 * @author H�kon
	 *
	 */	
	private class RequestThread extends ConnectionThread {
		public RequestThread(Socket s) throws IOException {
			super(s);
		}
		
		public void run() {
			while(Server.this.running) {
				try {
					Request request = (Request) this.input.readObject();
					Response r = Server.this.api.getResponse(request);
					this.output.writeObject(r);
				} catch (ClassNotFoundException e) {
					e("Error receiving object: " + e.getMessage());
					continue;
				} catch (IOException e) {
					if (e instanceof SocketException) {
						// Connection is torn down by client
						Server.this.requestThreads.removeElement(this);
						d("Connection thread torn down, initiated by client.");
						break;
					} else {
						e("Error receiving object: " + e.getMessage());
						continue;
					}
				}
				
			}
		}
		
	}
	/**
	 * This calss manages events to be sent out to clients
	 * @author H�kon
	 *
	 */
	private class EventThread extends ConnectionThread {
		public EventThread(Socket s) throws IOException {
			super(s);
		}

		public void run() {
			while(Server.this.running) {
				// Wating for an object which will never arrive
				try {
					this.input.readObject();
				} catch (ClassNotFoundException e) {
					
				} catch(IOException e) {
					if (e instanceof SocketException) {
						Server.this.eventThreads.removeElement(this);
						d("Connection thread torn down, initiated by client.");
						break;
					}
				}
				e("Event thread received an object which it should not");
			}
		}
		
		public void broadcast(Event e) {
			try {
				this.output.writeObject(e);
			} catch (IOException e1) {
				e("Could not write event to client.");
			}
		}
	}

}