package no.ntnu.idatt2003.chaosgame.tensors;

public record Matrix2x2(double a00, double a01, double a10, double a11) {

    public Vector2D multiply(Vector2D vector) {
        double newX0 = this.a00 * vector.getX0() + this.a01 * vector.getX1();
        double newX1 = this.a10 * vector.getX0() + this.a11 * vector.getX1();
        return new Vector2D(newX0, newX1);
    }

    @Override
    public String toString() {
        return a00 + "," + a01 + "," + a10 + "," + a11;
    }
}
