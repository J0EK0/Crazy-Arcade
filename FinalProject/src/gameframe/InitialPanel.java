package gameframe;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import main.StartGame;
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
        List<String> windowsize = Resourceloader.getResourceloader().getgameInfo().get("windowsize");
        int width = Integer.valueOf(windowsize.get(0));
        int height = Integer.valueOf(windowsize.get(1));
        startButton = new JButton();
        startButton.setIcon(Resourceloader.getResourceloader().getimageInfo().get("startbtn"));
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
    }

    private void startButtonActionPerformed(ActionEvent e){
        System.out.println("start");
        StartGame.startgame();
    }
}
