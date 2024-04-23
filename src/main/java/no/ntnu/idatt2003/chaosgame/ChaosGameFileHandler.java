package no.ntnu.idatt2003.chaosgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChaosGameFileHandler extends ChaosGameDescription {


    public ChaosGameFileHandler(List<Transform2D> transforms, Vector2D minCoords, Vector2D maxCoords) {
        super(transforms, minCoords, maxCoords);
    }

    public ChaosGameDescription readFromFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner reader = new Scanner(file);
        List<String> stringList = new ArrayList<>();

        while(reader.hasNextLine()){
            stringList.add(reader.nextLine());
        }
        reader.close();

        String transformationString = stringList.getFirst();
        String minCoordsString =  stringList.get(1);
        String maxCoordsString =  stringList.get(2);


        List<Transform2D> transformationsList = new ArrayList<>();

        String[] minCoordsStringLine = minCoordsString.split(",");
        String[] maxCoordsStringLine = minCoordsString.split(",");





        for (int i = 3; i < stringList.size(); i++) {
            String[] lineArray = stringList.get(i).split(",");

            if(transformationString.toUpperCase().equals(Transformations.AFFINE2D.toString())) {
                double a00 = Double.parseDouble(lineArray[0]);
                double a01 = Double.parseDouble(lineArray[1]);
                double a10 = Double.parseDouble(lineArray[2]);
                double a11 = Double.parseDouble(lineArray[3]);
                double b0 = Double.parseDouble(lineArray[4]);
                double b1 = Double.parseDouble(lineArray[5]);

                AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(a00, a01, a10, a11), new Vector2D(b0, b1));
                transformationsList.add(affineTransform2D);
            } else if(transformationString.toUpperCase().equals(Transformations.JULIA.toString())) {
                //JuliaTransform juliaTransform = new JuliaTransform(new Matrix2x2(a00, a01, a10, a11), new Vector2D(b0, b1));
                //transformationsList.add(affineTransform2D);
            }

        }

        return  new ChaosGameDescription(transformationsList,
                new Vector2D (Double.parseDouble(minCoordsStringLine[0]),
                        Double.parseDouble(minCoordsStringLine[1])),
                new Vector2D (Double.parseDouble(maxCoordsStringLine[0]),
                        Double.parseDouble(maxCoordsStringLine[1])));



    }

    public void writeToFile(ChaosGameDescription description, String path) throws IOException {
        File file = new File(path);

        FileWriter fileWriter = new FileWriter(file);

        List<String> lineList = new ArrayList<>();
        lineList.add("Affine2D");
        lineList.add((description.getMinCoords().getX0() + "," + description.getMinCoords().getX1()));
        lineList.add((description.getMaxCoords().getX0() + "," + description.getMaxCoords().getX1()));

        for (int i = 0; i < description.getTransforms().size(); i++) {
            AffineTransform2D affineTransform2D = (AffineTransform2D) description.getTransforms().get(i);
            lineList.add(affineTransform2D.toString());
        }
        String finalString = "";

        for(String s: lineList){
            finalString += s;
            finalString += "\n";
        }

        fileWriter.write(finalString);
        fileWriter.close();
    }
}
