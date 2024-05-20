package no.ntnu.idatt2003.chaosgame.Controller;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateFractalController {

    private Stage stage;
    private Text matrixText;
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
    private VBox inputFieldVBox;

    private Transformations currentTransformation;
    private List<Transform2D> transform2DList;

    public CreateFractalController(Stage stage) {
        this.stage = stage;
        this.transform2DList = new ArrayList<>();

    }

    public void addButtonListeners(){

        saveButton.setOnAction(actionEvent ->{
            ChaosGameDescription chaosGameDescription = new ChaosGameDescription(
                    transform2DList,
                    new Vector2D(Double.parseDouble(minCoordsX.getText()), Double.parseDouble(minCoordsY.getText())),
                    new Vector2D(Double.parseDouble(maxCoordsX.getText()), Double.parseDouble(maxCoordsY.getText())), currentTransformation);

            try {
                ChaosGameFileHandler.writeToFile(chaosGameDescription, "C:\\Users\\k_nal\\Documents\\test.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        backButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(1);
        });

        for (MenuItem menuItem: menuButton.getItems()) {
            menuItem.setOnAction(actionEvent -> {
                MenuItem item = (MenuItem)actionEvent.getSource();
                menuButton.setText(item.getText());
                currentTransformation = Transformations.valueOf(item.getText());
                updateContents(inputFieldVBox);
            });
        }

    }

    public void updateScene(Parent root){
        Scene scene = new Scene(root, 600, 600);

        stage.setScene(scene);
        stage.show();
    }

    public void populateMenuButton(){
        for (Transformations transformation: Transformations.values()) {
            menuButton.getItems().add(new MenuItem(transformation.name()));
        }
    }

    private void updateContents(VBox inputFieldsVBox){
        inputFieldsVBox.getChildren().clear();
        switch (currentTransformation){
            case AFFINE2D -> {
                matrixText = new Text("Write the numbers wanted for the matrix");
                matrixInputFieldA00 = new TextField();
                matrixInputFieldA01 = new TextField();
                matrixInputFieldA10 = new TextField();
                matrixInputFieldA11 = new TextField();
                vectorText = new Text("Write the numbers wanted for the vector");
                vectorInputFieldB0 = new TextField();
                vectorInputFieldB1 = new TextField();
                addToListButton = new Button("Add to list");

                addToListButton.setOnAction(actionEvent -> transform2DList.add(new AffineTransform2D(
                        new Matrix2x2(Double.parseDouble(matrixInputFieldA00.getText()), Double.parseDouble(matrixInputFieldA01.getText()), Double.parseDouble(matrixInputFieldA10.getText()), Double.parseDouble(matrixInputFieldA11.getText()))
                        , new Vector2D(Double.parseDouble(vectorInputFieldB0.getText()), Double.parseDouble(vectorInputFieldB1.getText())))
                ));

                inputFieldsVBox.getChildren().addAll(matrixText,matrixInputFieldA00,matrixInputFieldA01,matrixInputFieldA10,matrixInputFieldA11,
                        vectorText,vectorInputFieldB0,vectorInputFieldB1,addToListButton);

            }
            case JULIA -> {
                constantCText = new Text("Write the constant C");
                constantCInputFieldReal = new TextField();
                constantCInputFieldImaginary = new TextField();

                inputFieldsVBox.getChildren().addAll(constantCText,constantCInputFieldReal,constantCInputFieldImaginary);
            }

        }

        coordsMinText = new Text("Write the numbers wanted for minimum coordinates");
        minCoordsX = new TextField();
        minCoordsY = new TextField();
        coordsMaxText = new Text("Write the numbers wanted for maximum coordinates");
        maxCoordsX = new TextField();
        maxCoordsY = new TextField();

        inputFieldsVBox.getChildren().addAll(coordsMinText,minCoordsX,minCoordsY,coordsMaxText,maxCoordsX,maxCoordsY);
    }



    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    public void setMenuButton(MenuButton menuButton) {
        this.menuButton = menuButton;
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    public void setInputFieldVBox(VBox inputFieldVBox) {
        this.inputFieldVBox = inputFieldVBox;
    }
}
