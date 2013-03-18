package cim.net;

import cim.models.Account;
import cim.models.CalendarRegister;
import cim.net.packet.Event;
import cim.net.packet.Request;
import cim.net.packet.Response;
import cim.util.CloakedIronManException;
import cim.util.Log;
import cim.util.PersonalSettings;
import cim.util.Settings;
import cim.views.ApplicationWindow;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

//import cim.models.CalendarRegister;

public class Client {
    public static CalendarRegister register;

	private Socket eventSocket;
	private Socket requestSocket;

	private ObjectInputStream eventInput;
	private ObjectOutputStream eventOutput;
	private ObjectInputStream requestInput;
	private ObjectOutputStream requestOutput;
	
	private ClientEventHandler evt;
	
	public Client(String ip) throws CloakedIronManException {
		
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
			this.eventSocket = new Socket(ip, Settings.SERVER_EVENT_PORT);
			this.requestSocket = new Socket(ip,Settings.SERVER_REQUEST_PORT);
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

        //Creating register
        register = new CalendarRegister(this);

		// Creating authenticator instance
		this.evt = new ClientEventHandler();
		
	}
	
	public void run() throws CloakedIronManException {
		// Creating server event listener
		EventListenerThread e = new EventListenerThread();
		e.start();
		
		
		
		// Spawning authenticate window
		/*AuthenticateView auth = new AuthenticateView(this);
		auth.setVisible(true);
		Account acc = auth.getAccount();*/
		Response resp = this.request(new Request("GET_ACCOUNT", PersonalSettings.DEFAULT_ACCOUNT_ID));
		Account acc = (Account) resp.getData()[0];
		//Starting up register
        register.initialize(acc);
		if (acc != null) {
			// User managed to log in
			// Create new calendar gui.
			ApplicationWindow cv = new ApplicationWindow();
			cv.setVisible(true);
		} else {
			this.exit();
		}
        
	}
	
	public Response request(Request req) throws CloakedIronManException {
		Response r = null;
		try {
			this.requestOutput.writeObject(req);
			r = (Response)this.requestInput.readObject();
			Object [] data = r.getData();
			if(data.length > 0) {
				if (data[0] instanceof Exception) {
					throw (Exception)data[0];
				}
			}
			return r;
		} catch (Exception e) {
			throw new CloakedIronManException("Invalid response from server: " + e.getMessage(), e);
		} 
	}
	
	public void exit() {
		System.exit(1);
	}
	
	public void d(String d) {
		Log.d("Client", d);
	}
	
	public void e(String e) {
		Log.e("Client", e);
	}
	
	private class EventListenerThread extends Thread implements Runnable {
		
		public void run() {
			while(true) {
				try {
					Event e = (Event) eventInput.readObject();
					Client.this.evt.handleEvent(e);
				} catch (ClassNotFoundException e) {
					Log.e("Client", "Error receiving event: " + e.getMessage());
					continue;
				} catch (IOException e) {
					if (e instanceof SocketException) {
						Log.d("Client", "Server cancelled the connection.");
						break;
					} else {
						Log.e("Client","Error receiving event: " + e.getMessage());
						continue;
					}
				}
			}
		}
	}

	
}
