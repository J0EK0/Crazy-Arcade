package model.gamecharacter;

import controller.GameMap;
import controller.ObjectController;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import model.gamecharacter.Character;
import model.gameobject.MapObject;
import model.gameobject.SuperObject;
import resourceloader.Resourceloader;



public class Player extends Character{
    
    private ImageIcon img;
    private boolean isShowing;
    int moveX = 0;
    int moveY = 0;
    private boolean moveable;

    public Player(int x, int y, int width, int height, ImageIcon img) {
        super(x, y, width, height);
        this.img = img;
        moveable = true;
        isShowing = true;
    }

    public static Player createPlayer(int i, int j, List<String> playerInfo){
        int x = j*MapObject.PIXEL_X + GameMap.getBiasX();
        int y = i*MapObject.PIXEL_Y + GameMap.getBiasY();
        int w = MapObject.PIXEL_X;
        int h = MapObject.PIXEL_Y;

        HashMap<String, ImageIcon> imageInfo = Resourceloader.getResourceloader().getimageInfo();
        return  new Player(x, y, w, h, imageInfo.get(playerInfo.get(0))); //+String.valueOf(StartPanel.playerIndex)
    }

    @Override
    public void showObject(Graphics g) {
        if(isShowing==false){
            return;
        }
        g.drawImage(img.getImage(), getx(), gety(),getx()+getw(), gety()+geth(),0 , 0, img.getIconWidth(), img.getIconHeight(), null);
        //g.drawImage(img.getImage(), getx(), gety(),getx()+getw(), gety()+geth(), null);

    }
    //collision Detect

    public void plantBubble(){

    }

    //move
    public void act(char wasd){
        int tempx = getx();
        int tempy = gety();
        switch (wasd) {
            case ' ':
                plantBubble();
                break;
            case 'w':
                tempy -= MapObject.PIXEL_Y;
                break;
            case 's':
                tempy += MapObject.PIXEL_Y;
                break;
            case 'a':
                tempx -= MapObject.PIXEL_X;
                break;
            case 'd':
                tempx += MapObject.PIXEL_X; 
                break;
            default:
                break;
        }
        boolean b1 = collisiondetect(tempx, tempy, ObjectController.getObjController().getMap().get("obstacle"));
        boolean b2 = collisiondetect(tempx, tempy, ObjectController.getObjController().getMap().get("fragility"));
        if(b1 && b2 && moveable) {
            setx(tempx);
            sety(tempy);
            moveable = false;
        }
    }

    private boolean collisiondetect(int tempx, int tempy, List<SuperObject> objects){
        Rectangle rect = new Rectangle(tempx, tempy, getw(), geth());
        for(SuperObject obj : objects){
            Rectangle objrect = new Rectangle(obj.getx(), obj.gety(), obj.getw(),obj.geth());
            if(rect.intersects(objrect)){
                return false;
            }
        }
        return true;
    }

    public void setmoveable(boolean moveable){
        this.moveable = moveable;
    }
    public boolean getmoveable(){ return moveable;}
}