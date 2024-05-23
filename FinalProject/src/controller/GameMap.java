package controller;

//import model.gamecharacter.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.gameobject.*;
import resourceloader.Resourceloader;

public class GameMap {
    private static int width;
    private static int height;
    private static int rows;
    private static int cols;
    private static int biasX;
    private static int biasY;
    private List<List<String>> mapList;

    public GameMap(int width, int height){
        this.width=width;
        this.height=height;
        mapList = new ArrayList<>();
        initMapList();
        biasX = (width - MapObject.PIXEL_X*cols)/4;
        biasY = (height - MapObject.PIXEL_Y*rows)/2;
    }

    public void initMapList() {
        HashMap<String, List<String>> mapInfo = Resourceloader.getResourceloader().getMapInfo();

        List<String> sizeInfo = mapInfo.get("size");
        rows = Integer.valueOf(sizeInfo.get(0));
        cols = Integer.valueOf(sizeInfo.get(1));

        // 確保 mapList 是空的，以避免重複數據
        for (int i = 0; i < rows; i++) {
            mapList.add(mapInfo.get(String.valueOf(i + 1)));
        }
    }


<<<<<<< HEAD
=======
    rows = Integer.valueOf(sizeInfo.get(0));
    cols = Integer.valueOf(sizeInfo.get(1));

    // 確保 mapList 是空的，以避免重複數據
    mapList.clear();
    for (int i = 0; i < rows; i++) {
        mapList.add(mapInfo.get(String.valueOf(i + 1)));
    }

    // 打印 mapList 以查看其內容
    System.out.println("Map List:");
    for (List<String> row : mapList) {
        System.out.println(row);
    }
    System.out.println("test4");
    //createMap();
}


>>>>>>> 58d5c9b (o)
    public void createMap() {
        try {
            Resourceloader.getResourceloader().readMapCfg(); // 確保這裡傳遞 map 參數
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, List<String>> mapInfo = Resourceloader.getResourceloader().getMapInfo();
        mapList.clear();
        int rows = Integer.valueOf(mapInfo.get("size").get(0));
        int cols = Integer.valueOf(mapInfo.get("size").get(1));
    
        // 清除mapList之前的數據，以避免重複添加
        mapList.clear();
        for (int i = 0; i < rows; i++) {
            mapList.add(mapInfo.get(String.valueOf(i + 1)));
        }
    
        // 印出 mapList 的內容
        System.out.println("Map List:");
        for (List<String> row : mapList) {
            System.out.println(row);
        }
    
        // 調用 createMapObject 方法以建立地圖物件
        createMapObject();
    }
    private void createMapObject(){
        try {
            Resourceloader r = new Resourceloader();
            r.readMapObjectCfg();
        }catch (Exception e){
            e.printStackTrace();
        }
        HashMap<String, List<String>> objectInfo = Resourceloader.getResourceloader().getMapObjectInfo();
        //HashMap<String, List<String>>  mapInfo = Resourceloader.getResourceloader().getMapInfo();
        //System.out.println(ObjectController.getObjController());
<<<<<<< HEAD
        HashMap<String, List<SuperObject>> map = ObjectController.getObjController().getMap();
=======
        //HashMap<String, List<SuperObject>> map = ObjectController.getObjController().getMap();
        
>>>>>>> 58d5c9b (o)
        //创建地板
       /*  for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                //ImageIcon imageIcon = MapFloor.createMapFloor(i, j, objectInfo.get("10")).getImageIcon();
                map.get("floor").add(MapFloor.createMapFloor(i, j, objectInfo.get("10")));
            }
<<<<<<< HEAD
        }
=======
        }*/
        System.out.println("test666");
>>>>>>> 58d5c9b (o)
        //创建物品与人物
        /*for(int i=0;i<rows;i++){
            List<String> objs = mapInfo.get(String.valueOf(i+1));
            for(int j=0;j<objs.size();j++){
                String type = objs.get(j);
                switch (type.charAt(0)){
                    case '1':
                        map.get("obstacle").add(MapObstacle.createMapObstacle(i,j,objectInfo.get(type)));
                        break;
                    case '2':
                        map.get("fragility").add(MapFragility.createMapFragility(i, j, objectInfo.get(type)));
                        break;
                    case  '3':
                        map.get("gameprops").add(MapGameProps.createMapGameProps(i, j));
                    case  '6':
                        initPlayer(i, j);
                    default:
                        break;

                }
            }
        }*/
        //TODO:
    }

    public boolean isBlockWalkable(int i, int j){
        String type = mapList.get(i).get(j);
        switch (type.charAt(0)){
            case '1':
            case '2':
            case '3':
                return false;
            default:
                return true;
        }
    }

   /*  private void initPlayer(int i, int j){
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        HashMap<String, List<String>> objectInfo = ResourceLoader.getResourceLoader().getMapObjectInfo();
        List<String> playInfo = objectInfo.get("player");
        Player player = Player.createPlayer(i, j, playInfo);
        playerList.add(player);
    }*/

    public boolean outOfMap(int i, int j){
        if(i < 0 || i >= rows || j < 0 || j >= cols){
            return true;
        }else {
            return false;
        }
    }

    /*public boolean isObstacle(int i, int j){
        if(outOfMap(i, j)){
            return  true;
        }
        String mapObject = mapList.get(i).get(j);
        if(MapObjectType.valueOf(mapObject.charAt(0))==MapObjectType.OBSTACLE){
            return true;
        }else {
            return false;
        }
    }

    public boolean isFragility(int i, int j){
        if(outOfMap(i, j)){
            return true;
        }
        String mapObject = mapList.get(i).get(j);
        if(MapObjectType.valueOf(mapObject.charAt(0))==MapObjectType.FRAGILITY){
            return true;
        }else {
            return false;
        }
    }

    public boolean isBubble(int i, int j){
        if(outOfMap(i, j)){
            return true;
        }
        String mapObject = mapList.get(i).get(j);
        if(MapObjectType.valueOf(mapObject.charAt(0))==MapObjectType.BUBBLE){
            return true;
        }else {
            return false;
        }
    }*/

    public List<List<String>> getMapList(){
        return mapList;
    }

    /*public void setMapListObj(int i, int j, MapObjectType Objtype){
        mapList.get(i).set(j, Objtype.getVal()+"");
    }*/

    public static int getBiasX() {
        return biasX;
    }
    public static int getBiasY() {
        return biasY;
    }
    public static int getMapRows() {
        return rows;
    }
    public static int getMapCols() {
        return cols;
    }
}