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
        ImageIcon resizedIcon = rl.resizeIcon(originalIcon, 240, 80); // 调整图像大小
        ImageIcon originalIcon2 = rl.getimageInfo().get("introduce");
        ImageIcon resizedIcon2 = rl.resizeIcon(originalIcon2, 240, 80);
        ImageIcon originalIcon3 = rl.getimageInfo().get("select");
        ImageIcon resizedIcon3 = rl.resizeIcon(originalIcon3, 240, 80);
        ImageIcon originalIcon4 = rl.getimageInfo().get("change");
        ImageIcon resizedIcon4 = rl.resizeIcon(originalIcon4, 240, 80);
        ImageIcon originalIcon5 = rl.getimageInfo().get("ensure");
        ImageIcon resizedIcon5 = rl.resizeIcon(originalIcon5, 240, 80);
        ImageIcon originalIcon6 = rl.getimageInfo().get("character1");
        ImageIcon resizedIcon6 = rl.resizeIcon(originalIcon6, 240, 80);
        ImageIcon originalIcon7 = rl.getimageInfo().get("str_bg");
        ImageIcon resizedIcon7 = rl.resizeIcon(originalIcon7, 1200, 900);
        startLabel = new JLabel();
        startLabel.setIcon(resizedIcon7);
        startLabel.setVisible(true);
        startLabel.setBounds(0, 0, 1200, 900);
        startLabel.setVisible(true);
        this.add(startLabel);
        playerLabel = new JLabel();
        playerLabel.setIcon(resizedIcon6);
        playerLabel.setVisible(true);
        playerLabel.setBounds(800, 300, 240, 80);
        playerLabel.setVisible(false);
        this.add(playerLabel);
        introButton = new JButton();
        introButton.setIcon(resizedIcon2);
        introButton.setVisible(true);
        introButton.setBounds(width/6, height/3, 240, 80);
        introButton.setVisible(true);
        this.add(introButton);
        changeButton = new JButton();
        changeButton.setIcon(resizedIcon4);
        changeButton.setVisible(true);
        changeButton.setBounds(400, 700, 150, 100);
        changeButton.addActionListener(e -> changeButtonActionPerformed(e));
        changeButton.setVisible(false);
        this.add(changeButton);
        
        ensureButton = new JButton();
        ensureButton.setIcon(resizedIcon5);
        ensureButton.setVisible(true);
        ensureButton.setBounds(600, 700, 150, 100);
        ensureButton.addActionListener(e -> ensureButtonActionPerformed(e));
        ensureButton.setVisible(false);
        this.add(ensureButton);
        selectButton = new JButton();
        selectButton.setIcon(resizedIcon3);
        selectButton.setVisible(true);
        selectButton.setBounds(200, 700, 150, 100);
        selectButton.addActionListener(e -> selectButtonActionPerformed(e));
        selectButton.setVisible(true);
        this.add(selectButton);
        startButton = new JButton();
        startButton.setIcon(resizedIcon);
        startButton.setBounds(width/6, height/2, 240, 80);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setVisible(true);
        startButton.addActionListener(e -> startButtonActionPerformed(e));

        layeredPane.add(startLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(introButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(selectButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(changeButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(ensureButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(playerLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);
        this.setLayout(null);
        this.add(layeredPane);
        //this.add(introButton);
        this.setVisible(true);
        this.setOpaque(true);
<<<<<<< HEAD
        HashMap<String, List<String>> objectInfo = Resourceloader.getResourceloader().getMapObjectInfo();

=======
        //HashMap<String, List<String>> objectInfo = Resourceloader.getResourceloader().getMapObjectInfo();
>>>>>>> d16430c (0)
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
<<<<<<< HEAD
    }
    private void startButtonActionPerformed(ActionEvent e){
        System.err.println("Start");
        StartGame.startgame();

=======

        
       // run();
    }
     
    /*public void addImageLabel(ImageIcon icon, int x, int y) {
        JLabel label = new JLabel(icon);
        label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        this.add(label);
        this.repaint(); // Refresh panel to display newly added label
    }
    */
    private void startButtonActionPerformed(ActionEvent e){
        System.out.println("start");
        //StartGame.startgame();
>>>>>>> d16430c (0)
    }
    private void selectButtonActionPerformed(ActionEvent e){
        System.out.println("select");
        changeButton.setVisible(true);
        ensureButton.setVisible(true);
        playerLabel.setVisible(true);
        layeredPane.repaint();
        layeredPane.revalidate();
        //StartGame.startgame();
    }
    private void changeButtonActionPerformed(ActionEvent e){
        playerIndex = playerIndex + 1;
        if (playerIndex > 2) {
            playerIndex = 1;
        }
        ImageIcon originalIcon6 = rl.getimageInfo().get("character"+String.valueOf(playerIndex));
        ImageIcon resizedIcon6 = rl.resizeIcon(originalIcon6, 240, 80);
        
        //Image player_img = newPlayerIcon.getImage();
        //Image player_scaledImg = player_img.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        //newPlayerIcon.setImage(player_scaledImg);
        //playerIcon.setImage(player_scaledImg);

        playerLabel.setIcon(resizedIcon6);
        playerLabel.repaint();
        layeredPane.repaint();
        layeredPane.revalidate();
        //System.out.println("start");
        //StartGame.startgame();
    }
    private void ensureButtonActionPerformed(ActionEvent e){
        changeButton.setVisible(false);
        ensureButton.setVisible(false);
        playerLabel.setVisible(false);
        layeredPane.repaint();
        layeredPane.revalidate();
       // System.out.println("start");
        //StartGame.startgame();
<<<<<<< HEAD
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
=======
>>>>>>> d16430c (0)
    }
}
