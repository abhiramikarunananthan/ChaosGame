package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt2003.chaosgame.controller.ChaosGameStartController;

/**
 * JavaFX scene for displaying the front page
 * of the game. This class is controlled by
 * {@link ChaosGameStartController}
 *
 * @author 10052
 * @version 1.0
 */
public class ChaosGameStartScene {

    VBox root;
    private Button playButton;
    private final ChaosGameStartController chaosGameStartController;


    /**
     * Constructor for {@link ChaosGameStartScene}
     * @param chaosGameStartController The controller to be used for the {@link ChaosGameStartScene}
     */
    public ChaosGameStartScene(ChaosGameStartController chaosGameStartController) {

        this.chaosGameStartController = chaosGameStartController;

        createAndLayoutControls();

        chaosGameStartController.setPlayButton(playButton);
        chaosGameStartController.addButtonListeners();
    }

    /**
     * method to update the scene to be displayed
     */
    public void displayScene() {
        chaosGameStartController.updateScene(root);
    }

    /**
     * Creates and lays out the controls for the menu scene.
     *
     * This method initializes the title, description, and buttons
     * for the menu scene and sets their styles.
     */
    private void createAndLayoutControls() {
        Text title = new Text("ChaosGame");
        Text infoBox = new Text("This is ChaosGame, where you are able to create your \nown fractals, and display them at your pleasure");
        playButton = new Button("Play");

        root = new VBox(title, infoBox, playButton);

        title.getStyleClass().add("title");
        infoBox.getStyleClass().add("info");
    }

}
