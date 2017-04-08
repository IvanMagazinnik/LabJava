package server.ServerModel;

/**
 * Created by ivan on 06.04.2017.
 */

import java.util.*;
import java.util.logging.*;

public class ServerDispatcher extends Thread
{
    private static Logger log = Logger.getLogger(ServerModel.class.getName());
    private Vector<ClientInfo> mClients = new Vector<>();
    /**
     * Adds given client to the server's client list.
     */
    synchronized void addClient(ClientInfo aClientInfo)
    {
        mClients.add(aClientInfo);
        log.info("Added client: " + aClientInfo);
    }

    /**
     * Deletes given client from the server's client list
     * if the client is in the list.
     */
    synchronized void deleteClient(ClientInfo aClientInfo)
    {
        int clientIndex = mClients.indexOf(aClientInfo);
        if (clientIndex != -1)
            mClients.removeElementAt(clientIndex);
            log.info("Removed client: " + aClientInfo);
    }

    private synchronized void sendGameStatusToAll(String aMessage)
    {
        for (ClientInfo clientInfo: mClients) {
            clientInfo.mClientSender.sendMessage(aMessage);
            log.info("Message " + aMessage + " sent to client: " + clientInfo);
        }
    }

    public void run()
    {
        try {
            while (!isInterrupted()) {
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
        try
        {
            interrupt();
            log.info("ServerModel Dispatcher has successfully stopped");
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "There was some problem while stopServer the server ", e);
        }

    }

}
