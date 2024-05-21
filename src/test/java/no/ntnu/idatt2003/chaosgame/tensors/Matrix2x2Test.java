package no.ntnu.idatt2003.chaosgame.tensors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Matrix2x2Test {

    private Matrix2x2 matrix2x2Test1;
    private Matrix2x2 matrix2x2Test2;
    private Vector2D vector2DTest1;
    private Vector2D vector2DTest2;
    private Matrix2x2 matrix2x2TestNull;

    @BeforeEach
    void setUp() {
        vector2DTest1 = new Vector2D(1,7);
        vector2DTest2 = new Vector2D(2,-3);

        matrix2x2Test1 = new Matrix2x2(1,-4,0,2);
        matrix2x2Test2 = new Matrix2x2(2,3,-5,-2);
        matrix2x2TestNull = null;
    }

    @Test
    void multiply() {
        Vector2D matrix1MultipliedVector1 = matrix2x2Test1.multiply(vector2DTest1);
        Vector2D matrix1MultipliedVector2 = matrix2x2Test1.multiply(vector2DTest2);

        Vector2D matrix2MultipliedVector1 = matrix2x2Test2.multiply(vector2DTest1);
        Vector2D matrix2MultipliedVector2 = matrix2x2Test2.multiply(vector2DTest2);

        Assertions.assertEquals(-27,matrix1MultipliedVector1.getX0());
        Assertions.assertEquals(14, matrix1MultipliedVector1.getX1());

        Assertions.assertEquals(14,matrix1MultipliedVector2.getX0());
        Assertions.assertEquals(-6, matrix1MultipliedVector2.getX1());

        Assertions.assertEquals(23,matrix2MultipliedVector1.getX0());
        Assertions.assertEquals(-19,matrix2MultipliedVector1.getX1());

        Assertions.assertEquals(-5,matrix2MultipliedVector2.getX0());
        Assertions.assertEquals(-4,matrix2MultipliedVector2.getX1());

        Assertions.assertThrows(NullPointerException.class, () -> matrix2x2TestNull.multiply(vector2DTest1));
        Assertions.assertThrows(NullPointerException.class, () -> matrix2x2TestNull.multiply(vector2DTest2));
    }

    @Test
    void getA00() {
        double matrix1A00 = matrix2x2Test1.a00();
        double matrix2A00 = matrix2x2Test2.a00();

        Assertions.assertEquals(1, matrix1A00);
        Assertions.assertEquals(2, matrix2A00);

        Assertions.assertThrows(NullPointerException.class, () -> matrix2x2TestNull.a00());

    }

    @Test
    void getA01() {

        double matrix1A01 = matrix2x2Test1.a01();
        double matrix2A01 = matrix2x2Test2.a01();

        Assertions.assertEquals(-4, matrix1A01);
        Assertions.assertEquals(3, matrix2A01);

        Assertions.assertThrows(NullPointerException.class, () -> matrix2x2TestNull.a01());
    }

    @Test
    void getA10() {
        double matrix1A10 = matrix2x2Test1.a10();
        double matrix2A10 = matrix2x2Test2.a10();

        Assertions.assertEquals(0, matrix1A10);
        Assertions.assertEquals(-5, matrix2A10);

        Assertions.assertThrows(NullPointerException.class, () -> matrix2x2TestNull.a10());
    }

    @Test
    void getA11() {
        double matrix1A11 = matrix2x2Test1.a11();
        double matrix2A11 = matrix2x2Test2.a11();

        Assertions.assertEquals(2, matrix1A11);
        Assertions.assertEquals(-2, matrix2A11);

        Assertions.assertThrows(NullPointerException.class, () -> matrix2x2TestNull.a11());
    }
}