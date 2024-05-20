package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt2003.chaosgame.Controller.CanvasController;

public class CanvasScene {
    private VBox inputFieldsVBox;
    private TextField iterationInputField ;
    private Button runButton;
    private Button backButton;
    private Button canvasSizeConfirmButton;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private VBox inputFieldBox;

    private VBox root;
    private CanvasController canvasController;
    private TextField canvasSizeInputFieldWidth;
    private TextField canvasSizeInputFieldHeight;

    public CanvasScene(CanvasController canvasController){
        this.canvasController = canvasController;

        createAndLayoutControls();
        fillInputFieldsVBox();
        createGraphicsContext();

        canvasController.setBackButton(backButton);
        canvasController.setRunButton(runButton);
        canvasController.setIterationInputField(iterationInputField);
        canvasController.setGraphicsContext(graphicsContext);
        canvasController.setCanvasSizeConfirmButton(canvasSizeConfirmButton);
        canvasController.setCanvas(canvas);
        canvasController.setCanvasSizeInputFieldWidth(canvasSizeInputFieldWidth);
        canvasController.setCanvasSizeInputFieldHeight(canvasSizeInputFieldHeight);

        canvasController.addButtonListeners();
    }

    public void displayScene(){
        canvasController.updateScene(root);
    }

    private void fillInputFieldsVBox(){
        canvasController.fillInputFieldsVBox(inputFieldsVBox);
    }

    private void createGraphicsContext(){
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(1.0);

        graphicsContext.save();
        graphicsContext.translate(300, 300);
        graphicsContext.rotate(90);
        graphicsContext.translate(-300,-300);
    }

    private void createAndLayoutControls(){
        inputFieldsVBox = new VBox();
        Text iterationText = new Text("Input amount of iterations");
        iterationInputField = new TextField();
        runButton = new Button("Run");
        backButton = new Button("Back");
        canvas = new Canvas(600.0f, 600.0f);
        iterationInputField.setMinWidth(50);
        runButton.setMinWidth(50);

        Text canvasSizeText = new Text("Change canvas size:");
        canvasSizeInputFieldWidth = new TextField("600");
        canvasSizeInputFieldHeight = new TextField("600");


        HBox canvasSizeChange = new HBox(canvasSizeInputFieldWidth,canvasSizeInputFieldHeight);

        canvasSizeConfirmButton = new Button("Confirm");

        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        separator.setMinHeight(40);


        StackPane stackPane = new StackPane(canvas);
        stackPane.setStyle("-fx-background-color: black");
        ScrollPane scrollPaneTop = new ScrollPane(inputFieldsVBox);
        VBox bottomVBox = new VBox(iterationText, iterationInputField, runButton, canvasSizeText,canvasSizeChange,canvasSizeConfirmButton,separator,backButton);

        ScrollPane scrollPaneBottom = new ScrollPane(bottomVBox);

        inputFieldBox = new VBox(scrollPaneTop, scrollPaneBottom);
        HBox container = new HBox(inputFieldBox, stackPane);

        root = new VBox(container);

        scrollPaneTop.getStyleClass().add("scrollPaneTop");
        scrollPaneBottom.getStyleClass().add("scrollPaneBottom");
        backButton.getStyleClass().add("back");
        iterationInputField.getStyleClass().add("iteration");
        inputFieldBox.getStyleClass().add("leftSide");

    }

}
