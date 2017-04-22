package server.ServerModel;

/**
 * Created by ivan on 06.04.2017.
 */

import java.util.*;
import java.util.logging.*;
import java.net.*;

public class ServerDispatcher extends Thread
{
    private Vector mMessageQueue = new Vector();
    private static Logger log = Logger.getLogger(ServerModel.class.getName());
    private Vector<ClientInfo> mClients = new Vector<>();
    public Game game = new Game();
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

    public synchronized void dispatchMessage(ClientInfo aClientInfo, String aMessage)
    {
        Socket socket = aClientInfo.mSocket;
        String senderIP = socket.getInetAddress().getHostAddress();
        String senderPort = "" + socket.getPort();
        aMessage = senderIP + ":" + senderPort + " : " + aMessage;
        mMessageQueue.add(aMessage);
        notify();
    }

    /**
     * @return and deletes the next message from the message queue. If there is no
     * messages in the queue, falls in sleep until notified by dispatchMessage method.
     */
    private synchronized String getNextMessageFromQueue()
            throws InterruptedException
    {
        while (mMessageQueue.size()==0)
            wait();
        String message = (String) mMessageQueue.get(0);
        mMessageQueue.removeElementAt(0);
        return message;
    }

    public void run()
    {
        try {
            while (!isInterrupted()) {
                // Status new Status to top
                // Status get current status
                // sendGameStatusToAll(Status)
                String message = getNextMessageFromQueue();
                sleep(1000);
                sendGameStatusToAll(game.getSer());
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
