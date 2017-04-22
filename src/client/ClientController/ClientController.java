package client.ClientController;

/**
 * Created by ivan on 09.04.2017.
 */
public class ClientController {
    private String gameStatus = "";
    private String clientStatus = "";
    public boolean checkGameStatusUpdate() {
        if (gameStatus.length() > 1)
            return true;
        else return false;
    }
    public boolean checkClientStatusUpdate() {
        if (clientStatus.length() > 1)
            return true;
        else return false;
    }
    public void updateGameStatus(String status) {
        gameStatus = status;
    }
    public void updateClientStatus(String status) {
        clientStatus = status;
    }
    public String getGameStatus() {
        String res = gameStatus;
        gameStatus = "";
        return res;
    }
    public String getClientStatus() {
        String res = clientStatus;
        clientStatus = "";
        return res;
    }

}
