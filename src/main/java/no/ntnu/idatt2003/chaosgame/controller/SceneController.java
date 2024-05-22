package no.ntnu.idatt2003.chaosgame.controller;

import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.scenes.CanvasScene;
import no.ntnu.idatt2003.chaosgame.scenes.CreateFractalScene;
import no.ntnu.idatt2003.chaosgame.scenes.MenuScene;
import no.ntnu.idatt2003.chaosgame.scenes.StartScene;

/**
 * Controller class for switching between the
 * different scenes.
 *
 * @author 10052
 * @version 1.0
 */
public class SceneController {
    private final int MENU_SCENE = 1;
    private final int START_SCENE = 2;
    private final int CANVAS_SCENE = 3;
    private final int CREATE_FRACTAL = 4;
    private final Stage stage;
    private ChaosGameDescription chaosGameDescription;

    /**
     * Constructor for {@link SceneController} class.
     *
     * @param stage The stage of the application.
     */
    public SceneController(Stage stage) {
        this.stage = stage;
    }


    /**
     * Sets the chaos game description for this scene controller.
     *
     * @param chaosGameDescription The chaos game description to be set.
     */
    public void setGameDescription(ChaosGameDescription chaosGameDescription) {
        this.chaosGameDescription = chaosGameDescription;
    }

    /**
     * Switches the scene based on the provided scene number.
     *
     * @param sceneNumber The number representing the scene to switch to.
     *
     */
    public void switchScene(int sceneNumber) {
        switch (sceneNumber) {
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
