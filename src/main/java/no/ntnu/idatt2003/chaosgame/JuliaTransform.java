package no.ntnu.idatt2003.chaosgame;

public class JuliaTransform implements Transform2D{

    private Complex point;
    private int sign;


    public JuliaTransform(Complex point, int sign) {
        this.point = point;
        this.sign = sign;
    }

    @Override
    public Vector2D transform(Vector2D point) {
        Complex z = (Complex) this.point.subtract(point);
        return z.sqrt();
    }

    @Override
    public String toString() {
        return sign + "" + point.getX0() + ", " + point.getX1();
    }
}
