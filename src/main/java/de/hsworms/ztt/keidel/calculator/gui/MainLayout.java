package de.hsworms.ztt.keidel.calculator.gui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainLayout extends Application{

    // Declaring constants
    final int padding = 4;

    // Root BorderPane which is the main Layout Pane where everything is put into
    BorderPane root = new BorderPane();
    // VBox for each individual element to be stacked vertically
    VBox vbox = new VBox(padding);

    /**
     * Start the main stage (Entry point for the application)
     *
     * @param stage the stage that should be started
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        // Center the VBox in the BorderPane
        vbox.setAlignment(Pos.CENTER_RIGHT);
        vbox.setPadding(new Insets(5));
        vbox.setStyle("-fx-background-radius: 10px; " +
                "-fx-background-color: #383b49; " +
                "-fx-border-radius: 10px; " +
                "-fx-border-color: white");

        // Creates a custom Window Decoration
        WindowDecoration windowDecoration = new WindowDecoration();
        windowDecoration.setupWindowDecoration(vbox, stage);

        // Setup the postfixLabel, infixLabel and resultLabel in the Top of the window
        CalculationLabels calculationLabels = new CalculationLabels();
        calculationLabels.setupPostfixLabel(vbox);
        calculationLabels.setupInfixLabel(vbox);
        calculationLabels.setupResultLabel(vbox);

        // Setup all the buttons needed for the calculator
        CalculationButtons calculationButtons = new CalculationButtons();
        calculationButtons.setupCalculatorButtons(vbox, stage);

        // Centers the VBox in the BorderPane
        root.setCenter(vbox);
        root.setStyle("-fx-background-color: transparent");

        // Create a scene with the size of 320 x 470 and a transparent background
        Scene scene = new Scene(root, 320, 470, Color.TRANSPARENT);
        // Import the Roboto font from Google Web Fonts for us to use
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap");

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setMinHeight(520);
        stage.setMinWidth(320);
        stage.setScene(scene);
        stage.show();
    }
}
