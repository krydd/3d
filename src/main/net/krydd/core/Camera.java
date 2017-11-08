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

    public Vector getPlaneNormal() {
        return position.subtract(focus).getEntity();
        /*
        Matrix base = new Matrix(null);
        Matrix baseTranspose = base.transpose();
        return base.multiply(baseTranspose.multiply(base).inverse()).multiply(baseTranspose);
        */
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getRotation() {
        return rotation;
    }

    public void move(PlayerInput playerInput) {
        position = position.add(playerInput.getInputVelocity().scale(10));
    }
}
