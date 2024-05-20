package no.ntnu.idatt2003.chaosgame.transforms;

import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;

public class AffineTransform2D implements Transform2D {

    private Matrix2x2 matrix;
    private Vector2D vector;


    public AffineTransform2D(Matrix2x2 matrix, Vector2D vector) {
        this.matrix = matrix;
        this.vector = vector;
    }

    @Override
    public Vector2D transform(Vector2D point) {
        Vector2D aX = this.matrix.multiply(point);
        return aX.add(this.vector);
    }

    public Matrix2x2 getMatrix() {
        return matrix;
    }

    public Vector2D getVector() {
        return vector;
    }

    @Override
    public String toString() {
        return matrix.toString() + "," + vector.getX0() + "," + vector.getX1();
    }
}
