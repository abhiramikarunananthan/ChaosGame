package no.ntnu.idatt2003.chaosgame;

import javafx.application.Application;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.Controller.ChaosGameStartController;
import no.ntnu.idatt2003.chaosgame.scenes.ChaosGameStartScene;

public class ChaosGameMain extends Application {
    @Override
    public void start(Stage stage) {

        ChaosGameStartController chaosGameStartController = new ChaosGameStartController(stage);
        ChaosGameStartScene chaosGameStartScene = new ChaosGameStartScene(chaosGameStartController);
        chaosGameStartScene.displayScene();

    }

    public static void main(String[] args) {
        launch(args);
    }


}