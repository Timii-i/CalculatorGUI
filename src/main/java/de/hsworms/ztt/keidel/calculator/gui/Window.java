package de.hsworms.ztt.keidel.calculator.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Arrays;

public class Window extends Application implements EventHandler<ActionEvent> {

    // Declaring constants
    final int padding = 5;
    final int buttonWidth = 35;
    final int buttonHeight = 35;

    /**
     * Start a given stage (Entry point for the application)
     *
     * @param stage the stage that should be started
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        VBox vbox = new VBox(padding);

        // Set the CalculationField in the Top of the window
        TextField calculationField = new TextField();
        root.setTop(calculationField);

        // Setups all the buttons needed for the calculator
        setupButtons(vbox);

        // Centers the GridPane in the BorderPane
        root.setCenter(vbox);

        // Set the scene to the BorderPane and show it
        stage.setScene(new Scene(root, 300, 250));
        stage.show();
    }

    /**
     * Handles action when something is pressed
     *
     * @param actionEvent the event that should be handled
     */
    @Override
    public void handle(ActionEvent actionEvent) {

    }

    /**
     * Setups every button with a name, size and place in the window
     *
     * @param vbox the columns in which the hboxes are placed
     */
    public void setupButtons(VBox vbox) {
        HBox row = null;
        // Array for the Button texts
        String[] buttonText = {"AC","C","%","/","7","8","9","*","4","5","6","-","1","2","3","+","0",".","="};
        // Array for each button
        Button[] buttons = new Button[buttonText.length];

        // Give each button the corresponding name from buttonText array
        for (int i = 0; i < buttonText.length; i++) {
            buttons[i] = new Button(buttonText[i]);
            setButtonsSize(buttons[i]);

            // "newline" for hboxes every 4 buttons
            if (i % 4 == 0) {
                System.out.println(i);
                // HBox for each new row
                row = new HBox(padding);
                vbox.getChildren().add(row);
            }
            row.getChildren().add(buttons[i]);
        }
    }

    /**
     * Sets the height and width for a given button
     *
     * @param button the button that should be adjusted in size
     */
    public void setButtonsSize(Button button) {
        // Makes the "0" button double the width
        if (button.getText().equals("0")) {
            button.setPrefHeight(buttonHeight);
            button.setPrefWidth(buttonWidth * 2 + padding);
        } else {
            button.setPrefHeight(buttonHeight);
            button.setPrefWidth(buttonWidth);
        }
    }
}
