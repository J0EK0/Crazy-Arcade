package gameframe;

import controller.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.*;
import model.gameobject.SuperObject;

public class GamePanel extends JPanel implements Runnable{
    private boolean running;
    public GamePanel() {
        super();
        init();
        running = true;
    }

    private void init(){

    }

    public void paint(Graphics g){
        super.paint(g);
        Gamepaint(g);
    }

    @Override
    public void run() {
        this.repaint();
        /*while (running){
            try{
                Thread.sleep(20);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(GameController.isGameRunning()){
                this.repaint();
            }
        }*/
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