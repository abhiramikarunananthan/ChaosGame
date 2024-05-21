package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.Controller.SceneController;
import no.ntnu.idatt2003.chaosgame.Controller.StartController;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescriptionFactory;
import no.ntnu.idatt2003.chaosgame.components.Fractals;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

public class StartScene {
    private Button sierpinskiButton;
    private Button barnsleyButton;
    private Button juliaButton;
    private Button treeButton;
    private Button backButton;


    private VBox root;
    private StartController startController;

    public StartScene(StartController startController){

        this.startController = startController;
        createAndLayoutControls();

        startController.setSierpinskiButton(sierpinskiButton);
        startController.setBarnsleyButton(barnsleyButton);
        startController.setJuliaButton(juliaButton);
        startController.setBackButton(backButton);
        startController.setTreeButton(treeButton);

        startController.addButtonListeners();

    }

    public void displayScene(){
        startController.updateScene(root);
    }

    private void createAndLayoutControls(){
        Text title = new Text("Games");
        Text infoBox = new Text("Chose which fractal you want to display");
        sierpinskiButton = new Button("Sierpinski triangle");
        barnsleyButton = new Button("Barnsley fern");
        juliaButton = new Button("Julia set");
        treeButton = new Button("Tree");;
        backButton = new Button("Back");

        root = new VBox(title, infoBox, sierpinskiButton, barnsleyButton, juliaButton,treeButton, backButton);

        title.getStyleClass().add("title");
        infoBox.getStyleClass().add("description");
        backButton.getStyleClass().add("back");
    }
}
