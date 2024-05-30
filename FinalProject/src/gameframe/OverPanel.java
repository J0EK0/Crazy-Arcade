package gameframe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import main.StartGame;
import resourceloader.Resourceloader;

public class OverPanel extends JPanel {
    private ImageIcon img;
    private ImageIcon winner;
    private int w;
    private int h;
    private static JButton result = new JButton();
    private JLabel label;
    private static JLabel winnerlabel = new JLabel();
    private JButton restart;

    public OverPanel(){
        List<String> size = Resourceloader.getResourceloader().getgameInfo().get("windowsize");
        //img = Resourceloader.getResourceloader().getimageInfo().get("gameover");
        w = Integer.valueOf(size.get(0));
        h = Integer.valueOf(size.get(1));
        init();
    }

    public void init(){
        this.setLayout(null);
        //img.setImage(img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        //label = new JLabel();
        //label.setBounds(0, 0, w, h);


        ImageIcon restratImg = Resourceloader.getResourceloader().getimageInfo().get("restart");
        restratImg.setImage(restratImg.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH));
        restart = new JButton(restratImg);
        restart.setBounds(w/2-150, 3*h/4, 300, 100);
        restart.setBorderPainted(false);
        restart.setFocusPainted(false);
        restart.setContentAreaFilled(false);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartGame.changePanel("start");
            }
        });

        result.setFont(new Font("Times New Roman", Font.BOLD, 40));
        result.setBounds(0, 100, 550, 500);
        result.setHorizontalTextPosition(SwingConstants.CENTER);
        result.setVerticalTextPosition(SwingConstants.CENTER);
        result.setBorderPainted(false);
        result.setContentAreaFilled(false);
        result.setVisible(true);

        winnerlabel.setBounds(500, -50, 800, 800);
        winnerlabel.setVisible(true);

        this.add(winnerlabel);
        this.add(result);
        //this.add(label);
        this.setVisible(true);
        this.setOpaque(false);
    }

    public static JButton getResultButton(){
        return result;
    }
    
    public static JLabel getwinnerlabel(){
        return winnerlabel;
    }
}