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
            {"src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png"},
            {"src/images/blue_house.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/blue_house.png"},
            {"src/images/blue_house.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/blue_house.png"},
            {"src/images/blue_house.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/blue_house.png"},
            {"src/images/blue_house.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/blue_house.png"},
            {"src/images/blue_house.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/blue_house.png"},
            {"src/images/blue_house.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/blue_house.png"},
            {"src/images/blue_house.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/blue_house.png"},
            {"src/images/blue_house.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/green_tile.png", "src/images/blue_house.png"},
            {"src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png", "src/images/blue_house.png"}
        };

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                tiles[row][col] = new Tile(map[row][col], TILE_SIZE);
                mapPanel.add(tiles[row][col]);
            }
        }

        // Add the map panel to the layered pane
        layeredPane.add(mapPanel, JLayeredPane.DEFAULT_LAYER);

        // Initialize and place the boxes at their positions
        initializeBoxes();

        // Initialize and place the player at the starting position
        initializePlayer();

        // Add the layered pane to the GamePanel
        add(layeredPane);

        // Add key listener for player movement
        setFocusable(true);
        requestFocusInWindow(); // Ensure the panel has focus to receive key events
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
        String playerImagePath = "src/images/character" + playerIndex + ".png";
        ImageIcon playerIcon = new ImageIcon(playerImagePath);
        Image playerImg = playerIcon.getImage();
        Image scaledImg = playerImg.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(scaledImg);

        playerLabel = new JLabel(playerIcon);
        playerLabel.setBounds(playerCol * TILE_SIZE, playerRow * TILE_SIZE, TILE_SIZE, TILE_SIZE); // Set to the initial position
        layeredPane.add(playerLabel, JLayeredPane.PALETTE_LAYER);
    }

    private void initializeBoxes() {
        int[][] boxPositions = {
            {2, 2}, {2, 4}, {2, 6},
            {4, 2}, {4, 4}, {4, 6},
            {6, 2}, {6, 4}, {6, 6},
            {8, 2}, {8, 4}, {8, 6}
        };
        for (int[] pos : boxPositions) {
            JLabel boxLabel = createBoxLabel();
            boxLabel.setBounds(pos[1] * TILE_SIZE, pos[0] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            layeredPane.add(boxLabel, JLayeredPane.PALETTE_LAYER);
        }
    }

    private JLabel createBoxLabel() {
        String boxImagePath = "src/images/box.png";
        ImageIcon boxIcon = new ImageIcon(boxImagePath);
        Image boxImg = boxIcon.getImage();
        Image scaledImg = boxImg.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
        boxIcon = new ImageIcon(scaledImg);
        return new JLabel(boxIcon);
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
        if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 10 && !isBoxAt(newRow, newCol)) {
            playerRow = newRow;
            playerCol = newCol;
            playerLabel.setBounds(playerCol * TILE_SIZE, playerRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            repaint();
        }
    }

    private boolean isBoxAt(int row, int col) {
        String tileIcon = tiles[row][col].getIcon().toString();
        if (tileIcon.contains("box.png") || tileIcon.contains("blue_house.png")) {
            return true;
        }

        int[][] boxPositions = {
            {2, 2}, {2, 4}, {2, 6},
            {4, 2}, {4, 4}, {4, 6},
            {6, 2}, {6, 4}, {6, 6},
            {8, 2}, {8, 4}, {8, 6},
        };
        for (int[] pos : boxPositions) {
            if (pos[0] == row && pos[1] == col) {
                return true;
            }
        }
        return false;
    }
}
