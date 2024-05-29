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
        
        switch (code) {
            case ' ':
            case 87:
            case 83:
            case 65:
            case 68:
                if(playerList.size() > 0) {
                    Player player = (Player) playerList.get( 0 );
                    player.act(code);
                }
                break;
            case 77:
            case 37:
            case 38:
            case 39:
            case 40:if(playerList.size() > 1 ) {
                Player player = (Player) playerList.get( 1);
                player.act(code);
            }
            break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playerList = ObjectController.getObjController().getMap().get("player");
       
        int code = e.getKeyCode();
        switch (code) {
            case ' ':
            case 87:
            case 83:
            case 65:
            case 68:
                if(playerList.size() > 0) {
                    Player player = (Player) playerList.get( 0 );
                    player.setkeyrelease(true);
                }
                break;
            case 77:
            case 37:
            case 38:
            case 39:
            case 40:if(playerList.size() > 1 ) {
                Player player = (Player) playerList.get( 1);
                player.setkeyrelease(true);
            }
            break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
