package no.ntnu.idatt2003.chaosgame;

import javafx.application.Application;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.Controller.ChaosGameController;
import no.ntnu.idatt2003.chaosgame.scenes.ChaosGameScene;

import java.io.IOException;

public class ChaosGameMain extends Application {
    @Override
    public void start(Stage stage) {

        ChaosGameController chaosGameController = new ChaosGameController(stage);
        ChaosGameScene chaosGameScene = new ChaosGameScene(chaosGameController);
        chaosGameScene.displayScene();

    }

    public static void main(String[] args) {
        launch(args);
    }


}