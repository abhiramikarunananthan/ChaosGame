package no.ntnu.idatt2003.chaosgame.data;

import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.exceptions.IncorrectFileFormatException;
import no.ntnu.idatt2003.chaosgame.tensors.Complex;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.JuliaTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * A class for handling files. The class is meant
 * to interpret files to create objects of
 * the {@link ChaosGameDescription} class, or translate
 * {@link ChaosGameDescription} objects into files. The files
 * interpreted need to follow a specific format in order to work.
 *
 * @author 10052
 * @version 1.0
 */
public class ChaosGameFileHandler {

    /**
     * Creates an object of the {@link ChaosGameDescription} class from
     * the file specified by the filepath. The start of the file needs to follow the following format:
     * <p>
     * Transformation type<br>
     * minCoordX0, minCoordX1<br>
     * maxCoordX0, maxCoordX1<br>
     * </p>
     * <p>
     * The remaining part of the file contains the list of transformations, and
     * has its specific format based on the transformation type.
     *
     * @param path The file path of the file which needs to be interpreted
     * @return {@link ChaosGameDescription} object based on the information
     * from the interpreted file
     * @throws FileNotFoundException        If there was an error finding the file
     * @throws IncorrectFileFormatException If the format of the file does not fit
     *                                      the expected format
     */
    public static ChaosGameDescription readFromFile(String path) throws FileNotFoundException, IncorrectFileFormatException {
        File file;
        try {
            file = new File(path);
        } catch (NullPointerException e) {
            throw new NullPointerException("Invalid path");
        }
        Scanner reader = new Scanner(file);
        reader.useLocale(Locale.ENGLISH);
        List<String> stringList = new ArrayList<>();

        while (reader.hasNextLine()) {
            String newLine = reader.nextLine();
            String[] newLineSeperated = newLine.split("#");

            stringList.add(newLineSeperated[0].replaceAll("\\s", ""));
        }
        reader.close();

        String transformationString = stringList.get(0);
        String minCoordsString = stringList.get(1);
        String maxCoordsString = stringList.get(2);


        List<Transform2D> transformationsList = new ArrayList<>();


        String[] minCoordsStringLine = minCoordsString.split(",");
        String[] maxCoordsStringLine = maxCoordsString.split(",");

        boolean isAffineTransformation = transformationString.toUpperCase().equals(Transformations.AFFINE2D.toString());
        boolean isJuliaTransformation = transformationString.toUpperCase().equals(Transformations.JULIA.toString());

        if (!isAffineTransformation && !isJuliaTransformation) {
            throw new IncorrectFileFormatException("Transformation type not found");
        }

        for (int i = 3; i < stringList.size(); i++) {
            String[] lineArray = stringList.get(i).split(",");

            if (isAffineTransformation) {
                double a00 = Double.parseDouble(lineArray[0]);
                double a01 = Double.parseDouble(lineArray[1]);
                double a10 = Double.parseDouble(lineArray[2]);
                double a11 = Double.parseDouble(lineArray[3]);
                double b0 = Double.parseDouble(lineArray[4]);
                double b1 = Double.parseDouble(lineArray[5]);

                AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(a00, a01, a10, a11), new Vector2D(b0, b1));
                transformationsList.add(affineTransform2D);
            } else {
                double realPart = Double.parseDouble(lineArray[0]);
                double imaginaryPart = Double.parseDouble(lineArray[1]);

                JuliaTransform2D juliaTransformPositive = new JuliaTransform2D(new Complex(realPart, imaginaryPart), 1);
                JuliaTransform2D juliaTransformNegative = new JuliaTransform2D(new Complex(realPart, imaginaryPart), -1);
                transformationsList.add(juliaTransformPositive);
                transformationsList.add(juliaTransformNegative);
            }
        }

        if (isAffineTransformation) {

            return new ChaosGameDescription(transformationsList,
                    new Vector2D(Double.parseDouble(minCoordsStringLine[0]),
                            Double.parseDouble(minCoordsStringLine[1])),
                    new Vector2D(Double.parseDouble(maxCoordsStringLine[0]),
                            Double.parseDouble(maxCoordsStringLine[1])), Transformations.AFFINE2D);

        } else {

            return new ChaosGameDescription(transformationsList,
                    new Vector2D(Double.parseDouble(minCoordsStringLine[0]),
                            Double.parseDouble(minCoordsStringLine[1])),
                    new Vector2D(Double.parseDouble(maxCoordsStringLine[0]),
                            Double.parseDouble(maxCoordsStringLine[1])), Transformations.JULIA);

        }

    }

    /**
     * Translates the specified {@link ChaosGameDescription} to a file with
     * the specified path. The format of the created file follows the format
     * expected from the {@link #readFromFile(String)} method.
     *
     * @param description The {@link ChaosGameDescription} which will be translated
     * @param path        The path of the newly created file
     * @throws IOException If is a problem with file creation
     */
    public static void writeToFile(ChaosGameDescription description, String path) throws IOException {
        File file = new File(path);

        FileWriter fileWriter = new FileWriter(file);

        List<String> lineList = new ArrayList<>();
        if (description.getTransformation() == Transformations.AFFINE2D) {
            lineList.add("Affine2D");
        } else if (description.getTransformation() == Transformations.JULIA) {
            lineList.add("Julia");
        }


        lineList.add((description.getMinCoords().getX0() + "," + description.getMinCoords().getX1()));
        lineList.add((description.getMaxCoords().getX0() + "," + description.getMaxCoords().getX1()));

        for (int i = 0; i < description.getTransforms().size(); i++) {

            if (description.getTransformation() == Transformations.AFFINE2D) {
                AffineTransform2D affineTransform2D = (AffineTransform2D) description.getTransforms().get(i);
                lineList.add(affineTransform2D.toString());
            } else if (description.getTransformation() == Transformations.JULIA) {
                JuliaTransform2D juliaTransform2D = (JuliaTransform2D) description.getTransforms().get(i);
                lineList.add(juliaTransform2D.toString());
            }


        }
        String finalString = "";

        for (String s : lineList) {
            finalString += s;
            finalString += "\n";
        }

        fileWriter.write(finalString);
        fileWriter.close();

    }


}
