package no.ntnu.idatt2003.chaosgame.Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;
import no.ntnu.idatt2003.chaosgame.tensors.Complex;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.JuliaTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateFractalController {

    private Stage stage;
    private List<TextField> matrixInputFields;
    private List<TextField> vectorInputFields;

    private List<TextField> constantInputFields;
    private List<TextField> coordsInputFields;

    ListView<AffineTransform2D> affineTransform2DListView;


    private Button saveButton;
    private MenuButton menuButton;
    private Button backButton;
    private VBox inputFieldVBox;

    private Transformations currentTransformation;
    private List<Transform2D> transform2DList;

    public CreateFractalController(Stage stage) {
        this.stage = stage;
        this.transform2DList = new ArrayList<>();
        this.matrixInputFields = new ArrayList<>();
        this.vectorInputFields = new ArrayList<>();
        this.constantInputFields = new ArrayList<>();
        this.coordsInputFields = new ArrayList<>();
    }

    public void addButtonListeners(){

        saveButton.setOnAction(actionEvent ->{
            try{
                if(currentTransformation == Transformations.AFFINE2D){
                    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(transform2DList,
                            new Vector2D(Double.parseDouble(coordsInputFields.get(0).getText()),Double.parseDouble(coordsInputFields.get(1).getText())),
                            new Vector2D(Double.parseDouble(coordsInputFields.get(2).getText()),Double.parseDouble(coordsInputFields.get(3).getText())),
                            Transformations.AFFINE2D);

                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    directoryChooser.setTitle("Save");

                    File defaultDirectory = new File(System.getProperty("user.dir"));
                    directoryChooser.setInitialDirectory(defaultDirectory);

                    File selectedDirectory = directoryChooser.showDialog(stage);

                    ChaosGameFileHandler.writeToFile(chaosGameDescription, selectedDirectory.getPath() + "\\test.txt");

                }else if (currentTransformation == Transformations.JULIA){
                    List<Transform2D> transform2DList = new ArrayList<>();

                    transform2DList.add(new JuliaTransform2D(
                            new Complex(Double.parseDouble(constantInputFields.get(0).getText()),
                                    Double.parseDouble(constantInputFields.get(1).getText())), 1));

                    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(transform2DList,
                            new Vector2D(Double.parseDouble(coordsInputFields.get(0).getText()),Double.parseDouble(coordsInputFields.get(1).getText())),
                            new Vector2D(Double.parseDouble(coordsInputFields.get(2).getText()),Double.parseDouble(coordsInputFields.get(3).getText())),
                            Transformations.JULIA);

                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    directoryChooser.setTitle("Save");

                    File defaultDirectory = new File(System.getProperty("user.dir"));
                    directoryChooser.setInitialDirectory(defaultDirectory);

                    File selectedDirectory = directoryChooser.showDialog(stage);

                    ChaosGameFileHandler.writeToFile(chaosGameDescription, selectedDirectory.getPath() + "\\test.txt");
                }

            }catch (NullPointerException | NumberFormatException e){

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
        this.coordsInputFields = new ArrayList<>();
        switch (currentTransformation){
            case AFFINE2D -> {
                ObservableList<AffineTransform2D> affineTransform2DObservableList = FXCollections.observableArrayList();
                affineTransform2DListView.setVisible(true);

                Text matrixText = new Text("Write the numbers wanted for the matrix");
                TextField matrixInputFieldA00 = new TextField();
                TextField matrixInputFieldA01 = new TextField();
                TextField matrixInputFieldA10 = new TextField();
                TextField matrixInputFieldA11 = new TextField();
                Text vectorText = new Text("Write the numbers wanted for the vector");
                TextField vectorInputFieldB0 = new TextField();
                TextField vectorInputFieldB1 = new TextField();
                Button addToListButton = new Button("Add to list");

                HBox matrixTopRow = new HBox(matrixInputFieldA00, matrixInputFieldA01);
                HBox matrixBottomRow = new HBox(matrixInputFieldA10, matrixInputFieldA11);

                addToListButton.setOnAction(actionEvent -> {
                    AffineTransform2D affineTransform2D = new AffineTransform2D(
                            new Matrix2x2(Double.parseDouble(matrixInputFieldA00.getText()), Double.parseDouble(matrixInputFieldA01.getText()), Double.parseDouble(matrixInputFieldA10.getText()), Double.parseDouble(matrixInputFieldA11.getText()))
                            ,new Vector2D(Double.parseDouble(vectorInputFieldB0.getText()), Double.parseDouble(vectorInputFieldB1.getText())));

                    transform2DList.add(affineTransform2D);
                    affineTransform2DObservableList.add(affineTransform2D);
                    affineTransform2DListView.setItems(affineTransform2DObservableList);
                });

                inputFieldsVBox.getChildren().addAll(matrixText,matrixTopRow,matrixBottomRow,
                        vectorText,vectorInputFieldB0,vectorInputFieldB1, addToListButton);



            }
            case JULIA -> {
                affineTransform2DListView.setItems(null);
                affineTransform2DListView.setVisible(false);
                this.constantInputFields = new ArrayList<>();

                Text constantCText = new Text("Write the constant C");
                TextField constantCInputFieldReal = new TextField();
                TextField constantCInputFieldImaginary = new TextField();

                constantInputFields.add(constantCInputFieldReal);
                constantInputFields.add(constantCInputFieldImaginary);

                inputFieldsVBox.getChildren().addAll(constantCText,constantCInputFieldReal,constantCInputFieldImaginary);
            }

        }

        Text coordsMinText = new Text("Write the numbers wanted for minimum coordinates");
        TextField minCoordsX = new TextField();
        TextField minCoordsY = new TextField();
        Text coordsMaxText = new Text("Write the numbers wanted for maximum coordinates");
        TextField maxCoordsX = new TextField();
        TextField maxCoordsY = new TextField();

        coordsInputFields.add(minCoordsX);
        coordsInputFields.add(minCoordsY);
        coordsInputFields.add(maxCoordsX);
        coordsInputFields.add(maxCoordsY);

        inputFieldsVBox.getChildren().addAll(coordsMinText,minCoordsX,minCoordsY, coordsMaxText,maxCoordsX,maxCoordsY);
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

    public void setAffineTransform2DListView(ListView<AffineTransform2D> affineTransform2DListView) {
        this.affineTransform2DListView = affineTransform2DListView;
    }
}
