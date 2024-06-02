package gameframe;
import controller.ObjectController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import main.StartGame;
import model.gameobject.SuperObject;
import resourceloader.Resourceloader;



public class InitialPanel extends JPanel {
    public static int playerIndex;
    private JLabel startLabel;
    private JLabel playerLabel;
    private JButton startButton;

    private JLayeredPane layeredPane;
    private ImageIcon playerIcon;

    public InitialPanel(){
        init();
    }
    Resourceloader rl;
    public void init(){
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1200, 900);
        List<String> windowsize = Resourceloader.getResourceloader().getgameInfo().get("windowsize");
        int width = Integer.valueOf(windowsize.get(0));
        int height = Integer.valueOf(windowsize.get(1));
        playerIndex = 1;
        rl = Resourceloader.getResourceloader();
        ImageIcon originalIcon = rl.getimageInfo().get("startbtn");
        ImageIcon resizedIcon = rl.resizeIcon(originalIcon, 400, 400); // 调整图像大小
        ImageIcon originalIcon7 = rl.getimageInfo().get("str_bg");
        ImageIcon resizedIcon7 = rl.resizeIcon(originalIcon7, 1200, 900);

        startLabel = new JLabel();
        startLabel.setIcon(resizedIcon7);
        startLabel.setVisible(true);
        startLabel.setBounds(0, 0, 1200, 900);
        startLabel.setVisible(true);
        this.add(startLabel);
        
        startButton = new JButton();
        startButton.setIcon(resizedIcon);
        startButton.setBounds(400, 300, 400, 400);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setVisible(true);
        startButton.addActionListener(e -> startButtonActionPerformed(e));

        layeredPane.add(startLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);
        this.setLayout(null);
        this.add(layeredPane);
        this.setVisible(true);
        this.setOpaque(true);
        HashMap<String, List<String>> objectInfo = Resourceloader.getResourceloader().getMapObjectInfo();

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
    }

    private void startButtonActionPerformed(ActionEvent e){
        StartGame.startgame();
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
        for(String key:map.keySet()){
            List<SuperObject> list = map.get(key);
            for(int i=0;i<list.size();i++){
                list.get(i).showObject(g);
            }
        }
    }
}
