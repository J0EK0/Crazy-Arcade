package model.gamecharacter;

import java.awt.*;
import model.gameobject.SuperObject;

public class Character extends SuperObject{
    
    public int score;
    protected boolean dead;
    protected int speed; 
    protected int bubbleNum;
    protected int magicSaveCount;
    protected int magicPowerCount;
    protected int healthPoint;
    protected int bubblepower;
    

    public Character(int x, int y, int width, int height) {
        super(x, y, width, height);

        dead = false;
        speed = 5;
        bubbleNum = 10;
        score = 0;
        magicSaveCount = 0;
        magicPowerCount = 1;
    }
    
    public void setDead(boolean dead){ this.dead = dead;}
    public boolean getDead(){return dead;}

    public void setSpeed(int speed){this.speed = speed;}
    public int getSpeed(){return speed;}

    public void setScore(int score){this.score = score;}
    public int getScore(){return score;}
    
    @Override
    public void move(){};

    @Override
    public void destroy(){};

    @Override
    public void showObject(Graphics g){};

    public int getmagicSaveCount(){
        return this.magicSaveCount;
    }

    public int getmagicPowerCount(){
        return this.magicPowerCount;
    }

    public void setmagicSaveCount(int magicSaveCount){
        this.magicSaveCount = magicSaveCount;
    }

    public void setmagicPowerCount(int magicPowerCount){
        this.magicPowerCount = magicPowerCount;
    }

    public int getbubblepower(){return this.bubblepower;};

    public void setbubblepower(int bubblepower){
        this.bubblepower = bubblepower;
    }

}
