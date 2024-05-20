package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.Controller.CreateFractalController;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateFractalScene {

    private VBox inputFieldsVBox;
    private Button saveButton;
    private MenuButton menuButton;
    private Button backButton;

    private VBox root;
    private CreateFractalController createFractalController;

    public CreateFractalScene(CreateFractalController createFractalController){
        this.createFractalController = createFractalController;
        createAndLayoutControls();
        createFractalController.setSaveButton(saveButton);
        createFractalController.setBackButton(backButton);

        createFractalController.setMenuButton(menuButton);
        createFractalController.populateMenuButton();
        createFractalController.setInputFieldVBox(inputFieldsVBox);
        createFractalController.addButtonListeners();

    }

    public void displayScene(){
        createFractalController.updateScene(root);
    }

    private void createAndLayoutControls(){
        inputFieldsVBox = new VBox();
        menuButton = new MenuButton("Transformation");
        saveButton = new Button("Save to file");
        backButton = new Button("Back");

        root = new VBox(menuButton, inputFieldsVBox, saveButton, backButton);
    }
}
