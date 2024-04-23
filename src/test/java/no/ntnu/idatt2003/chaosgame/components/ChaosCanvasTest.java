package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.components.ChaosCanvas;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChaosCanvasTest {

    Vector2D vector2d1;
    Vector2D vector2d2;
    int width;
    int height;

    ChaosCanvas chaosCanvas;

    @BeforeEach
    void setUp() {
        vector2d1 = new Vector2D(0, 0);
        vector2d2 = new Vector2D(120, 40);
        width = 120;
        height = 40;
        chaosCanvas = new ChaosCanvas(width, height, vector2d1, vector2d2);
    }

    @Test
    void putPixel() {
        Vector2D point = new Vector2D(3, 8);

        chaosCanvas.putPixel(point);

        assertEquals(1, chaosCanvas.getCanvasArray()[31][2]);

    }
    @Test
    void getPixel() {
        Vector2D point = new Vector2D(6, 9);
        chaosCanvas.putPixel(point);

        int pixel = chaosCanvas.getPixel(point);

        assertEquals(1, pixel);
    }


    @Test
    void getCanvasArray() {
        int[][] expectedArray = new int[40][120];

        assertEquals(expectedArray.length, chaosCanvas.getCanvasArray().length);
        assertEquals(expectedArray[0].length, chaosCanvas.getCanvasArray()[0].length);
    }

    @Test
    void clear() {
        Vector2D point = new Vector2D(6, 9);
        chaosCanvas.putPixel(point);

        chaosCanvas.clear();

        assertEquals(0, chaosCanvas.getCanvasArray()[30][5]);

    }

    @Test
    void translate() {
        Vector2D point = new Vector2D(3,14);

        Vector2D translatedPoint = chaosCanvas.translate(point);

        assertEquals(25,(int)translatedPoint.getX0());
        assertEquals(2, (int)translatedPoint.getX1());
    }
}