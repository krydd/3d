package net.krydd.core;

import net.krydd.shapes.Line;
import net.krydd.math.Vector;
import net.krydd.math.Vector2D;
import net.krydd.shapes.Line2D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameEngine implements Runnable {
    private static final int MAX_FPS = 60;

    private final PlayerInput playerInput;
    private final GameCanvas canvas;
    private final Camera camera;
    private final List<Line2D> objects = new ArrayList<>();

    public GameEngine(PlayerInput playerInput) {
        this.playerInput = playerInput;
        canvas = new GameCanvas(this);
        camera = new Camera(new Vector(0, 0, 500));
    }

    public void initGame() {
    }

    @Override
    public void run() {
        playerInput.run();

        List<Line> obj3d = new ArrayList<>();
        obj3d.add(new Line(new Vector(50, 50, 0), new Vector(100, 100, 0)));

        long start;
        while (!playerInput.quit()) {
            start = System.currentTimeMillis();
            objects.clear();
            camera.move();

            // Calc plane from camera
            // Project objects on plane
            // Draw projected objects

            objects.add(new Line2D(new Vector2D(0, 0), new Vector2D(50, 50)));

            canvas.repaint();

            sleep(System.currentTimeMillis() - start);
        }
        System.exit(1);
    }

    private void sleep(long elapsed) {
        final long sleepTime = 1000 / MAX_FPS - elapsed;
        if (sleepTime <= 0) {
            return;
        }
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public GameCanvas getCanvas() {
        return canvas;
    }

    public Collection<Line2D> getObjects() {
        return objects;
    }
}
