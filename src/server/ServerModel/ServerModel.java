package server.ServerModel;

/**
 * Created by ivan on 06.04.2017.
 */

import java.net.*;
import java.io.*;
import java.util.logging.*;

public class ServerModel extends Thread
{
    private static Logger log = Logger.getLogger(ServerModel.class.getName());
    private ServerDispatcher serverDispatcher = new ServerDispatcher();
    private ServerSocket serverSocket = null;
    private static final int LISTENING_PORT = 1995;

    public void run()
    {
        // Start ServerDispatcher thread
        serverDispatcher.start();
        log.info("Dispatcher has successfully start");
        // Accept and handle client connections
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
            log.info("ServerModel successfully started on port " + LISTENING_PORT);
        } catch (IOException se) {
            log.info("Can not start listening on port " + LISTENING_PORT);
            log.log(Level.SEVERE, "Exception ", se);
            System.exit(-1);
        }

        try {
            while (!isInterrupted()) {
                try {
                    Socket socket = serverSocket.accept();
                    ClientInfo clientInfo = new ClientInfo();
                    clientInfo.mSocket = socket;
                    ClientListener clientListener =
                            new ClientListener(clientInfo, serverDispatcher);
                    ClientSender clientSender =
                            new ClientSender(clientInfo, serverDispatcher);
                    clientInfo.mClientListener = clientListener;
                    clientInfo.mClientSender = clientSender;
                    clientListener.start();
                    clientSender.start();
                    serverDispatcher.addClient(clientInfo);
                } catch (IOException ioe) {
                    log.log(Level.SEVERE, "Exception ", ioe);
                }
            }
        }
        catch (Exception e) {
            log.log(Level.SEVERE, "Exception ", e);
        }
    }
    public void stopServer() {
        serverDispatcher.stopDispatcher();
        interrupt();
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception ", e);
        }

        log.info("Server has stopped listening port");

    }
}