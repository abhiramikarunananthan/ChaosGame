package no.ntnu.idatt2003.chaosgame;

import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.scenes.CanvasScene;
import no.ntnu.idatt2003.chaosgame.scenes.MenuScene;
import no.ntnu.idatt2003.chaosgame.scenes.StartScene;

public class SceneController {
    private Stage stage;
    private final int MENU_SCENE = 1;
    private final int START_SCENE = 2;
    private final int CANVAS_SCENE = 3;



    public SceneController(Stage stage){
        this.stage = stage;
    }

    public void switchScene(int sceneNumber){
        switch (sceneNumber){
            case MENU_SCENE -> {
                MenuScene menuScene = new MenuScene(stage);
                stage.setScene(menuScene.getScene());
                break;
            }
            case START_SCENE -> {
                StartScene startScene = new StartScene(stage);
                stage.setScene(startScene.getScene());
                break;
            }
            case CANVAS_SCENE -> {
                CanvasScene canvasScene = new CanvasScene(stage);
                stage.setScene(canvasScene.getScene());
                break;
            }
        }

    }
}
