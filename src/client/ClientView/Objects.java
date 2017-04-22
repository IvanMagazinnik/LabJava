package client.ClientView;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

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
    }
}
