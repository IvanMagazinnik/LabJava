package client.ClientModel;

import client.ClientController.ClientController;
import client.client;

import java.io.*;
import java.net.*;

/**
 * Created by ivan on 08.04.2017.
 */
public class ClientModel implements Runnable
{
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Socket clientSocket = null;
    private ClientController controller;
    public ClientModel(Socket socket, ClientController controller_) {
        clientSocket = socket;
        controller = controller_;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
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
