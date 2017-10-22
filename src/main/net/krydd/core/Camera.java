package net.krydd.core;

import net.krydd.math.Vector;

public class Camera {
    private Vector position;
    private Vector rotation;
    private final Vector focus = new Vector(0, 0, 0);

    public Camera(Vector position) {
        this.position = position;
        rotation = new Vector(0, 1, 0);
    }

    public void getPlane() {

    }

    public Vector getPosition() {
        return position;
    }

    public Vector getRotation() {
        return rotation;
    }

    public void move() {

    }
}
