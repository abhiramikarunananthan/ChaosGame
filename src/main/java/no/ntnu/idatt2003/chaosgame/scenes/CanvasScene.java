package no.ntnu.idatt2003.chaosgame.scenes;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.Controller.CanvasController;
import no.ntnu.idatt2003.chaosgame.Controller.ChaosGameController;
import no.ntnu.idatt2003.chaosgame.Controller.SceneController;
import no.ntnu.idatt2003.chaosgame.components.ChaosGame;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

public class CanvasScene {
    private VBox inputFieldsVBox;
    private TextField iterationInputField ;
    private Button runButton;
    private Button backButton;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private VBox inputFieldBox;

    private VBox root;
    private CanvasController canvasController;

    public CanvasScene(CanvasController canvasController){
        this.canvasController = canvasController;

        createAndLayoutControls();
        fillInputFieldsVBox();
        createGraphicsContext();

        canvasController.setBackButton(backButton);
        canvasController.setRunButton(runButton);
        canvasController.setIterationInputField(iterationInputField);
        canvasController.setGraphicsContext(graphicsContext);

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

        StackPane stackPane = new StackPane(canvas);
        stackPane.setStyle("-fx-background-color: black");
        ScrollPane scrollPane = new ScrollPane(inputFieldsVBox);
        inputFieldBox = new VBox(scrollPane, iterationText, iterationInputField, runButton, backButton);
        HBox container = new HBox(inputFieldBox, stackPane);

        root = new VBox(container);

    }

}
