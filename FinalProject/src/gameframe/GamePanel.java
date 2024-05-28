package gameframe;

import controller.*;
import java.awt.*;
import controller.GameController;
import controller.ObjectController;
import main.StartGame;
import model.gamecharacter.Character;
import model.gamecharacter.Player;
import model.gameobject.MapObject;
import model.gameobject.SuperObject;
import resourceloader.Resourceloader;
//import Thread.GameKeyListener;
import thread.GameThread;
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

public class GamePanel extends JPanel implements Runnable{
    
    private boolean running;
    private GameKeyListener keyListener;
    private JButton magicWei;
    private JButton magicJiu;
    public GamePanel() {
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

    public void paint(Graphics g){
        super.paint(g);
        Gamepaint(g);
    }

    private void  init(){
         magicWei = new JButton();
         ImageIcon weiImg = Resourceloader.getResourceloader().getimageInfo().get("jiuming");
         weiImg.setImage(weiImg.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
         magicWei.setIcon(weiImg);
         magicWei.setBounds(950, 390, 70, 70);
         magicWei.addActionListener(e -> weiButtonActionPerformed(e));

         magicJiu = new JButton();
         ImageIcon jiuImg = Resourceloader.getResourceloader().getimageInfo().get("weili");
         jiuImg.setImage(jiuImg.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
         magicJiu.setIcon(jiuImg);
         magicJiu.setBounds(950, 470, 70, 70);
         magicJiu.addActionListener(e -> jiuButtonActionPerformed(e));
         this.setLayout(null);
         this.add(magicWei);
         this.add(magicJiu);
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
    */
    public void weiButtonActionPerformed(ActionEvent e){
        getFocus();
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        Player player = (Player) playerList.get(0);
        /*if(player.getMagicPowerCount() > 0 && !player.isDying()){
            player.setMagicPowerCount(player.getMagicPowerCount() - 1);
            player.setBubblePower(player.getBubblePower() + 1);
        }*/
    }

    public void jiuButtonActionPerformed(ActionEvent e){
        getFocus();
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        Player player = (Player) playerList.get(0);
        /*if(player.getMagicSaveCount()>0){
            player.setMagicSaveCount(player.getMagicSaveCount() - 1);
            player.setDying(false);
            player.setSpeed(Character.INIT_SPEED);
            player.setNormalImg();
        }*/
    }

    /*public void gameCtrlActionPerformed(ActionEvent e){
        getFocus();
        if(GameController.isGameRunning()){
            GameController.setGameRunning(false);
        }else {
            GameController.setGameRunning(true);
        }
    }*/

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

    public void addGameKeyListener(){
        if(keyListener != null){
            this.removeKeyListener(keyListener);
            this.addKeyListener(keyListener);
        }
    }
    
    public void removeGameKeyListener(){
        this.removeKeyListener(keyListener);
    }
    public void getFocus(){
        this.requestFocus();
    }

}