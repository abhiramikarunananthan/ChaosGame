package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.Controller.MenuController;
import no.ntnu.idatt2003.chaosgame.Controller.SceneController;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.io.File;
import java.io.FileNotFoundException;

public class MenuScene {

    private Scene scene;

    private Text title;
    private Text infoBox;
    private Button startButton;
    private Button importButton;
    private Button createYourOwnButton;
    private Button quitButton;

    private VBox root;

    private MenuController menuController;
    public MenuScene(MenuController menuController){
        this.menuController = menuController;

        createAndLayoutControls();

        menuController.setStartButton(startButton);
        menuController.setImportButton(importButton);
        menuController.setCreateYourOwnButton(createYourOwnButton);
        menuController.setQuitButton(quitButton);

        menuController.addButtonListeners();

    }

    public void displayScene(){
        menuController.updateScene(root);
    }

    private void createAndLayoutControls(){
        title = new Text("Menu");
        infoBox = new Text("Chose one of the options below");
        startButton = new Button("START");
        importButton = new Button("Import your own fractal");
        createYourOwnButton = new Button("Create your own fractal");
        quitButton = new Button("Quit");

        root = new VBox(title, infoBox, startButton, importButton, createYourOwnButton, quitButton);
    }
}
