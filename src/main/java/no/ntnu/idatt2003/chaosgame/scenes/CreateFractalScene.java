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
    private Text matrixText;
    private VBox inputFieldsVBox;
    private TextField matrixInputFieldA00;
    private TextField matrixInputFieldA01;
    private TextField matrixInputFieldA10;
    private TextField matrixInputFieldA11;
    private Text vectorText;
    private TextField vectorInputFieldB0;
    private TextField vectorInputFieldB1;
    private Button addToListButton;
    private Text constantCText;
    private TextField constantCInputFieldReal;
    private TextField constantCInputFieldImaginary;
    private Text coordsMinText;
    private TextField minCoordsX;
    private TextField minCoordsY;
    private Text coordsMaxText;
    private TextField maxCoordsX;
    private TextField maxCoordsY;
    private Button saveButton;
    private MenuButton menuButton;
    private Button backButton;

    private VBox root;
    private CreateFractalController createFractalController;

    public CreateFractalScene(CreateFractalController createFractalController){
        this.createFractalController = createFractalController;
        createAndLayoutControls();

        createFractalController.setMatrixText(matrixText);
        createFractalController.setMatrixInputFieldA00(matrixInputFieldA00);
        createFractalController.setMatrixInputFieldA01(matrixInputFieldA01);
        createFractalController.setMatrixInputFieldA10(matrixInputFieldA10);
        createFractalController.setMatrixInputFieldA11(matrixInputFieldA11);

        createFractalController.setVectorText(vectorText);
        createFractalController.setVectorInputFieldB0(vectorInputFieldB0);
        createFractalController.setVectorInputFieldB1(vectorInputFieldB1);

        createFractalController.setConstantCText(constantCText);
        createFractalController.setConstantCInputFieldReal(constantCInputFieldReal);
        createFractalController.setConstantCInputFieldImaginary(constantCInputFieldImaginary);

        createFractalController.setCoordsMinText(coordsMinText);
        createFractalController.setMinCoordsX(minCoordsX);
        createFractalController.setMinCoordsY(minCoordsY);
        createFractalController.setCoordsMaxText(coordsMaxText);
        createFractalController.setMaxCoordsX(maxCoordsX);
        createFractalController.setMaxCoordsY(maxCoordsY);

        createFractalController.setAddToListButton(addToListButton);
        createFractalController.setSaveButton(saveButton);
        createFractalController.setBackButton(backButton);

        createFractalController.setMenuButton(menuButton);
        createFractalController.populateMenuButton();

        createFractalController.addButtonListeners();

    }

    public void displayScene(){
        createFractalController.updateScene(root);
    }

    private void createAndLayoutControls(){
        inputFieldsVBox = new VBox();
        menuButton = new MenuButton("Transformation");
        matrixText = new Text("Write the numbers wanted for the matrix");
        matrixInputFieldA00 = new TextField();
        matrixInputFieldA01 = new TextField();
        matrixInputFieldA10 = new TextField();
        matrixInputFieldA11 = new TextField();
        vectorText = new Text("Write the numbers wanted for the vector");
        vectorInputFieldB0 = new TextField();
        vectorInputFieldB1 = new TextField();
        addToListButton = new Button("Add to list");

        constantCText = new Text("Write the constant C");
        constantCInputFieldReal = new TextField();
        constantCInputFieldImaginary = new TextField();

        coordsMinText = new Text("Write the numbers wanted for minimum coordinates");
        minCoordsX = new TextField();
        minCoordsY = new TextField();
        coordsMaxText = new Text("Write the numbers wanted for maximum coordinates");
        maxCoordsX = new TextField();
        maxCoordsY = new TextField();
        saveButton = new Button("Save to file");
        backButton = new Button("Back");

        root = new VBox(menuButton, matrixText, matrixInputFieldA00, matrixInputFieldA01, matrixInputFieldA10, matrixInputFieldA11,
                vectorText, vectorInputFieldB0, vectorInputFieldB1,addToListButton,constantCText,constantCInputFieldReal,constantCInputFieldImaginary,
                coordsMinText, minCoordsX, minCoordsY, coordsMaxText, maxCoordsX, maxCoordsY, saveButton, backButton);
    }
}
