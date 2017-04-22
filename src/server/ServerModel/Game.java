package server.ServerModel;

import client.ClientView.Object;
import client.ClientView.Objects;


import java.awt.*;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ivan on 09.04.2017.
 */
public class Game {
    Objects Obhects_ = new Objects();
    public Game() {
        for (int i =0;i<10; i++) {
            Obhects_.put(new Object(new Point(ThreadLocalRandom.current().nextInt(0, 1024 + 1), ThreadLocalRandom.current().nextInt(0, 768 + 1)),20));
        }
    }
    public String getSer()
    {
        return Obhects_.toString();
    }
    public void updateObject(String obj) {
        String params[] = obj.split(";");
        UUID id = UUID.fromString(params[0]);
        Point point = new Point(Integer.parseInt(params[1]),Integer.parseInt(params[2]));
        Obhects_.updateObj(id,point);
    }
}
