package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.util.List;

public class ChaosGameDescription {
    private List<Transform2D> transforms;
    private Vector2D minCoords;
    private Vector2D maxCoords;
    private Transformations transformation;

    public ChaosGameDescription(List<Transform2D> transforms, Vector2D minCoords, Vector2D maxCoords, Transformations transformation){
        this.transforms = transforms;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
        this.transformation = transformation;
    }

    public void setTransforms(List<Transform2D> transforms) {
        this.transforms = transforms;
    }

    public void setMinCoords(Vector2D minCoords) {
        this.minCoords = minCoords;
    }

    public void setMaxCoords(Vector2D maxCoords) {
        this.maxCoords = maxCoords;
    }

    public List<Transform2D> getTransforms() {
        return transforms;
    }

    public Vector2D getMinCoords() {
        return minCoords;
    }

    public Vector2D getMaxCoords() {
        return maxCoords;
    }

    public Transformations getTransformation() {
        return transformation;
    }
}
