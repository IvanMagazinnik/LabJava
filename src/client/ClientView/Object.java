package client.ClientView;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ivan on 09.04.2017.
 */
public class Object {
    private Point position;
    private int radius = 0;
    private Color color;
    public Object(Point p, int r) {
        position = p;
        radius = r;
        float red = ThreadLocalRandom.current().nextFloat();
        float green = ThreadLocalRandom.current().nextFloat();
        float blue = ThreadLocalRandom.current().nextFloat();
        color = new Color(red, green, blue);
    }
    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
