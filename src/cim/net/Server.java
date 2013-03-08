package cim.net;

import cim.database.DatabaseHandler;
import cim.util.CloakedIronManException;
import cim.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server
{

    DatabaseHandler dbHandler;

	private final short port;
    private ServerSocket server;
    private Thread listenThread;
    // Thread-safe
    private ConcurrentLinkedQueue<Bucket> bucketQueue;
    private Vector<ConnectionThread> connections;

    private boolean running = false;

	
	/**
	 * Initiates a server with the given port number
	 * @param port
	 */
	public Server(short port) throws CloakedIronManException
    {
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

		this.port = port;
        try
        {
            server = new ServerSocket(port);
        }
        catch(IOException e)
        {
            // Sad Panda
            throw new CloakedIronManException("Server Connection not established");
        }

        Log.d("Server", "Server running");
	}
	
	/**
	 * Starts the server.
	 */
	public void run()
    {
        running = true;
        listenThread = new Thread()
        {
            public void run()
            {
                while(running)
                {
                    try
                    {
                        Socket socket = server.accept();
                        ConnectionThread newConnection = new ConnectionThread(socket, Server.this);
                        connections.add(newConnection);
                    }
                    catch(IOException e)
                    {
                        Log.e("Server", "Connection accept error");
                    }
                }
            }
        };
        listenThread.start();
	}


    public Bucket handleQuery(Bucket bucket)
    {
        String sqlAction = Bucket.decodeFlag(bucket.flag);
        int[] indexFlags = Bucket.getIndexes(bucket.indexFlag);
        return null;
    }

    //Connection stuff
    public void addConnection(Socket socket)
    {
        ConnectionThread connection = new ConnectionThread(socket, this);
        connections.add(connection);
    }

    public void removeConnection(ConnectionThread connection)
    {
        if(connections.contains(connection))
            connections.remove(connection);
    }

    private class ConnectionThread extends Thread implements Runnable
    {
        private Server server;
        private Socket socket;
        ObjectInputStream input;
        ObjectOutputStream output;
        private boolean running;

        public ConnectionThread(Socket socket, Server server)
        {
            this.socket = socket;
            this.server = server;

            init();
            run();
        }

        private void init()
        {
            running = true;
            try
            {
                input = new ObjectInputStream(socket.getInputStream());
                output = new ObjectOutputStream(socket.getOutputStream());
            }
            catch(Exception e)
            {
                //TODO: Handle Exception (Bad socket)
                e.printStackTrace();
            }
        }


        @Override
        public void run()
        {
            Bucket bucket;
            try
            {
                while(running && (bucket = (Bucket)input.readObject()) != null)
                {

                }
            }
            catch(ClassNotFoundException e)
            {
                //At this point we should hang ourselves

            }
            catch(IOException e)
            {
                //Connection is most likely broken, shut down
                running = false;
                server.removeConnection(this);
            }
        }

    }
}
