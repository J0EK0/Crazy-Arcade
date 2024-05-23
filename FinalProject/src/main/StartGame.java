package main;
import controller.GameController;
import controller.ObjectController;
import gameframe.GameFrame;
import gameframe.GamePanel;
import javax.swing.*;
import resourceloader.Resourceloader;
import thread.GameThread;
public class StartGame {
    private static GameFrame gameFrame;
    private static GameThread gamethread;
    private static GamePanel gamePanel;
    private static JPanel contentPane;
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
        System.out.println("Changing panel ");
        if(GameController.isGameRunning() == false){
            GameController.setGameRunning(true);
        }
        else{
            GameController.setGameRunning(false);
        }
        gameFrame.changePanel(panelname);
        gameFrame.setVisible(true);
    }
    /*public static void changePanel(String panelName){
        if(panelName.equals("game")){
            GameController.setGameRunning(true);
        }else {
            GameController.setGameRunning(false);
        }
        gameFrame.switchPanel(panelName);
        gameFrame.setVisible(true);
    }*/

}
