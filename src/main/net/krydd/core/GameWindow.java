package net.krydd.core;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    final static int WINDOW_WIDTH = 640;
    final static int WINDOW_HEIGHT = 480;

    public GameWindow() {
        setInitialValues();
    }

    private void setInitialValues() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setGameCanvas(GameCanvas gameCanvas) {
        getContentPane().add(gameCanvas);
    }
}
