package client.ClientView;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import static java.lang.Math.sqrt;

/**
 * Created by ivan on 09.04.2017.
 */
public class Objects {
    private Map<UUID, Object> Objects = new HashMap<UUID, Object>();
    public void put(Object obj) {
        Objects.put(obj.getId(), obj);
    }
//    public Vector<Object> getObjects(){
//        return Objects;
//    }
    public Objects(){

    }
    public Objects(String str) {
        String strs[] = str.split("/");
        for (String istr: strs) {
            Object obj = new Object(istr);
            Objects.put(obj.getId(), obj);
        }
    }
    public String toString()
    {
        String res = "";
        for (Map.Entry<UUID, Object> pair : Objects.entrySet()){
            res += pair.getValue().toString();
            res += "/";
        }
        return res;
    }
    public void updateObj(UUID id, Point pos){
        Objects.get(id).setPosition(pos);
        checkColision(id);
    }
    private void checkColision(UUID id_) {
        Object curr_obj = Objects.get(id_);
        for (Map.Entry<UUID, Object> pair : Objects.entrySet()) {
            if (pair.getKey() == id_)
                continue;
            Object obj = pair.getValue();
            double dist = sqrt((curr_obj.getPosition().x - obj.getPosition().x)*(curr_obj.getPosition().x - obj.getPosition().x) +
                    (curr_obj.getPosition().y - obj.getPosition().y)*(curr_obj.getPosition().y - obj.getPosition().y));
            if (dist == 0.0)
                continue;
            if(curr_obj.getRadius() < obj.getRadius()) {
                if (dist < obj.getRadius()) {
                    System.out.println("Killed first");
                }
            }
            else {
                if (dist < curr_obj.getRadius()) {
                    System.out.println("Eaten");
                    curr_obj.setRadius(curr_obj.getRadius()+obj.getRadius());
                    Objects.remove(obj.getId());
                    break;
                }
            }
        }
    }
    public Map<UUID, Object> getObjects()
    {
        return Objects;
    }
}
