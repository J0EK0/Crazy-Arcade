package controller;

import java.util.*;

import model.gameobject.MapObject;
import model.gameobject.SuperObject;
import resourceloader.Resourceloader;
public class ObjectController {
    private static ObjectController oc;
    static{
        oc = new ObjectController();
    }
    private GameMap gameMap;
    private HashMap<String, List<SuperObject>> map;
    private HashMap<String, Integer> priority;
    
    public ObjectController() {
        init();
    }

    public void init(){
        List<String> windowsize = Resourceloader.getResourceloader().getgameInfo().get("windowsize");
        if (windowsize == null || windowsize.isEmpty()) {
            throw new IllegalStateException("Window size configuration is missing.");
        }
        gameMap = new GameMap(Integer.parseInt(windowsize.get(0)), Integer.parseInt(windowsize.get(1)));
        map = new HashMap<>();
        priority = new HashMap<>();
        map.put("floor", new ArrayList<SuperObject>());
        map.put("player", new ArrayList<SuperObject>());
        map.put("obstacle", new ArrayList<SuperObject>());
        map.put("fragility", new ArrayList<SuperObject>());
        map.put("gameprops", new ArrayList<SuperObject>());
        map.put("bubble", new ArrayList<SuperObject>());
        map.put("bubbleExplode", new ArrayList<SuperObject>());
        priority.put("floor", -1);
        priority.put("player", 6);
        priority.put("obstacle", 5);
        priority.put("fragility",2);
        priority.put("gameprops",3);
        priority.put("bubble", 1);
        priority.put("bubbleExplode", 4);
        
    }
    public static ObjectController getObjController(){
        return oc;
    }

    public HashMap<String, List<SuperObject>> getMap(){
        return map;
    }

    public Comparator<String> getPriorty(){
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int p1 = priority.get(o1);
                int p2 = priority.get(o2);
                if(p1>p2){
                    return 1;
                }else if(p1<p2){
                    return -1;
                }else {
                    return 0;
                }
            }
        };
    }

    public static List<Integer> getPosIndex(int x, int y){
        List<Integer> posIndex = new ArrayList<>();
        posIndex.add((y-GameMap.getBiasY())/MapObject.PIXEL_Y);
        posIndex.add((x-GameMap.getBiasX())/MapObject.PIXEL_X);
        return posIndex;
    }
    
    public void loadMap(){
        gameMap.createMap();
    }
    public GameMap getGameMap(){
        return gameMap;
    }
    public static List<Integer> getPosIndex(int x, int y) {
    List<Integer> posIndex = new ArrayList<>();
    posIndex.add((y - GameMap.getBiasY()) / MapObject.PIXEL_Y);
    posIndex.add((x - GameMap.getBiasX()) / MapObject.PIXEL_X);
    return posIndex;
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
