package client.ClientModel;
import client.ClientController.ClientController;

import java.io.*;
import java.net.*;

/**
 * Created by ivan on 08.04.2017.
 */
class Sender extends Thread
{
    private PrintWriter mOut;
    private ClientController controller;
    public Sender(PrintWriter aOut, ClientController controller_)
    {
        mOut = aOut;
        controller = controller_;
    }

    /**
     * Until interrupted reads messages from the standard input (keyboard)
     * and sends them to the chat server through the socket.
     */
    public void run()
    {
        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (!isInterrupted()) {
//                String message = in.readLine();
                if(controller.checkClientStatusUpdate())
                {
                    String message = controller.getClientStatus();
                    mOut.println(message);
                    mOut.flush();
                }
            }
        } catch (Exception e) {
            // Communication is broken
        }
    }
}