package model.gamecharacter;

import model.gameobject.SuperObject;

public class Character extends SuperObject{
    
    public int score;
    protected boolean dead;
    protected int speed; 
    protected int bubbleNum;
    

    public Character(int x, int y, int width, int height) {
        super(x, y, width, height);
        //this.img = new ImageIcon("FinalProject/src/image/character1.png");
        
        dead = false;
        speed = 5;
        bubbleNum = 10;
        score = 0;
    }
    
    public void setDead(boolean dead){ this.dead = dead;}
    public boolean getDead(){return dead;}

    public void setSpeed(int speed){this.speed = speed;}
    public int getSpeed(){return speed;}

    public void setBubbleNum(int bubbleNum){this.bubbleNum = bubbleNum;}
    public int getBubbleNum(){return bubbleNum;}

    public void setScore(int score){this.score = score;}
    public int getScore(){return score;}
    
}
