package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;

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
    private AffineTransform2D transformCoordsToIndices;


    /**
     * Constructor for the {@code ChaosCanvas} class. Initializes
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
     */
    public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords) throws NegativeArraySizeException {
        this.width = width;
        this.height = height;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;

        if (height < 0 || width < 0) {
            throw new NegativeArraySizeException();
        }

        this.canvas = new int[height][width];

        this.transformCoordsToIndices =
                new AffineTransform2D(
                        new Matrix2x2(
                                0,
                                (double) (this.height - 1) / (this.minCoords.getX1() - this.maxCoords.getX1()),
                                (double) (this.width - 1) / (this.maxCoords.getX0() - this.minCoords.getX0()),
                                0),
                        new Vector2D((this.height - 1)
                                * this.maxCoords.getX1()
                                / (this.maxCoords.getX1() - this.minCoords.getX1()),
                                (this.width - 1)
                                        * this.minCoords.getX0()
                                        / (this.maxCoords.getX0() - this.minCoords.getX0())));
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
        Vector2D indices = transformCoordsToIndices.transform((point));
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

}
