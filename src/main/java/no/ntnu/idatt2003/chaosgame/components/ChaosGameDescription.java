package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.exceptions.MinimumBiggerThanMaximumException;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.util.List;

/**
 * A class representing the description for
 * a {@link ChaosGame}. The description contains
 * a list of transformations used to calculate points.
 *
 * @author 10052
 * @version 1.0
 */
public class ChaosGameDescription {
    private List<Transform2D> transforms;
    private Vector2D minCoords;
    private Vector2D maxCoords;
    private final Transformations transformation;


    /**
     * Constructor for the {@link ChaosGameDescription} class. This is an
     * alternative constructor without {@link #transformation}.
     *
     * @param transforms A list containing different transformations of
     *                   the {@link Transform2D} class
     * @param minCoords The minimum vector coordinates representing the
     *                  bottom left corner of the range
     * @param maxCoords The maximum vector coordinates representing the
     *                  upper right corner of the range
     * @throws MinimumBiggerThanMaximumException If the {@link #minCoords} vector values are bigger than the {@link #maxCoords} vector values
     * @throws IllegalArgumentException If any of the parameters are {@code null}
     */
    public ChaosGameDescription(List<Transform2D> transforms, Vector2D minCoords, Vector2D maxCoords) throws MinimumBiggerThanMaximumException{
        if(transforms == null || minCoords == null || maxCoords == null){
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        if(minCoords.getX0() > maxCoords.getX0() || minCoords.getX1() > maxCoords.getX1()){
            throw new MinimumBiggerThanMaximumException("Minimum vector cannot be bigger than maximum vector");
        }

        this.transforms = transforms;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
        this.transformation = null;
    }

    /**
     * Constructor for the {@link ChaosGameDescription} class. This is an
     * alternative constructor with {@link #transformation}.
     *
     * @param transforms A list containing different transformations of
     *                   the {@link Transform2D} class
     * @param minCoords The minimum vector coordinates representing the
     *                  bottom left corner of the range
     * @param maxCoords The maximum vector coordinates representing the
     *                  upper right corner of the range
     * @param transformation The transformation type of the game description of the {@link Transformations} enum class
     * @throws MinimumBiggerThanMaximumException If the {@link #minCoords} vector values are bigger than the {@link #maxCoords} vector values
     * @throws IllegalArgumentException If any of the parameters are null, except for {@link #transformation}
     */
    public ChaosGameDescription(List<Transform2D> transforms, Vector2D minCoords, Vector2D maxCoords, Transformations transformation) throws MinimumBiggerThanMaximumException{
        if(transforms == null || minCoords == null || maxCoords == null){
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        if(minCoords.getX0() > maxCoords.getX0() || minCoords.getX1() > maxCoords.getX1()){
            throw new MinimumBiggerThanMaximumException("Minimum vector cannot be bigger than maximum vector");
        }

        this.transforms = transforms;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
        this.transformation = transformation;
    }

    /**
     * Set method for setting {@link #transforms}
     *
     * @param transforms A list of {@link Transform2D} objects
     */
    public void setTransforms(List<Transform2D> transforms) {
        this.transforms = transforms;
    }

    /**
     * Set method for setting {@link #minCoords}
     *
     * @param minCoords The new minimum vector cordinates
     */
    public void setMinCoords(Vector2D minCoords) {
        this.minCoords = minCoords;
    }

    /**
     * Set method for setting {@link #maxCoords}
     *
     * @param maxCoords The new maximum vector cordinates
     */
    public void setMaxCoords(Vector2D maxCoords) {
        this.maxCoords = maxCoords;
    }

    /**
     * Get method for retrieving {@link #transforms}
     *
     * @return {@link #transforms}
     */
    public List<Transform2D> getTransforms() {
        return transforms;
    }

    /**
     * Get method for retrieving {@link #minCoords}
     *
     * @return {@link #minCoords}
     */
    public Vector2D getMinCoords() {
        return minCoords;
    }

    /**
     * Get method for retrieving {@link #maxCoords}
     *
     * @return {@link #maxCoords}
     */
    public Vector2D getMaxCoords() {
        return maxCoords;
    }

    /**
     * Get method for retrieving {@link #transformation}
     *
     * @return {@link #transformation}
     */
    public Transformations getTransformation() {
        return transformation;
    }
}
