package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.SceneController;

import java.io.IOException;

public class MenuScene {

    private Scene scene;

    public MenuScene(Stage stage){

        Text title = new Text("Menu");
        Text infoBox = new Text("Chose one of the options below");
        Button startButton = new Button("START");
        Button importButton = new Button("Import your own fractal");
        Button createYourOwnButton = new Button("Create your own fractal");


        startButton.setOnAction(actionEvent -> {
                    SceneController sceneController = new SceneController(stage);
                    sceneController.switchScene(2);
                });

        VBox root = new VBox(title, infoBox, startButton, importButton, createYourOwnButton);


        scene = new Scene(root, 600, 600);

    }

    public Scene getScene() {
        return scene;
    }
}
