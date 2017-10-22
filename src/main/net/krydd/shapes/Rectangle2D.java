package net.krydd.shapes;

import net.krydd.math.Vector2D;

import java.awt.*;

public class Rectangle2D {
    private final Line2D top;
    private final Line2D left;
    private final Line2D right;
    private final Line2D bottom;

    public Rectangle2D(Vector2D upperLeft, Vector2D lowerRight) {
        top = new Line2D(upperLeft, new Vector2D(lowerRight.getX(), upperLeft.getY()));
        left = new Line2D(new Vector2D(upperLeft.getX(), lowerRight.getY()), upperLeft);
        right = new Line2D(new Vector2D(lowerRight.getX(), upperLeft.getY()), lowerRight);
        bottom = new Line2D(lowerRight, new Vector2D(upperLeft.getX(), lowerRight.getY()));
    }

    public void draw(Graphics g) {
        top.draw(g);
        left.draw(g);
        right.draw(g);
        bottom.draw(g);
    }
}
