package gameframe;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class InitialPanel extends JPanel {
    public static int playerIndex;
    private JLabel startLabel;
    private JLabel playerLabel;
    private JButton introButton;
    private JButton selectButton;
    private JButton ensureButton;
    private JButton changeButton;
    private JButton startButton;
    private JLayeredPane layeredPane;
    private ImageIcon playerIcon;

    public InitialPanel() {
        init();
    }

    private void init() {
        playerIndex = 1;

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 600);

        ImageIcon startBg = new ImageIcon("src/images/str_bg.jpeg");
        Image img = startBg.getImage();
        Image scaledImg = img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        startBg = new ImageIcon(scaledImg);

        startLabel = new JLabel(startBg);
        startLabel.setBounds(0, 0, 800, 600);
        startLabel.setVisible(true);

        ImageIcon introducebuttIcon = new ImageIcon("images/introduce.png");
        Image intro_butt_img = introducebuttIcon.getImage();
        Image intro_scaledImg = intro_butt_img.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        introducebuttIcon = new ImageIcon(intro_scaledImg);

        introButton = new JButton(introducebuttIcon);
        introButton.setBounds(200, 250, 200, 100);
        introButton.setBorderPainted(false);
        introButton.setFocusPainted(false);
        introButton.setContentAreaFilled(false);
        introButton.setVisible(true);

        ImageIcon selectbuttIcon = new ImageIcon("images/select.png");
        Image select_butt_img = selectbuttIcon.getImage();
        Image select_scaledImg = select_butt_img.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        selectbuttIcon = new ImageIcon(select_scaledImg);

        selectButton = new JButton(selectbuttIcon);
        selectButton.setBounds(200, 400, 200, 100);
        selectButton.setBorderPainted(false);
        selectButton.setFocusPainted(false);
        selectButton.setContentAreaFilled(false);
        selectButton.setVisible(true);
        selectButton.addActionListener(e -> selectButtonActionPerformed());

        ImageIcon changebuttIcon = new ImageIcon("images/change.png");
        Image change_butt_img = changebuttIcon.getImage();
        Image change_scaledImg = change_butt_img.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        changebuttIcon = new ImageIcon(change_scaledImg);

        changeButton = new JButton(changebuttIcon);
        changeButton.setBounds(450, 400, 200, 100);
        changeButton.setBorderPainted(false);
        changeButton.setFocusPainted(false);
        changeButton.setContentAreaFilled(false);
        changeButton.setVisible(false);
        changeButton.addActionListener(e -> changePlayerActionPerformed());

        ImageIcon ensurebuttIcon = new ImageIcon("images/check.png");
        Image ensure_butt_img = ensurebuttIcon.getImage();
        Image ensure_scaledImg = ensure_butt_img.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        ensurebuttIcon = new ImageIcon(ensure_scaledImg);

        ensureButton = new JButton(ensurebuttIcon);
        ensureButton.setBounds(650, 400, 200, 100);
        ensureButton.setBorderPainted(false);
        ensureButton.setFocusPainted(false);
        ensureButton.setContentAreaFilled(false);
        ensureButton.setVisible(false);
        ensureButton.addActionListener(e -> ensureButtonActionPerformed());

        ImageIcon startbuttIcon = new ImageIcon("images/start-button.png");
        Image start_butt_img = startbuttIcon.getImage();
        Image start_scaledImg = start_butt_img.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        startbuttIcon = new ImageIcon(start_scaledImg);

        startButton = new JButton(startbuttIcon);
        startButton.setBounds(200, 150, 200, 100);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setVisible(true);
        startButton.addActionListener(e -> startButtonActionPerformed());

        playerIcon = new ImageIcon("images/character1.png");
        Image player_img = playerIcon.getImage();
        Image player_scaledImg = player_img.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(player_scaledImg);
        playerLabel = new JLabel(playerIcon);
        playerLabel.setBounds(500, 200, 200, 100);
        playerLabel.setVisible(false);

        layeredPane.add(startLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(introButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(selectButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(changeButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(ensureButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(playerLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);

        this.setLayout(null);
        this.add(layeredPane);

        this.setVisible(true);
        this.setOpaque(true);
    }

    private void selectButtonActionPerformed() {
        changeButton.setVisible(true);
        ensureButton.setVisible(true);
        playerLabel.setVisible(true);
        layeredPane.repaint();
        layeredPane.revalidate();
    }

    private void changePlayerActionPerformed() {
        playerIndex = playerIndex + 1;
        if (playerIndex > 2) {
            playerIndex = 1;
        }
        String playerImagePath = "images/character" + playerIndex + ".png";
        ImageIcon newPlayerIcon = new ImageIcon(playerImagePath);
        Image player_img = newPlayerIcon.getImage();
        Image player_scaledImg = player_img.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        newPlayerIcon.setImage(player_scaledImg);
        playerIcon.setImage(player_scaledImg);
        playerLabel.repaint();
        layeredPane.repaint();
        layeredPane.revalidate();
    }

    private void ensureButtonActionPerformed() {
        changeButton.setVisible(false);
        ensureButton.setVisible(false);
        playerLabel.setVisible(false);
        layeredPane.repaint();
        layeredPane.revalidate();
    }

    private void startButtonActionPerformed() {
        GameFrame parentFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
        parentFrame.switchToGamePanel();
    }
}
