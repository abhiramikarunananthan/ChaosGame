package no.ntnu.idatt2003.chaosgame;

import java.util.Vector;

public class ChaosCanvas {

    private int[][] canvas;
    private int width;
    private int height;
    private Vector2D minCoords;
    private Vector2D maxCoords;
    private AffineTransform2D transformCoordsToIndices;


    public ChaosCanvas( int width, int height, Vector2D minCoords, Vector2D maxCoords) {
        this.width = width;
        this.height = height;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;

        this.canvas = new int[height][width];
    }

    public int getPixel(Vector2D point){
        return canvas[Math.abs((int) translate(point).getX0())][Math.abs((int) translate(point).getX1())];
    }

    public void putPixel(Vector2D point){
        canvas[Math.abs((int) translate(point).getX0())][Math.abs((int) translate(point).getX1())] = 1;
    }

    public int[][] getCanvasArray() {
        return canvas;
    }
    public void clear(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                canvas[i][j] = 0;
            }
        }
    }
    public Vector2D translate(Vector2D point){
        Matrix2x2 a = new Matrix2x2(0
                , (height-1)/(minCoords.getX1()-maxCoords.getX1())
                ,(width-1)/(maxCoords.getX0()- minCoords.getX0())
                , 0 );
        Vector2D b = new Vector2D(((height-1)* maxCoords.getX1())/ (maxCoords.getX1()- minCoords.getX1())
                ,((width-1)* minCoords.getX0()) / (minCoords.getX0()- maxCoords.getX0()));

        //TODO: Optimize the use of the field variable
        AffineTransform2D affineTransform2D = new AffineTransform2D(a, b);
        return affineTransform2D.transform(point);
    }
}
