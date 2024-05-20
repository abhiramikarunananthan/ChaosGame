package no.ntnu.idatt2003.chaosgame.tensors;

public class Vector2D {
    private double x0;
    private double x1;

    public Vector2D(double x0, double x1) {
        this.x0 = x0;
        this.x1 = x1;
    }

    public double getX0() {
        return x0;
    }

    public double getX1() {
        return x1;
    }

    public Vector2D add(Vector2D other){
        double newX0 = this.x0 + other.getX0();
        double newX1 = this.x1 + other.getX1();
        return new Vector2D(newX0, newX1);
    }
    public Vector2D subtract(Vector2D other){
        double newX0 = this.x0 - other.getX0();
        double newX1 = this.x1 - other.getX1();
        return new Vector2D(newX0, newX1);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x0=" + x0 +
                ", x1=" + x1 +
                '}';
    }
}


