package server.ServerController;
import server.ServerModel.ServerModel;
import java.net.ServerSocket;

/**
 * Created by ivan on 08.04.2017.
 */
public class ServerController {
    private ServerSocket serverSocket = null;
    private ServerModel model = null;
    public ServerController(ServerSocket socket) {
        serverSocket = socket;
    }
    public void start() {
        model = new ServerModel(serverSocket);
        model.start();
    }
    public void stop() {
        model.stopServer();
        model = null;
    }
}
