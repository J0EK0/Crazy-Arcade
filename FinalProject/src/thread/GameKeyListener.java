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
    public void keyPressed(KeyEvent event){
        playerList = ObjectController.getObjController().getMap().get("player");
        Player player = (Player) playerList.get(0);

        char code = event.getKeyChar();

        switch (code) {
            case 'w':
                break;
            case 's':
                break;
            case 'a':
                break;
            case 'd':
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playerList = ObjectController.getObjController().getMap().get("player");
        Player player = (Player) playerList.get(0);

        char code = e.getKeyChar();
        player.move(code);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
