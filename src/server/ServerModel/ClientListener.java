package server.ServerModel;

/**
 * Created by ivan on 06.04.2017.
 */

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class ClientListener extends Thread
{
    private static Logger log = Logger.getLogger(ServerModel.class.getName());
    private ServerDispatcher mServerDispatcher;
    private ClientInfo mClientInfo;
    private BufferedReader mIn;

    public ClientListener(ClientInfo aClientInfo, ServerDispatcher aServerDispatcher)
            throws IOException
    {
        mClientInfo = aClientInfo;
        mServerDispatcher = aServerDispatcher;
        Socket socket = aClientInfo.mSocket;
        mIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run()
    {
        try {
            while (!isInterrupted()) {
                String message = mIn.readLine();
                if (message == null)
                    break;
                // Refresh states from client info
            }
        } catch (IOException ioex) {
            log.log(Level.SEVERE, "Problem reading from socket (communication is broken) ", ioex);
        }

        // Communication is broken. Interrupt both listener and sender threads
        mClientInfo.mClientSender.interrupt();
        mServerDispatcher.deleteClient(mClientInfo);
    }

}
