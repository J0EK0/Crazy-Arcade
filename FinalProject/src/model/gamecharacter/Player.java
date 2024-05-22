package model.gamecharacter;

import javax.swing.ImageIcon;
import model.gamecharacter.Character;


public class Player extends Character{
    
    private ImageIcon img;

    public Player(int x, int y, int width, int height, ImageIcon img) {
        super(x, y, width, height);
        this.img = img;
    }

}
