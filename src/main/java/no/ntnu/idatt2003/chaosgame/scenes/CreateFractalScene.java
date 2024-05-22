package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import no.ntnu.idatt2003.chaosgame.controller.CreateFractalController;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;

/**
 * JavaFX scene for displaying an interface
 * for creating your own fractals. This class
 * is controlled by {@link CreateFractalController}
 *
 * @author 10052
 * @version 1.0
 */
public class CreateFractalScene {

    ListView<AffineTransform2D> affineTransform2DListView;
    private VBox inputFieldsVBox;
    private Button saveButton;
    private MenuButton menuButton;
    private Button backButton;
    private HBox root;
    private final CreateFractalController createFractalController;

    /**
     * Constructor for {@link CreateFractalScene}
     * @param createFractalController The controller to be used for the {@link CreateFractalScene}
     */
    public CreateFractalScene(CreateFractalController createFractalController) {
        this.createFractalController = createFractalController;
        createAndLayoutControls();
        createFractalController.setSaveButton(saveButton);
        createFractalController.setBackButton(backButton);

        createFractalController.setMenuButton(menuButton);
        createFractalController.populateMenuButton();
        createFractalController.setInputFieldVBox(inputFieldsVBox);
        createFractalController.setAffineTransform2DListView(affineTransform2DListView);
        createFractalController.addButtonListeners();

    }
    /**
     * method to update the scene to be displayed
     */
    public void displayScene() {
        createFractalController.updateScene(root);
    }
    /**
     * Creates and lays out the controls for the menu scene.
     *
     * This method initializes the title, description, and buttons
     * for the menu scene and sets their styles.
     */
    private void createAndLayoutControls() {
        inputFieldsVBox = new VBox();
        menuButton = new MenuButton("Transformation");
        saveButton = new Button("Save to file");
        backButton = new Button("Back");
        affineTransform2DListView = new ListView<>();

        affineTransform2DListView.setVisible(false);
        saveButton.setVisible(false);

        VBox leftSideVBox = new VBox(menuButton, inputFieldsVBox, saveButton, backButton);

        root = new HBox(leftSideVBox, affineTransform2DListView);
        backButton.getStyleClass().add("back");
    }
}
