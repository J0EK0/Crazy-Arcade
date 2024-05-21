package model.gamecharacter;

import javax.swing.*;
import model.gameobject.SuperObject;
public class Character extends SuperObject{
    public ImageIcon img;
    public Character(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.img = new ImageIcon("../../image/character1.png");

    }
}
