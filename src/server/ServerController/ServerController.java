package server.ServerController;
import server.ServerModel.ServerModel;
import java.net.ServerSocket;

/**
 * Created by ivan on 08.04.2017.
 */
public class ServerController {
    private ServerModel model = null;
    public void start() {
        model = new ServerModel();
        model.start();
    }
    public void stop() {
        model.stopServer();
        model = null;
    }
}
