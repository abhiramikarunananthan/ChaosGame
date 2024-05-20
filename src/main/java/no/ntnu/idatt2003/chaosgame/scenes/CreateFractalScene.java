package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateFractalScene {

    private Scene scene;




    public CreateFractalScene(Stage stage){

        VBox inputFieldsVBox = new VBox();

        Text matrixText = new Text("Write the numbers wanted for the matrix");
        TextField matrixInputFieldA00 = new TextField();
        TextField matrixInputFieldA01 = new TextField();
        TextField matrixInputFieldA10 = new TextField();
        TextField matrixInputFieldA11 = new TextField();
        Text vectorText = new Text("Write the numbers wanted for the vector");
        TextField vectorInputFieldB0 = new TextField();
        TextField vectorInputFieldB1 = new TextField();
        Button addToListButton = new Button("Add to list");
        Text coordsMinText = new Text("Write the numbers wanted for minimum coordinates");
        TextField minCoordsX = new TextField();
        TextField minCoordsY = new TextField();
        Text coordsMaxText = new Text("Write the numbers wanted for maximum coordinates");
        TextField maxCoordsX = new TextField();
        TextField maxCoordsY = new TextField();

        Button saveButton = new Button("Save to file");
        inputFieldsVBox.getChildren().addAll(matrixText, matrixInputFieldA00, matrixInputFieldA01, matrixInputFieldA10, matrixInputFieldA11, vectorText, vectorInputFieldB0, vectorInputFieldB1, addToListButton, coordsMinText, minCoordsX, minCoordsY, coordsMaxText, maxCoordsX, maxCoordsY, saveButton);
        List<Transform2D> transform2DList = new ArrayList<>();

        addToListButton.setOnAction(actionEvent -> {
            transform2DList.add(new AffineTransform2D(
                    new Matrix2x2(Double.parseDouble(matrixInputFieldA00.getText()), Double.parseDouble(matrixInputFieldA01.getText()), Double.parseDouble(matrixInputFieldA10.getText()), Double.parseDouble(matrixInputFieldA11.getText()))
                    , new Vector2D(Double.parseDouble(vectorInputFieldB0.getText()), Double.parseDouble(vectorInputFieldB1.getText())))
            );
        });



        saveButton.setOnAction(actionEvent ->{
            ChaosGameDescription chaosGameDescription = new ChaosGameDescription(
                transform2DList,
                new Vector2D(Double.parseDouble(minCoordsX.getText()), Double.parseDouble(minCoordsY.getText())),
                new Vector2D(Double.parseDouble(maxCoordsX.getText()), Double.parseDouble(maxCoordsY.getText())));




            try {
                ChaosGameFileHandler.writeToFile(chaosGameDescription, "C:\\Users\\k_nal\\Documents\\test.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        VBox root = new VBox(inputFieldsVBox);


        scene = new Scene(root, 600, 600);


    }
    public Scene getScene() {
        return scene;
    }
}
