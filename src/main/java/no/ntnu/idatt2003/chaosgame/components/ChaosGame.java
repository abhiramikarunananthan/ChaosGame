package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;

import java.util.Random;

public class ChaosGame {
    private ChaosCanvas canvas;
    private ChaosGameDescription description;
    private Vector2D currentPoint;
    public Random random;


    public ChaosGame(ChaosGameDescription description, int height, int width) {
        this.description = description;
        this.canvas = new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
        this.random = new Random();
    }

    public ChaosCanvas getCanvas(){
        return canvas;
    }
    public void runSteps(int steps){
        this.currentPoint = new Vector2D(0, 0);

        for (int i = 0; i < steps; i++) {
            int randomNum = random.nextInt(description.getTransforms().size());
            Transform2D transformation = description.getTransforms().get(randomNum);
            currentPoint = transformation.transform(currentPoint);
            Vector2D point = new Vector2D(currentPoint.getX0() * description.getMaxCoords().getX0(), currentPoint.getX1() * description.getMaxCoords().getX1());
            canvas.putPixel(point);
        }
    }
}
