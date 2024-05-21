package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.exceptions.MinimumBiggerThanMaximumException;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;

import java.util.Arrays;

/**
 * A class representing a canvas for different points in a
 * 2x2 matrix, where the value 0 means the point is empty.
 * The canvas has a defined width and height.
 *
 * @author 10052
 * @version 1.0
 */
public class ChaosCanvas {

    private int[][] canvas;
    private int width;
    private int height;
    private Vector2D minCoords;
    private Vector2D maxCoords;
    private Transform2D transformCoordsToIndices;


    /**
     * Constructor for the {@link ChaosCanvas} class. Initializes
     * the field variable {@link #transformCoordsToIndices} according
     * to the mathematical operation of translating normal vector coordinates
     * to computer array coordinates.
     *
     * @param width     Value for the width of the canvas
     * @param height    Value for the height of the canvas
     * @param minCoords The minimum vector coordinates representing the
     *                  bottom left corner of the range
     * @param maxCoords The maximum vector coordinates representing the
     *                  upper right corner of the range
     * @throws NegativeArraySizeException If either the {@link #width} or {@link #height} is negative
     * @throws MinimumBiggerThanMaximumException If the {@link #minCoords} vector values are bigger than the {@link #maxCoords} vector values
     */
    public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords) throws NegativeArraySizeException, MinimumBiggerThanMaximumException {
        if(minCoords.getX0() > maxCoords.getX0() || minCoords.getX1() > maxCoords.getX1()){
            throw new MinimumBiggerThanMaximumException("Minimum vector cannot be bigger than maximum vector");
        }

        if (height < 0 || width < 0) {
            throw new NegativeArraySizeException("Width or height cannot be negative");
        }

        this.width = width;
        this.height = height;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;

        this.canvas = new int[height][width];


        Matrix2x2 A = new Matrix2x2(
                0,
                (double) (this.height - 1) / (this.minCoords.getX1() - this.maxCoords.getX1()),
                (double) (this.width - 1) / (this.maxCoords.getX0() - this.minCoords.getX0()),
                0);

        Vector2D b = new Vector2D((this.height - 1)
                * this.maxCoords.getX1()
                / (this.maxCoords.getX1() - this.minCoords.getX1()),
                (this.width - 1)
                        * this.minCoords.getX0()
                        / (this.maxCoords.getX0() - this.minCoords.getX0()));

        this.transformCoordsToIndices = (point) -> A.multiply(point).add(b);

    }

    /**
     * Method for retrieving the translated coordinates of
     * specified vector. The translation is done using the
     * field variable {@link #transformCoordsToIndices}.
     *
     * @param point The vector coordinates of a point
     * @return The {@link #canvas} array value of
     * the translated corresponding location of the
     * specified point
     * @throws ArrayIndexOutOfBoundsException If the vector coordinates are out of bounds of
     *                                        {@link #canvas} size
     */
    public int getPixel(Vector2D point) throws ArrayIndexOutOfBoundsException {
        int i = (int) Math.round(point.getX0());
        int j = (int) Math.round(point.getX1());
        return this.canvas[i][j];
    }

    /**
     * Method for placing a pixel in the {@link #canvas}. This is done
     * by using the translated corresponding specified vector coordinates,
     * and increasing the value of the location by 1 in the canvas.
     *
     * @param point The vector coordinates of a point
     * @return The vector coordinates of the placed point
     */
    public Vector2D putPixel(Vector2D point) {
        Vector2D indices = transformCoordsToIndices.transform(point);
        int i = (int) Math.round(indices.getX0());
        int j = (int) Math.round(indices.getX1());
        if (i >= 0 && i < width && j >= 0 && j < height) {
            this.canvas[i][j] += 1;
        }
        return indices;
    }

    /**
     * Get method for {@link #canvas}.
     *
     * @return {@link #canvas}
     */
    public int[][] getCanvasArray() {
        return canvas;
    }

    /**
     * Method for clearing the {@link #canvas} and setting
     * all the values equal to 0.
     */
    public void clear() {
        this.canvas = new int[width][height];
    }

    /**
     * {@code toString()} method for retrieving a string
     * of the {@link #canvas} array
     *
     * @return String containing the whole {@link #canvas} array
     */
    @Override
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                finalString.append(canvas[i][j]);
                finalString.append(",");
            }
            finalString.append("\n");
        }
        return finalString.toString();
    }
}
