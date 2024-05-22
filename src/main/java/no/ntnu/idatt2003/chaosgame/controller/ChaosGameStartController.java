package no.ntnu.idatt2003.chaosgame.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller class for {@link no.ntnu.idatt2003.chaosgame.scenes.ChaosGameStartScene}
 *
 * @author 10052
 * @version 1.0
 */
public class ChaosGameStartController {

    private Stage stage;
    private Button playButton;


    /**
     * Constructor for {@link ChaosGameStartController} with the stage.
     *
     * @param stage Stage to be used
     */
    public ChaosGameStartController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the play button for {@link ChaosGameStartController}.
     *
     * @param playButton The play button to be set.
     */
    public void setPlayButton(Button playButton) {
        this.playButton = playButton;
    }

    /**
     * Adds listener to play button in {@link ChaosGameStartController} to make it
     * switch scene to next scene.
     *
     */
    public void addButtonListeners() {
        playButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(1);
        });
    }

    /**
     * Updates the current scene with the provided root node and default dimensions.
     *
     * This method creates a new Scene with the specified root node and
     * dimensions of 600 by 600 pixels.
     * It then applies a CSS stylesheet for styling and sets the new scene on the
     * primary stage before displaying it.
     *
     * @param root The root node of the new scene to be displayed.
     */
    public void updateScene(Parent root) {
        Scene scene = new Scene(root, 600, 600);

        String css = this.getClass().getResource("/ChaosGameStartStylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }
}
