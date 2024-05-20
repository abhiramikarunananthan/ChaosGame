package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt2003.chaosgame.Controller.ChaosGameController;

public class ChaosGameScene {

    private Button playButton;
    VBox root;

    private ChaosGameController chaosGameController;
    public ChaosGameScene(ChaosGameController chaosGameController){

        this.chaosGameController = chaosGameController;

        createAndLayoutControls();

        chaosGameController.setPlayButton(playButton);
        chaosGameController.addButtonListeners();
    }

    public void displayScene(){
        chaosGameController.updateScene(root);
    }


    private void createAndLayoutControls(){
        Text title = new Text("ChaosGame");
        Text infoBox = new Text("This is a chaos game");
        playButton = new Button("Play");

        root = new VBox(title, infoBox, playButton);
    }

}
