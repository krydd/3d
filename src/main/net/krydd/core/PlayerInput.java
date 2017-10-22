package net.krydd.core;

import net.krydd.math.Vector2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInput extends Component implements Runnable, KeyListener {

    private boolean quit;
    private boolean firing;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    public PlayerInput() {
        quit = false;
        firing = false;
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public Vector2D getInputVelocity() {
        return new Vector2D(getDeltaX(), getDeltaY());
    }

    private double getDeltaX() {
        double deltaX = 0.0;
        if (left) {
            deltaX -= 0.1;
        }
        if (right) {
            deltaX += 0.1;
        }
        return deltaX;
    }

    private double getDeltaY() {
        double deltaY = 0.0;
        if (up) {
            deltaY -= 0.1;
        }
        if (down) {
            deltaY += 0.1;
        }
        return deltaY;
    }

    public boolean isFiring() {
        return firing;
    }

    public boolean quit() {
        return quit;
    }

    @Override
    public void run() {
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            firing = true;
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            quit = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            firing = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        }
    }
}
