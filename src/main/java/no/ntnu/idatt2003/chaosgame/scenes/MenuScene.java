package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt2003.chaosgame.Controller.MenuController;

/**
 * JavaFX scene for the main menu of
 * the game. This class is controlled by
 * {@link MenuController}
 *
 * @author 10052
 * @version 1.0
 */
public class MenuScene {
    private Button startButton;
    private Button importButton;
    private Button createYourOwnButton;
    private Button quitButton;

    private VBox root;

    private final MenuController menuController;

    public MenuScene(MenuController menuController) {
        this.menuController = menuController;

        createAndLayoutControls();

        menuController.setStartButton(startButton);
        menuController.setImportButton(importButton);
        menuController.setCreateYourOwnButton(createYourOwnButton);
        menuController.setQuitButton(quitButton);

        menuController.addButtonListeners();

    }

    public void displayScene() {
        menuController.updateScene(root);
    }

    private void createAndLayoutControls() {
        Text title = new Text("Menu");
        Text infoBox = new Text("Chose one of the options below");
        startButton = new Button("START");
        importButton = new Button("Import your own fractal");
        createYourOwnButton = new Button("Create your own fractal");
        quitButton = new Button("Quit");

        root = new VBox(title, infoBox, startButton, importButton, createYourOwnButton, quitButton);

        title.getStyleClass().add("title");
        infoBox.getStyleClass().add("description");
        quitButton.getStyleClass().add("quit");
    }
}
