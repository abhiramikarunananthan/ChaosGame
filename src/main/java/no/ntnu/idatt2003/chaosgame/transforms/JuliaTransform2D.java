package no.ntnu.idatt2003.chaosgame.transforms;

import no.ntnu.idatt2003.chaosgame.exceptions.IllegalSignException;
import no.ntnu.idatt2003.chaosgame.tensors.Complex;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;

/**
 * A class representing a julia transformation,
 * and implements the interface {@link Transform2D}.
 *
 * @author 10052
 * @version 1.0
 */
public class JuliaTransform2D implements Transform2D{

    private final Complex constant;
    private final int sign;


    /**
     * Constructor for the {@link JuliaTransform2D} class.
     *
     * @param constant The complex number representing the constant
     *                 c in a julia transformation
     * @param sign     The number representing the sign in front of the
     *                 square root in a julia transformation. Must be
     *                 either {@code 1} or {@code -1}
     * @throws IllegalArgumentException If the specified {@link Complex} is {@code null}
     * @throws IllegalSignException If the sign is not {@code 1} or {@code -1}
     */
    public JuliaTransform2D(Complex constant, int sign) {
        if(constant == null){
            throw new IllegalArgumentException("The complex constant cannot be null");
        }
        if(sign != 1 && sign != -1){
            throw new IllegalSignException("The sign must either be 1 or -1");
        }
        this.constant = constant;
        this.sign = sign;
    }

    /**
     * The method for performing a julia transformation on the
     * specified {@link Vector2D} object. The transformation follows
     * the mathematical transformation:
     * <p>
     *     z -> +-sqrt(z - c)
     * </p>
     *
     * where {@code z} is the specified {@link Vector2D}, {@code +-}
     * is {@link #sign} and {@code c} is {@link #constant}.
     *
     * @param point The vector point which the transformation will
     *              be applied to
     * @return {@link Vector2D} as a result of the transformation
     */
    @Override
    public Vector2D transform(Vector2D point) {
        Vector2D underTheSquareRoot = point.subtract(constant);
        Complex z = new Complex(underTheSquareRoot.getX0(), underTheSquareRoot.getX1());
        z = z.sqrt();
        return new Complex(sign * z.getX0(), sign * z.getX1());
    }

    /**
     * Get method for retrieving {@link #constant}
     *
     * @return {@link #constant}
     */
    public Complex getConstant() {
        return constant;
    }

    /**
     * {@code toString()} method containing the
     * information about the julia transformation.
     *
     * @return String with transformation details
     */
    @Override
    public String toString() {
        return constant.getX0() + ", " + constant.getX1();
    }
}
