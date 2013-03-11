package cim.net;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import cim.util.CloakedIronManException;
import cim.util.Log;
import cim.util.Settings;

public class Client {
	private Socket eventSocket;
	private Socket requestSocket;

	private ObjectInputStream eventInput;
	private ObjectOutputStream eventOutput;
	private ObjectInputStream requestInput;
	private ObjectOutputStream requestOutput;
	
	public Client() throws CloakedIronManException {
		
		// Configuring Log file
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
		
		// Set up sockets
		try {
			this.eventSocket = new Socket("localhost", Settings.SERVER_EVENT_PORT);
			this.requestSocket = new Socket("localhost", Settings.SERVER_REQUEST_PORT);
		} catch (IOException e) {
			Log.e("Client", e.getMessage());
			throw new CloakedIronManException("Sockets could not be established.", e);
		}
		// Saving IO-streams is always a good idea
		try {
			this.eventOutput = new ObjectOutputStream(this.eventSocket.getOutputStream());
			this.eventInput = new ObjectInputStream(this.eventSocket.getInputStream());
			this.requestOutput = new ObjectOutputStream(this.requestSocket.getOutputStream());
			this.requestInput = new ObjectInputStream(this.requestSocket.getInputStream());
		} catch (IOException e) {
			Log.e("Client", e.getMessage());
			throw new CloakedIronManException("Streams could not be created.", e);
			
		}
		
	}
	
	public void run() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
