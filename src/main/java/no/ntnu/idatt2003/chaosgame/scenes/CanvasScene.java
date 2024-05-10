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

import java.io.FileNotFoundException;

public class CanvasScene {

    private Scene scene;

    public CanvasScene(Stage stage){

        TextField iterationInputField = new TextField("Choose iterations");
        Button runButton = new Button("Run");
        Canvas canvas = new Canvas(600.0f, 600.0f);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.setStroke(Color.PURPLE);
        graphicsContext.setLineWidth(1.0);

        graphicsContext.save();
        graphicsContext.translate(300, 300);
        graphicsContext.rotate(90);
        graphicsContext.translate(-300,-300);



        runButton.setOnAction(actionEvent -> {
            try{

                ChaosGameDescription chaosGameDescription = ChaosGameFileHandler.readFromFile("barnsley.txt");

                ChaosGame chaosGame = new ChaosGame(chaosGameDescription, 600,600);
                chaosGame.runSteps(Integer.parseInt(iterationInputField.getText()));
                int[][] canvasArray = chaosGame.getCanvas().getCanvasArray();
                for (int i = 0; i < canvasArray.length; i++) {
                    for (int j = 0; j < canvasArray.length; j++) {
                        if(canvasArray[i][j] == 1){
                            graphicsContext.strokeLine(i,j, i, j);
                        }
                    }
                }
            }catch (FileNotFoundException fileNotFoundException){

            }

        });

        iterationInputField.setMinWidth(50);
        runButton.setMinWidth(50);

        VBox inputFieldBox = new VBox(iterationInputField, runButton);
        HBox container = new HBox(inputFieldBox, canvas);


        VBox root = new VBox(container);


        scene = new Scene(root, 600, 600);

    }

    public Scene getScene() {
        return scene;
    }

}
