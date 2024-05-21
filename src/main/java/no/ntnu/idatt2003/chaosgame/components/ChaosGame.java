package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.exceptions.NegativeDimensionsException;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class representing the game object of
 * a fractal game. The class contains the description
 * of the fractal {@link #description}, and the
 * canvas containing the fractal points {@link #canvas}.
 *
 * @author 10052
 * @version 1.0
 */
public class ChaosGame {
    private ChaosCanvas canvas;
    private ChaosGameDescription description;
    private Vector2D currentPoint;

    /**
     * Constructor for the {@link ChaosGame} class. Sets the
     * starting point of {@link #currentPoint} to {@code (0,0)}.
     * A {@link ChaosCanvas} gets initialized and is used for the game.
     *
     * @param description The {@link ChaosGameDescription} containing the
     *                    different transformations
     * @param height The value which defines the {@link #canvas} height
     * @param width The value which defines the {@link #canvas} width
     * @throws NegativeDimensionsException If either the height or width specified is negative
     */
    public ChaosGame(ChaosGameDescription description, int height, int width) throws NegativeDimensionsException {
        if (height < 0 || width < 0) {
            throw new NegativeDimensionsException("Width or height cannot be negative");
        }

        this.description = description;
        this.canvas = new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
        this.currentPoint = new Vector2D(0, 0);
    }

    /**
     * Get method for retrieving {@link #canvas}
     *
     * @return {@link #canvas}
     */
    public ChaosCanvas getCanvas(){
        return canvas;
    }

    /**
     * The method for running the game. Through each iteration, it
     * calculates new points from the previous points. Starting from
     * {@link #currentPoint}, it uses the transformations from {@link #description}
     * and calculates each next point. Each next point is then used as the starting point
     * for the next iteration. For each new point, the method calls the {@code update()} method
     * from {@link ChaosGameObserver} interface.
     *
     * @param steps The number of iterations that will be performed
     * @param chaosGameObserver The observer for tracking each iteration
     */
    public void runSteps(int steps, ChaosGameObserver chaosGameObserver){
        Queue<Vector2D> pointQueue = new LinkedList<>();
        pointQueue.add(this.currentPoint);

        for (int i = 0; i < steps; i++) {

            List<Vector2D> addedList = new ArrayList<>();
            pointQueue.forEach(p -> this.description.getTransforms().forEach(t -> addedList.add(t.transform(p))));

            addedList.forEach(newPoint -> chaosGameObserver.update(this.canvas.putPixel(newPoint)));

            pointQueue.clear();
            pointQueue.addAll(addedList);
        }


    }
}
