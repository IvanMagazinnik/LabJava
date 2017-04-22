package client.ClientView;

import java.awt.*;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ivan on 09.04.2017.
 */
public class Object {
    private Point position;
    private int radius = 0;
    private Color color;
    private UUID id;
    public Object(Point p, int r) {
        position = p;
        radius = r;
        float red = ThreadLocalRandom.current().nextFloat();
        float green = ThreadLocalRandom.current().nextFloat();
        float blue = ThreadLocalRandom.current().nextFloat();
        id = UUID.randomUUID();
        color = new Color(red, green, blue);
    }
    public Object(String str) {
        String strs[] = str.split(";");
        id = UUID.fromString(strs[0]);
        position = new Point(Integer.parseInt(strs[1]),Integer.parseInt(strs[2]));
        radius = Integer.parseInt(strs[3]);
        color = new Color(Integer.parseInt(strs[4]), Integer.parseInt(strs[5]) , Integer.parseInt(strs[6]));
    }
    
    public UUID getId(){
        return id;
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
    public String toString() {
        String res = "";
        res += id.toString();
        res += ';';
        res += position.x;
        res += ';';
        res += position.y;
        res += ';';
        Integer rad = radius;
        res += rad.toString();
        res += ';';
        res += color.getRed();
        res += ';';
        res += color.getGreen();
        res += ';';
        res += color.getBlue();
        res += ';';
        return res;
    }
}
