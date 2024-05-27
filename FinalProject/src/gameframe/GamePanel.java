package gameframe;

import controller.*;
import java.awt.*;
import controller.GameController;
import controller.ObjectController;
import main.StartGame;
import model.gamecharacter.Character;
//import model.gamecharacter.Player;
import model.gameobject.MapObject;
import model.gameobject.SuperObject;
import resourceloader.Resourceloader;
//import Thread.GameKeyListener;
import Thread.Gamethread;
//import Thread.PlayGameMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.*;
import model.gameobject.SuperObject;
import thread.GameKeyListener;


public class GamePanel extends JPanel implements Runnable {
    //public static PlayGameMusic playmusic = new PlayGameMusic();
    private JButton stopMusic;
    private JButton continueMusic;
    //private GameKeyListener keyListener;
    private JLabel playerImg;
    private JButton magicPao;
    private JButton magicWei;
    private JButton magicJiu;
    private JButton gameControl;
    private JButton backMain;
    private boolean running;
    private GameKeyListener keyListener;


    public GamePanel(){
        super();
        running = true;
       // keyListener = new GameKeyListener();
        this.setFocusable(true);
        //this.addGameKeyListener();
       // System.out.println(playmusic.getState());
       /*  if(playmusic.getState() == Thread.State.NEW){
            playmusic.start();
        }else {
            playmusic.continues();
        }*/
        init();
        running = true;

        keyListener = new GameKeyListener();
        this.addGameKeyListener();
        System.err.println("start game key listener");

    }

    private void init(){

    }

    public void paint(Graphics g){
        super.paint(g);
        Gamepaint(g);
    }

    private void  init(){

        stopMusic = new JButton();
        ImageIcon stopImg = Resourceloader.getResourceloader().getimageInfo().get("stopmusic");
        stopImg.setImage(stopImg.getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH));
        stopMusic.setIcon(stopImg);
        stopMusic.setBounds(120,30,150,60);
        stopMusic.setBorderPainted(false);
        stopMusic.setContentAreaFilled(false);
        //stopMusic.addActionListener(e -> stopMusicActionPerformed(e));

         continueMusic = new JButton();
         ImageIcon playImg = Resourceloader.getResourceloader().getimageInfo().get("playmusic");
         playImg.setImage(playImg.getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH));
         continueMusic.setIcon(playImg);
         continueMusic.setBounds(300,30,150,60);
         continueMusic.setBorderPainted(false);
         continueMusic.setFocusPainted(false);
         continueMusic.setContentAreaFilled(false);
        // continueMusic.addActionListener(e -> continueMusicActionPerformed(e));

         gameControl = new JButton();
         ImageIcon controlImg = Resourceloader.getResourceloader().getimageInfo().get("gamecontrol");
         controlImg.setImage(controlImg.getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH));
         gameControl.setIcon(controlImg);
         gameControl.setBorderPainted(false);
         gameControl.setContentAreaFilled(false);
         gameControl.setBounds(480, 30, 150, 60);
        // gameControl.addActionListener(e -> gameCtrlActionPerformed(e));

         backMain = new JButton();
         ImageIcon backImg = Resourceloader.getResourceloader().getimageInfo().get("backMain");
         backImg.setImage(backImg.getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH));
         backMain.setIcon(backImg);
         backMain.setBorderPainted(false);
         backMain.setContentAreaFilled(false);
         backMain.setFocusPainted(false);
         backMain.setBounds(660, 30, 150, 60);
         //backMain.addActionListener(e -> backMainBtnActionPerformed(e));

         playerImg = new JLabel();
         ImageIcon img = Resourceloader.getResourceloader().getimageInfo().get("player"+String.valueOf(StartPanel.playerIndex)+"img");
         img.setImage(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
         playerImg.setIcon(img);
         playerImg.setBounds(950, 50, 100, 100);

         magicPao = new JButton();
         ImageIcon paoImg = Resourceloader.getResourceloader().getimageInfo().get("magicPao");
         paoImg.setImage(paoImg.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
         magicPao.setIcon(paoImg);
         magicPao.setBounds(950, 310, 70, 70);
         //magicPao.addActionListener(e -> paoButtonActionPerformed(e));

         magicWei = new JButton();
         ImageIcon weiImg = Resourceloader.getResourceloader().getimageInfo().get("magicWei");
         weiImg.setImage(weiImg.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
         magicWei.setIcon(weiImg);
         magicWei.setBounds(950, 390, 70, 70);
         //magicWei.addActionListener(e -> weiButtonActionPerformed(e));

         magicJiu = new JButton();
         ImageIcon jiuImg = Resourceloader.getResourceloader().getimageInfo().get("magicJiu");
         jiuImg.setImage(jiuImg.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
         magicJiu.setIcon(jiuImg);
         magicJiu.setBounds(950, 470, 70, 70);
         //magicJiu.addActionListener(e -> jiuButtonActionPerformed(e));

         this.setLayout(null);
         this.add(magicPao);
         this.add(magicWei);
         this.add(magicJiu);
         this.add(stopMusic);
         this.add(backMain);
         this.add(continueMusic);
         this.add(gameControl);
         this.add(playerImg);
         this.setVisible(true);
         this.setOpaque(true);
    }

    /*public void stopMusicActionPerformed(ActionEvent e){
        getFocus();
        playmusic.stops();
    }
    public void continueMusicActionPerformed(ActionEvent e){
          getFocus();
          playmusic.continues();
    }

    public void backMainBtnActionPerformed(ActionEvent e){
        getFocus();
        playmusic.stops();
        GameFrame.getGameThread().setOver(true);
        GameFrame.getGameThread().setRunning(false);
        running = false;
        ObjectController.getObjController().gameClean();
        GameController.setGameRunning(false);
    }

    public void paoButtonActionPerformed(ActionEvent e){
        getFocus();
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        Player player = (Player) playerList.get(0);
        if(player.getMagicBubbleCount() > 0 && !player.isDying()){
            player.setMagicBubbleCount(player.getMagicBubbleCount() - 1);
            player.setBubbleNum(player.getBubbleNum() + 1);
        }
    }

    public void weiButtonActionPerformed(ActionEvent e){
        getFocus();
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        Player player = (Player) playerList.get(0);
        if(player.getMagicPowerCount() > 0 && !player.isDying()){
            player.setMagicPowerCount(player.getMagicPowerCount() - 1);
            player.setBubblePower(player.getBubblePower() + 1);
        }
    }

    public void jiuButtonActionPerformed(ActionEvent e){
        getFocus();
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        Player player = (Player) playerList.get(0);
        if(player.getMagicSaveCount()>0){
            player.setMagicSaveCount(player.getMagicSaveCount() - 1);
            player.setDying(false);
            player.setSpeed(Character.INIT_SPEED);
            player.setNormalImg();
        }
    }

    public void gameCtrlActionPerformed(ActionEvent e){
        getFocus();
        if(GameController.isGameRunning()){
            GameController.setGameRunning(false);
        }else {
            GameController.setGameRunning(true);
        }
    }*/

    public void paint(Graphics g){
        super.paint(g);
        GamePaint(g);
    }

    @Override
    public void run() {
        while (running){
            try{
                Thread.sleep(20);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(GameController.isGameRunning()){
                this.repaint();
            }
        }
    }

    public void Gamepaint(Graphics g){
        HashMap<String, List<SuperObject>> map = ObjectController.getObjController().getMap();
        Set<String> sortitems = new TreeSet<String>(ObjectController.getObjController().getPriorty());
        sortitems.addAll(map.keySet());
        for(String key:sortitems){
            List<SuperObject> list = map.get(key);
            for(int i=0;i<list.size();i++){
                list.get(i).showObject(g);
            }
        }
    }
}