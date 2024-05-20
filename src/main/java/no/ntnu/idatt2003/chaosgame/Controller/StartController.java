package no.ntnu.idatt2003.chaosgame.Controller;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescriptionFactory;
import no.ntnu.idatt2003.chaosgame.components.Fractals;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.io.File;
import java.io.FileNotFoundException;

public class StartController {

    private Stage stage;
    private Button sierpinskiButton;
    private Button barnsleyButton;
    private Button juliaButton;
    private Button backButton;

    public StartController(Stage stage) {
        this.stage = stage;
    }

    public void setSierpinskiButton(Button sierpinskiButton) {
        this.sierpinskiButton = sierpinskiButton;
    }

    public void setBarnsleyButton(Button barnsleyButton) {
        this.barnsleyButton = barnsleyButton;
    }

    public void setJuliaButton(Button juliaButton) {
        this.juliaButton = juliaButton;
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    public void addButtonListeners(){

        sierpinskiButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.setGameDescription(new ChaosGameDescriptionFactory().createGameDescription(Fractals.SIERPINSKI));
            sceneController.setTransformations(Transformations.AFFINE2D);
            sceneController.switchScene(3);
        });

        barnsleyButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.setGameDescription(new ChaosGameDescriptionFactory().createGameDescription(Fractals.BARNSLEY));
            sceneController.setTransformations(Transformations.AFFINE2D);
            sceneController.switchScene(3);
        });

        juliaButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.setGameDescription(new ChaosGameDescriptionFactory().createGameDescription(Fractals.JULIASET));
            sceneController.setTransformations(Transformations.JULIA);
            sceneController.switchScene(3);
        });

        backButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(1);
        });
    }

    public void updateScene(Parent root){
        Scene scene = new Scene(root, 600, 600);

        String css = this.getClass().getResource("/StartStylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }
}
