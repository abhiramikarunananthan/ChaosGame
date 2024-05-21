package no.ntnu.idatt2003.chaosgame.transforms;

import no.ntnu.idatt2003.chaosgame.tensors.Complex;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;

public class JuliaTransform2D implements Transform2D{

    private final Complex constant;
    private final int sign;


    public JuliaTransform2D(Complex constant, int sign) {
        if(constant == null){
            throw new IllegalArgumentException("The complex constant cannot be null");
        }
        this.constant = constant;
        this.sign = sign;
    }

    @Override
    public Vector2D transform(Vector2D point) {
        Vector2D underTheSquareRoot = point.subtract(constant);
        Complex z = new Complex(underTheSquareRoot.getX0(), underTheSquareRoot.getX1());
        z = z.sqrt();
        return new Complex(sign * z.getX0(), sign * z.getX1());
    }

    public Complex getConstant() {
        return constant;
    }

    @Override
    public String toString() {
        return constant.getX0() + ", " + constant.getX1();
    }
}
