package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt2003.chaosgame.controller.StartController;

/**
 * JavaFX scene for displaying the different
 * predefined fractals, after pressing start.
 * This class is controlled by {@link StartController}
 *
 * @author 10052
 * @version 1.0
 */
public class StartScene {
    private Button sierpinskiButton;
    private Button barnsleyButton;
    private Button juliaButton;
    private Button treeButton;
    private Button backButton;


    private VBox root;
    private final StartController startController;

    /**
     * Constructor for {@link StartScene}
     * @param startController The controller to be used for the {@link StartScene}
     */
    public StartScene(StartController startController) {

        this.startController = startController;
        createAndLayoutControls();

        startController.setSierpinskiButton(sierpinskiButton);
        startController.setBarnsleyButton(barnsleyButton);
        startController.setJuliaButton(juliaButton);
        startController.setBackButton(backButton);
        startController.setTreeButton(treeButton);

        startController.addButtonListeners();

    }

    /**
     * method to update the scene to be displayed
     */
    public void displayScene() {
        startController.updateScene(root);
    }


    /**
     * Creates and lays out the controls for the menu scene.
     *
     * This method initializes the title, description, and buttons
     * for the menu scene and sets their styles.
     */
    private void createAndLayoutControls() {
        Text title = new Text("Games");
        Text infoBox = new Text("Chose which fractal you want to display");
        sierpinskiButton = new Button("Sierpinski triangle");
        barnsleyButton = new Button("Barnsley fern");
        juliaButton = new Button("Julia set");
        treeButton = new Button("Tree");
        backButton = new Button("Back");

        root = new VBox(title, infoBox, sierpinskiButton, barnsleyButton, juliaButton, treeButton, backButton);

        title.getStyleClass().add("title");
        infoBox.getStyleClass().add("description");
        backButton.getStyleClass().add("back");
    }
}
