package model.gamecharacter;

import controller.GameMap;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import model.gamecharacter.Character;
import model.gameobject.MapObject;
import resourceloader.Resourceloader;



public class Player extends Character{
    
    private ImageIcon img;
    private boolean isShowing;
    int moveX = 0;
    int moveY = 0;

    public Player(int x, int y, int width, int height, ImageIcon img) {
        super(x, y, width, height);
        this.img = img;
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
    public void move(char wasd){
        
        switch (wasd) {
            case 'w':
                sety(gety() - MapObject.PIXEL_Y);
                break;
            case 's':
                sety(gety() + MapObject.PIXEL_Y);
                break;
            case 'a':
                setx(getx() - MapObject.PIXEL_X);
                break;
            case 'd':
                setx(getx() + MapObject.PIXEL_X);
                break;
            default:
                break;
        }
    }

}