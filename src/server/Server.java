package server;

/**
 * Created by ivan on 06.04.2017.
 */
import java.net.*;
import java.io.*;
import java.util.logging.*;

public class Server
{
    private static Logger log = Logger.getLogger(Server.class.getName());
    private static final int LISTENING_PORT = 1995;
    private boolean server_status = true;
    private ServerDispatcher serverDispatcher = new ServerDispatcher();
    public void start()
    {
        // Open server socket for listening
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
            log.info("Server successfully started on port " + LISTENING_PORT);
        } catch (IOException se) {
            log.info("Can not start listening on port " + LISTENING_PORT);
            log.log(Level.SEVERE, "Exception ", se);
            System.exit(-1);
        }

        // Start ServerDispatcher thread
        serverDispatcher.start();
        // Accept and handle client connections
        while (server_status) {
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
    public void stop() {
        if (server_status) {
            server_status = false;
        }
    }
}