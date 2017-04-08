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

    private static Logger log = Logger.getLogger(ServerModel.class.getName());
    public static void main(String[] args) throws InterruptedException {
        // Open server socket for listening


//        model.start();
//        sleep(1000);
//        model.stopServer();
////        model.start();
//        ServerModel test_model = new ServerModel(serverSocket);
//        test_model.start();
        ServerController controller = new ServerController();
        ServerView view = new ServerView(controller);
        view.start();
    }
}
