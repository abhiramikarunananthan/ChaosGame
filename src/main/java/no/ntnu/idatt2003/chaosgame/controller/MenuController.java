package no.ntnu.idatt2003.chaosgame.controller;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Controller class for {@link no.ntnu.idatt2003.chaosgame.scenes.MenuScene}
 *
 * @author 10052
 * @version 1.0
 */
public class MenuController {

    private final Stage stage;

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

    public void addButtonListeners() {
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
            File defaultDirectory = new File(System.getProperty("user.dir"));
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(defaultDirectory);
            fileChooser.setTitle("Open fractal text file");
            File file = fileChooser.showOpenDialog(stage);
            try {
                ChaosGameDescription chaosGameDescription = ChaosGameFileHandler.readFromFile(file.getPath());

                SceneController sceneController = new SceneController(stage);
                sceneController.setGameDescription(chaosGameDescription);
                sceneController.switchScene(3);

            } catch (FileNotFoundException | NullPointerException ignored) {

            }
        });
    }

    public void updateScene(Parent root) {
        Scene scene = new Scene(root, 600, 600);

        String css = this.getClass().getResource("/MenuStylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }
}
