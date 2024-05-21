package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.exceptions.MinimumBiggerThanMaximumException;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChaosCanvasTest {

    ChaosCanvas chaosCanvasTest;
    Vector2D minCoordsTest;
    Vector2D maxCoordsTest;

    @BeforeEach
    void setUp() {

        minCoordsTest = new Vector2D(0,0);
        maxCoordsTest = new Vector2D(1,1);

        chaosCanvasTest = new ChaosCanvas(600,600,minCoordsTest,maxCoordsTest);
    }

    @Test
    void widthOrHeightNegative(){
        Assertions.assertThrows(NegativeArraySizeException.class, () -> new ChaosCanvas(-600,600,minCoordsTest,maxCoordsTest));
        Assertions.assertThrows(NegativeArraySizeException.class, () -> new ChaosCanvas(600,-470,minCoordsTest,maxCoordsTest));
    }

    @Test
    void minimumVectorBiggerThanMaximumVector(){
        Vector2D minCoordsTest1 = new Vector2D(1,0);
        Vector2D maxCoordsTest1 = new Vector2D(0,1);

        Vector2D minCoordsTest2 = new Vector2D(0,1);
        Vector2D maxCoordsTest2 = new Vector2D(1,0);

        Assertions.assertThrows(MinimumBiggerThanMaximumException.class, () -> new ChaosCanvas(600,600,minCoordsTest1,maxCoordsTest1));
        Assertions.assertThrows(MinimumBiggerThanMaximumException.class, () -> new ChaosCanvas(600,600,minCoordsTest2,maxCoordsTest2));
    }

    @Test
    void nullParametersInConstructor(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ChaosCanvas(600,600,null,maxCoordsTest));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ChaosCanvas(600,600,minCoordsTest,null));
    }

    @Test
    void deepCopyConstructor(){
        chaosCanvasTest.putPixel(new Vector2D(0.2,0.2));

        ChaosCanvas deepCopy = new ChaosCanvas(chaosCanvasTest);

        Assertions.assertNotEquals(deepCopy,chaosCanvasTest);
        Assertions.assertNotEquals(deepCopy.getCanvasArray(),chaosCanvasTest.getCanvasArray());
        Assertions.assertEquals(deepCopy.getCanvasArray()[479][120],chaosCanvasTest.getCanvasArray()[479][120]);
    }

    @Test
    void getPixelAndPutPixel() {
        Vector2D point1 = new Vector2D(0,0);
        Vector2D point2 = new Vector2D(2,0);

        Vector2D newPosition1 = chaosCanvasTest.putPixel(point1);
        Vector2D newPosition2 = chaosCanvasTest.putPixel(point2);

        Assertions.assertEquals(1,chaosCanvasTest.getPixel(newPosition1));

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, ()->chaosCanvasTest.getPixel(newPosition2));

    }

    @Test
    void getCanvasArray() {
        chaosCanvasTest.putPixel(new Vector2D(0.2,0.2));

        int[][] canvasArray = chaosCanvasTest.getCanvasArray();

        Assertions.assertEquals(1,canvasArray[479][120]);

        chaosCanvasTest.putPixel(new Vector2D(0,0));

        Assertions.assertEquals(0,canvasArray[599][0]);
        Assertions.assertEquals(1,chaosCanvasTest.getCanvasArray()[599][0]);

    }

    @Test
    void clear() {

        int sum = 0;
        chaosCanvasTest.putPixel(new Vector2D(0.1,0.4));
        chaosCanvasTest.putPixel(new Vector2D(0.5,0.3));
        chaosCanvasTest.putPixel(new Vector2D(0.6,0.2));

        int[][] canvasArray = chaosCanvasTest.getCanvasArray();

        for (int i = 0; i < canvasArray.length; i++) {
            for (int j = 0; j < canvasArray[0].length; j++) {
                sum += canvasArray[i][j];
            }
        }

        Assertions.assertTrue(sum > 0);

        sum = 0;

        chaosCanvasTest.clear();

        canvasArray = chaosCanvasTest.getCanvasArray();

        for (int i = 0; i < canvasArray.length; i++) {
            for (int j = 0; j < canvasArray[0].length; j++) {
                sum += canvasArray[i][j];
            }
        }

        Assertions.assertEquals(0, sum);



    }
}