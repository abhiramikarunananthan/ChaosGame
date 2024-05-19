package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.SceneController;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescriptionFactory;
import no.ntnu.idatt2003.chaosgame.components.Fractals;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

public class StartScene {
    private Scene scene;

    public StartScene(Stage stage){

        Text title = new Text("Games");
        Text infoBox = new Text("Chose which fractal you want to display");
        Button sierpinskiButton = new Button("Sierpinski triangle");
        Button barnsleyButton = new Button("Barnsley fern");
        Button juliaButton = new Button("Julia set");
        Button backButton = new Button("Back");

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


        VBox root = new VBox(title, infoBox, sierpinskiButton, barnsleyButton, juliaButton, backButton);


        scene = new Scene(root, 600, 600);

    }

    public Scene getScene() {
        return scene;
    }
}
