package no.ntnu.idatt2003.chaosgame.Controller;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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

    private Transformations currentTransformation;
    private List<Transform2D> transform2DList;

    public CreateFractalController(Stage stage) {
        this.stage = stage;
        this.transform2DList = new ArrayList<>();
    }

    public void addButtonListeners(){
        addToListButton.setOnAction(actionEvent -> transform2DList.add(new AffineTransform2D(
                new Matrix2x2(Double.parseDouble(matrixInputFieldA00.getText()), Double.parseDouble(matrixInputFieldA01.getText()), Double.parseDouble(matrixInputFieldA10.getText()), Double.parseDouble(matrixInputFieldA11.getText()))
                , new Vector2D(Double.parseDouble(vectorInputFieldB0.getText()), Double.parseDouble(vectorInputFieldB1.getText())))
        ));

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
                updateContents();
            });
        }

    }

    public void updateScene(Parent root){
        showAffineOptions(false);
        showJuliaOptions(false);
        showMinMaxCoordsOptions(false);

        Scene scene = new Scene(root, 600, 600);

        stage.setScene(scene);
        stage.show();
    }

    public void populateMenuButton(){
        for (Transformations transformation: Transformations.values()) {
            menuButton.getItems().add(new MenuItem(transformation.name()));
        }
    }

    private void updateContents(){
        switch (currentTransformation){
            case AFFINE2D -> {
                showAffineOptions(true);
                showJuliaOptions(false);
                showMinMaxCoordsOptions(true);
            }
            case JULIA -> {
                showAffineOptions(false);
                showJuliaOptions(true);
                showMinMaxCoordsOptions(true);
            }

        }
    }

    private void showAffineOptions(boolean bool){
        matrixText.setVisible(bool);
        matrixInputFieldA00.setVisible(bool);
        matrixInputFieldA01.setVisible(bool);
        matrixInputFieldA10.setVisible(bool);
        matrixInputFieldA11.setVisible(bool);
        vectorText.setVisible(bool);
        vectorInputFieldB0.setVisible(bool);
        vectorInputFieldB1.setVisible(bool);
        addToListButton.setVisible(bool);
    }

    private void showJuliaOptions(boolean bool){
        constantCText.setVisible(bool);
        constantCInputFieldReal.setVisible(bool);
        constantCInputFieldImaginary.setVisible(bool);
    }

    private void showMinMaxCoordsOptions(boolean bool){
        coordsMinText.setVisible(bool);
        minCoordsX.setVisible(bool);
        minCoordsY.setVisible(bool);
        coordsMaxText.setVisible(bool);
        maxCoordsX.setVisible(bool);
        maxCoordsY.setVisible(bool);
    }

    public void setMatrixInputFieldA00(TextField matrixInputFieldA00) {
        this.matrixInputFieldA00 = matrixInputFieldA00;
    }

    public void setMatrixInputFieldA01(TextField matrixInputFieldA01) {
        this.matrixInputFieldA01 = matrixInputFieldA01;
    }

    public void setMatrixInputFieldA10(TextField matrixInputFieldA10) {
        this.matrixInputFieldA10 = matrixInputFieldA10;
    }

    public void setMatrixInputFieldA11(TextField matrixInputFieldA11) {
        this.matrixInputFieldA11 = matrixInputFieldA11;
    }

    public void setVectorInputFieldB0(TextField vectorInputFieldB0) {
        this.vectorInputFieldB0 = vectorInputFieldB0;
    }

    public void setVectorInputFieldB1(TextField vectorInputFieldB1) {
        this.vectorInputFieldB1 = vectorInputFieldB1;
    }

    public void setAddToListButton(Button addToListButton) {
        this.addToListButton = addToListButton;
    }

    public void setMinCoordsX(TextField minCoordsX) {
        this.minCoordsX = minCoordsX;
    }

    public void setMinCoordsY(TextField minCoordsY) {
        this.minCoordsY = minCoordsY;
    }

    public void setMaxCoordsX(TextField maxCoordsX) {
        this.maxCoordsX = maxCoordsX;
    }

    public void setMaxCoordsY(TextField maxCoordsY) {
        this.maxCoordsY = maxCoordsY;
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

    public void setMatrixText(Text matrixText) {
        this.matrixText = matrixText;
    }

    public void setVectorText(Text vectorText) {
        this.vectorText = vectorText;
    }

    public void setCoordsMinText(Text coordsMinText) {
        this.coordsMinText = coordsMinText;
    }

    public void setCoordsMaxText(Text coordsMaxText) {
        this.coordsMaxText = coordsMaxText;
    }

    public void setConstantCText(Text constantCText) {
        this.constantCText = constantCText;
    }

    public void setConstantCInputFieldReal(TextField constantCInputFieldReal) {
        this.constantCInputFieldReal = constantCInputFieldReal;
    }

    public void setConstantCInputFieldImaginary(TextField constantCInputFieldImaginary) {
        this.constantCInputFieldImaginary = constantCInputFieldImaginary;
    }
}
