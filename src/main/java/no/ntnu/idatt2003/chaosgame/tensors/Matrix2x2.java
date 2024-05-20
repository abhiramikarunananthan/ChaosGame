package no.ntnu.idatt2003.chaosgame.tensors;

public class Matrix2x2 {
    private double a00;
    private double a01;
    private double a10;
    private double a11;


    public Matrix2x2(double a00, double a01, double a10, double a11) {
        this.a00 = a00;
        this.a01 = a01;
        this.a10 = a10;
        this.a11 = a11;
    }

    public Vector2D multiply(Vector2D vector){
        double newX0= this.a00* vector.getX0() + this.a01* vector.getX1();
        double newX1= this.a10* vector.getX0() + this.a11* vector.getX1();
        return new Vector2D(newX0, newX1);
    }

    public double getA00() {
        return a00;
    }

    public double getA01() {
        return a01;
    }

    public double getA10() {
        return a10;
    }

    public double getA11() {
        return a11;
    }

    @Override
    public String toString() {
        return a00 + "," + a01 + "," + a10 + "," + a11;
    }
}
