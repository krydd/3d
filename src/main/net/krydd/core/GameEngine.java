package net.krydd.core;

import net.krydd.shapes.Line;
import net.krydd.math.Vector;
import net.krydd.math.Vector2D;
import net.krydd.shapes.Line2D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

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
        obj3d.add(new Line(new Vector(-50, 0, 0), new Vector(50, 0, 0)));
        obj3d.add(new Line(new Vector(-50, 0, 0), new Vector(-50, 125, 0)));
        obj3d.add(new Line(new Vector(50, 0, 0), new Vector(50, 125, 0)));
        obj3d.add(new Line(new Vector(-50, 125, 0), new Vector(50, 125, 0)));

        obj3d.add(new Line(new Vector(-50, 0, 100), new Vector(50, 0, 100)));
        obj3d.add(new Line(new Vector(-50, 0, 100), new Vector(-50, 125, 100)));
        obj3d.add(new Line(new Vector(50, 0, 100), new Vector(50, 125, 100)));
        obj3d.add(new Line(new Vector(-50, 125, 100), new Vector(50, 125, 100)));

        obj3d.add(new Line(new Vector(-50, 0, 0), new Vector(-50, 0, 100)));
        obj3d.add(new Line(new Vector(50, 0, 0), new Vector(50, 0, 100)));
        obj3d.add(new Line(new Vector(-50, 125, 0), new Vector(-50, 125, 100)));
        obj3d.add(new Line(new Vector(50, 125, 0), new Vector(50, 125, 100)));

        long start;
        int tick = 0;
        while (!playerInput.quit()) {
            start = System.currentTimeMillis();
            objects.clear();
            camera.move(playerInput);
            Vector planeNormal = camera.getPlaneNormal();

            obj3d.stream()
                    .map(line -> new Line2D(project(line.getStart(), planeNormal), project(line.getEnd(), planeNormal)))
                    .forEach(objects::add);

            canvas.repaint();

            sleep(System.currentTimeMillis() - start);

            int fps = (int) (1000 / (System.currentTimeMillis() - start));
            if (tick % 20 == 0) {
                System.out.println(fps);
            }

            ++tick;
            if (tick == 1000) {
                tick = 0;
            }
        }
        System.exit(1);
    }

    private Vector2D project(Vector vector, Vector planeNormal) {
        final Vector projection = vector.subtract(planeNormal.scale(vector.product(planeNormal)));
        return new Vector2D(projection.getX(), projection.getY());
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
