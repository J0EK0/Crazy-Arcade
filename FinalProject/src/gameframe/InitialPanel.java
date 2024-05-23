package gameframe;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.GameController;
import controller.ObjectController;
import main.StartGame;
import model.gameobject.MapFloor;
import model.gameobject.SuperObject;
import resourceloader.Resourceloader;
import javax.swing.ImageIcon;
import resourceloader.Resourceloader;



public class InitialPanel extends JPanel {
    public static int playerIndex;
    private JLabel startLabel;
    private JLabel playerLabel;
    private JButton introButton;
    private JButton selectButton;
    private JButton ensureButton;
    private JButton changeButton;
    private JButton startButton;
    private JLayeredPane layeredPane;
    private ImageIcon playerIcon;

    public InitialPanel(){
        init();
    }

    public void init(){
        Resourceloader rl = Resourceloader.getResourceloader();
        ImageIcon originalIcon = rl.getimageInfo().get("startbtn");
        ImageIcon resizedIcon = rl.resizeIcon(originalIcon, 240, 80); // 调整图像大小
        /*ImageIcon originalIcon2 = rl.getimageInfo().get("fragility");
        ImageIcon resizedIcon2 = rl.resizeIcon(originalIcon2, 240, 80);
        introButton = new JButton();
        introButton.setIcon(resizedIcon2);
        introButton.setVisible(true);
        
        this.add(introButton);*/
        List<String> windowsize = Resourceloader.getResourceloader().getgameInfo().get("windowsize");
        
        int width = Integer.valueOf(windowsize.get(0));
        int height = Integer.valueOf(windowsize.get(1));
        //introButton.setBounds(width/7, height/4, 240, 80);
        startButton = new JButton();
        startButton.setIcon(resizedIcon);
        startButton.setBounds(width/6, height/3, 240, 80);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setVisible(true);
        startButton.addActionListener(e -> startButtonActionPerformed(e));

        this.setLayout(null);
        this.add(startButton);
        this.setVisible(true);
        this.setOpaque(true);
        //HashMap<String, List<String>> objectInfo = Resourceloader.getResourceloader().getMapObjectInfo();
        //System.out.println(objectInfo.get("10"));
        /*for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ImageIcon imageIcon = MapFloor.createMapFloor(i, j, objectInfo.get("10")).getImageIcon();
                if (imageIcon != null) {
                    addImageLabel(imageIcon, j * imageIcon.getIconWidth(), i * imageIcon.getIconHeight());
                } else {
                    System.out.println("未找到圖標: i=" + i + ", j=" + j);
                }
            }
        }*/

        run();
    }
    public void run() {
        this.repaint();
    }
    public void paint(Graphics g){
        super.paint(g);
        GamePaint(g);
    }
    public void GamePaint(Graphics g){
        HashMap<String, List<SuperObject>> map = ObjectController.getObjController().getMap();
        //Set<String> sortItems = new TreeSet<String>(ObjectController.getObjController().getMapObjCmp());
        //sortItems.addAll(map.keySet());
        for(String key:map.keySet()){
            List<SuperObject> list = map.get(key);
            for(int i=0;i<list.size();i++){
                list.get(i).showObject(g);
            }
        }
    }
   /*  public void addImageLabel(ImageIcon icon, int x, int y) {
        JLabel label = new JLabel(icon);
        label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        this.add(label);
        this.repaint(); // Refresh panel to display newly added label
    }*/
    
    private void startButtonActionPerformed(ActionEvent e){
        System.out.println("start");
        StartGame.startgame();
    }
}
