package no.ntnu.idatt2003.chaosgame;

import javafx.application.Application;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.Controller.ChaosGameStartController;
import no.ntnu.idatt2003.chaosgame.scenes.ChaosGameStartScene;

/**
 * Main class for running the ChaosGame. The ChaosGame is
 * a game that allows the player to browse and modify different
 * types of fractals. The player is also able to import their own
 * fractals, or create them within the game.
 *
 * @author 10052
 * @version 1.0
 */
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