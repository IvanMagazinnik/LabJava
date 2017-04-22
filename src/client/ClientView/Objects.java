package client.ClientView;

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
    public String toString()
    {
        String res = "";
        for (Map.Entry<UUID, Object> pair : Objects.entrySet()){
            res += pair.getValue().toString();
            res += "/";
        }
        return res;
    }
}
