package no.ntnu.idatt2003.chaosgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AffineTransform2DTest {

    Vector2D vector2d1;
    Vector2D vector2d2;
    Matrix2x2 matrix2x2;
    AffineTransform2D affineTransform2D;
    @BeforeEach
    void setUp() {
        vector2d1 = new Vector2D(3, 1);
        vector2d2 = new Vector2D(1, 2);
        matrix2x2 = new Matrix2x2(0.5, 1, 1, 0.5);
        affineTransform2D = new AffineTransform2D(matrix2x2, vector2d1);
    }


    @Test
    void transform() {

        Vector2D vector2DExpected = new Vector2D(5.5,3);


        Vector2D vector2DTransformed = affineTransform2D.transform(vector2d2);


        assertEquals(vector2DExpected.getX0(), vector2DTransformed.getX0());
        assertEquals(vector2DExpected.getX1(), vector2DTransformed.getX1());

    }
}