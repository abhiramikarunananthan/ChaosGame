package no.ntnu.idatt2003.chaosgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Matrix2x2Test {

    Vector2D vector2d1;
    Vector2D vector2d2;
    Matrix2x2 matrix2x2;
    @BeforeEach
    void setUp() {
        vector2d1 = new Vector2D(3, 7);
        vector2d2 = new Vector2D(-6, 10);
        matrix2x2 = new Matrix2x2(3, 2, 4, 3);
    }

    @Test
    void multiply() {

        Vector2D vector2DExpected1 = new Vector2D(23, 33);
        Vector2D vector2DExpected2 = new Vector2D(2, 6);


        Vector2D vector2DProduct1 = matrix2x2.multiply(vector2d1);
        Vector2D vector2DProduct2 = matrix2x2.multiply(vector2d2);


        assertEquals(vector2DExpected1.getX0(), vector2DProduct1.getX0());
        assertEquals(vector2DExpected1.getX1(), vector2DProduct1.getX1());
        assertEquals(vector2DExpected2.getX0(), vector2DProduct2.getX0());
        assertEquals(vector2DExpected2.getX1(), vector2DProduct2.getX1());

    }
}