package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.SceneController;

public class StartScene {
    private Scene scene;

    public StartScene(Stage stage){

        Text title = new Text("Games");
        Text infoBox = new Text("Chose which fractal you want to display");
        Button sierpinskiButton = new Button("Sierpinski triangle");
        Button barnsleyButton = new Button("Barnsley fern");
        Button juliaButton = new Button("Julia set");

        sierpinskiButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(3);
        });

        VBox root = new VBox(title, infoBox, sierpinskiButton, barnsleyButton, juliaButton);


        scene = new Scene(root, 600, 600);

    }

    public Scene getScene() {
        return scene;
    }
}
