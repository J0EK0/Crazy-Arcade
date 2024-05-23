package main;

import controller.GameController;
import controller.ObjectController;
import gameframe.GameFrame;
import resourceloader.Resourceloader;
public class StartGame {
    private static GameFrame gameFrame;
    private static Resourceloader rl;
    public static void main(String[] args) {
        ObjectController oc = new ObjectController();
        gameFrame = new GameFrame();
        gameFrame.setVisible(true);
    }

    public static void startgame(){
        gameFrame.startGame();
        changePanel("game");
    }

    public static void changePanel(String panelname){
        if(GameController.isGameRunning() == false){
            GameController.setGameRunning(true);
        }
        else{
            GameController.setGameRunning(false);
        }
        gameFrame.changePanel(panelname);
        gameFrame.setVisible(true);
    }
}
