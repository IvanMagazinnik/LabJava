package server;

import server.ServerModel.ServerModel;

/**
 * Created by ivan on 08.04.2017.
 */
public class server {
    public static void main(String[] args) {
        ServerModel model = new ServerModel();
        model.start();
    }
}
