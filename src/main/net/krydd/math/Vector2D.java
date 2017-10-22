package net.krydd.math;


public class Vector2D {

    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector2D add(Vector2D vectorToAdd) {
        return new Vector2D(x + vectorToAdd.getX(), y + vectorToAdd.getY());
    }

    public Vector2D subtract(Vector2D vectorToSubtract) {
        return new Vector2D(x - vectorToSubtract.getX(), y - vectorToSubtract.getY());
    }

    public Vector2D scale(double scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }

    public Vector2D getEntity() {
        return scale(1.0 / length());
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D copy() {
        return new Vector2D(x, y);
    }
}
