package no.ntnu.idatt2003.chaosgame.tensors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {

    private Vector2D vector2DTest1;
    private Vector2D vector2DTest2;
    private Vector2D vector2DTest3;
    private Vector2D vector2DTestNull;

    @BeforeEach
    void setUp() {
        vector2DTest1 = new Vector2D(1,7);
        vector2DTest2 = new Vector2D(2,-3);
        vector2DTest3 = new Vector2D(-4,0);
        vector2DTestNull = null;

    }

    @Test
    void getX0() {
        double vector1X0 = vector2DTest1.getX0();
        double vector2X0 = vector2DTest2.getX0();
        double vector3X0 = vector2DTest3.getX0();

        Assertions.assertEquals(1,vector1X0);
        Assertions.assertNotEquals(7,vector1X0);

        Assertions.assertEquals(2,vector2X0);
        Assertions.assertNotEquals(-3,vector2X0);

        Assertions.assertEquals(-4,vector3X0);
        Assertions.assertNotEquals(0,vector3X0);

        Assertions.assertThrows(NullPointerException.class,() -> vector2DTestNull.getX0());

    }

    @Test
    void getX1() {
        double vector1X1 = vector2DTest1.getX1();
        double vector2X1 = vector2DTest2.getX1();
        double vector3X1 = vector2DTest3.getX1();


        Assertions.assertEquals(7,vector1X1);
        Assertions.assertNotEquals(1,vector1X1);

        Assertions.assertEquals(-3,vector2X1);
        Assertions.assertNotEquals(2,vector2X1);

        Assertions.assertEquals(0,vector3X1);
        Assertions.assertNotEquals(-4,vector3X1);

        Assertions.assertThrows(NullPointerException.class,() -> vector2DTestNull.getX1());
    }

    @Test
    void add() {
        Vector2D vector1PlusVector2 = vector2DTest1.add(vector2DTest2);
        Vector2D vector2PlusVector1 = vector2DTest2.add(vector2DTest1);

        Vector2D vector1PlusVector3 = vector2DTest1.add(vector2DTest3);
        Vector2D vector3PlusVector1 = vector2DTest3.add(vector2DTest1);

        Vector2D vector2PlusVector3 = vector2DTest2.add(vector2DTest3);
        Vector2D vector3PlusVector2 = vector2DTest3.add(vector2DTest2);

        Assertions.assertEquals(3,vector1PlusVector2.getX0());
        Assertions.assertEquals(4,vector1PlusVector2.getX1());

        Assertions.assertEquals(3,vector2PlusVector1.getX0());
        Assertions.assertEquals(4,vector2PlusVector1.getX1());

        Assertions.assertEquals(-3,vector1PlusVector3.getX0());
        Assertions.assertEquals(7,vector1PlusVector3.getX1());

        Assertions.assertEquals(-3,vector3PlusVector1.getX0());
        Assertions.assertEquals(7,vector3PlusVector1.getX1());

        Assertions.assertEquals(-2,vector2PlusVector3.getX0());
        Assertions.assertEquals(-3,vector2PlusVector3.getX1());

        Assertions.assertEquals(-2,vector3PlusVector2.getX0());
        Assertions.assertEquals(-3,vector3PlusVector2.getX1());

        Assertions.assertThrows(NullPointerException.class,() -> vector2DTest1.add(vector2DTestNull));

    }

    @Test
    void subtract() {

        Vector2D vector1MinusVector2 = vector2DTest1.subtract(vector2DTest2);
        Vector2D vector2MinusVector1 = vector2DTest2.subtract(vector2DTest1);

        Vector2D vector1MinusVector3 = vector2DTest1.subtract(vector2DTest3);
        Vector2D vector3MinusVector1 = vector2DTest3.subtract(vector2DTest1);

        Vector2D vector2MinusVector3 = vector2DTest2.subtract(vector2DTest3);
        Vector2D vector3MinusVector2 = vector2DTest3.subtract(vector2DTest2);

        Assertions.assertEquals(-1,vector1MinusVector2.getX0());
        Assertions.assertEquals(10,vector1MinusVector2.getX1());

        Assertions.assertEquals(1,vector2MinusVector1.getX0());
        Assertions.assertEquals(-10,vector2MinusVector1.getX1());

        Assertions.assertEquals(5,vector1MinusVector3.getX0());
        Assertions.assertEquals(7,vector1MinusVector3.getX1());

        Assertions.assertEquals(-5,vector3MinusVector1.getX0());
        Assertions.assertEquals(-7,vector3MinusVector1.getX1());

        Assertions.assertEquals(6,vector2MinusVector3.getX0());
        Assertions.assertEquals(-3,vector2MinusVector3.getX1());

        Assertions.assertEquals(-6,vector3MinusVector2.getX0());
        Assertions.assertEquals(3,vector3MinusVector2.getX1());

        Assertions.assertThrows(NullPointerException.class,() -> vector2DTest1.subtract(vector2DTestNull));

    }
}