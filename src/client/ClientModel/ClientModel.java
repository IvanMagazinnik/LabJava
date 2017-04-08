package client.ClientModel;

import client.client;

import java.io.*;
import java.net.*;

/**
 * Created by ivan on 08.04.2017.
 */
public class ClientModel{
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Socket clientSocket = null;
    public ClientModel(Socket socket) {
        clientSocket = socket;
    }
    public void start() {
        // Create and start Sender thread
        try {
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            out = new PrintWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sender sender = new Sender(out);
        sender.setDaemon(true);
        sender.start();

        try {
            // Read messages from the server and print them
            String message;
            while ((message=in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException ioe) {
            System.err.println("Connection to server broken.");
            ioe.printStackTrace();
        }
    }
}
