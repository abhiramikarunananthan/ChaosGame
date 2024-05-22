package no.ntnu.idatt2003.chaosgame.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescriptionFactory;
import no.ntnu.idatt2003.chaosgame.components.Fractals;

/**
 * Controller class for {@link no.ntnu.idatt2003.chaosgame.scenes.StartScene}
 *
 * @author 10052
 * @version 1.0
 */
public class StartController {

    private final Stage stage;
    private Button sierpinskiButton;
    private Button barnsleyButton;
    private Button juliaButton;
    private Button treeButton;
    private Button backButton;

    /**
     * Constructor for {@link StartController} class.
     *
     * @param stage The stage of the application.
     */
    public StartController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Set-method for the Sierpinski fractal button.
     *
     * @param sierpinskiButton The Sierpinski fractal button.
     */
    public void setSierpinskiButton(Button sierpinskiButton) {
        this.sierpinskiButton = sierpinskiButton;
    }

    /**
     * Set-method for the Barnsley fern fractal button.
     *
     * @param barnsleyButton The Barnsley fern fractal button.
     */
    public void setBarnsleyButton(Button barnsleyButton) {
        this.barnsleyButton = barnsleyButton;
    }

    /**
     * Set-method for the Julia fractal button.
     *
     * @param juliaButton The Julia fractal button.
     */
    public void setJuliaButton(Button juliaButton) {
        this.juliaButton = juliaButton;
    }

    /**
     * Set-method for the back button for navigating to the main menu.
     *
     * @param backButton The back button.
     */
    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    /**
     * Set-method for the Tree fractal button.
     *
     * @param treeButton The Tree fractal button.
     */
    public void setTreeButton(Button treeButton) {
        this.treeButton = treeButton;
    }

    /**
     * Adds action listeners to the buttons.
     *
     * These listeners handle the button clicks and switch
     * to the corresponding fractal scene or back to the main menu.
     */
    public void addButtonListeners() {

        sierpinskiButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.setGameDescription(new ChaosGameDescriptionFactory().createGameDescription(Fractals.SIERPINSKI));
            sceneController.switchScene(3);
        });

        barnsleyButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.setGameDescription(new ChaosGameDescriptionFactory().createGameDescription(Fractals.BARNSLEY));
            sceneController.switchScene(3);
        });

        juliaButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.setGameDescription(new ChaosGameDescriptionFactory().createGameDescription(Fractals.JULIASET));
            sceneController.switchScene(3);
        });

        treeButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.setGameDescription(new ChaosGameDescriptionFactory().createGameDescription(Fractals.TREE));
            sceneController.switchScene(3);
        });

        backButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(1);
        });
    }


    /**
     * Updates the scene with the specified root node.
     *
     * @param root The root node of the scene.
     */
    public void updateScene(Parent root) {
        Scene scene = new Scene(root, 600, 600);

        String css = this.getClass().getResource("/StartStylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }
}
