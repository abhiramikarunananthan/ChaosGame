package no.ntnu.idatt2003.chaosgame.tensors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {

    private Complex complexTest1;
    private Complex complexTest2;
    private Complex complexTestNull;

    @BeforeEach
    void setUp() {
        complexTest1 = new Complex(0.4, 0.8);
        complexTest2 = new Complex(1,3);
        complexTestNull = null;

    }

    @Test
    void square() {

        Complex complexTest1Squared = complexTest1.square();
        Complex complexTest2Squared = complexTest2.square();

        Assertions.assertEquals(0.16,complexTest1Squared.getX0(), 0.00001);
        Assertions.assertEquals(0.64,complexTest1Squared.getX1(), 0.00001);

        Assertions.assertEquals(1,complexTest2Squared.getX0(), 0.00001);
        Assertions.assertEquals(9,complexTest2Squared.getX1(), 0.00001);


        Assertions.assertThrows(NullPointerException.class, () -> complexTestNull.square());

    }

    @Test
    void sqrt() {

        Complex complexTest1SquareRooted = complexTest1.sqrt();
        Complex complexTest2SquareRooted = complexTest2.sqrt();

        Assertions.assertEquals(0.8044,complexTest1SquareRooted.getX0(), 0.0001);
        Assertions.assertEquals(0.4972,complexTest1SquareRooted.getX1(), 0.0001);

        Assertions.assertEquals(1.4426,complexTest2SquareRooted.getX0(), 0.0001);
        Assertions.assertEquals(1.0398,complexTest2SquareRooted.getX1(), 0.0001);


        Assertions.assertThrows(NullPointerException.class, () -> complexTestNull.sqrt());

    }
}