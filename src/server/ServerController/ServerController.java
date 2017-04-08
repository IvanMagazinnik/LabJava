package server.ServerController;
import server.ServerModel.ServerModel;
import java.net.ServerSocket;

/**
 * Created by ivan on 08.04.2017.
 */
public class ServerController {
    private ServerModel serverModel = null;
    public ServerController(ServerModel model) {
        serverModel = model;
    }
    public void start() {
        serverModel.start();
    }
    public void stop() {
        serverModel.stopServer();
    }
}
