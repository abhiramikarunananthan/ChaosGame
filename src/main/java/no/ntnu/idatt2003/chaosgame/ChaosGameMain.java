package no.ntnu.idatt2003.chaosgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGame;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;

import java.io.IOException;
import java.util.Scanner;

public class ChaosGameMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChaosGameMain.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        ChaosGameDescription description = null;
        while (true){


            System.out.println("Choose one of the following options you would like to perform:");
            System.out.println("[1] Import file");
            System.out.println("[2] Export file");
            Scanner userInput = new Scanner(System.in);
            int chosenOption = userInput.nextInt();
            userInput.nextLine();
            switch (chosenOption){
                case (1):
                    System.out.println("Input file path");
                    String filePathRead = userInput.nextLine();

                    try{
                        description = ChaosGameFileHandler.readFromFile(filePathRead);
                    }catch (Exception e){
                        System.out.println("You did enter correct path format");
                    }

                    System.out.println("How many iterations do you want?");
                    int iterations = userInput.nextInt();
                    userInput.nextLine();

                    ChaosGame chaosGame = new ChaosGame(description, (int) description.getMaxCoords().getX0(), (int) description.getMaxCoords().getX1());
                    chaosGame.runSteps(iterations);

                    for (int i = (int) description.getMaxCoords().getX1(); i >= 0; i--) {
                        for (int j = 0; j < description.getMaxCoords().getX0(); j++) {
                            Vector2D vector = new Vector2D(j, i);
                            int point = chaosGame.getCanvas().getPixel(vector);

                            if (point == 0) {
                                System.out.print(" ");
                            } else {
                                System.out.print("X");
                            }

                        }
                        System.out.println();
                    }
                    break;

                case (2):
                    System.out.println("Input file path");
                    String filePathWrite = userInput.nextLine();
                    try{
                        ChaosGameFileHandler.writeToFile(description, filePathWrite);
                    } catch (Exception e ){
                        System.out.println("Path not found");
                    }
                    break;

            }





        }



/*
        int width = 360;
        int height = 120;

        AffineTransform2D affineTransform2D1 = new AffineTransform2D(new Matrix2x2(0.5,0,0,0.5), new Vector2D(0, 0));
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(new Matrix2x2(0.5,0,0,0.5), new Vector2D(0.5, 0));
        AffineTransform2D affineTransform2D3 = new AffineTransform2D(new Matrix2x2(0.5,0,0,0.5), new Vector2D(0.25, 0.5));

        AffineTransform2D barnsley1 = new AffineTransform2D(new Matrix2x2(0,0,0,0.16), new Vector2D(0, 0));
        AffineTransform2D barnsley2 = new AffineTransform2D(new Matrix2x2(0.85,0.04,-0.04,0.85), new Vector2D(0, 1.6));
        AffineTransform2D barnsley3 = new AffineTransform2D(new Matrix2x2(0.2,-0.26,0.23,0.22), new Vector2D(0, 1.6));
        AffineTransform2D barnsley4 = new AffineTransform2D(new Matrix2x2(-0.15,0.28,0.26,0.24), new Vector2D(0, 0.44));


        List<Transform2D> listOfTranformation = new ArrayList<>();
        listOfTranformation.add(affineTransform2D1);
        listOfTranformation.add(affineTransform2D2);
        listOfTranformation.add(affineTransform2D3);

        List<Transform2D> listOfBarnsleyTransformations = new ArrayList<>();
        listOfBarnsleyTransformations.add(barnsley1);
        listOfBarnsleyTransformations.add(barnsley2);
        listOfBarnsleyTransformations.add(barnsley3);
        listOfBarnsleyTransformations.add(barnsley4);

        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(listOfTranformation, new Vector2D(0, 0), new Vector2D(width , height));
        ChaosGame chaosGame = new ChaosGame(chaosGameDescription, height, width);
        chaosGame.runSteps(50000);

        for (int i = height; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                Vector2D vector = new Vector2D(j,i);
                int point = chaosGame.getCanvas().getPixel(vector);

                if(point == 0){
                    System.out.print(" ");
                } else {
                    System.out.print("X");
                }

            }
            System.out.println();
        }
*/
    }


}