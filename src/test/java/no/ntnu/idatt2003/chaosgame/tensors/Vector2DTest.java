package no.ntnu.idatt2003.chaosgame.tensors;

import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {
    Vector2D vector2d1;
    Vector2D vector2d2;
    @BeforeEach
    void setUp() {
        vector2d1 = new Vector2D(3, 7);
        vector2d2 = new Vector2D(-6, 10);
    }

    @Test
    void getX0() {
        double x0 = vector2d1.getX0();

        assertEquals(3, x0);
    }

    @Test
    void getX1() {
        double x1 = vector2d2.getX1();

        assertEquals(10, x1);
    }

    @Test
    void add() {
        Vector2D vectorExpected = new Vector2D(-3, 17);

        Vector2D vectorSum = vector2d1.add(vector2d2);

        assertEquals(vectorExpected.getX0(), vectorSum.getX0());
        assertEquals(vectorExpected.getX1(), vectorSum.getX1());


    }

    @Test
    void subtract() {
        Vector2D vectorExpected = new Vector2D(9, -3);

        Vector2D vectorSum = vector2d1.subtract(vector2d2);

        assertEquals(vectorExpected.getX0(), vectorSum.getX0());
        assertEquals(vectorExpected.getX1(), vectorSum.getX1());
    }
}