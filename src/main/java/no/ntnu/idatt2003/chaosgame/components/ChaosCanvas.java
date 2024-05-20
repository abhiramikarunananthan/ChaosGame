package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;

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

        this.transformCoordsToIndices =
                new AffineTransform2D(
                        new Matrix2x2(
                                0,
                                (double) (this.height-1) / (this.minCoords.getX1()-this.maxCoords.getX1()),
                                (double) (this.width-1) / (this.maxCoords.getX0()-this.minCoords.getX0()),
                                0),
                        new Vector2D((this.height - 1)
                        * this.maxCoords.getX1()
                        / (this.maxCoords.getX1() -this.minCoords.getX1()),
                                (this.width -1)
                        * this.minCoords.getX0()
                        /(this.maxCoords.getX0()-this.minCoords.getX0())));



    }

    public int getPixel(Vector2D point){
        Vector2D indices = transformCoordsToIndices.transform((point));
        int i = (int) Math.round(indices.getX0());
        int j = (int) Math.round(indices.getX1());
        return this.canvas[i][j];
    }

    public void putPixel(Vector2D point){
        Vector2D indices = transformCoordsToIndices.transform((point));
        int i = (int) Math.round(indices.getX0());
        int j = (int) Math.round(indices.getX1());
        if(i >= 0 && i < width && j >= 0 && j < height){
        this.canvas[i][j]+=1;
        }
    }

    public Vector2D getPixelCoords(Vector2D point){
        return transformCoordsToIndices.transform((point));
    }

    public int[][] getCanvasArray() {
        return canvas;
    }
    public void clear(){
      this.canvas = new int[width][height];
    }

}
