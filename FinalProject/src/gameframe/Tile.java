package gameframe;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tile extends JLabel {
    private ImageIcon icon;

    public Tile(String imagePath, int tileSize) {
        icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH));
        setIcon(icon);
        setSize(tileSize, tileSize);
    }
}
