package no.ntnu.idatt2003.chaosgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

        Text title = new Text("ChaosGame");
        Text infoBox = new Text("This is a chaos game");
        Button playButton = new Button("Play");

        playButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(1);
        });


        VBox root = new VBox(title, infoBox, playButton);


        Scene scene = new Scene(root, 600, 600);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        launch(args);

        ChaosGameDescription description = null;
        int width = 500;
        int height = 500;

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

                    ChaosGame chaosGame = new ChaosGame(description, height,width);
                    chaosGame.runSteps(iterations);
                    int[][] canvasArray = chaosGame.getCanvas().getCanvasArray();

                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            if (canvasArray[i][j] == 0){
                                System.out.print(" ");
                            }  else {
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



    }


}