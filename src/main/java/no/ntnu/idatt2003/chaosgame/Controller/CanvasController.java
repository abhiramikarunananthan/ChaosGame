package no.ntnu.idatt2003.chaosgame.Controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGame;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameObserver;
import no.ntnu.idatt2003.chaosgame.tensors.Complex;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.JuliaTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.util.ArrayList;
import java.util.List;

public class CanvasController implements ChaosGameObserver {

    private Stage stage;
    private Transformations transformation;
    private ChaosGameDescription chaosGameDescription;
    private ChaosGame chaosGame;

    private List<TextField> matrixInputFields;
    private List<TextField> vectorInputFields;

    private List<TextField> constantInputFields;
    private List<TextField> coordsInputFields;

    private Button runButton;
    private Button backButton;
    private TextField iterationInputField;
    private GraphicsContext graphicsContext;
    int[][] vistedPoints = new int[600][600];

    public CanvasController(Stage stage, Transformations transformation, ChaosGameDescription chaosGameDescription){
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
            updateChaosGameDescription();
            chaosGame = new ChaosGame(chaosGameDescription, 600,600);
            chaosGame.runSteps(Integer.parseInt(iterationInputField.getText()), this);
        });

        backButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(2);
        });
    }

    public void fillInputFieldsVBox(VBox inputFieldsVBox) {
        switch (transformation){
            case AFFINE2D -> {
                matrixInputFields = new ArrayList<>();
                vectorInputFields = new ArrayList<>();
                coordsInputFields = new ArrayList<>();
                for (Transform2D ignored : chaosGameDescription.getTransforms()) {
                    Text matrixText = new Text("Write the numbers wanted for the matrix");

                    TextField matrixInputFieldA00 = new TextField();
                    TextField matrixInputFieldA01 = new TextField();
                    TextField matrixInputFieldA10 = new TextField();
                    TextField matrixInputFieldA11 = new TextField();

                    HBox matrixTopRow = new HBox(matrixInputFieldA00, matrixInputFieldA01);
                    HBox matrixBottomRow = new HBox(matrixInputFieldA10, matrixInputFieldA11);
                    Text vectorText = new Text("Write the numbers wanted for the vector");
                    TextField vectorInputFieldB0 = new TextField();
                    TextField vectorInputFieldB1 = new TextField();

                    inputFieldsVBox.getChildren().addAll(matrixText, matrixTopRow,matrixBottomRow, vectorText,
                            vectorInputFieldB0, vectorInputFieldB1);

                    matrixInputFields.add(matrixInputFieldA00);
                    matrixInputFields.add(matrixInputFieldA01);
                    matrixInputFields.add(matrixInputFieldA10);
                    matrixInputFields.add(matrixInputFieldA11);

                    vectorInputFields.add(vectorInputFieldB0);
                    vectorInputFields.add(vectorInputFieldB1);


                }
                Text minCoordsText = new Text("Write the numbers wanted for the minimum vector");
                TextField minCoordX = new TextField();
                TextField minCoordY = new TextField();

                HBox minCoordHBox = new HBox(minCoordX, minCoordY);

                Text maxCoordsText = new Text("Write the numbers wanted for the maximum vector");
                TextField maxCoordX = new TextField();
                TextField maxCoordY = new TextField();

                HBox maxCoordHBox = new HBox(maxCoordX, maxCoordY);

                coordsInputFields.add(minCoordX);
                coordsInputFields.add(minCoordY);
                coordsInputFields.add(maxCoordX);
                coordsInputFields.add(maxCoordY);

                inputFieldsVBox.getChildren().addAll(minCoordsText,minCoordHBox,maxCoordsText,maxCoordHBox);

            }
            case JULIA -> {
                coordsInputFields = new ArrayList<>();
                Text constantText = new Text("Write the constant C");
                TextField constantInputFieldReal = new TextField();
                TextField constantInputFieldImaginary = new TextField();

                constantInputFields = new ArrayList<>();
                constantInputFields.add(constantInputFieldReal);
                constantInputFields.add(constantInputFieldImaginary);



                Text minCoordsText = new Text("Write the numbers wanted for the minimum vector");
                TextField minCoordX = new TextField();
                TextField minCoordY = new TextField();

                HBox minCoordHBox = new HBox(minCoordX, minCoordY);

                Text maxCoordsText = new Text("Write the numbers wanted for the maximum vector");
                TextField maxCoordX = new TextField();
                TextField maxCoordY = new TextField();

                HBox maxCoordHBox = new HBox(maxCoordX, maxCoordY);

                coordsInputFields.add(minCoordX);
                coordsInputFields.add(minCoordY);
                coordsInputFields.add(maxCoordX);
                coordsInputFields.add(maxCoordY);

                HBox constantInputFields = new HBox(constantInputFieldReal, constantInputFieldImaginary);
                inputFieldsVBox.getChildren().addAll(constantText, constantInputFields, minCoordsText, minCoordHBox, maxCoordsText, maxCoordHBox);
            }
        }
    }

    public void updateScene(Parent root){
        Scene scene = new Scene(root, 850, 600);

        stage.setScene(scene);
        stage.show();
    }

    private void updateChaosGameDescription(){
        try{
            if(transformation == Transformations.AFFINE2D){
                List<Transform2D> transform2DList = new ArrayList<>();
                List<Matrix2x2> matrix2x2List = new ArrayList<>();
                List<Vector2D> vector2DList = new ArrayList<>();

                for (int i = 0; i < matrixInputFields.size(); i+=4) {
                    matrix2x2List.add(new Matrix2x2(Double.parseDouble(matrixInputFields.get(i).getText()),
                            Double.parseDouble(matrixInputFields.get(i+1).getText()),
                            Double.parseDouble(matrixInputFields.get(i+2).getText()),
                            Double.parseDouble(matrixInputFields.get(i+3).getText())));
                }

                for (int i = 0; i < vectorInputFields.size(); i+=2) {
                    vector2DList.add(new Vector2D(Double.parseDouble(vectorInputFields.get(i).getText()),
                            Double.parseDouble(vectorInputFields.get(i+1).getText())));
                }

                for (int i = 0; i < matrix2x2List.size(); i++) {
                    transform2DList.add(new AffineTransform2D(matrix2x2List.get(i),vector2DList.get(i)));
                }

                chaosGameDescription.setTransforms(transform2DList);
                chaosGameDescription.setMinCoords(new Vector2D(Double.parseDouble(constantInputFields.get(0).getText()),Double.parseDouble(constantInputFields.get(1).getText())));
                chaosGameDescription.setMaxCoords(new Vector2D(Double.parseDouble(constantInputFields.get(2).getText()),Double.parseDouble(constantInputFields.get(3).getText())));

            }else if (transformation == Transformations.JULIA){
                List<Transform2D> transform2DList = new ArrayList<>();

                transform2DList.add(new JuliaTransform2D(
                        new Complex(Double.parseDouble(constantInputFields.get(0).getText()),
                                Double.parseDouble(constantInputFields.get(1).getText())), 1));
                transform2DList.add(new JuliaTransform2D(
                        new Complex(Double.parseDouble(constantInputFields.get(0).getText()),
                                Double.parseDouble(constantInputFields.get(1).getText())), -1));

                chaosGameDescription.setTransforms(transform2DList);
                chaosGameDescription.setMinCoords(new Vector2D(Double.parseDouble(coordsInputFields.get(0).getText()),Double.parseDouble(coordsInputFields.get(1).getText())));
                chaosGameDescription.setMaxCoords(new Vector2D(Double.parseDouble(coordsInputFields.get(2).getText()),Double.parseDouble(coordsInputFields.get(3).getText())));

            }

            graphicsContext.clearRect(0,0,600,600);

        }catch (NullPointerException | NumberFormatException e){

        }




    }

    @Override
    public void update(Vector2D point) {

        int[][] canvasArray = chaosGame.getCanvas().getCanvasArray();
        int i = (int) point.getX0();
        int j = (int) point.getX1();

        if(i < 600 && i >= 0 && j < 600 && j >= 0){

            vistedPoints[i][j] += canvasArray[i][j];
            Color color = Color.RED;

            color = Color.hsb((color.getHue() + 30) * vistedPoints[i][j], color.getSaturation(), color.getBrightness());

            graphicsContext.setStroke(color);
            graphicsContext.strokeLine(i, j, i, j);
        }

    }
}
