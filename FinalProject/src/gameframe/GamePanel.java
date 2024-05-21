package gameframe;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class GamePanel extends JPanel {
    private Tile[][] tiles;
    private JLabel playerLabel;
    private JLayeredPane layeredPane;
    private int playerRow = 1;
    private int playerCol = 1;
    private static final int TILE_SIZE = 50; // 設定每個Tile的大小

    public GamePanel() {
        init();
    }

    private void init() {
        setLayout(null);

        // Create a layered pane to hold the map and player
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(100, 50, TILE_SIZE * 10, TILE_SIZE * 10);

        // Create the map using a GridLayout
        JPanel mapPanel = new JPanel(new GridLayout(10, 10, 0, 0));
        mapPanel.setBounds(0, 0, TILE_SIZE * 10, TILE_SIZE * 10); // Adjust the size as needed

        // Initialize the map with tiles
        tiles = new Tile[10][10];
        String[][] map = {
            {"images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png"},
            {"images/blue_house.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/blue_house.png"},
            {"images/blue_house.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/green_tile.png", "images/blue_house.png"},
            {"images/blue_house.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/blue_house.png"},
            {"images/blue_house.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/green_tile.png", "images/blue_house.png"},
            {"images/blue_house.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/blue_house.png"},
            {"images/blue_house.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/green_tile.png", "images/blue_house.png"},
            {"images/blue_house.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/green_tile.png", "images/blue_house.png"},
            {"images/blue_house.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/box.png", "images/green_tile.png", "images/green_tile.png", "images/blue_house.png"},
            {"images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png", "images/blue_house.png"}
        };

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                tiles[row][col] = new Tile(map[row][col], TILE_SIZE);
                mapPanel.add(tiles[row][col]);
            }
        }

        // Add the map panel to the layered pane
        layeredPane.add(mapPanel, JLayeredPane.DEFAULT_LAYER);

        // Initialize and place the player at the starting position
        initializePlayer();

        // Add the layered pane to the GamePanel
        add(layeredPane);

        // Add key listener for player movement
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                movePlayer(e.getKeyCode());
            }
        });

        // Set panel properties
        setVisible(true);
    }

    private void initializePlayer() {
        int playerIndex = 1; // This should be set based on your game's logic
        String playerImagePath = "images/character" + playerIndex + ".png";
        ImageIcon playerIcon = new ImageIcon(playerImagePath);
        Image playerImg = playerIcon.getImage();
        Image scaledImg = playerImg.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(scaledImg);
        
        playerLabel = new JLabel(playerIcon);
        playerLabel.setBounds(100 + playerCol * TILE_SIZE, 50 + playerRow * TILE_SIZE, TILE_SIZE, TILE_SIZE); // Set to the initial position
        layeredPane.add(playerLabel, JLayeredPane.PALETTE_LAYER);
    }

    private void movePlayer(int keyCode) {
        System.out.println("Key pressed: " + KeyEvent.getKeyText(keyCode)); // Add this line for debugging
    
        int newRow = playerRow;
        int newCol = playerCol;
    
        switch (keyCode) {
            case KeyEvent.VK_W:
                newRow--;
                break;
            case KeyEvent.VK_A:
                newCol--;
                break;
            case KeyEvent.VK_S:
                newRow++;
                break;
            case KeyEvent.VK_D:
                newCol++;
                break;
        }
    
        // Check boundaries and obstacles
        if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 10 && !tiles[newRow][newCol].getIcon().toString().contains("box.png")) {
            playerRow = newRow;
            playerCol = newCol;
            playerLabel.setBounds(100 + playerCol * TILE_SIZE, 50 + playerRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            repaint();
        }
    }
    
}
