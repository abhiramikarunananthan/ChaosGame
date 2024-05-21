package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.exceptions.MinimumBiggerThanMaximumException;
import no.ntnu.idatt2003.chaosgame.tensors.Complex;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.JuliaTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChaosGameDescriptionTest {

    ChaosGameDescription chaosGameDescriptionTest1;
    ChaosGameDescription chaosGameDescriptionTest2;
    List<Transform2D> transformationsList1;
    List<Transform2D> transformationsList2;

    @BeforeEach
    void setUp() {

        Matrix2x2 matrix2x2Test1 = new Matrix2x2(0.5,0,0,0.5);
        Vector2D vector2DTest1 = new Vector2D(0.5,0);
        AffineTransform2D affineTransform2DTest = new AffineTransform2D(matrix2x2Test1,vector2DTest1);

        transformationsList1 = new ArrayList<>();
        transformationsList1.add(affineTransform2DTest);

        transformationsList2 = new ArrayList<>();
        transformationsList2.add((v) -> matrix2x2Test1.multiply(v).add(vector2DTest1));

        chaosGameDescriptionTest1 = new ChaosGameDescription(transformationsList1, new Vector2D(0, 0), new Vector2D(1,1), Transformations.AFFINE2D);
        chaosGameDescriptionTest2 = new ChaosGameDescription(transformationsList2, new Vector2D(0,0), new Vector2D(1,1));

    }

    @Test
    void nullParametersInConstructor(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ChaosGameDescription(null,new Vector2D(0, 0),new Vector2D(1,1)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ChaosGameDescription(transformationsList1,null,new Vector2D(1,1)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ChaosGameDescription(transformationsList1,new Vector2D(0, 0),null));
        Assertions.assertDoesNotThrow(() -> new ChaosGameDescription(transformationsList1,new Vector2D(0, 0),new Vector2D(1,1),null));
    }

    @Test
    void minimumVectorBiggerThanMaximumVector(){
        Vector2D minCoordsTest1 = new Vector2D(1,0);
        Vector2D maxCoordsTest1 = new Vector2D(0,1);

        Vector2D minCoordsTest2 = new Vector2D(0,1);
        Vector2D maxCoordsTest2 = new Vector2D(1,0);

        Assertions.assertThrows(MinimumBiggerThanMaximumException.class, () -> new ChaosGameDescription(transformationsList1,minCoordsTest1,maxCoordsTest1));
        Assertions.assertThrows(MinimumBiggerThanMaximumException.class, () -> new ChaosGameDescription(transformationsList1,minCoordsTest2,maxCoordsTest2));
    }

    @Test
    void emptyTransformationList(){
        List<Transform2D> emptyList = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () -> new ChaosGameDescription(emptyList, new Vector2D(0, 0),new Vector2D(1,1)));
    }

    @Test
    void setTransforms() {
        List<Transform2D> list = new ArrayList<>();
        list.add(new JuliaTransform2D(new Complex(0.4,0.2),1));
        list.add(new JuliaTransform2D(new Complex(0.4,0.2),1));
        list.add(new JuliaTransform2D(new Complex(0.4,0.2),-1));

        Assertions.assertNotEquals(list.size(),chaosGameDescriptionTest1.getTransforms().size());

        chaosGameDescriptionTest1.setTransforms(list);

        Assertions.assertEquals(list.size(),chaosGameDescriptionTest1.getTransforms().size());

    }

    @Test
    void setMinCoords() {
        Vector2D minCoords = new Vector2D(0.2,0.2);

        Assertions.assertNotEquals(minCoords.getX0(),chaosGameDescriptionTest1.getMinCoords().getX0());
        Assertions.assertNotEquals(minCoords.getX1(),chaosGameDescriptionTest1.getMinCoords().getX1());

        chaosGameDescriptionTest1.setMinCoords(minCoords);

        Assertions.assertEquals(minCoords.getX0(),chaosGameDescriptionTest1.getMinCoords().getX0());
        Assertions.assertEquals(minCoords.getX1(),chaosGameDescriptionTest1.getMinCoords().getX1());

    }

    @Test
    void setMaxCoords() {
        Vector2D maxCoords = new Vector2D(0.2,0.2);

        Assertions.assertNotEquals(maxCoords.getX0(),chaosGameDescriptionTest1.getMinCoords().getX0());
        Assertions.assertNotEquals(maxCoords.getX1(),chaosGameDescriptionTest1.getMinCoords().getX1());

        chaosGameDescriptionTest1.setMinCoords(maxCoords);

        Assertions.assertEquals(maxCoords.getX0(),chaosGameDescriptionTest1.getMinCoords().getX0());
        Assertions.assertEquals(maxCoords.getX1(),chaosGameDescriptionTest1.getMinCoords().getX1());

    }

    @Test
    void getTransforms() {
        List<Transform2D> transform2DList = chaosGameDescriptionTest1.getTransforms();

        Assertions.assertEquals(transform2DList.get(0),chaosGameDescriptionTest1.getTransforms().get(0));
    }

    @Test
    void getMinCoords() {
        Vector2D minCoords = chaosGameDescriptionTest1.getMinCoords();

        Assertions.assertEquals(0,minCoords.getX0());
        Assertions.assertEquals(0,minCoords.getX1());

        Assertions.assertNotEquals(minCoords, chaosGameDescriptionTest1.getMinCoords());
    }

    @Test
    void getMaxCoords() {
        Vector2D maxCoords = chaosGameDescriptionTest1.getMaxCoords();

        Assertions.assertEquals(1,maxCoords.getX0());
        Assertions.assertEquals(1,maxCoords.getX1());

        Assertions.assertNotEquals(maxCoords, chaosGameDescriptionTest1.getMaxCoords());
    }

    @Test
    void getTransformation() {
        Transformations transformations1 = chaosGameDescriptionTest1.getTransformation();
        Transformations transformations2 = chaosGameDescriptionTest2.getTransformation();

        Assertions.assertEquals(Transformations.AFFINE2D,transformations1);
        Assertions.assertNull(transformations2);


    }
}