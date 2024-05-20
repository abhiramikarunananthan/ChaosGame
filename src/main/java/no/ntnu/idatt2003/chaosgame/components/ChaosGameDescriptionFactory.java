package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.tensors.Complex;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.JuliaTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.util.ArrayList;
import java.util.List;

public class ChaosGameDescriptionFactory {

    public ChaosGameDescription createGameDescription(Fractals fractals){
        switch (fractals){
            case SIERPINSKI -> {
                List<Transform2D> transform2DList = new ArrayList<>();
                Transform2D sierpinskiTransformationOne = new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0));
                Transform2D sierpinskiTransformationTwo = new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.5, 0));
                Transform2D sierpinskiTransformationThree = new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.25, 0.5));

                transform2DList.add(sierpinskiTransformationOne);
                transform2DList.add(sierpinskiTransformationTwo);
                transform2DList.add(sierpinskiTransformationThree);
                return new ChaosGameDescription(transform2DList, new Vector2D(0,0), new Vector2D(1, 1), Transformations.AFFINE2D);
            }

            case BARNSLEY -> {
                List<Transform2D> transform2DList = new ArrayList<>();
                Transform2D barnsleyTransformationOne = new AffineTransform2D(new Matrix2x2(0,0,0,0.16), new Vector2D(0,0));
                Transform2D barnsleyTransformationTwo = new AffineTransform2D(new Matrix2x2(0.85,0.04,-0.04,0.85), new Vector2D(0,1.6));
                Transform2D barnsleyTransformationThree = new AffineTransform2D(new Matrix2x2(0.2,-0.26,0.23,0.22), new Vector2D(0,1.6));
                Transform2D barnsleyTransformationFour = new AffineTransform2D(new Matrix2x2(-0.15,0.28,0.26,0.24), new Vector2D(0,0.44));

                transform2DList.add(barnsleyTransformationOne);
                transform2DList.add(barnsleyTransformationTwo);
                transform2DList.add(barnsleyTransformationThree);
                transform2DList.add(barnsleyTransformationFour);

                return new ChaosGameDescription(transform2DList, new Vector2D(0,0), new Vector2D(2,2), Transformations.AFFINE2D);
            }
            case JULIASET -> {
                List<Transform2D> transform2DList = new ArrayList<>();
                Transform2D juliaTransformationOne = new JuliaTransform2D(new Complex(0.5, 0.5), 1);
                Transform2D juliaTransformationTwo = new JuliaTransform2D(new Complex(0.5, 0.5), -1);

                transform2DList.add(juliaTransformationOne);
                transform2DList.add(juliaTransformationTwo);

                return new ChaosGameDescription(transform2DList, new Vector2D(0, 0), new Vector2D(1.6,1.6), Transformations.JULIA);
            }
            default->{
                return null;
            }


        }
    }

}
