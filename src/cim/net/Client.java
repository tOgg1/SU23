package cim.net;

import cim.models.Account;
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
    private ConcurrentLinkedQueue<Bucket> bucketQueue;

    private boolean running = false;

    public Client()
    {
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
		running = true;
        while(running)
        {

        }
	}
}
