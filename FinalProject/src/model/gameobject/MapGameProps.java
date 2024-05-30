package model.gameobject;

import controller.GameMap;
import controller.ObjectController;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import model.gamecharacter.Player;
import resourceloader.Resourceloader;

public class MapGameProps extends MapObject{
    private String type; 
    private boolean eaten;
    private int playerIndex;
    private static HashMap<String, List<String>> objectInfo = Resourceloader.getResourceloader().getMapObjectInfo();

    public MapGameProps(int i, int j, ImageIcon img, int sx1, int sy1, int sx2, int sy2, int scaleX, int scaleY, String type){
        super(i, j, img, sx1, sy1, sx2, sy2, scaleX, scaleY);
        eaten = false;
        this.type = type;
    }

    public static String getProps(){
        Integer rand = new Random().nextInt(10);
        if(rand%2==0){
            return "1";
        }
        return "2";
    }
    public static MapGameProps createMapGameProps(int i, int j){
        String propType = getProps();
        propType = "3" + propType;
        List<String> propsData = objectInfo.get(propType);
        ImageIcon img = Resourceloader.getResourceloader().getimageInfo().get(propsData.get(0));
        int sx1 = Integer.valueOf(propsData.get(1));
        int sy1 = Integer.valueOf(propsData.get(2));
        int sx2 = Integer.valueOf(propsData.get(3));
        int sy2 = Integer.valueOf(propsData.get(4));
        int scaleX = Integer.valueOf(propsData.get(6));
        int scaleY = Integer.valueOf(propsData.get(7));
        return  new MapGameProps(i, j, img, sx1, sy1, sx2, sy2, scaleX, scaleY, propType);
    }

    @Override
    public void destroy() {
        if(eaten){
            GameMap gameMap = ObjectController.getObjController().getGameMap();
            int i = ObjectController.getPosIndex(getx(), gety()).get(0);
            int j = ObjectController.getPosIndex(getx(), gety()).get(1);
            gameMap.setMapListObj(i, j, MapObjectType.FLOOR);
            List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
            Player player = (Player)playerList.get(playerIndex);
            switch (type){
                case "32":
                    player.setmagicSaveCount(player.getmagicSaveCount() + 1);
                    break;
                case "31":
                    player.setmagicPowerCount(player.getmagicPowerCount() + 1);
                    break;
                default:
                    break;
            }
            eaten = false;
            setalive(false);
        }
    }


    public boolean isEaten(){
        return eaten;
    }

    public void setEaten(boolean eat) {
        this.eaten = eat;
    }

    public void setPlayerIndex(int playerIndex){
        this.playerIndex = playerIndex;
    }
}
