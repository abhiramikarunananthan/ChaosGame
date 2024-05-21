package no.ntnu.idatt2003.chaosgame.transforms;

import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AffineTransform2DTest {

    private AffineTransform2D affineTransform2DTest;
    private Vector2D vector2DTest;
    private Matrix2x2 matrix2x2Test;

    @BeforeEach
    void setUp() {

        vector2DTest = new Vector2D(3,1);
        matrix2x2Test = new Matrix2x2(0.5,1,1,0.5);

        affineTransform2DTest = new AffineTransform2D(matrix2x2Test, vector2DTest);

    }

    @Test
    void testNullParametersInContructor(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> new AffineTransform2D(null, vector2DTest));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new AffineTransform2D(matrix2x2Test, null));

    }

    @Test
    void transform() {

        Vector2D vectorPointX = new Vector2D(1,2);


        Vector2D transformedVector = affineTransform2DTest.transform(vectorPointX);


        Assertions.assertEquals(5.5,transformedVector.getX0());
        Assertions.assertEquals(3,transformedVector.getX1());

    }

    @Test
    void getMatrix() {
        Matrix2x2 affineTransformMatrix = affineTransform2DTest.getMatrix();

        Assertions.assertEquals(0.5, affineTransformMatrix.a00());
        Assertions.assertEquals(1, affineTransformMatrix.a01());
        Assertions.assertEquals(1, affineTransformMatrix.a10());
        Assertions.assertEquals(0.5, affineTransformMatrix.a11());

    }

    @Test
    void getVector() {
        Vector2D affineTransformVector = affineTransform2DTest.getVector();

        Assertions.assertEquals(3,affineTransformVector.getX0());
        Assertions.assertEquals(1,affineTransformVector.getX1());


    }
}