package thread;

import controller.GameController;
import controller.ObjectController;

import main.StartGame;
import model.gameobject.MapFragility;
import resourceloader.Resourceloader;
import model.gameobject.*;
import model.gamecharacter.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
            loadMap();
            runGame();
            //gameClean();
            //StartGame.changePanel("over");
        }
        
    }

    private void loadMap(){
        ObjectController.getObjController().loadMap();
    }

    private void runGame(){
        gameTime = 120*1000;
        while (running){
            //System.out.println("runGame");
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
                //playerExplode();
                //bubbleExplodeSerial();
                //gameResult();
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
        //GamePanel.getPlaymusic().stops();
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
                    //gameProps.setPlayerIndex(i);
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
                    player.setDying(true);
                }
            }
        }
    }
    public static int getGameTime(){
        return gameTime;
    }

    private void gameResult(){
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        int survivalNum = 0;
        for(SuperObject so: playerList){
            if(!((Player)so).isDead()){
                survivalNum++;
            }
        }
        if(survivalNum == 0){
            running = false;
            over = true;
            OverPanel.getResultButton().setIcon(Resourceloader.getResourceloader().getImageInfo().get("fail"));
        }

        if(survivalNum > 0 && gameTime <= 0){
            running = false;
            over = true;
            OverPanel.getResultButton().setIcon(Resourceloader.getResourceloader().getImageInfo().get("win"));
        }
    }

    public void setRunning(boolean status){
        running = status;
    }

    public void setOver(boolean status){
        over = status;
    }
}
