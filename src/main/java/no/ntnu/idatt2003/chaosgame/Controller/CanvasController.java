package no.ntnu.idatt2003.chaosgame.Controller;

import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGame;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameObserver;
import no.ntnu.idatt2003.chaosgame.exceptions.MinimumBiggerThanMaximumException;
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
    private Button canvasSizeConfirmButton;
    private TextField iterationInputField;
    private GraphicsContext graphicsContext;
    private Canvas canvas;

    private TextField canvasSizeInputFieldWidth;
    private TextField canvasSizeInputFieldHeight;

    public CanvasController(Stage stage, Transformations transformation, ChaosGameDescription chaosGameDescription){
        this.stage = stage;
        this.transformation = transformation;
        this.chaosGameDescription = chaosGameDescription;
    }

    public void addButtonListeners(){

        runButton.setOnAction(actionEvent -> {
            try{
                updateChaosGameDescription();
                graphicsContext.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                chaosGame = new ChaosGame(chaosGameDescription, (int) canvas.getHeight(), (int) canvas.getWidth());
                chaosGame.runSteps(Integer.parseInt(iterationInputField.getText()), this);
            }catch (NumberFormatException e){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Iteration input field can not be empty");
                errorAlert.showAndWait();
            }catch (NegativeArraySizeException e){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Canvas size cannot be negative");
                errorAlert.showAndWait();
            }catch (MinimumBiggerThanMaximumException e){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Minimum vector values cannot be bigger than maximum vector values");
                errorAlert.showAndWait();
            } catch (Exception e){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Error occured");
                errorAlert.setContentText("An unexpected error has occured:\n" + e);
                errorAlert.showAndWait();
            }


        });

        backButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(2);
        });

        canvasSizeConfirmButton.setOnAction(actionEvent -> {
            double oldWidth = canvas.getWidth();
            double oldHeight = canvas.getHeight();

            graphicsContext.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
            canvas.setWidth(Double.parseDouble(canvasSizeInputFieldWidth.getText()));
            canvas.setHeight(Double.parseDouble(canvasSizeInputFieldHeight.getText()));

            Parent root = new VBox(stage.getScene().getRoot());

            updateScene(root,250 + canvas.getWidth(), Double.parseDouble(canvasSizeInputFieldHeight.getText()));

            graphicsContext.translate((canvas.getHeight() - oldHeight) / 2, (oldWidth - canvas.getWidth()) / 2);


        });
    }

    public void fillInputFieldsVBox(VBox inputFieldsVBox) {
        if(transformation != null)
            switch (transformation){
                case AFFINE2D -> {
                    matrixInputFields = new ArrayList<>();
                    vectorInputFields = new ArrayList<>();
                    coordsInputFields = new ArrayList<>();
                    for (Transform2D transform2D : chaosGameDescription.getTransforms()) {
                        Text matrixText = new Text("Matrix:");
                        AffineTransform2D affineTransform2D = (AffineTransform2D) transform2D;
                        TextField matrixInputFieldA00 = new TextField(String.valueOf(affineTransform2D.getMatrix().a00()));
                        TextField matrixInputFieldA01 = new TextField(String.valueOf(affineTransform2D.getMatrix().a01()));
                        TextField matrixInputFieldA10 = new TextField(String.valueOf(affineTransform2D.getMatrix().a10()));
                        TextField matrixInputFieldA11 = new TextField(String.valueOf(affineTransform2D.getMatrix().a11()));

                        HBox matrixTopRow = new HBox(matrixInputFieldA00, matrixInputFieldA01);
                        HBox matrixBottomRow = new HBox(matrixInputFieldA10, matrixInputFieldA11);
                        Text vectorText = new Text("Vector:");
                        TextField vectorInputFieldB0 = new TextField(String.valueOf(affineTransform2D.getVector().getX0()));
                        TextField vectorInputFieldB1 = new TextField(String.valueOf(affineTransform2D.getVector().getX1()));

                        Separator separator = new Separator();
                        separator.setOrientation(Orientation.HORIZONTAL);
                        separator.setMinHeight(40);

                        inputFieldsVBox.getChildren().addAll(matrixText, matrixTopRow,matrixBottomRow, vectorText,
                                vectorInputFieldB0, vectorInputFieldB1, separator);

                        matrixInputFields.add(matrixInputFieldA00);
                        matrixInputFields.add(matrixInputFieldA01);
                        matrixInputFields.add(matrixInputFieldA10);
                        matrixInputFields.add(matrixInputFieldA11);

                        vectorInputFields.add(vectorInputFieldB0);
                        vectorInputFields.add(vectorInputFieldB1);


                    }
                    Text minCoordsText = new Text("Minimum Vector:");
                    TextField minCoordX = new TextField(String.valueOf(chaosGameDescription.getMinCoords().getX0()));
                    TextField minCoordY = new TextField(String.valueOf(chaosGameDescription.getMinCoords().getX1()));

                    HBox minCoordHBox = new HBox(minCoordX, minCoordY);

                    Text maxCoordsText = new Text("Maximum Vector");
                    TextField maxCoordX = new TextField(String.valueOf(chaosGameDescription.getMaxCoords().getX0()));
                    TextField maxCoordY = new TextField(String.valueOf(chaosGameDescription.getMaxCoords().getX1()));

                    HBox maxCoordHBox = new HBox(maxCoordX, maxCoordY);

                    coordsInputFields.add(minCoordX);
                    coordsInputFields.add(minCoordY);
                    coordsInputFields.add(maxCoordX);
                    coordsInputFields.add(maxCoordY);

                    inputFieldsVBox.getChildren().addAll(minCoordsText,minCoordHBox,maxCoordsText,maxCoordHBox);

                }
                case JULIA -> {
                    coordsInputFields = new ArrayList<>();
                    JuliaTransform2D juliaTransform2D = (JuliaTransform2D) chaosGameDescription.getTransforms().get(0);

                    Text constantText = new Text("Constant C:");
                    TextField constantInputFieldReal = new TextField(String.valueOf(juliaTransform2D.getConstant().getX0()));
                    TextField constantInputFieldImaginary = new TextField(String.valueOf(juliaTransform2D.getConstant().getX1()));

                    constantInputFields = new ArrayList<>();
                    constantInputFields.add(constantInputFieldReal);
                    constantInputFields.add(constantInputFieldImaginary);

                    Separator separator = new Separator();
                    separator.setOrientation(Orientation.HORIZONTAL);
                    separator.setMinHeight(40);

                    Text minCoordsText = new Text("Minimum Vector:");
                    TextField minCoordX = new TextField(String.valueOf(chaosGameDescription.getMinCoords().getX0()));
                    TextField minCoordY = new TextField(String.valueOf(chaosGameDescription.getMinCoords().getX1()));

                    HBox minCoordHBox = new HBox(minCoordX, minCoordY);

                    Text maxCoordsText = new Text("Maximum Vector:");
                    TextField maxCoordX = new TextField(String.valueOf(chaosGameDescription.getMaxCoords().getX0()));
                    TextField maxCoordY = new TextField(String.valueOf(chaosGameDescription.getMaxCoords().getX1()));

                    HBox maxCoordHBox = new HBox(maxCoordX, maxCoordY);

                    coordsInputFields.add(minCoordX);
                    coordsInputFields.add(minCoordY);
                    coordsInputFields.add(maxCoordX);
                    coordsInputFields.add(maxCoordY);

                    HBox constantInputFields = new HBox(constantInputFieldReal, constantInputFieldImaginary);
                    inputFieldsVBox.getChildren().addAll(constantText, constantInputFields, separator,minCoordsText, minCoordHBox, maxCoordsText, maxCoordHBox);
                }
            }
    }

    public void updateScene(Parent root){
        Scene scene = new Scene(root, 850, 600);

        String css = this.getClass().getResource("/CanvasStylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public void updateScene(Parent root, double width, double height){
        Scene scene = new Scene(root, width, height);

        String css = this.getClass().getResource("/CanvasStylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);

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

                chaosGameDescription.setMinCoords(new Vector2D(Double.parseDouble(coordsInputFields.get(0).getText()),Double.parseDouble(coordsInputFields.get(1).getText())));
                chaosGameDescription.setMaxCoords(new Vector2D(Double.parseDouble(coordsInputFields.get(2).getText()),Double.parseDouble(coordsInputFields.get(3).getText())));

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

        }catch (NullPointerException | NumberFormatException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error occured");
            errorAlert.setContentText("An unexpected error has occured:\n" + e);
            errorAlert.showAndWait();
        }

    }

    @Override
    public void update(Vector2D point) {

        int i = (int) point.getX0();
        int j = (int) point.getX1();

        int pointValue = 0;

        try{
            pointValue = chaosGame.getCanvas().getPixel(point);
        }catch (ArrayIndexOutOfBoundsException ignored){

        }

        if(i < canvas.getWidth() && i >= 0 && j < canvas.getHeight() && j >= 0){
            Color color = Color.RED;

            color = Color.hsb((color.getHue() + 30) * pointValue, color.getSaturation(), color.getBrightness());

            graphicsContext.setStroke(color);
            graphicsContext.strokeLine(i, j, i, j);
        }

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

    public void setCanvasSizeConfirmButton(Button canvasSizeConfirmButton) {
        this.canvasSizeConfirmButton = canvasSizeConfirmButton;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setCanvasSizeInputFieldWidth(TextField canvasSizeInputFieldWidth) {
        this.canvasSizeInputFieldWidth = canvasSizeInputFieldWidth;
    }

    public void setCanvasSizeInputFieldHeight(TextField canvasSizeInputFieldHeight) {
        this.canvasSizeInputFieldHeight = canvasSizeInputFieldHeight;
    }
}
