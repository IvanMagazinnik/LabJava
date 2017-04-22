package server.ServerModel;

/**
 * Created by ivan on 06.04.2017.
 */

import java.net.Socket;
import java.util.UUID;

public class ClientInfo {
    public UUID id;
    public Socket mSocket = null;
    public ClientListener mClientListener = null;
    public ClientSender mClientSender = null;
}
