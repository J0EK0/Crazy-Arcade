package controller;

import java.util.*;
import resourceloader.Resourceloader;
public class ObjectController {
    private static ObjectController oc;
    private GameMap gameMap;
    static{
        oc = new ObjectController();
    }
    public ObjectController() {
        init();
    }
    public void init(){
        List<String> windowsize = Resourceloader.getResourceloader().getgameInfo().get("windowsize");
        gameMap = new GameMap(Integer.parseInt(windowsize.get(0)), Integer.parseInt(windowsize.get(1)));
    }
}
