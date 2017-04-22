package server.ServerModel;

import client.ClientView.Object;
import client.ClientView.Objects;

import java.awt.*;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ivan on 09.04.2017.
 */
public class Game {
    Objects obj = new Objects();
    public Game() {
        for (int i =0;i<10; i++) {
            obj.put(new Object(new Point(ThreadLocalRandom.current().nextInt(0, 1024 + 1), ThreadLocalRandom.current().nextInt(0, 768 + 1)),20));
        }
    }
    public String getSer()
    {
        return obj.toString();
    }
}
