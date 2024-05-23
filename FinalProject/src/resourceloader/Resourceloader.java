package resourceloader;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import javax.swing.*;
public class Resourceloader {
    private static Resourceloader rl;
    private Properties properties;
    private HashMap<String, List<String>> gameInfo;
    private HashMap<String, ImageIcon> imageInfo;
    private HashMap<String, List<String>> mapInfo;
    private HashMap<String, List<String>> mapObjInfo;

    static {
        rl = new Resourceloader();
    }

    public Resourceloader() {
        properties = new Properties();
        gameInfo = new HashMap<>();
        imageInfo = new HashMap<>();
        mapInfo = new HashMap<>();
        mapObjInfo = new HashMap<>();
        try {
            readMapCfg();
            readimagecfg();
            readgamecfg();
            readMapObjectCfg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Resourceloader getResourceloader() {
        return rl;
    }

    public List<String> StrtoList(String str) {
        return Arrays.asList(str.split(","));
    }

    /*public void readmapcfg() throws IOException {
    InputStream in = getClass().getResourceAsStream("/config/map.cfg");
    if (in == null) {
        throw new FileNotFoundException("Configuration file '/config/map.cfg' not found in resources.");
    }
    properties.clear();
    properties.load(in);
    for (String key : properties.stringPropertyNames()) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            System.err.println("Missing or empty value for key: " + key);
        } else {
            mapInfo.put(key, StrtoList(value));
        }
    }
    in.close();
    }*/


    public void readimagecfg() throws IOException {
        InputStream in = getClass().getResourceAsStream("/config/image.cfg");
        if (in == null) {
            throw new IOException("Configuration file '/config/image.cfg' not found.");
        }
    
        try {
            properties.clear();
            properties.load(in);
            for (String key : properties.stringPropertyNames()) {
                String path = "/" + properties.getProperty(key);  // 注意這裡加了 `/`
                URL imageUrl = getClass().getResource(path);
                if (imageUrl != null) {
                    ImageIcon icon = new ImageIcon(imageUrl);
                    imageInfo.put(key, icon);
                } else {
                    System.err.println("Resource not found for key '" + key + "': " + path);
                    throw new IOException("Resource not found for key '" + key + "': " + path);
                }
            }
        } finally {
            //printImageInfo();
            in.close();
        }
    }
    public void printImageInfo() {
        if (imageInfo.isEmpty()) {
            System.out.println("No images loaded.");
        } else {
            System.out.println("Loaded images:");
            for (Map.Entry<String, ImageIcon> entry : imageInfo.entrySet()) {
                ImageIcon icon = entry.getValue();
                System.out.println("Key: " + entry.getKey() + " - Image Dimensions: " + icon.getIconWidth() + "x" + icon.getIconHeight());
            }
        }
    }
    

    public void readgamecfg() throws IOException {
        InputStream in = getClass().getResourceAsStream("/config/game.cfg");
        properties.clear();
        properties.load(in);
        for (String key : properties.stringPropertyNames()) {
            gameInfo.put(key, StrtoList(properties.getProperty(key)));
        }
        in.close();
    }

    public HashMap<String, List<String>> getgameInfo() {
        return gameInfo;
    }
    public HashMap<String, List<String>>getMapInfo() {return mapInfo;}
    public HashMap<String, List<String>> getMapObjectInfo() {return  mapObjInfo;}
    public HashMap<String, ImageIcon> getimageInfo() {
        return imageInfo;
    }
    public void readMapCfg() throws IOException {
        String mapFile = "/config/MapStage/map.cfg";  // 指定的 map 檔案名稱
    
        // 使用 try-with-resources 語句來自動關閉 InputStream
        try (InputStream in = Resourceloader.class.getResourceAsStream(mapFile)) {
            if (in == null) {
                throw new FileNotFoundException("Map configuration file not found: " + mapFile);
            }
    
            properties.clear();
            properties.load(in);
            mapInfo.clear();
    
            // 使用增強型 for 循環遍歷所有屬性名稱
            for (String key : properties.stringPropertyNames()) {
                String mInfo = properties.getProperty(key);
                List<String> values = StringToList(mInfo, ",");
                mapInfo.put(key, values);
    
                //System.out.println("Key: " + key + ", Values: " + values);  // 可以移除此行，如果不需要在控制台輸出
            }
        } catch (IOException e) {
            System.err.println("Error loading map configuration: " + e.getMessage());
            throw e;  // 可以選擇重新拋出異常
        }
    }
    
    /**
     * 將逗號分隔的字符串轉換成列表
     * @param input 輸入的字符串
     * @param delimiter 分隔符，這裡是 ","
     * @return 轉換後的字符串列表
     */
    private List<String> StringToList(String input, String delimiter) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(input.split(delimiter));
    }
    
    public void readMapObjectCfg() throws IOException {
        // 使用 try-with-resources 語句來自動關閉 InputStream
        try (InputStream in = getClass().getResourceAsStream("/config/mapObj.cfg")) {
            if (in == null) {
                System.out.println("Failed to load /config/mapObject.cfg");
            } else {
                //System.out.println("Successfully loaded /config/mapObject.cfg");
            }
            properties.clear();
            properties.load(in);
            
            // 使用增強型 for 循環遍歷所有屬性名稱
            for (String key : properties.stringPropertyNames()) {
                String mInfo = properties.getProperty(key);
                // 將字符串分割成列表並存儲到 mapObjectInfo 中
                mapObjInfo.put(key, StrtoList(mInfo));
            }
            for (String key : mapObjInfo.keySet()) {
                List<String> values = mapObjInfo.get(key);
                //System.out.println("Key: " + key + ", Values: " + values);
            }
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            throw e;  // 可以選擇重新拋出異常
        }
    }

    public ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
    Image image = icon.getImage(); // 将 ImageIcon 转换为 Image
    Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // 缩放图像
    return new ImageIcon(resizedImage); // 将缩放后的 Image 转换回 ImageIcon
}
}
