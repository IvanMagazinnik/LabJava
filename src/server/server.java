package server;

import server.ServerModel.ServerModel;
import server.ServerView.ServerView;
import server.ServerController.ServerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * Created by ivan on 08.04.2017.
 */
public class server {
    private static final int LISTENING_PORT = 1995;
    private static Logger log = Logger.getLogger(ServerModel.class.getName());
    public static void main(String[] args) throws InterruptedException {
        // Open server socket for listening
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
            log.info("ServerModel successfully started on port " + LISTENING_PORT);
        } catch (IOException se) {
            log.info("Can not start listening on port " + LISTENING_PORT);
            log.log(Level.SEVERE, "Exception ", se);
            System.exit(-1);
        }
        ServerModel model = new ServerModel(serverSocket);
//        model.start();
//        sleep(1000);
//        model.stopServer();
////        model.start();
//        ServerModel test_model = new ServerModel(serverSocket);
//        test_model.start();
        ServerController controller = new ServerController(model);
        ServerView view = new ServerView();
        view.start(controller);
    }
}
