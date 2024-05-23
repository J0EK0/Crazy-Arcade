package gameframe;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import resourceloader.Resourceloader;
public class GameFrame extends JFrame {
    private JPanel contentPane;
    private GamePanel gamePanel;
    private InitialPanel startPanel;
    private OverPanel overPanel;
    public GameFrame() {
        init();
    }
    
    public void init(){
        this.setTitle("PaoPaoTang");
        List<String> windowsize = Resourceloader.getResourceloader().getgameInfo().get("windowsize");
        this.setSize(Integer.valueOf(windowsize.get(0)), Integer.valueOf(windowsize.get(1)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);        //将窗口设置于屏幕中央
        CardLayout cardLayout = new CardLayout();
        this.contentPane = new JPanel();
        this.setContentPane(contentPane);
        this.contentPane.setLayout(cardLayout);
        this.startPanel = new InitialPanel();
        this.contentPane.add("start",startPanel);
        this.overPanel = new OverPanel();
        this.contentPane.add("over", overPanel);
        ((CardLayout)this.contentPane.getLayout()).show(contentPane,"start");
        this.setVisible(true);
    }
    
}
