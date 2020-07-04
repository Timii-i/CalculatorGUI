package de.hsworms.ztt.keidel.calculator.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Layout extends Application{

    // Declaring constants
    final int padding = 0;
    final int buttonWidth = 75;
    final int buttonHeight = 75;

    Label calculationField = new Label();

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

        // Center the VBox in the BorderPane
        vbox.setAlignment(Pos.CENTER);

        // Setup the CalculationField in the Top of the window
        setupCalculationField(root);

        // Setups all the buttons needed for the calculator
        setupButtons(vbox);

        // Centers the GridPane in the BorderPane
        root.setCenter(vbox);

        // Scale the BorderPane with the window size
        //root.prefWidthProperty().bind(stage.widthProperty());
        //root.prefHeightProperty().bind(stage.heightProperty());

        // Set the scene to the BorderPane and show it
        stage.setScene(new Scene(root, 300, 470));
        stage.show();
    }

    /**
     * Setups every button with a name, size and place in the window
     *
     * @param vbox the columns in which the hboxes are placed
     */
    public void setupButtons(VBox vbox) {
        HBox row = null;
        // Array for the Button texts
        String[] buttonText = {"AC","C","%","/","7","8","9","*","4","5","6","-","1","2","3","+","0",",","="};
        // Array for each button
        Button[] buttons = new Button[buttonText.length];

        // Give each button the corresponding name from buttonText array
        for (int i = 0; i < buttonText.length; i++) {
            String buttonName = buttonText[i];
            buttons[i] = new Button(buttonText[i]);
            setButtonsSize(buttons[i]);

            // Sets the handler for each button
            buttons[i].setOnAction(event -> {
                if (!(buttonName.equals("AC"))) { editCalculation(buttonName); }
                else { clearCalculation(); }
            });

            // HBox to hold each row of Buttons
            if (i % 4 == 0) {
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

    /**
     * Setups the calculationField in which the calculation is displayed
     *
     * @param root the parent BorderPane to position calculationField
     */
    public void setupCalculationField(BorderPane root) {
        calculationField.setMinSize(300, 100);
        calculationField.setMaxSize(450, 150);
        calculationField.setFont(new Font("Arial", 30));
        calculationField.setAlignment(Pos.BASELINE_RIGHT);
        calculationField.setWrapText(false);
        //calculationField.setStyle("-fx-background-color: blue;");
        root.setTop(calculationField);
    }

    /**
     * Edits the calculationField
     *
     * @param element the value of the button that's pressed
     */
    public void editCalculation(String element) {
        switch (element){
            case ",":
            case "=":
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "C":
                calculationField.setText(calculationField.getText() + " " + element + " ");
                break;
            default:
                calculationField.setText(calculationField.getText() + element);
        }
        System.out.println("CalculationField: " + calculationField.getText());
    }

    /**
     * Clears the calculationField
     */
    private void clearCalculation() {
        calculationField.setText("");
    }
}
