package no.ntnu.idatt2003.chaosgame.Controller;

import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.scenes.CanvasScene;
import no.ntnu.idatt2003.chaosgame.scenes.CreateFractalScene;
import no.ntnu.idatt2003.chaosgame.scenes.MenuScene;
import no.ntnu.idatt2003.chaosgame.scenes.StartScene;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

public class SceneController {
    private Stage stage;
    private ChaosGameDescription chaosGameDescription;
    private final int MENU_SCENE = 1;
    private final int START_SCENE = 2;
    private final int CANVAS_SCENE = 3;
    private final int CREATE_FRACTAL = 4;


    public SceneController(Stage stage){
        this.stage = stage;
    }

    public void setGameDescription(ChaosGameDescription chaosGameDescription){
        this.chaosGameDescription = chaosGameDescription;
    }

    public void switchScene(int sceneNumber){
        switch (sceneNumber){
            case MENU_SCENE -> {
                MenuController menuController = new MenuController(stage);
                MenuScene menuScene = new MenuScene(menuController);
                menuScene.displayScene();
            }

            case START_SCENE -> {
                StartController startController = new StartController(stage);
                StartScene startScene = new StartScene(startController);
                startScene.displayScene();
            }

            case CANVAS_SCENE -> {
                CanvasController canvasController = new CanvasController(stage, chaosGameDescription.getTransformation(), chaosGameDescription);
                CanvasScene canvasScene = new CanvasScene(canvasController);
                canvasScene.displayScene();
            }

            case CREATE_FRACTAL -> {
                CreateFractalController createFractalController = new CreateFractalController(stage);
                CreateFractalScene createFractalScene = new CreateFractalScene(createFractalController);
                createFractalScene.displayScene();
            }
        }

    }
}
