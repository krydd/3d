package net.krydd.math;

import java.util.Arrays;

public class Matrix {
    private final double[][] matrix;

    public static Matrix identity(int size) {
        double[][] matrix = new double[size][size];
        for (int row = 0; row < size; ++row) {
            for (int col = 0; col < size; ++col) {
                matrix[row][col] = row == col ? 1 : 0;
            }
        }
        return new Matrix(matrix);
    }

    public static Matrix asRows(Vector2D... vectors) {
        double[][] matrix = new double[vectors.length][2];
        int row = 0;
        for (Vector2D v : vectors) {
            matrix[row][0] = v.getX();
            matrix[row][1] = v.getY();
            row++;
        }
        return new Matrix(matrix);
    }

    public static Matrix asColumns(Vector2D... vectors) {
        double[][] matrix = new double[2][vectors.length];
        int col = 0;
        for (Vector2D v : vectors) {
            matrix[0][col] = v.getX();
            matrix[1][col] = v.getY();
            col++;
        }
        return new Matrix(matrix);
    }

    public static Matrix asRows(Vector... vectors) {
        double[][] matrix = new double[vectors.length][3];
        int row = 0;
        for (Vector v : vectors) {
            matrix[row][0] = v.getX();
            matrix[row][1] = v.getY();
            matrix[row][2] = v.getZ();
            row++;
        }
        return new Matrix(matrix);
    }

    public static Matrix asColumns(Vector... vectors) {
        double[][] matrix = new double[3][vectors.length];
        int col = 0;
        for (Vector v : vectors) {
            matrix[0][col] = v.getX();
            matrix[1][col] = v.getY();
            matrix[2][col] = v.getZ();
            col++;
        }
        return new Matrix(matrix);
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public int getNumColumns() {
        return matrix[0].length;
    }

    public int getNumRows() {
        return matrix.length;
    }

    public Matrix multiply(Matrix other) {
        if (getNumColumns() != other.getNumRows()) {
            throw new RuntimeException("This n != that m: " + getNumColumns() + ", " + other.getNumRows());
        }
        double[][] matrix = new double[getNumRows()][other.getNumColumns()];
        for (int row = 0; row < getNumRows(); ++row) {
            for (int col = 0; col < other.getNumColumns(); ++col) {
                matrix[row][col] = multAndAdd(getRow(row), other.getCol(col));
            }
        }

        return new Matrix(matrix);
    }

    private double multAndAdd(double[] row, double[] col) {
        if (row.length != col.length) {
            throw new RuntimeException("row and col are not of same length. Row length: " + row.length + ", col length: " + col.length);
        }
        double sum = 0.0;
        for (int i = 0; i < row.length; ++i) {
            sum += row[i]*col[i];
        }
        return sum;
    }

    private double[] getCol(int col) {
        double[] colValues = new double[getNumRows()];
        for (int row = 0; row < getNumRows(); ++row) {
            colValues[row] = matrix[row][col];
        }
        return colValues;
    }

    private double[] getRow(int row) {
        return matrix[row].clone();
    }

    public Matrix scale(double scalar) {
        double[][] matrix = this.matrix.clone();
        for (int row = 0; row < getNumRows(); ++row) {
            for (int col = 0; col < getNumColumns(); ++col) {
                matrix[row][col] = matrix[row][col]*scalar;
            }
        }
        return new Matrix(matrix);
    }

    public Matrix inverse() {
        if (determinant() == 0) {
            throw new RuntimeException("Can not inverse, determinant is zero");
        }

        double factor = 1/determinant();
        if (isSquareOf(2)) {
            return Matrix.asRows(new Vector2D(matrix[1][1], -matrix[0][1] + 0.0d), new Vector2D(-matrix[1][0] + 0.0d, matrix[0][0])).scale(factor);
        } else if (isSquareOf(3)) {
            return Matrix.asRows(
                    new Vector(matrix[1][1]*matrix[2][2] - matrix[1][2]*matrix[2][1] + 0.0d, -(matrix[0][1]*matrix[2][2] - matrix[0][2]*matrix[2][1]) + 0.0d, matrix[0][1]*matrix[1][2] - matrix[0][2]* matrix[1][1] + 0.0d),
                    new Vector(-(matrix[1][0]*matrix[2][2] - matrix[1][2]*matrix[2][0]) + 0.0d, matrix[0][0]*matrix[2][2] - matrix[0][2]*matrix[2][0] + 0.0d, -(matrix[0][0]*matrix[1][2] - matrix[0][2]*matrix[1][0]) + 0.0d),
                    new Vector(matrix[1][0]*matrix[2][1] - matrix[1][1]*matrix[2][0] + 0.0d, -(matrix[0][0]*matrix[2][1] - matrix[0][1]*matrix[2][0]) + 0.0d, matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0] + 0.0d)
            ).scale(factor);
        }

        throw new RuntimeException("Matrix is not 2- or 3-square matrix, can't inverse: m=" + getNumRows() + ", n=" + getNumColumns());
    }

    public double determinant() {
        if (isSquareOf(2)) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else if (isSquareOf(3)) {
            return matrix[0][0] * matrix[1][1] * matrix[2][2]
                    - matrix[0][0] * matrix[1][2] * matrix[2][1]
                    - matrix[0][1] * matrix[1][0] * matrix[2][2]
                    + matrix[0][1] * matrix[1][2] * matrix[2][0]
                    + matrix[0][2] * matrix[1][0] * matrix[2][1]
                    - matrix[0][2] * matrix[1][1] * matrix[2][0];
        }
        throw new RuntimeException("Matrix is not 2- or 3-square matrix, can't calculate determinant: m=" + getNumRows() + ", n=" + getNumColumns());
    }

    private boolean isSquareOf(int size) {
        return size == getNumColumns() && size == getNumRows();
    }

    public Matrix transpose() {
        double[][] matrix = new double[getNumColumns()][getNumRows()];
        for (int row = 0; row < getNumRows(); ++row) {
            for (int col = 0; col < getNumColumns(); ++col) {
                matrix[col][row] = this.matrix[row][col];
            }
        }
        return new Matrix(matrix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix1 = (Matrix) o;

        return Arrays.deepEquals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }

    @Override
    public String toString() {
        String out = "";
        for (int x = 0; x < getNumRows(); ++x) {
            out += "[";
            for (int y = 0; y < getNumColumns(); ++y) {
                out += y == 0 ? "" : " ";
                out += matrix[x][y];
            }
            out += "]\n";
        }
        return out;
    }
}
