package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.SceneController;
import no.ntnu.idatt2003.chaosgame.components.ChaosGame;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.io.FileNotFoundException;
import java.util.List;

public class CanvasScene {

    private Scene scene;

    public CanvasScene(Stage stage, ChaosGameDescription chaosGameDescription, Transformations transformation){

        VBox inputFieldsVBox = new VBox();
        switch (transformation){
            case AFFINE2D -> {
                Text matrixText = new Text("Write the numbers wanted for the matrix");
                TextField matrixInputFieldA00 = new TextField();
                TextField matrixInputFieldA01 = new TextField();
                TextField matrixInputFieldA10 = new TextField();
                TextField matrixInputFieldA11 = new TextField();
                Text vectorText = new Text("Write the numbers wanted for the vector");
                TextField vectorInputFieldB0 = new TextField();
                TextField vectorInputFieldB1 = new TextField();
                inputFieldsVBox.getChildren().addAll(matrixText, matrixInputFieldA00, matrixInputFieldA01, matrixInputFieldA10, matrixInputFieldA11, vectorText, vectorInputFieldB0, vectorInputFieldB1);

            }
            case JULIA -> {
                Text constantText = new Text("Write the constant C");
                TextField constantInputField = new TextField();
                inputFieldsVBox.getChildren().addAll(constantText, constantInputField);
            }
        }
        Text iterationText = new Text("Input amount of iterations");
        TextField iterationInputField = new TextField();
        Button runButton = new Button("Run");
        Button backButton = new Button("Back");
        Canvas canvas = new Canvas(600.0f, 600.0f);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(1.0);

        int[][] vistedPoints = new int[600][600];

        graphicsContext.save();
        graphicsContext.translate(300, 300);
        graphicsContext.rotate(90);
        graphicsContext.translate(-300,-300);



                runButton.setOnAction(actionEvent -> {
                ChaosGame chaosGame = new ChaosGame(chaosGameDescription, 600,600);
                chaosGame.runSteps(Integer.parseInt(iterationInputField.getText()));
                int[][] canvasArray = chaosGame.getCanvas().getCanvasArray();
                for (int i = 0; i < canvasArray.length; i++) {
                    for (int j = 0; j < canvasArray.length; j++) {
                        if(canvasArray[i][j] >= 1){
                            vistedPoints[i][j] += canvasArray[i][j];
                            Color color = Color.RED;


                                for (int k = 0; k < vistedPoints[i][j]; k++) {
                                    color = Color.hsb(color.getHue() + 30, color.getSaturation(), color.getBrightness());
                                }

                                graphicsContext.setStroke(color);
                                graphicsContext.strokeLine(i,j, i, j);




                        }
                    }
                }

        });
        backButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(2);
        });

        iterationInputField.setMinWidth(50);
        runButton.setMinWidth(50);

        VBox inputFieldBox = new VBox(inputFieldsVBox, iterationText, iterationInputField, runButton, backButton);
        HBox container = new HBox(inputFieldBox, canvas);


        VBox root = new VBox(container);


        scene = new Scene(root, 600, 600);

    }

    public Scene getScene() {
        return scene;
    }

}
