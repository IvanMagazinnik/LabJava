package client;
import client.ClientModel.ClientModel;

import java.io.*;
import java.net.*;

/**
 * Created by ivan on 06.04.2017.
 */
public class client {
    public static final String SERVER_HOSTNAME = "localhost";
    public static final int SERVER_PORT = 1995;

    public static void main(String[] args)
    {

        try {
            // Connect to Nakov Chat Server
            Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
            System.out.println("Connected to server " +
                    SERVER_HOSTNAME + ":" + SERVER_PORT);
            ClientModel client = new ClientModel(socket);
            client.start();
        } catch (IOException ioe) {
            System.err.println("Can not establish connection to " +
                    SERVER_HOSTNAME + ":" + SERVER_PORT);
            ioe.printStackTrace();
            System.exit(-1);
        }

    }
}

