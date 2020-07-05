package de.hsworms.ztt.keidel.calculator.gui;

import de.hsworms.ztt.keidel.calculator.Calculator;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application{

    // Declaring constants
    final int padding = 0;
    final int buttonWidth = 75;
    final int buttonHeight = 75;


    Label infixCalculation = new Label();
    Label postfixCalculation = new Label();
    BorderPane root = new BorderPane();
    VBox vbox = new VBox(padding);

    /**
     * Start a given stage (Entry point for the application)
     *
     * @param stage the stage that should be started
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        // Center the VBox in the BorderPane
        vbox.setAlignment(Pos.CENTER);

        // Setup the postfixCalculation and infixCalculation in the Top of the window
        setupPostfixCalculation(vbox);
        setupInfixCalculation(vbox);

        // Setups all the buttons needed for the calculator
        setupButtons(vbox);

        // Centers the VBox in the BorderPane
        root.setCenter(vbox);

        // Scale the BorderPane with the window size
        //root.prefWidthProperty().bind(stage.widthProperty());
        //root.prefHeightProperty().bind(stage.heightProperty());

        // Set the scene to the BorderPane and show it
        stage.setScene(new Scene(root, 300, 470));
        //stage.setResizable(false);
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
        String[] buttonText = {"AC","(",")","%","7","8","9","/","4","5","6","*","1","2","3","-","0","=","+"};
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
            // Let the HBoxes and VBox grow with the window size
            HBox.setHgrow(buttons[i], Priority.ALWAYS);
            VBox.setVgrow(row, Priority.ALWAYS);
            //buttons[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
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
            button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            button.setPrefHeight(buttonHeight);
            button.setPrefWidth(buttonWidth * 2);
        } else {
            button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            button.setPrefHeight(buttonHeight);
            button.setPrefWidth(buttonWidth);
        }
    }

    /**
     * Setups the infixCalculation in which the calculation is displayed
     *
     * @param vbox the parent VBox to position infixCalculation
     */
    public void setupInfixCalculation(VBox vbox) {
        infixCalculation.setMinSize(300, 100);
        infixCalculation.setMaxSize(1920, 150);
        infixCalculation.setFont(new Font("Arial", 30));
        infixCalculation.setAlignment(Pos.BASELINE_RIGHT);
        infixCalculation.setWrapText(false);
        infixCalculation.setStyle("-fx-background-color: lightblue;");
        vbox.getChildren().add(infixCalculation);
    }

    public void setupPostfixCalculation(VBox vbox) {
        postfixCalculation.setMinSize(300, 20);
        postfixCalculation.setMaxSize(1920, 50);
        postfixCalculation.setFont(new Font("Arial", 20));
        postfixCalculation.setAlignment(Pos.BASELINE_RIGHT);
        postfixCalculation.setWrapText(false);
        postfixCalculation.setStyle("-fx-background-color: lightgreen");
        vbox.getChildren().add(postfixCalculation);
    }

    /**
     * Edits the infixCalculation
     *
     * @param element the value of the button that's pressed
     */
    public void editCalculation(String element) {
        String calculation = infixCalculation.getText();
        switch (element){
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "(":
                infixCalculation.setText(calculation + " " + element + " ");
                break;

            case ")":
                // if there hasn't been a opening bracket add a opening bracket and the other way around
                if (calculation.indexOf('(') != -1) {
                    infixCalculation.setText(calculation + " ) ");
                }
                break;

            case "=":
                // compute result and show it in calculationField
                try {
                    double result = Calculator.getResult(calculation);
                    infixCalculation.setText(Double.toString(result));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            default:
                infixCalculation.setText(calculation + element);
        }
        System.out.println("infixCalculation: " + infixCalculation.getText());
    }

    /**
     * Clears the infixCalculation
     */
    private void clearCalculation() {
        infixCalculation.setText("");
    }
}
