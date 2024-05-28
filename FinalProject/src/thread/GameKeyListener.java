package thread;

import controller.ObjectController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import model.gamecharacter.Player;
import model.gameobject.SuperObject;

public class GameKeyListener implements KeyListener{
   private List<SuperObject> playerList;

    @Override
    public void keyPressed(KeyEvent e){
        playerList = ObjectController.getObjController().getMap().get("player");

        int code = e.getKeyCode();
        for(int num = 0; num < playerList.size(); num++){
            Player player = (Player) playerList.get(num);
            player.act(code);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playerList = ObjectController.getObjController().getMap().get("player");
       
        int code = e.getKeyCode();
        for(int num = 0; num < playerList.size(); num++){
            Player player = (Player) playerList.get(num);
            player.act(code * 10);

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
