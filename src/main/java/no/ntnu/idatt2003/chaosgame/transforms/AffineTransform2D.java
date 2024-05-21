package no.ntnu.idatt2003.chaosgame.transforms;

import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;

/**
 * A class representing an affine transformation,
 * and implements the interface {@link Transform2D}.
 * An affine transformation is a linear mapping method
 * that preserves lines and parallelism.
 *
 * @author 10052
 * @version 1.0
 */
public class AffineTransform2D implements Transform2D {

    private final Matrix2x2 matrix;
    private final Vector2D vector;


    /**
     * Constructor for the {@link AffineTransform2D} class.
     *
     * @param matrix The matrix representing matrix A in an
     *               affine transformation
     * @param vector The vector representing vector b in an
     *               affine transformation
     * @throws IllegalArgumentException If any of the specified parameters are {@code null}
     */
    public AffineTransform2D(Matrix2x2 matrix, Vector2D vector) {
        if(matrix == null){
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        if(vector == null){
            throw new IllegalArgumentException("Vector cannot be null");
        }

        this.matrix = matrix;
        this.vector = vector;
    }

    /**
     * The method for performing an affine transformation on the
     * specified {@link Vector2D} object. The transformation follows
     * the mathematical transformation:
     *
     * <p>
     *     x -> Ax + b
     * </p>
     *
     * where {@code x} is the specified {@link Vector2D}, {@code A}
     * is {@link #matrix} and {@code b} is {@link #vector}.
     *
     * @param point The vector point which the transformation will
     *              be applied to
     * @return {@link Vector2D} as a result of the transformation
     */
    @Override
    public Vector2D transform(Vector2D point) {
        Vector2D aX = this.matrix.multiply(point);
        return aX.add(this.vector);
    }

    /**
     * Get method for retrieving {@link #matrix}
     *
     * @return {@link #matrix}
     */
    public Matrix2x2 getMatrix() {
        return matrix;
    }

    /**
     * Get method for retrieving {@link #vector}
     *
     * @return {@link #vector}
     */
    public Vector2D getVector() {
        return vector;
    }

    /**
     * {@code toString()} method containing the
     * information about the affine transformation.
     *
     * @return String with transformation details
     */
    @Override
    public String toString() {
        return matrix + "," + vector.getX0() + "," + vector.getX1();
    }
}
