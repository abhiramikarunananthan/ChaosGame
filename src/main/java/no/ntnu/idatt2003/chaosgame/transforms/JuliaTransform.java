package no.ntnu.idatt2003.chaosgame.transforms;

import no.ntnu.idatt2003.chaosgame.tensors.Complex;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;

public class JuliaTransform implements Transform2D{

    private Complex point;
    private int sign;


    public JuliaTransform(Complex point, int sign) {
        this.point = point;
        this.sign = sign;
    }

    @Override
    public Vector2D transform(Vector2D point) {
        Vector2D vector2D = new Vector2D(point.getX0(), point.getX1());
        if(vector2D instanceof Complex){
            Complex z = (Complex) this.point.subtract(vector2D);
            return z.sqrt();
        }
        return null;
    }

    @Override
    public String toString() {
        return sign + "" + point.getX0() + ", " + point.getX1();
    }
}
