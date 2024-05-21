package no.ntnu.idatt2003.chaosgame.Controller;

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

    public ChaosGameStartController(Stage stage) {
        this.stage = stage;
    }

    public void setPlayButton(Button playButton) {
        this.playButton = playButton;
    }

    public void addButtonListeners(){
        playButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(1);
        });
    }

    public void updateScene(Parent root){
        Scene scene = new Scene(root, 600, 600);

        String css = this.getClass().getResource("/ChaosGameStartStylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }
}
