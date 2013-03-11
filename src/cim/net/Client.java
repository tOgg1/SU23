package cim.net;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import cim.util.Authenticator;
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
	
	private Authenticator auth;
	
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
			this.eventSocket = new Socket(InetAddress.getLocalHost(), Settings.SERVER_EVENT_PORT);
			this.requestSocket = new Socket(InetAddress.getLocalHost(),Settings.SERVER_REQUEST_PORT);
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
		
		// Creating authenticator instance
		this.auth = new Authenticator();
		
	}
	
	public void run() throws CloakedIronManException {
		// Must log in first
		Scanner sc = new Scanner(System.in);
		System.out.println("Skriv inn brukernavn: ");
		//String username = sc.nextLine();
		//String pw = sc.nextLine();
		Response resp = this.request(new Request("AUTHENTICATE", "per", "p�l"));
		System.out.println(resp.getData()[0]);
		
		/*
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}
	
	public Response request(Request req) throws CloakedIronManException {
		Response r = null;
		try {
			this.requestOutput.writeObject(req);
			r = (Response)this.requestInput.readObject();	
			return r;
		} catch (IOException | ClassNotFoundException e) {
			throw new CloakedIronManException("Invalid response from server.", e);
		} 
	}
	
}
