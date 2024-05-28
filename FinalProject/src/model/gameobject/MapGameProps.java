package model.gameobject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.*;
import resourceloader.Resourceloader;

public class MapGameProps extends MapObject{
    private boolean destroyed;
    private static HashMap<String, List<String>> objectInfo = Resourceloader.getResourceloader().getMapObjectInfo();
    private static HashMap<String, Integer> propsChance = new HashMap<String, Integer>();
    static {
        propsChance.put("0", 60);
        propsChance.put("1", 25);
        propsChance.put("2", 15);
    }
    public MapGameProps(int i, int j, ImageIcon img, int sx1, int sy1, int sx2, int sy2, int scaleX, int scaleY){
        super(i, j, img, sx1, sy1, sx2, sy2, scaleX, scaleY);
        destroyed = false;
    }
    
    public static String getChanceProps(){
        Integer sum = 0;
        for(Integer val:propsChance.values()){
            sum += val;
        }
        Integer rand = new Random().nextInt(sum)+1;
        for(Map.Entry<String,Integer> entry:propsChance.entrySet()){
            rand -= entry.getValue();
            if(rand<=0){
                return entry.getKey();
            }
        }
        return null;
    }
    public static MapGameProps createMapGameProps(int i, int j){
        String propType = getChanceProps();
        propType = "3" + propType;
        List<String> propsData = objectInfo.get(propType);
        ImageIcon img = Resourceloader.getResourceloader().getimageInfo().get(propsData.get(0));
        int sx1 = Integer.valueOf(propsData.get(1));
        int sy1 = Integer.valueOf(propsData.get(2));
        int sx2 = Integer.valueOf(propsData.get(3));
        int sy2 = Integer.valueOf(propsData.get(4));
        int scaleX = Integer.valueOf(propsData.get(6));
        int scaleY = Integer.valueOf(propsData.get(7));
        return  new MapGameProps(i, j, img, sx1, sy1, sx2, sy2, scaleX, scaleY);
    }

    @Override
    public void update() {
        destroy();
    }

    @Override
    public void destroy() {
        
    }

    public boolean isDestroyed(){
        return destroyed;
    }

    public void setDestroyed(boolean destroyed){
        this.destroyed=destroyed;
    }
}
