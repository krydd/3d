package net.krydd.math;

public class Matrix {
    private final double[][] matrix;

    public Matrix(Vector... vectors) {
        matrix = new double[vectors.length][3];
        int x = 0;
        for (Vector v : vectors) {
            matrix[x][0] = v.getX();
            matrix[x][1] = v.getY();
            matrix[x][2] = v.getZ();
            x++;
        }
    }

    public int getNumColumns() {
        return matrix.length;
    }

    public int getNumRows() {
        return matrix[0].length;
    }

    public Matrix multiply(Matrix other) {
        if (getNumColumns() != other.getNumRows()) {
            throw new RuntimeException("This n != that m: " + getNumColumns() + ", " + other.getNumRows());
        }
        return null;
    }

    public Matrix inverse() {
        return null;
    }

    public Matrix transform() {
        return null;
    }
}
