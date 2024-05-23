package model.gameobject;

import java.util.List;
import javax.swing.ImageIcon;
import resourceloader.Resourceloader;



public class MapFloor extends MapObject{
    
    ImageIcon img;
    public MapFloor(int i, int j, ImageIcon img,int sx1, int sy1, int sx2, int sy2, int scaleX, int scaleY){
        super(i, j, img, sx1, sy1, sx2, sy2, scaleX, scaleY);
        this.img=img;
    }
    
    public static MapFloor createMapFloor(int i, int j, List<String> floorData){
        //System.out.println(floorData.get(0));
        
        ImageIcon img = Resourceloader.getResourceloader().getimageInfo().get(floorData.get(0));
        //System.out.println(img);
        int sx1 = Integer.valueOf(floorData.get(1));
        int sy1 = Integer.valueOf(floorData.get(2));
        int sx2 = Integer.valueOf(floorData.get(3));
        int sy2 = Integer.valueOf(floorData.get(4));
        int scaleX = Integer.valueOf(floorData.get(6));
        int scaleY = Integer.valueOf(floorData.get(7));
        return new MapFloor(i, j, img,sx1, sy1, sx2, sy2 ,scaleX, scaleY);
    }
    public ImageIcon getImageIcon() {
        return this.img;
    }
    
}