package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt2003.chaosgame.Controller.ChaosGameStartController;

public class ChaosGameStartScene {

    private Button playButton;
    VBox root;

    private ChaosGameStartController chaosGameStartController;
    public ChaosGameStartScene(ChaosGameStartController chaosGameStartController){

        this.chaosGameStartController = chaosGameStartController;

        createAndLayoutControls();

        chaosGameStartController.setPlayButton(playButton);
        chaosGameStartController.addButtonListeners();
    }

    public void displayScene(){
        chaosGameStartController.updateScene(root);
    }


    private void createAndLayoutControls(){
        Text title = new Text("ChaosGame");
        Text infoBox = new Text("This is ChaosGame, where you are able to create your \nown fractals, and display them at your pleasure");
        playButton = new Button("Play");

        root = new VBox(title, infoBox, playButton);

        title.getStyleClass().add("title");
        infoBox.getStyleClass().add("info");
    }

}
