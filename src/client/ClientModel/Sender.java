package client.ClientModel;
import java.io.*;
import java.net.*;

/**
 * Created by ivan on 08.04.2017.
 */
class Sender extends Thread
{
    private PrintWriter mOut;

    public Sender(PrintWriter aOut)
    {
        mOut = aOut;
    }

    /**
     * Until interrupted reads messages from the standard input (keyboard)
     * and sends them to the chat server through the socket.
     */
    public void run()
    {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (!isInterrupted()) {
                String message = in.readLine();
                mOut.println(message);
                mOut.flush();
            }
        } catch (IOException ioe) {
            // Communication is broken
        }
    }
}