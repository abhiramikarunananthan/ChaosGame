package no.ntnu.idatt2003.chaosgame.Controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChaosGameController {

    private Stage stage;
    private Button playButton;

    public ChaosGameController(Stage stage) {
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

        stage.setScene(scene);
        stage.show();
    }
}
