package controller;

import java.util.*;

import model.gamecharacter.Player;
import model.gameobject.SuperObject;
import resourceloader.Resourceloader;
public class ObjectController {
    private static ObjectController oc;
    static{
        oc = new ObjectController();
    }
    private GameMap gameMap;
    private HashMap<String, List<SuperObject>> map;
    
    public ObjectController() {
       //System.out.println("new2");
        init();
    }
    public HashMap<String, List<SuperObject>> getMap(){
        return map;
    }
    public void init(){
        List<String> windowsize = Resourceloader.getResourceloader().getgameInfo().get("windowsize");
        if (windowsize == null || windowsize.isEmpty()) {
            throw new IllegalStateException("Window size configuration is missing.");
        }
        gameMap = new GameMap(Integer.parseInt(windowsize.get(0)), Integer.parseInt(windowsize.get(1)));
        map = new HashMap<>();
        map.put("floor", new ArrayList<SuperObject>());
        map.put("player", new ArrayList<SuperObject>());
        
    }
    public static ObjectController getObjController(){
        return oc;
    }

    public void loadMap(){
        gameMap.createMap();
    }

    private void initPlayer(int i, int j){
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        HashMap<String, List<String>> objectInfo = Resourceloader.getResourceloader().getMapObjectInfo();
        List<String> playInfo = objectInfo.get("player");
        Player player = Player.createPlayer(i, j, playInfo);
        playerList.add(player);
    }
    
    /*public void gameClean(){
        ObjectController.getObjController().getMap().get("player").clear();
        ObjectController.getObjController().getMap().get("floor").clear();
        ObjectController.getObjController().getMap().get("obstacle").clear();
        ObjectController.getObjController().getMap().get("fragility").clear();
        ObjectController.getObjController().getMap().get("gameprops").clear();
        ObjectController.getObjController().getMap().get("bubble").clear();
        ObjectController.getObjController().getMap().get("bubbleExplode").clear();
    }*/
}
