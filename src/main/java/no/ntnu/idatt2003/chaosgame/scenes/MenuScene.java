package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.SceneController;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuScene {

    private Scene scene;

    public MenuScene(Stage stage){

        Text title = new Text("Menu");
        Text infoBox = new Text("Chose one of the options below");
        Button startButton = new Button("START");
        Button importButton = new Button("Import your own fractal");
        Button createYourOwnButton = new Button("Create your own fractal");
        Button quitButton = new Button("Quit");


        startButton.setOnAction(actionEvent -> {
                    SceneController sceneController = new SceneController(stage);
                    sceneController.switchScene(2);
                });
        quitButton.setOnAction(actionEvent -> {
            Platform.exit();
        });

        createYourOwnButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(4);
        });
        importButton.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open fractal text file");
            File file = fileChooser.showOpenDialog(stage);
            try {
                ChaosGameDescription chaosGameDescription = ChaosGameFileHandler.readFromFile(file.getPath());

                SceneController sceneController = new SceneController(stage);
                sceneController.setGameDescription(chaosGameDescription);
                sceneController.setTransformations(Transformations.AFFINE2D);
                sceneController.switchScene(3);

            } catch (FileNotFoundException fnfe){

            }
        });

        VBox root = new VBox(title, infoBox, startButton, importButton, createYourOwnButton, quitButton);


        scene = new Scene(root, 600, 600);

    }

    public Scene getScene() {
        return scene;
    }
}
