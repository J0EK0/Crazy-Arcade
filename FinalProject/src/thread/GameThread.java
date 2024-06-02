package thread;

import controller.GameController;
import controller.ObjectController;
import gameframe.OverPanel;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import main.StartGame;
import model.gamecharacter.Player;
import model.gameobject.*;
import resourceloader.Resourceloader;

public class GameThread extends Thread{

    private boolean running;
    private boolean over = false;
    private static int refreshTime = 20;
    private static int gameTime = 120*1000;

    public GameThread(){
        super();
    }

    @Override
    public void run() {
        while (!over){
            running = true;
            over = false;
            loadMap();
            runGame();
            gameClean();
            StartGame.changePanel("over");
        }
        
    }

    private void loadMap(){
        ObjectController.getObjController().loadMap();
    }

    private void runGame(){
        gameTime = 120*1000;
        //System.out.println("runGame "+ gameTime + refreshTime);
        while (running){
            if(GameController.isGameRunning()){
                HashMap<String, List<SuperObject>> map = ObjectController.getObjController().getMap();
                Set<String> objSet = map.keySet();
                for(String obj:objSet){
                    List<SuperObject> objList = map.get(obj);
                    for(int i = 0;i<objList.size();i++){
                        objList.get(i).update();
                        if(!objList.get(i).isalive()){
                            objList.remove(i);
                        }
                    }
                }
                ExplodeFragility();
                playerGameprops();
                playerExplode();
                bubbleExplodeSerial();
                gameResult();
                playerSuperMode();
                gameTime = gameTime - refreshTime;
            }
            try{
                sleep(20);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void gameClean(){
        ObjectController.getObjController().gameClean();
    }

    private void ExplodeFragility(){
        List<SuperObject> explodeList = ObjectController.getObjController().getMap().get("bubbleExplode");
        List<SuperObject> fragilityList = ObjectController.getObjController().getMap().get("fragility");
        for(int i = 0;i<explodeList.size();i++){
            for(int j=0;j<fragilityList.size();j++){
                if(explodeList.get(i).collision(fragilityList.get(j))){
                    MapFragility fragility = (MapFragility)fragilityList.get(j);
                    fragility.setDestroyed(true);
                }
            }
        }
    }

    private void playerGameprops(){
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        List<SuperObject> gamepropsList = ObjectController.getObjController().getMap().get("gameprops");
        for(int i = 0; i < playerList.size(); i++){
            for(int j = 0; j < gamepropsList.size(); j++){
                if(gamepropsList.get(j).collision(playerList.get(i))){
                    MapGameProps gameProps = (MapGameProps) gamepropsList.get(j);
                    gameProps.setPlayerIndex(i);
                    gameProps.setEaten(true);
                }
            }
        }
    }

    private void bubbleExplodeSerial(){
        List<SuperObject> bubbleList = ObjectController.getObjController().getMap().get("bubble");
        List<SuperObject> explodeList = ObjectController.getObjController().getMap().get("bubbleExplode");
        for(int i = 0;i<explodeList.size();i++){
            for(int j = 0;j<bubbleList.size();j++){
                if(explodeList.get(i).collision(bubbleList.get(j))){
                    MapBubble bubble = (MapBubble)bubbleList.get(j);
                    bubble.setalive(false);
                }
            }
        }
    }

    private void  playerExplode(){
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        List<SuperObject> explodeList = ObjectController.getObjController().getMap().get("bubbleExplode");
        for(int i = 0; i < playerList.size(); i++){
            for(int j = 0; j < explodeList.size(); j++){
                if(explodeList.get(j).collision(playerList.get(i))){
                    Player player = (Player) playerList.get(i);
                    if( player.getSuperMode()) ;
                    else if( !player.getSuperMode()) player.setDying(true);
                }
            }
        }
    }

    private void playerSuperMode(){ 
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        
        for(int i = 0; i < playerList.size(); i++){
            Player player = (Player) playerList.get(i); 
            if(player.getSuperMode() &&( gameTime%100 == 00 ) ){
                if(player.getshowing()) player.setshowing(false);
                else player.setshowing(true);
            }
        }
    }

    private void gameResult(){
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        int loser = 0;
        for(SuperObject so: playerList){
            
            if(((Player)so).isDead()){
                loser += playerList.indexOf(so) + 1;
            }
        }
        if(loser != 0 || gameTime <=0){
            running = false;
            over = true;
            System.out.println("end");
            if(loser == 1){
                OverPanel.getResultButton().setIcon(Resourceloader.getResourceloader().getimageInfo().get("win"));
                ImageIcon icon = Resourceloader.getResourceloader().getimageInfo().get("player2");
                ImageIcon r1 = Resourceloader.getResourceloader().resizeIcon(icon,800,800);
                OverPanel.getwinnerlabel().setIcon(r1);
            }
            else if(loser == 2){
                OverPanel.getResultButton().setIcon(Resourceloader.getResourceloader().getimageInfo().get("win"));
                ImageIcon icon = Resourceloader.getResourceloader().getimageInfo().get("player1");
                ImageIcon r1 = Resourceloader.getResourceloader().resizeIcon(icon,800,800);
                OverPanel.getwinnerlabel().setIcon(r1);
            }
            else{
                ImageIcon icon = Resourceloader.getResourceloader().getimageInfo().get("tie");
                ImageIcon r1 = Resourceloader.getResourceloader().resizeIcon(icon,1200,800);
                OverPanel.getResultButton().setIcon(r1);
                OverPanel.getResultButton().setBounds(0,0, 1200, 800);
            }
        }
        
    }

    public static int getGameTime(){
        return gameTime;
    }

    public void setRunning(boolean status){
        running = status;
    }

    public void setOver(boolean status){
        over = status;
    }
}
