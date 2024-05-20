package no.ntnu.idatt2003.chaosgame.Controller;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.io.File;
import java.io.FileNotFoundException;

public class MenuController {

    private Stage stage;

    private Button startButton;
    private Button importButton;
    private Button createYourOwnButton;
    private Button quitButton;

    public MenuController(Stage stage) {
        this.stage = stage;
    }

    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }

    public void setImportButton(Button importButton) {
        this.importButton = importButton;
    }

    public void setCreateYourOwnButton(Button createYourOwnButton) {
        this.createYourOwnButton = createYourOwnButton;
    }

    public void setQuitButton(Button quitButton) {
        this.quitButton = quitButton;
    }

    public void addButtonListeners(){
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
    }

    public void updateScene(Parent root){
        Scene scene = new Scene(root, 600, 600);

        stage.setScene(scene);
        stage.show();
    }
}
