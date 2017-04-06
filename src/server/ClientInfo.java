package server;

/**
 * Created by ivan on 06.04.2017.
 */
import java.net.Socket;

public class ClientInfo {
    public Socket mSocket = null;
    public ClientListener mClientListener = null;
    public ClientSender mClientSender = null;
}
