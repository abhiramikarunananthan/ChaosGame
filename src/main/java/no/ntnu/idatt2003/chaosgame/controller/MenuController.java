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

    /**
     * Constructor for {@link MenuController}.
     *
     * @param stage The primary stage of the application, which serves as the
     * main window for the UI.
     */
    public MenuController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the start button for this controller.
     *
     * @param startButton The start button to be set.
     */
    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }


    /**
     * Sets the import button to import new ChaosGame.
     *
     * @param importButton The import button to be set.
     */
    public void setImportButton(Button importButton) {
        this.importButton = importButton;
    }

    /**
     * Sets the button to create own fractal.
     *
     * @param createYourOwnButton The create-your-own button to be set.
     */
    public void setCreateYourOwnButton(Button createYourOwnButton) {
        this.createYourOwnButton = createYourOwnButton;
    }

    /**
     * Sets the quit button to quit the game.
     *
     * @param quitButton The quit button to be set.
     */
    public void setQuitButton(Button quitButton) {
        this.quitButton = quitButton;
    }


    /**
     * Adds action listeners to the buttons for handling user interactions.
     * Specifies actions to be performed when each button is clicked.
     */
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


    /**
     * Updates the scene with the provided root node and applies the menu stylesheet.
     *
     * @param root The root node of the scene to be updated.
     */
    public void updateScene(Parent root) {
        Scene scene = new Scene(root, 600, 600);

        String css = this.getClass().getResource("/MenuStylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }
}
