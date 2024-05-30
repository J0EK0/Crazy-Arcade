package gameframe;

import controller.GameController;
import controller.ObjectController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.*;
import model.gamecharacter.Player;
import model.gameobject.SuperObject;
import resourceloader.Resourceloader;
import thread.GameKeyListener;
import thread.GameThread;

public class GamePanel extends JPanel implements Runnable{
    
    private boolean running;
    private GameKeyListener keyListener;
    private JButton magicWei;
    private JButton magicJiu;
    private JButton magicWei2;
    private JButton magicJiu2;
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
         ImageIcon weiImg = Resourceloader.getResourceloader().getimageInfo().get("weili");
         weiImg.setImage(weiImg.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
         magicWei.setIcon(weiImg);
         magicWei.setBounds(950, 390, 70, 70);
         //magicWei.addActionListener(e -> weiButtonActionPerformed(e));

         magicJiu = new JButton();
         ImageIcon jiuImg = Resourceloader.getResourceloader().getimageInfo().get("jiuming");
         jiuImg.setImage(jiuImg.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
         magicJiu.setIcon(jiuImg);
         magicJiu.setBounds(950, 470, 70, 70);
         magicJiu.addActionListener(e -> jiuButtonActionPerformed(e));

         magicWei2 = new JButton();
         ImageIcon weiImg2 = Resourceloader.getResourceloader().getimageInfo().get("weili");
         weiImg2.setImage(weiImg2.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
         magicWei2.setIcon(weiImg2);
         magicWei2.setBounds(950, 240, 70, 70);
         //magicWei2.addActionListener(e -> weiButton2ActionPerformed(e));

         magicJiu2 = new JButton();
         ImageIcon jiuImg2 = Resourceloader.getResourceloader().getimageInfo().get("jiuming");
         jiuImg2.setImage(jiuImg2.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
         magicJiu2.setIcon(jiuImg2);
         magicJiu2.setBounds(950, 320, 70, 70);
         magicJiu2.addActionListener(e -> jiuButton2ActionPerformed(e));

         this.setLayout(null);
         this.add(magicWei);
         this.add(magicJiu);
         this.add(magicWei2);
         this.add(magicJiu2);
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

public void jiuButtonActionPerformed(ActionEvent e){ // oh my god what a save
        getFocus();
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        Player player = (Player) playerList.get(0);
        if(player.getmagicSaveCount()>0){
            player.setmagicSaveCount(player.getmagicSaveCount() - 1);
            player.setDying(false);
            player.setNormalImg();
        }
    }
    public void weiButton2ActionPerformed(ActionEvent e){
        getFocus();
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        Player player = (Player) playerList.get(1);
        if(player.getmagicPowerCount() > 0 && !player.isDying()){
            player.setmagicPowerCount(player.getmagicPowerCount() - 1);
            player.setbubblepower(player.getbubblepower() + 1);
        }
    }

    public void jiuButton2ActionPerformed(ActionEvent e){
        getFocus();
        List<SuperObject> playerList = ObjectController.getObjController().getMap().get("player");
        Player player = (Player) playerList.get(1);
        if(player.getmagicSaveCount()>0){
            player.setmagicSaveCount(player.getmagicSaveCount() - 1);
            player.setDying(false);
            //player.setSpeed(Character.INIT_SPEED);
            player.setNormalImg();
        }
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
        if(map.get("player").size() > 0){
            Player player = (Player) map.get("player").get(0);
            Player player2 = (Player) map.get("player").get(1);
            g.setFont(new Font("宋体", Font.BOLD, 24));
            /*if(InitialPanel.playerIndex == 1){
                g.drawString("Duck", 1080 , 100);
            }else {
                g.drawString("Hero", 1080, 100);
            }*/
            //g.drawString("泡泡数量:   "+String.valueOf(player.getBubbleNum()), 950, 180);
            g.drawString("泡泡威力:   " + String.valueOf(player.getbubblepower()), 950, 210);
            g.setFont(new Font("宋体", Font.BOLD, 18));
            //g.drawString("数量:  "+String.valueOf(player.getMagicBubbleCount()), 1030, 350);
            g.drawString("数量:  " + String.valueOf(player.getmagicPowerCount()), 1030, 430);
            g.drawString("数量:  "+String.valueOf(player.getmagicSaveCount()), 1030, 510);

            g.setFont(new Font("宋体", Font.BOLD, 24));
            g.drawString("泡泡威力:   " + String.valueOf(player2.getbubblepower()), 950, 110);
            g.setFont(new Font("宋体", Font.BOLD, 18));
            //g.drawString("数量:  "+String.valueOf(player.getMagicBubbleCount()), 1030, 350);
            g.drawString("数量:  " + String.valueOf(player2.getmagicPowerCount()), 1030, 280);
            g.drawString("数量:  "+String.valueOf(player2.getmagicSaveCount()), 1030, 360);

            int gameTime = GameThread.getGameTime()/1000;
            int minute = gameTime / 60;
            int seconds = gameTime % 60;
            String min = "0" + String.valueOf(minute);
            String sec;
            if(seconds < 10){
                sec = "0" + String.valueOf(seconds);
            }else {
                sec = String.valueOf(seconds);
            }
            g.setFont(new Font("Times New Roman", Font.BOLD, 36));
            g.drawString("Time: "+ min + ":" + sec, 950, 650);
            /*if(player.isDying()){
                g.setFont(new Font("Times New Roman", Font.BOLD, 24));
                g.setColor(Color.red);
                g.drawString("You are dying!!!", 950, 720);
                g.drawString("Time Remaining: "+ String.valueOf(player.getDyingTime()/1000) + "s", 950, 760);
                g.setColor(Color.BLACK);
            }*/
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