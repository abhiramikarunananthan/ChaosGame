package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt2003.chaosgame.Controller.ChaosGameController;

public class ChaosGameScene {

    private Text title;
    private Text infoBox;
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
        title = new Text("ChaosGame");
        infoBox = new Text("This is a chaos game");
        playButton = new Button("Play");

        root = new VBox(title, infoBox, playButton);
    }

}
