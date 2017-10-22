package net.krydd.core;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    private final GameEngine engine;

    public GameCanvas(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void paint(Graphics g) {
        clear(g);

        g.setColor(Color.BLUE);
        engine.getObjects().forEach(o -> o.draw(g));

        /*new Line2D(new Vector2D(50d, 100d), new Vector2D(300d,300d)).draw(g);
        new Line2D(new Vector2D(50d, 100d), new Vector2D(55d,300d)).draw(g);

        new Rectangle2D(new Vector2D(125, 100), new Vector2D(300, 400)).draw(g);*/
    }

    public static void drawPoint(Graphics g, int x, int y) {
        g.drawLine(x + GameWindow.WINDOW_WIDTH/2, y + GameWindow.WINDOW_HEIGHT/2, x + GameWindow.WINDOW_WIDTH/2, y + GameWindow.WINDOW_HEIGHT/2);
    }

    private void clear(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GameWindow.WINDOW_WIDTH, GameWindow.WINDOW_HEIGHT);
    }
}
