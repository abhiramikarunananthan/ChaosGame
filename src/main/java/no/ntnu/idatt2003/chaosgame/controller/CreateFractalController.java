package no.ntnu.idatt2003.chaosgame.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatt2003.chaosgame.components.ChaosGameDescription;
import no.ntnu.idatt2003.chaosgame.data.ChaosGameFileHandler;
import no.ntnu.idatt2003.chaosgame.tensors.Complex;
import no.ntnu.idatt2003.chaosgame.tensors.Matrix2x2;
import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;
import no.ntnu.idatt2003.chaosgame.transforms.AffineTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.JuliaTransform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transform2D;
import no.ntnu.idatt2003.chaosgame.transforms.Transformations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for {@link no.ntnu.idatt2003.chaosgame.scenes.CreateFractalScene}
 *
 * @author 10052
 * @version 1.0
 */
public class CreateFractalController {

    ListView<AffineTransform2D> affineTransform2DListView;
    private final Stage stage;
    private final List<TextField> matrixInputFields;
    private final List<TextField> vectorInputFields;
    private List<TextField> constantInputFields;
    private List<TextField> coordsInputFields;
    private Button saveButton;
    private MenuButton menuButton;
    private Button backButton;
    private VBox inputFieldVBox;

    private Transformations currentTransformation;
    private final List<Transform2D> transform2DList;


    /**
     * Constructor for the {@link CreateFractalController} class.
     * @param stage Primary satge for {@link CreateFractalController}.
     */
    public CreateFractalController(Stage stage) {
        this.stage = stage;
        this.transform2DList = new ArrayList<>();
        this.matrixInputFields = new ArrayList<>();
        this.vectorInputFields = new ArrayList<>();
        this.constantInputFields = new ArrayList<>();
        this.coordsInputFields = new ArrayList<>();
    }


    /**
     * Adds action listeners to buttons in {@link CreateFractalController}.
     *
     * saveButton: Saves fractal description to file based on transformation type.
     * If any errors occur during the process, null values, number format exceptions,
     * or IO exceptions, an error alert is displayed
     *
     * backButton: Switches the scene to a different view using {@link SceneController}.
     *
     * menuButton: The menu button contains menu items representing different transformations.
     * When a menu item is clicked, it updates the text of the menu button to display
     * the selected transformation.
     */
    public void addButtonListeners() {

        saveButton.setOnAction(actionEvent -> {
            try {
                if (currentTransformation == Transformations.AFFINE2D) {
                    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(transform2DList,
                            new Vector2D(Double.parseDouble(coordsInputFields.get(0).getText()), Double.parseDouble(coordsInputFields.get(1).getText())),
                            new Vector2D(Double.parseDouble(coordsInputFields.get(2).getText()), Double.parseDouble(coordsInputFields.get(3).getText())),
                            Transformations.AFFINE2D);

                    saveDescriptionToFile(chaosGameDescription);

                } else if (currentTransformation == Transformations.JULIA) {
                    List<Transform2D> transform2DList = new ArrayList<>();

                    transform2DList.add(new JuliaTransform2D(
                            new Complex(Double.parseDouble(constantInputFields.get(0).getText()),
                                    Double.parseDouble(constantInputFields.get(1).getText())), 1));

                    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(transform2DList,
                            new Vector2D(Double.parseDouble(coordsInputFields.get(0).getText()), Double.parseDouble(coordsInputFields.get(1).getText())),
                            new Vector2D(Double.parseDouble(coordsInputFields.get(2).getText()), Double.parseDouble(coordsInputFields.get(3).getText())),
                            Transformations.JULIA);

                    saveDescriptionToFile(chaosGameDescription);
                }

            } catch (NullPointerException | NumberFormatException | IOException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Error occured");
                errorAlert.setContentText("An unexpected error has occured:\n" + e);
                errorAlert.showAndWait();
            }

        });

        backButton.setOnAction(actionEvent -> {
            SceneController sceneController = new SceneController(stage);
            sceneController.switchScene(1);
        });

        for (MenuItem menuItem : menuButton.getItems()) {
            menuItem.setOnAction(actionEvent -> {
                MenuItem item = (MenuItem) actionEvent.getSource();
                menuButton.setText(item.getText());
                currentTransformation = Transformations.valueOf(item.getText());
                updateContents(inputFieldVBox);
            });
        }

    }

    /**
     * Saves the given ChaosGameDescription to a file.
     *
     * @param chaosGameDescription The ChaosGameDescription to be saved.
     * @throws IOException If an I/O error occurs while saving the file.
     */

    private void saveDescriptionToFile(ChaosGameDescription chaosGameDescription) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*txt"),
                new FileChooser.ExtensionFilter("CSV", "*.csv"));

        File defaultDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(defaultDirectory);

        File selectedDirectory = fileChooser.showSaveDialog(stage);

        ChaosGameFileHandler.writeToFile(chaosGameDescription, selectedDirectory.getPath());

        Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
        successAlert.setHeaderText("File saved");
        successAlert.setContentText("file saved to: " + selectedDirectory.getPath());
        successAlert.showAndWait();
    }

    /**
     * Updates the current scene with the provided root node.
     * Applies default dimensions and CSS stylesheet to the scene.
     *
     * @param root The root node of the new scene to be displayed.
     */
    public void updateScene(Parent root) {
        Scene scene = new Scene(root, 600, 600);

        String css = this.getClass().getResource("/CreateFractalStylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Populates the menu button with menu items representing different transformations.
     */
    public void populateMenuButton() {
        for (Transformations transformation : Transformations.values()) {
            menuButton.getItems().add(new MenuItem(transformation.name()));
        }
    }

    /**
     * Updates the input fields displayed in the provided VBox based on the current transformation.
     * Clears the existing content, sets visibility for specific UI elements, and populates input fields accordingly.
     *
     * @param inputFieldsVBox The VBox containing the input fields to be updated.
     */
    private void updateContents(VBox inputFieldsVBox) {
        inputFieldsVBox.getChildren().clear();
        saveButton.setVisible(true);
        this.coordsInputFields = new ArrayList<>();
        switch (currentTransformation) {
            case AFFINE2D -> {
                ObservableList<AffineTransform2D> affineTransform2DObservableList = FXCollections.observableArrayList();
                affineTransform2DListView.setVisible(true);

                Text matrixText = new Text("Write the numbers wanted for the matrix");
                TextField matrixInputFieldA00 = new TextField();
                TextField matrixInputFieldA01 = new TextField();
                TextField matrixInputFieldA10 = new TextField();
                TextField matrixInputFieldA11 = new TextField();
                Text vectorText = new Text("Write the numbers wanted for the vector");
                TextField vectorInputFieldB0 = new TextField();
                TextField vectorInputFieldB1 = new TextField();
                Button addToListButton = new Button("Add to list");

                HBox matrixTopRow = new HBox(matrixInputFieldA00, matrixInputFieldA01);
                HBox matrixBottomRow = new HBox(matrixInputFieldA10, matrixInputFieldA11);

                addToListButton.setOnAction(actionEvent -> {
                    AffineTransform2D affineTransform2D = new AffineTransform2D(
                            new Matrix2x2(Double.parseDouble(matrixInputFieldA00.getText()), Double.parseDouble(matrixInputFieldA01.getText()), Double.parseDouble(matrixInputFieldA10.getText()), Double.parseDouble(matrixInputFieldA11.getText()))
                            , new Vector2D(Double.parseDouble(vectorInputFieldB0.getText()), Double.parseDouble(vectorInputFieldB1.getText())));

                    transform2DList.add(affineTransform2D);
                    affineTransform2DObservableList.add(affineTransform2D);
                    affineTransform2DListView.setItems(affineTransform2DObservableList);
                });

                inputFieldsVBox.getChildren().addAll(matrixText, matrixTopRow, matrixBottomRow,
                        vectorText, vectorInputFieldB0, vectorInputFieldB1, addToListButton);


            }
            case JULIA -> {
                affineTransform2DListView.setItems(null);
                affineTransform2DListView.setVisible(false);
                this.constantInputFields = new ArrayList<>();

                Text constantCText = new Text("Write the constant C");
                TextField constantCInputFieldReal = new TextField();
                TextField constantCInputFieldImaginary = new TextField();

                constantInputFields.add(constantCInputFieldReal);
                constantInputFields.add(constantCInputFieldImaginary);

                inputFieldsVBox.getChildren().addAll(constantCText, constantCInputFieldReal, constantCInputFieldImaginary);
            }

        }

        Text coordsMinText = new Text("Write the numbers wanted for minimum coordinates");
        TextField minCoordsX = new TextField();
        TextField minCoordsY = new TextField();
        Text coordsMaxText = new Text("Write the numbers wanted for maximum coordinates");
        TextField maxCoordsX = new TextField();
        TextField maxCoordsY = new TextField();

        coordsInputFields.add(minCoordsX);
        coordsInputFields.add(minCoordsY);
        coordsInputFields.add(maxCoordsX);
        coordsInputFields.add(maxCoordsY);

        HBox minCoordHBox = new HBox(minCoordsX, minCoordsY);
        HBox maxCoordHBox = new HBox(maxCoordsX, maxCoordsY);

        inputFieldsVBox.getChildren().addAll(coordsMinText, minCoordHBox, coordsMaxText, maxCoordHBox);
    }

    /**
     * Sets the save button for this controller.
     *
     * @param saveButton The save button to be set.
     */
    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    /**
     * Sets the menu button for this controller.
     *
     * @param menuButton The menu button to be set.
     */
    public void setMenuButton(MenuButton menuButton) {
        this.menuButton = menuButton;
    }

    /**
     * Sets the back button for this controller.
     *
     * @param backButton The back button to be set.
     */
    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    /**
     * Sets the input fields VBox for this controller.
     *
     * @param inputFieldVBox The input fields VBox to be set.
     */
    public void setInputFieldVBox(VBox inputFieldVBox) {
        this.inputFieldVBox = inputFieldVBox;
    }

    /**
     *  Sets the list view for AffineTransform2D objects for this controller.
     *
     * @param affineTransform2DListView List to be set of AffineTransform2D
     */
    public void setAffineTransform2DListView(ListView<AffineTransform2D> affineTransform2DListView) {
        this.affineTransform2DListView = affineTransform2DListView;
    }
}
