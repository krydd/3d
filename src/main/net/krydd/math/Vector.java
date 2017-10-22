package net.krydd.math;


public class Vector {

    private double x;
    private double y;
    private double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector add(Vector vectorToAdd) {
        return new Vector(x + vectorToAdd.getX(), y + vectorToAdd.getY(), z + vectorToAdd.getZ());
    }

    public Vector subtract(Vector vectorToSubtract) {
        return new Vector(x - vectorToSubtract.getX(), y - vectorToSubtract.getY(), z - vectorToSubtract.getZ());
    }

    public Vector scale(double scalar) {
        return new Vector(x * scalar, y * scalar, z * scalar);
    }

    public Vector getEntity() {
        return scale(1.0 / length());
    }

    public Vector crossProduct(Vector other) {
        return new Vector(y*other.z - z*other.y, -(x*other.z - z*other.x), x*other.y - y*other.x);
    }

    public double product(Vector other) {
        return x*other.x + y*other.y + z*other.z;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z*z);
    }

    public Vector copy() {
        return new Vector(x, y, z);
    }
}
