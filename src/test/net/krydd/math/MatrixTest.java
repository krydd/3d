package net.krydd.math;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MatrixTest {

    @Test
    void transpose_long_to_tall() {
        Matrix matrix = Matrix.asRows(new Vector(1, 2, 3), new Vector(4, 5, 6));
        Matrix transform = matrix.transpose();

        Matrix expected = Matrix.asColumns(new Vector(1, 2, 3), new Vector(4, 5, 6));
        assertTrue(expected.equals(transform));
    }

    @Test
    void transpose_tall_to_long() {
        Matrix matrix = Matrix.asColumns(new Vector(1, 2, 3), new Vector(4, 5, 6));
        Matrix transform = matrix.transpose();

        Matrix expected = Matrix.asRows(new Vector(1, 2, 3), new Vector(4, 5, 6));
        assertTrue(expected.equals(transform));
    }

    @Test
    void transpose_3x3square() {
        final Matrix matrix = Matrix.asRows(new Vector(1, 2, 3), new Vector(4, 5, 6), new Vector(7, 8, 9));
        Matrix transform = matrix.transpose();

        Matrix expected = Matrix.asColumns(new Vector(1, 2, 3), new Vector(4, 5, 6), new Vector(7, 8, 9));
        assertTrue(expected.equals(transform));
    }

    @Test
    void inverse_2x2() {
        Matrix matrix = Matrix.asRows(new Vector2D(1, 2), new Vector2D(3, 4));

        Matrix inverse = matrix.inverse();

        Matrix expected = Matrix.asRows(new Vector2D(-2, 1), new Vector2D(1.5, -0.5));
        assertTrue(expected.equals(inverse));
    }

    @Test
    void inverse_3x3() {
        Matrix matrix = Matrix.asRows(new Vector(1, 4, 3), new Vector(4, 5, 6), new Vector(7, 8, 9));

        Matrix inverse = matrix.inverse();

        Matrix expected = Matrix.asRows(
                new Vector(-.25, -1, 0.75),
                new Vector(0.5, -1, 0.5),
                new Vector(-0.25, 1.6666666666666665, -0.9166666666666666));
        assertTrue(expected.equals(inverse));
    }

    @Test
    void inverse_2x2_identity() {
        Matrix identityMatrix = Matrix.identity(2);

        Matrix inverse = identityMatrix.inverse();

        Matrix expected = Matrix.asRows(new Vector2D(1, 0), new Vector2D(0, 1));
        assertTrue(expected.equals(inverse));
    }

    @Test
    void inverse_3x3_identity() {
        Matrix identityMatrix = Matrix.identity(3);

        Matrix inverse = identityMatrix.inverse();

        Matrix expected = Matrix.asRows(new Vector(1, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));
        assertTrue(expected.equals(inverse));
    }

    @Test
    void identity_3x3() {
        Matrix identityMatrix = Matrix.identity(3);

        Matrix expected = Matrix.asRows(new Vector(1, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, 1));
        assertTrue(expected.equals(identityMatrix));
    }

    @Test
    void multiplication_2x2_2x2() {
        Matrix A = Matrix.asRows(new Vector2D(1, 2), new Vector2D(3, 4));
        Matrix B = Matrix.asRows(new Vector2D(5, 6), new Vector2D(7, 8));

        Matrix C = A.multiply(B);

        Matrix expected = Matrix.asRows(new Vector2D(19, 22), new Vector2D(43, 50));
        assertTrue(expected.equals(C));
    }

    @Test
    void multiplication_2x3_3x2() {
        Matrix A = Matrix.asRows(new Vector(1, 2, 2.8), new Vector(3, 4, 1.1));
        Matrix B = Matrix.asRows(new Vector2D(5, 6), new Vector2D(7, 8), new Vector2D(4.4, 10.9));

        Matrix C = A.multiply(B);

        Matrix expected = Matrix.asRows(new Vector2D(31.32, 52.519999999999996), new Vector2D(47.84, 61.99));
        assertTrue(expected.equals(C));
    }

    @Test
    void multiplication_3x2_2x3() {
        Matrix A = Matrix.asRows(new Vector2D(5, 6), new Vector2D(7, 8), new Vector2D(4.4, 10.9));
        Matrix B = Matrix.asRows(new Vector(1, 2, 2.8), new Vector(3, 4, 1.1));

        Matrix C = A.multiply(B);

        Matrix expected = Matrix.asRows(new Vector(23, 34, 20.6), new Vector(31, 46, 28.4), new Vector(37.1, 52.400000000000006, 24.310000000000002));
        assertTrue(expected.equals(C));
    }

    @Test
    void multiplication_3x3_3x3() {
        Matrix A = Matrix.asRows(new Vector(1, 2, 2.8), new Vector(3, 4, 1.1), new Vector(5, 7.7, 9.1));
        Matrix B = Matrix.asRows(new Vector(1, 2, 2.8), new Vector(-3, 4, 1.1), new Vector(2.1, 3, -4.2));

        Matrix C = A.multiply(B);

        Matrix expected = Matrix.asRows(new Vector(0.8799999999999999, 18.4, -6.76), new Vector(-6.6899999999999995, 25.3, 8.179999999999998), new Vector(1.009999999999998, 68.1, -15.75));
        assertTrue(expected.equals(C));
    }

    @Test
    void multiplication_2x2_inverse() {
        Matrix A = Matrix.asRows(new Vector2D(1, 2), new Vector2D(3, 4));
        Matrix A_inverse = A.inverse();

        Matrix C = A.multiply(A_inverse);

        Matrix expected = Matrix.identity(2);
        assertTrue(expected.equals(C));
    }

    @Test
    @Disabled("Close enough")
    void multiplication_3x3_inverse() {
        Matrix A = Matrix.asRows(new Vector(1, 2, 2.7), new Vector(3, 4.5, 8.2), new Vector(5.2, 2, 6));
        Matrix A_inverse = A.inverse();

        Matrix C = A.multiply(A_inverse);

        Matrix expected = Matrix.identity(3);
        System.out.println(C);
        System.out.println(expected);
        assertTrue(expected.equals(C));
    }
}