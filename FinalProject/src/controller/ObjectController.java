package controller;

import java.util.*;

import model.gameobject.SuperObject;
import resourceloader.Resourceloader;
public class ObjectController {
    private static ObjectController oc;
    static{
        //System.out.println("new");
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
        
    }
    public static ObjectController getObjController(){
        /*if (oc == null) {
            oc = new ObjectController();
        }*/
        //System.out.println("new");
        return oc;
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
