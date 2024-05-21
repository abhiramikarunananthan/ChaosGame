package no.ntnu.idatt2003.chaosgame.transforms;

import no.ntnu.idatt2003.chaosgame.tensors.Complex;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuliaTransform2DTest {
    private JuliaTransform2D juliaTransform2DTest1;
    private JuliaTransform2D juliaTransform2DTest2;

    @BeforeEach
    void setUp() {

        Complex complexTest = new Complex(0.3, 0.6);

        juliaTransform2DTest1 = new JuliaTransform2D(complexTest,1);
        juliaTransform2DTest2 = new JuliaTransform2D(complexTest,-1);

    }

    @Test
    void testNullParametersInContructor(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new JuliaTransform2D(null, 1));
    }

    @Test
    void transform() {

        Complex zComplex = new Complex(0.4,0.2);

        Vector2D transformedPoint1 = juliaTransform2DTest1.transform(zComplex);
        Vector2D transformedPoint2 = juliaTransform2DTest2.transform(zComplex);

        Assertions.assertEquals(0.506,transformedPoint1.getX0(), 0.001);
        Assertions.assertEquals(0.395,transformedPoint1.getX1(), 0.001);

        Assertions.assertEquals(-0.506,transformedPoint2.getX0(), 0.001);
        Assertions.assertEquals(-0.395,transformedPoint2.getX1(), 0.001);

    }

    @Test
    void getConstant() {
        Complex juliaTransformConstant = juliaTransform2DTest1.getConstant();

        Assertions.assertEquals(0.3,juliaTransformConstant.getX0());
        Assertions.assertEquals(0.6,juliaTransformConstant.getX1());
    }
}