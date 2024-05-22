package main;

import controller.ObjectController;
import gameframe.GameFrame;
import resourceloader.Resourceloader;
public class StartGame {
    private static GameFrame gameFrame;
    private static Resourceloader rl;
    public static void main(String[] args) {
        ObjectController oc = new ObjectController();
        gameFrame = new GameFrame();
    }
}
