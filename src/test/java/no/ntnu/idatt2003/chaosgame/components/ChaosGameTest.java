package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.exceptions.NegativeDimensionsException;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChaosGameTest {

    @Test
    void negativeDimensions(){
        Matrix2x2 matrix2x2Test1 = new Matrix2x2(0.5,0,0,0.5);
        Vector2D vector2DTest1 = new Vector2D(0.5,0);
        AffineTransform2D affineTransform2DTest = new AffineTransform2D(matrix2x2Test1,vector2DTest1);

        List<Transform2D> transformationsList1 = new ArrayList<>();
        transformationsList1.add(affineTransform2DTest);

        ChaosGameDescription chaosGameDescriptionTest1 = new ChaosGameDescription(transformationsList1, new Vector2D(0, 0), new Vector2D(1,1), Transformations.AFFINE2D);


        Assertions.assertThrows(NegativeDimensionsException.class, () -> new ChaosGame(chaosGameDescriptionTest1, -600, 600));
        Assertions.assertThrows(NegativeDimensionsException.class, () -> new ChaosGame(chaosGameDescriptionTest1, 600, -600));

    }

    @Test
    void descriptionIsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ChaosGame(null, 600, 600));
    }
}