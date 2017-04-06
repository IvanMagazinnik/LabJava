package server;

/**
 * Created by ivan on 06.04.2017.
 */
import java.util.*;
import java.util.logging.*;

public class ServerDispatcher extends Thread
{
    private static Logger log = Logger.getLogger(Server.class.getName());
    private Vector mClients = new Vector();
    private boolean enabled = true;
    /**
     * Adds given client to the server's client list.
     */
    public synchronized void addClient(ClientInfo aClientInfo)
    {
        mClients.add(aClientInfo);
    }

    /**
     * Deletes given client from the server's client list
     * if the client is in the list.
     */
    public synchronized void deleteClient(ClientInfo aClientInfo)
    {
        int clientIndex = mClients.indexOf(aClientInfo);
        if (clientIndex != -1)
            mClients.removeElementAt(clientIndex);
    }

    private synchronized void sendGameStatusToAll(String aMessage)
    {
        for (int i=0; i<mClients.size(); i++) {
            ClientInfo clientInfo = (ClientInfo) mClients.get(i);
            clientInfo.mClientSender.sendMessage(aMessage);
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
