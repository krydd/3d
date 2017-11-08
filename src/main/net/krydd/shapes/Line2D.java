package net.krydd.shapes;

import net.krydd.math.Vector2D;

import java.awt.*;

import static net.krydd.core.GameCanvas.drawPoint;

public class Line2D {

    private final Vector2D start, end;
    private final double length;

    public Line2D(Vector2D start, Vector2D end) {
        this.start = start;
        this.end = end;
        length = start.subtract(end).length();
    }

    public void draw(Graphics g) {
        Vector2D lineVector = end.subtract(start);
        for (double d = 0; d <= length; d += .5d) {
            final Vector2D point = start.add(lineVector.scale(d / length));
            drawPoint(g, (int) point.getX(), (int) point.getY());
        }
    }
}
