package no.ntnu.idatt2003.chaosgame.tensors;

/**
 * A class representing a mathematical
 * 2x1 vector. A mathematical vector contains rows
 * and 1 column, with a value in each row. In
 * this case there are 2 rows.
 *
 * @author 10052
 * @version 1.0
 */
public class Vector2D {
    private final double x0;
    private final double x1;

    /**
     * Constructor for the {@link Vector2D} class. Creates
     * a 2x1 vector with the specified numbers.
     *
     * @param x0 The value representing the top value
     * @param x1 The value representing the bottom value
     */
    public Vector2D(double x0, double x1) {
        this.x0 = x0;
        this.x1 = x1;
    }

    /**
     * Constructor for the {@link Vector2D} class. Creates
     * a deep copy of the specified {@link Vector2D}
     *
     * @param vector2D The {@link Vector2D} that gets deep-copied
     */
    public Vector2D(Vector2D vector2D){
        this(vector2D.getX0(), vector2D.getX1());
    }

    /**
     * Get method for retrieving {@link #x0}
     *
     * @return {@link #x0}
     */
    public double getX0() {
        return x0;
    }

    /**
     * Get method for retrieving {@link #x1}
     *
     * @return {@link #x1}
     */
    public double getX1() {
        return x1;
    }

    /**
     * Method for adding two {@link Vector2D} objects
     * together according to the mathematical operation of
     * adding two vectors together.
     *
     * @param other The {@link Vector2D} that will be added to this
     * @return {@link Vector2D} as a result of the operation
     */
    public Vector2D add(Vector2D other){
        double newX0 = this.x0 + other.getX0();
        double newX1 = this.x1 + other.getX1();
        return new Vector2D(newX0, newX1);
    }

    /**
     * Method for subtracting two {@link Vector2D} objects
     * from each other according to the mathematical operation of
     * subtracting two vectors from each other.
     *
     * @param other The {@link Vector2D} that will be subtracted from this
     * @return {@link Vector2D} as a result of the operation
     */
    public Vector2D subtract(Vector2D other){
        double newX0 = this.x0 - other.getX0();
        double newX1 = this.x1 - other.getX1();
        return new Vector2D(newX0, newX1);
    }

    /**
     * {@code toString()} method containing the
     * information about the vector values.
     *
     * @return String with vector values
     */
    @Override
    public String toString() {
        return "Vector2D{" +
                "x0=" + x0 +
                ", x1=" + x1 +
                '}';
    }
}


