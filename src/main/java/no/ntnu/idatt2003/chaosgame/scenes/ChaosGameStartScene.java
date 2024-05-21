package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt2003.chaosgame.Controller.ChaosGameStartController;

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

    public ChaosGameStartScene(ChaosGameStartController chaosGameStartController) {

        this.chaosGameStartController = chaosGameStartController;

        createAndLayoutControls();

        chaosGameStartController.setPlayButton(playButton);
        chaosGameStartController.addButtonListeners();
    }

    public void displayScene() {
        chaosGameStartController.updateScene(root);
    }


    private void createAndLayoutControls() {
        Text title = new Text("ChaosGame");
        Text infoBox = new Text("This is ChaosGame, where you are able to create your \nown fractals, and display them at your pleasure");
        playButton = new Button("Play");

        root = new VBox(title, infoBox, playButton);

        title.getStyleClass().add("title");
        infoBox.getStyleClass().add("info");
    }

}
