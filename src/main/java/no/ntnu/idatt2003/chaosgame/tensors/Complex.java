package no.ntnu.idatt2003.chaosgame.tensors;

/**
 * A class representing a complex number and extends
 * the {@code Vector2D} class. It provides methods to compute the
 * square and square root of a complex number.
 *
 * @author 10052
 * @version 1.0
 */
public class Complex extends Vector2D {

    /**
     * Constructor for the {@link Complex} class. Creates
     * a complex number with the specified values.
     *
     * @param realPart The value representing the real part
     *                 of a complex number
     * @param imaginaryPart The value representing the imaginary
     *                      part of a complex number
     */
    public Complex(double realPart, double imaginaryPart) {
        super(realPart, imaginaryPart);
    }

    /**
     * Method for calculating the squares of each
     * part of this {@link Complex} object.
     *
     * @return {@link Complex} object with each part squared
     */
    public Complex square(){
        double newRealPart = this.getX0() * this.getX0();
        double newImaginaryPart = this.getX1() * this.getX1();
        return new Complex(newRealPart, newImaginaryPart);
    }


    /**
     * Method for calculating the square root of each
     * part of this {@link Complex} object.
     *
     * @return {@link Complex} object with each part square rooted
     */
    public Complex sqrt(){
       double newRealPart  = Math.sqrt(0.5*(Math.sqrt(Math.pow(this.getX0(), 2) +Math.pow(this.getX1(), 2) ) + this.getX0()));
       double newImaginaryPart = Math.sqrt(0.5*(Math.sqrt(Math.pow(this.getX0(), 2) +Math.pow(this.getX1(), 2) ) - this.getX0()));
        return new Complex(newRealPart, newImaginaryPart);
    }

}
