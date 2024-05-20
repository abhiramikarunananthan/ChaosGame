package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.util.Random;

public class ChaosGame {
    private ChaosCanvas canvas;
    private ChaosGameDescription description;
    private Vector2D currentPoint;
    public Random random;


    public ChaosGame(ChaosGameDescription description, int height, int width) {
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
