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

    public ChaosGame(ChaosGameDescription description, int height, int width) throws NegativeDimensionsException {
        if (height < 0 || width < 0) {
            throw new NegativeDimensionsException("Width or height cannot be negative");
        }

        this.description = description;
        this.canvas = new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
        this.currentPoint = new Vector2D(0, 0);
    }

    public ChaosCanvas getCanvas(){
        return canvas;
    }
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
