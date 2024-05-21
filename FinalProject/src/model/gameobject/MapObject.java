package model.gameobject;

import java.awt.*;
import javax.swing.*;
public class MapObject extends SuperObject{
    private ImageIcon img;
    private int sx1, sy1, sx2, sy2;
    public MapObject(int x, int y, int width, int height, int sx1, int sy1, int sx2, int sy2, ImageIcon img) {
        super(x, y, width, height);
        this.sx1 = sx1;
        this.sy1 = sy1;
        this.sx2 = sx2;
        this.sy2 = sy2;

    }
    public void showObject(Graphics g){
        g.drawImage(img.getImage(), getx(), gety(), getx()+getw(), gety()+geth(), sx1, sy1, sx2, sy2, null);
    }
    public ImageIcon getimg(){
        return img;
    }
    public void setimg(ImageIcon img){
        this.img = img;
    }
}
