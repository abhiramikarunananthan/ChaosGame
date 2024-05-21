package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.exceptions.NegativeDimensionsException;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.util.Random;

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
    public Random random;


    public ChaosGame(ChaosGameDescription description, int height, int width) throws NegativeDimensionsException {
        if (height < 0 || width < 0) {
            throw new NegativeDimensionsException("Width or height cannot be negative");
        }

        this.description = description;
        this.canvas = new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
        this.currentPoint = new Vector2D(0, 0);
        this.random = new Random();
    }

    public ChaosCanvas getCanvas(){
        return canvas;
    }
    public void runSteps(int steps, ChaosGameObserver chaosGameObserver){
        for (int i = 0; i < steps; i++) {
            Transform2D transform =
                    this.description
                            .getTransforms()
                            .get(this.random.nextInt(this.description.getTransforms().size()));
            this.currentPoint = transform.transform(this.currentPoint);

            chaosGameObserver.update(this.canvas.putPixel(this.currentPoint));

        }
    }
}
