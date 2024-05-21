package main;
import gameframe.GameFrame;
import javax.swing.*;
public class StartGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
