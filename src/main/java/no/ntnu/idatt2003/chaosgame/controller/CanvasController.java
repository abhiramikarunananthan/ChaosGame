package no.ntnu.idatt2003.chaosgame.controller;

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
import no.ntnu.idatt2003.chaosgame.components.ChaosCanvas;
import no.ntnu.idatt2003.chaosgame.components.ChaosGame;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameObserver;
import no.ntnu.idatt2003.chaosgame.exceptions.MinimumBiggerThanMaximumException;
import no.ntnu.idatt2003.chaosgame.exceptions.NegativeDimensionsException;
import no.ntnu.idatt2003.chaosgame.tensors.Complex;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.JuliaTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for {@link no.ntnu.idatt2003.chaosgame.scenes.CanvasScene}
 *
 * @author 10052
 * @version 1.0
 */
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

    /**
     * Constructor for the {@link CanvasController} class.
     * @param stage The primary stage of the application, which serves as the
     * main window for the UI.
     * @param transformation An instance of Transformations
     * that defines the transformations to be applied within the canvas.
     * @param chaosGameDescription An instance of ChaosGameDescription that contains
     * the settings and rules for the chaos game to be visualized on the canvas.
     *
     */
    public CanvasController(Stage stage, Transformations transformation, ChaosGameDescription chaosGameDescription) {
        this.stage = stage;
        this.transformation = transformation;
        this.chaosGameDescription = chaosGameDescription;
    }

    /**
     * Adds event listeners to the buttons in the user interface on {@link no.ntnu.idatt2003.chaosgame.scenes.CanvasScene}
     *
     * This method sets up action handlers for the following buttons:
     *
     * runButton: Initiates the chaos game by reading input values, clearing the canvas,
     * creating a new ChaosGame instance, and running the specified number of steps.
     * Handles various exceptions and displays appropriate error alerts.
     * backButton: Switches the scene to a different view using {@link SceneController}.
     * canvasSizeConfirmButton: Updates the canvas size based on user input, clears the canvas,
     * and adjusts the scene dimensions accordingly.
     */
    public void addButtonListeners() {

        runButton.setOnAction(actionEvent -> {
            try {
                updateChaosGameDescription();
                graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                chaosGame = new ChaosGame(chaosGameDescription, (int) canvas.getHeight(), (int) canvas.getWidth());
                chaosGame.runSteps(Integer.parseInt(iterationInputField.getText()), this);

            } catch (NumberFormatException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Iteration input field can not be empty");
                errorAlert.showAndWait();
            } catch (NegativeArraySizeException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Canvas size cannot be negative");
                errorAlert.showAndWait();
            } catch (MinimumBiggerThanMaximumException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Minimum vector values cannot be bigger than maximum vector values");
                errorAlert.showAndWait();
            } catch (Exception e) {
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

            graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            canvas.setWidth(Double.parseDouble(canvasSizeInputFieldWidth.getText()));
            canvas.setHeight(Double.parseDouble(canvasSizeInputFieldHeight.getText()));

            Parent root = new VBox(stage.getScene().getRoot());

            updateScene(root, 250 + canvas.getWidth(), Double.parseDouble(canvasSizeInputFieldHeight.getText()));

            graphicsContext.translate((canvas.getHeight() - oldHeight) / 2, (oldWidth - canvas.getWidth()) / 2);


        });
    }

    /**
     *  Fills the provided VBox with input fields based on the current
     *  transformation.
     *
     * If the transformation is AFFINE2D, the method creates and adds text fields for the matrix and vector
     * components of each affine transformation in the chaos game description. It also adds input fields for the
     * minimum and maximum coordinate vectors.
     *
     * If the transformation is JULIA, the method creates and adds input fields for the constant vector
     * of the Julia transformation, along with the minimum and maximum coordinate vectors.
     *
     * @param inputFieldsVBox The VBox container to be filled with input fields.
     */
    public void fillInputFieldsVBox(VBox inputFieldsVBox) {
        if (transformation != null)
            switch (transformation) {
                case AFFINE2D -> {
                    matrixInputFields = new ArrayList<>();
                    vectorInputFields = new ArrayList<>();
                    coordsInputFields = new ArrayList<>();
                    for (Transform2D transform2D : chaosGameDescription.getTransforms()) {
                        Text matrixText = new Text("Matrix:");
                        AffineTransform2D affineTransform2D = (AffineTransform2D) transform2D;
                        TextField matrixInputFieldA00 = new TextField(String.valueOf(affineTransform2D.getMatrix().getA00()));
                        TextField matrixInputFieldA01 = new TextField(String.valueOf(affineTransform2D.getMatrix().getA01()));
                        TextField matrixInputFieldA10 = new TextField(String.valueOf(affineTransform2D.getMatrix().getA10()));
                        TextField matrixInputFieldA11 = new TextField(String.valueOf(affineTransform2D.getMatrix().getA11()));

                        HBox matrixTopRow = new HBox(matrixInputFieldA00, matrixInputFieldA01);
                        HBox matrixBottomRow = new HBox(matrixInputFieldA10, matrixInputFieldA11);
                        Text vectorText = new Text("Vector:");
                        TextField vectorInputFieldB0 = new TextField(String.valueOf(affineTransform2D.getVector().getX0()));
                        TextField vectorInputFieldB1 = new TextField(String.valueOf(affineTransform2D.getVector().getX1()));

                        Separator separator = new Separator();
                        separator.setOrientation(Orientation.HORIZONTAL);
                        separator.setMinHeight(40);

                        inputFieldsVBox.getChildren().addAll(matrixText, matrixTopRow, matrixBottomRow, vectorText,
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

                    inputFieldsVBox.getChildren().addAll(minCoordsText, minCoordHBox, maxCoordsText, maxCoordHBox);

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
                    inputFieldsVBox.getChildren().addAll(constantText, constantInputFields, separator, minCoordsText, minCoordHBox, maxCoordsText, maxCoordHBox);
                }
            }
    }

    /**
     * Updates the current scene with the provided root node.
     *
     * This method creates a new scene with the specified root node and default dimensions
     * of 850 by 600 pixels. It then applies a CSS stylesheet for styling and sets the new scene on
     * the primary stage before displaying it.
     *
     * @param root The root node of the new scene to be displayed.
     */
    public void updateScene(Parent root) {
        Scene scene = new Scene(root, 850, 600);

        String css = this.getClass().getResource("/CanvasStylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Updates the current scene with the provided root node and specified dimensions.
     *
     * This method creates a new Scene with the specified root node and dimensions.
     * It then applies a CSS stylesheet for styling and sets the new scene on the primary stage
     * before displaying it.
     *
     * @param root The root node of the new scene to be displayed.
     * @param width The width of the new scene.
     * @param height The height of the new scene.
     */
    public void updateScene(Parent root, double width, double height) {
        Scene scene = new Scene(root, width, height);

        String css = this.getClass().getResource("/CanvasStylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    /**
     *  Updates the chaosGameDescription object based on the current input fields.
     *
     * If the transformation is AFFINE2D, the method parses the matrix and vector input fields
     * to create new Matrix2x2 and Vector2D objects, respectively. It then combines these
     * into AffineTransform2D objects and updates the transforms in the chaosGameDescription.
     * It also updates the minimum and maximum coordinate vectors.
     *
     * If the transformation is JULIA, it reads the constant values from the input fields to create
     * new Complex objects for the Julia transformation and updates the transforms in the chaosGameDescription.
     * It also updates the minimum and maximum coordinate vectors.
     *
     * If any parsing error occurs, NullPointerException or NumberFormatException,
     * an error alert is shown to the user.
     */
    private void updateChaosGameDescription() {
        try {
            if (transformation == Transformations.AFFINE2D) {
                List<Transform2D> transform2DList = new ArrayList<>();
                List<Matrix2x2> matrix2x2List = new ArrayList<>();
                List<Vector2D> vector2DList = new ArrayList<>();

                for (int i = 0; i < matrixInputFields.size(); i += 4) {
                    matrix2x2List.add(new Matrix2x2(Double.parseDouble(matrixInputFields.get(i).getText()),
                            Double.parseDouble(matrixInputFields.get(i + 1).getText()),
                            Double.parseDouble(matrixInputFields.get(i + 2).getText()),
                            Double.parseDouble(matrixInputFields.get(i + 3).getText())));
                }

                for (int i = 0; i < vectorInputFields.size(); i += 2) {
                    vector2DList.add(new Vector2D(Double.parseDouble(vectorInputFields.get(i).getText()),
                            Double.parseDouble(vectorInputFields.get(i + 1).getText())));
                }

                for (int i = 0; i < matrix2x2List.size(); i++) {
                    transform2DList.add(new AffineTransform2D(matrix2x2List.get(i), vector2DList.get(i)));
                }

                chaosGameDescription.setTransforms(transform2DList);

                chaosGameDescription.setMinCoords(new Vector2D(Double.parseDouble(coordsInputFields.get(0).getText()), Double.parseDouble(coordsInputFields.get(1).getText())));
                chaosGameDescription.setMaxCoords(new Vector2D(Double.parseDouble(coordsInputFields.get(2).getText()), Double.parseDouble(coordsInputFields.get(3).getText())));

            } else if (transformation == Transformations.JULIA) {
                List<Transform2D> transform2DList = new ArrayList<>();

                transform2DList.add(new JuliaTransform2D(
                        new Complex(Double.parseDouble(constantInputFields.get(0).getText()),
                                Double.parseDouble(constantInputFields.get(1).getText())), 1));
                transform2DList.add(new JuliaTransform2D(
                        new Complex(Double.parseDouble(constantInputFields.get(0).getText()),
                                Double.parseDouble(constantInputFields.get(1).getText())), -1));

                chaosGameDescription.setTransforms(transform2DList);
                chaosGameDescription.setMinCoords(new Vector2D(Double.parseDouble(coordsInputFields.get(0).getText()), Double.parseDouble(coordsInputFields.get(1).getText())));
                chaosGameDescription.setMaxCoords(new Vector2D(Double.parseDouble(coordsInputFields.get(2).getText()), Double.parseDouble(coordsInputFields.get(3).getText())));

            }

        } catch (NullPointerException | NumberFormatException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error occured");
            errorAlert.setContentText("An unexpected error has occured:\n" + e);
            errorAlert.showAndWait();
        }

    }

    /**
     * This method converts the coordinates of the given point to integers,
     * retrieves the pixel value from the canvas, and if the point is within the
     * canvas bounds, updates the display by drawing a colored point.
     *
     * @param point The new point to be updated on the canvas.
     */
    @Override
    public void update(Vector2D point) {

        int i = (int) point.getX0();
        int j = (int) point.getX1();

        int pointValue = 0;

        try {
            pointValue = chaosGame.getCanvas().getPixel(point);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        if (i < canvas.getWidth() && i >= 0 && j < canvas.getHeight() && j >= 0) {
            Color color = Color.RED;

            color = Color.hsb((color.getHue() + 30) * pointValue, color.getSaturation(), color.getBrightness());

            graphicsContext.setStroke(color);
            graphicsContext.strokeLine(i, j, i, j);
        }

    }

    /**
     * Sets the run button for the {@link CanvasController}.
     *
     * @param runButton The button to start the chaos game.
     */
    public void setRunButton(Button runButton) {
        this.runButton = runButton;
    }
    /**
     * Sets the back button for the {@link CanvasController}.
     *
     * @param backButton The button to navigate back to the previous scene.
     */
    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    /**
     * Sets the iteration input field for the {@link CanvasController}.
     *
     * @param iterationInputField The text field to input the number of iterations.
     */
    public void setIterationInputField(TextField iterationInputField) {
        this.iterationInputField = iterationInputField;
    }

    /**
     * Sets the graphics context for the {@link CanvasController}.
     *
     * @param graphicsContext The graphics context used to draw on the canvas.
     */
    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    /**
     * Sets the canvas size confirm button for the {@link CanvasController}.
     *
     * @param canvasSizeConfirmButton The button to confirm the new canvas size.
     */
    public void setCanvasSizeConfirmButton(Button canvasSizeConfirmButton) {
        this.canvasSizeConfirmButton = canvasSizeConfirmButton;
    }

    /**
     * Sets the canvas for the {@link CanvasController}.
     *
     * @param canvas The canvas to draw on.
     */
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * Sets the width input field for the canvas size.
     *
     * @param canvasSizeInputFieldWidth The text field to input the width
     * of the canvas.
     */
    public void setCanvasSizeInputFieldWidth(TextField canvasSizeInputFieldWidth) {
        this.canvasSizeInputFieldWidth = canvasSizeInputFieldWidth;
    }

    /**
     * Sets the height input field for the canvas size.
     *
     * @param canvasSizeInputFieldHeight The text field to input the height of
     * the canvas.
     */
    public void setCanvasSizeInputFieldHeight(TextField canvasSizeInputFieldHeight) {
        this.canvasSizeInputFieldHeight = canvasSizeInputFieldHeight;
    }
}
