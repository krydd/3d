package net.krydd.shapes;

import net.krydd.math.Vector;

public class Line {
    private final Vector start;
    private final Vector end;

    public Line(Vector start, Vector end) {
        this.start = start;
        this.end = end;
    }

    public Vector getStart() {
        return start;
    }

    public Vector getEnd() {
        return end;
    }
}
