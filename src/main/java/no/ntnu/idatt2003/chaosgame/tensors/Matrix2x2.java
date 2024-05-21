package no.ntnu.idatt2003.chaosgame.tensors;

/**
 * A class representing a mathematical
 * 2x2 matrix. A mathematical matrix contains rows
 * and columns, with a value between each one. In
 * this case there are 2 rows and 2 columns
 *
 * @author 10052
 * @version 1.0
 */
public class Matrix2x2 {
    private final double a00;
    private final double a01;
    private final double a10;
    private final double a11;


    /**
     * Constructor for the {@link Matrix2x2} class. Creates
     * a 2x2 matrix with the specified numbers.
     *
     * @param a00 The value for the upper-left number
     * @param a01 The value for the upper-right number
     * @param a10 The value for the lower-left number
     * @param a11 The value for the lower-right number
     */
    public Matrix2x2(double a00, double a01, double a10, double a11) {
        this.a00 = a00;
        this.a01 = a01;
        this.a10 = a10;
        this.a11 = a11;
    }

    /**
     * Method for multiplying this {@link Matrix2x2} object
     * with the specified {@link Vector2D} object.
     *
     * @param vector The {@link Vector2D} which this {@link Matrix2x2}
     *               will be multiplied with
     * @return {@link Vector2D} object representing the result of
     * the mathematical operation
     */
    public Vector2D multiply(Vector2D vector){
        double newX0= this.a00* vector.getX0() + this.a01* vector.getX1();
        double newX1= this.a10* vector.getX0() + this.a11* vector.getX1();
        return new Vector2D(newX0, newX1);
    }

    /**
     * Get method for retrieving {@link #a00}
     *
     * @return {@link #a00}
     */
    public double getA00() {
        return a00;
    }

    /**
     * Get method for retrieving {@link #a01}
     *
     * @return {@link #a01}
     */
    public double getA01() {
        return a01;
    }

    /**
     * Get method for retrieving {@link #a10}
     *
     * @return {@link #a10}
     */
    public double getA10() {
        return a10;
    }

    /**
     * Get method for retrieving {@link #a11}
     *
     * @return {@link #a11}
     */
    public double getA11() {
        return a11;
    }

    /**
     * {@code toString()} method with the values
     * of the matrix seperated by commas
     *
     * @return String with information about the matrix
     */
    @Override
    public String toString() {
        return a00 + "," + a01 + "," + a10 + "," + a11;
    }
}
