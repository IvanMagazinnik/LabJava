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
    public ServerModel(ServerSocket server_socket)
    {
        serverSocket = server_socket;
    }
    public void run()
    {

        // Start ServerDispatcher thread
        serverDispatcher.start();
        log.info("Dispatcher has successfully start");
        // Accept and handle client connections
        try {
            while (!isInterrupted()) {
                try {
                    Socket socket = serverSocket.accept();
                    ClientInfo clientInfo = new ClientInfo();
                    clientInfo.mSocket = socket;
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
        log.info("Server has stopped listening port");
        interrupt();
    }
}