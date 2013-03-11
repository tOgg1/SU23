package cim.net2;

import cim.models.Account;
import cim.util.Authenticator;
import cim.models.CalendarRegister;
import cim.util.Settings;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Client
{
    private Socket socket;
    private Account association;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Thread runThread;
    private ConcurrentLinkedQueue<Request> requestQueue;
    
	private static Authenticator authenticator = new Authenticator();

    CalendarRegister register;

    private boolean running = false;

    public Client()
    {
        register = new CalendarRegister();
        try
        {
            socket = new Socket(InetAddress.getLocalHost(), Settings.SERVER_PORT);
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException e)
        {
            // lol
        }
        catch(IOException e)
        {
            //more lol
        }
    }

	public void run()
    {
		
		// F�rst m� man authentisere
		// Her skal programmet settes opp med listeners og GUI.
		
		// Creating a thread that sends messages to the server.
		RequestSenderThread rct = new RequestSenderThread();
		rct.run();
		running = true;
        while(running)
        {

        }
	}
	
	public Request sendRequestToServer(Request req)
	{
		requestQueue.add(req);
		return null;
	}
	
	private class RequestSenderThread extends Thread {
		public void run() {
			while (true) {
				
			}
		}
	}
	
}
