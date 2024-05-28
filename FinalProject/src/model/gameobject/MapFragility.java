package model.gameobject;

import controller.ObjectController;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import resourceloader.Resourceloader;

public class MapFragility extends MapObject{

    private boolean destroyed;

    public MapFragility(int i, int j, ImageIcon img, int sx1, int sy1, int sx2, int sy2, int scaleX, int scaleY){
        super(i, j, img, sx1, sy1, sx2, sy2, scaleX, scaleY);
        destroyed = false;
    }
    
    public static MapFragility createMapFragility(int i, int j, List<String>fragilityData){
        ImageIcon img = Resourceloader.getResourceloader().getimageInfo().get(fragilityData.get(0));
        int sx1 = Integer.valueOf(fragilityData.get(1));
        int sy1 = Integer.valueOf(fragilityData.get(2));
        int sx2 = Integer.valueOf(fragilityData.get(3));
        int sy2 = Integer.valueOf(fragilityData.get(4));
        int scaleX = Integer.valueOf(fragilityData.get(6));
        int scaleY = Integer.valueOf(fragilityData.get(7));
        return  new MapFragility(i, j, img, sx1, sy1, sx2, sy2, scaleX, scaleY);
    }

    @Override
    public void update() {
        destroy();
    }

    @Override
    public void destroy() {
        HashMap<String, List<SuperObject>> objects = ObjectController.getObjController().getMap();
        int i = ObjectController.getPosIndex(getx(), gety()).get(0);
        int j = ObjectController.getPosIndex(getx(), gety()).get(1);
        
    }

    public boolean isDestroyed(){
        return destroyed;
    }

    public void setDestroyed(boolean destroyed){
        this.destroyed=destroyed;
    }
}
