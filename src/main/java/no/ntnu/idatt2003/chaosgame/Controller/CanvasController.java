package no.ntnu.idatt2003.chaosgame.Controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGame;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

public class CanvasController {

    private Stage stage;
    private Transformations transformation;
    private ChaosGameDescription chaosGameDescription;

    private Button runButton;
    private Button backButton;
    private TextField iterationInputField;
    private GraphicsContext graphicsContext;
    int[][] vistedPoints = new int[600][600];

    public CanvasController(Stage stage, Transformations transformation, ChaosGameDescription chaosGameDescription) {
        this.stage = stage;
        this.transformation = transformation;
        this.chaosGameDescription = chaosGameDescription;
    }

    public void setRunButton(Button runButton) {
        this.runButton = runButton;
    }

    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    public void setIterationInputField(TextField iterationInputField) {
        this.iterationInputField = iterationInputField;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public void addButtonListeners(){

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
    }

    public void fillInputFieldsVBox(VBox inputFieldsVBox) {
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
    }

    public void updateScene(Parent root){
        Scene scene = new Scene(root, 600, 600);

        stage.setScene(scene);
        stage.show();
    }
}
