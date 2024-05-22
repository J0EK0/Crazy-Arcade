package resourceloader;
import java.util.*;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

public class Resourceloader {
    private static Resourceloader rl;
    private Properties properties;
    private HashMap<String, List<String>> gameInfo;
    private HashMap<String, ImageIcon> imageInfo;
    private HashMap<String, List<String>> mapInfo;
    private HashMap<String, List<String>> mapObjInfo;
    static{
        rl = new Resourceloader();
    }
    private Resourceloader(){
        properties = new Properties();
        gameInfo = new HashMap<String, List<String>>();
        imageInfo = new HashMap<String, ImageIcon>();
        mapInfo = new HashMap<String, List<String>>();
        mapObjInfo = new HashMap<String, List<String>>();
        try{
           readmapcfg();
           readimagecfg();
           //readmapObjcfg();
           readgamecfg();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Resourceloader getResourceloader(){
        return rl;
    }

    public List<String> StrtoList(String str){
        return Arrays.asList(str.split(","));
    }

    public void readmapcfg() throws IOException{
        //mapInfo.clear();
        InputStream in = Resourceloader.class.getResourceAsStream("/config/map.cfg");
        properties.clear();
        properties.load(in);
        Iterator it = properties.keySet().iterator();
        while (it.hasNext()) {
            String keyString = (String)it.next();
            String valueString = properties.getProperty(keyString);
            mapInfo.put(keyString, StrtoList(valueString));
        }
    }

    public void readimagecfg() throws IOException{
        InputStream in = Resourceloader.class.getResourceAsStream("/config/image.cfg");
        properties.clear();
        properties.load(in);
        Iterator it = properties.keySet().iterator();
        while (it.hasNext()) {
            String keyString = (String)it.next();
            String valueString = properties.getProperty(keyString);
            imageInfo.put(keyString, new ImageIcon(valueString));
        }
    }

    public void readgamecfg() throws IOException{
        //mapInfo.clear();
        InputStream in = Resourceloader.class.getResourceAsStream("/config/game.cfg");
        properties.clear();
        properties.load(in);
        Iterator it = properties.keySet().iterator();
        while (it.hasNext()) {
            String keyString = (String)it.next();
            String valueString = properties.getProperty(keyString);
            //System.out.println(keyString + "=" + valueString);
            gameInfo.put(keyString, StrtoList(valueString));
        }
    }

    public HashMap<String, List<String>> getgameInfo() {return gameInfo;}
}
