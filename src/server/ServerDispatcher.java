package server;

/**
 * Created by ivan on 06.04.2017.
 */
import java.util.*;
import java.util.logging.*;
import java.util.UUID;

public class ServerDispatcher extends Thread
{
    private static Logger log = Logger.getLogger(Server.class.getName());
    private Vector mClients = new Vector();
    Map<UUID, ClientInfo> mClients = new HashMap<UUID, ClientInfo>();
    private boolean enabled = true;
    /**
     * Adds given client to the server's client list.
     */
    public synchronized void addClient(ClientInfo aClientInfo)
    {
        aClientInfo.clientID = UUID.randomUUID();
        mClients.put(aClientInfo.clientID, aClientInfo);
    }

    /**
     * Deletes given client from the server's client list
     * if the client is in the list.
     */
    public synchronized void deleteClient(ClientInfo aClientInfo)
    {
        mClients.remove(aClientInfo.clientID);
    }

    private synchronized void sendGameStatusToAll(String aMessage)
    {
        for (Map.Entry<UUID, ClientInfo> pair : mClients.entrySet())
        {
            ClientInfo clientInfo = pair.getValue();
            clientInfo.mClientSender.sendMessage(aMessage);
            log.info("Message " + aMessage + " sent to client with id: " + pair.getKey());
        }
    }

    public void run()
    {
        try {
            while (enabled) {
                // Status new Status to top
                // Status get current status
                // sendGameStatusToAll(Status)
                String message = "Hello";
                sendGameStatusToAll(message);
            }
        } catch (Exception e) {
            // Thread interrupted. Stop its execution
            log.log(Level.SEVERE, "Exception ", e);
        }
    }

    public void stopDispatcher()
    {
        enabled = false;
    }

}
