package de.hsworms.ztt.keidel.calculator.gui;

import de.hsworms.ztt.keidel.calculator.Calculator;
import de.hsworms.ztt.keidel.calculator.InfixToPostfixConverter;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application{

    // Declaring constants
    final int padding = 2;
    final int buttonWidth = 75;
    final int buttonHeight = 75;
    final int minLabelHeight = 30;
    final int minLabelWidth = 300;


    Label postfixLabel = new Label();
    Label infixLabel = new Label();
    Label resultLabel = new Label();
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
        vbox.setStyle("-fx-background-color: #383b49");

        // Setup the postfixLabel, infixLabel and resultLabel in the Top of the window
        setupCalculationLabels(vbox, postfixLabel);
        setupCalculationLabels(vbox, infixLabel);
        setupResultLabel(vbox);

        // Setup all the buttons needed for the calculator
        setupButtons(vbox, stage);

        // Centers the VBox in the BorderPane
        root.setCenter(vbox);

        // Scale the BorderPane with the window size
        //root.prefWidthProperty().bind(stage.widthProperty());
        //root.prefHeightProperty().bind(stage.heightProperty());

        // Create a scene and add the root BorderPane to it with the window sizes of 320 x 470
        Scene scene = new Scene(root, 320, 470);
        // Import the Roboto font to use
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap");
        stage.setScene(scene);
        stage.setMinHeight(520);
        stage.setMinWidth(320);
        //stage.setResizable(false);
        stage.show();
    }

    /**
     * Setups every button with a name, size and place in the window
     *
     * @param vbox the columns in which the hboxes are placed
     */
    public void setupButtons(VBox vbox, Stage stage) {
        HBox row = null;
        // Array for the Button texts
        String[] buttonText = {"AC","(",")","%","7","8","9","/","4","5","6","*","1","2","3","-","0","=","+"};
        // Array for each button
        Button[] buttons = new Button[buttonText.length];

        // Give each button the corresponding name from buttonText array
        for (int i = 0; i < buttonText.length; i++) {
            String buttonName = buttonText[i];
            buttons[i] = new Button(buttonText[i]);
            setupButtons(buttons[i], stage);
            styleButtons(buttons[i]);
            buttons[i].setEffect(new DropShadow());

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
            //HBox.setHgrow(buttons[i], Priority.ALWAYS);
            //VBox.setVgrow(row, Priority.ALWAYS);
            //buttons[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }
    }

    /**
     * Sets the height and width for a given button
     *
     * @param button the button that should be adjusted in size
     */
    public void setupButtons(Button button, Stage stage) {
        // Makes the "0" button double the width
        if (button.getText().equals("0")) {
            /*button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            button.setPrefHeight(buttonHeight);
            button.setPrefWidth(buttonWidth * 2);*/
            button.setMinWidth(74);
            // Scales the width and height of the button with the window size
            button.prefHeightProperty().bind(Bindings.divide(stage.widthProperty(), 1.0));
            button.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 0.81));
        } else {
            /*button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            button.setPrefHeight(buttonHeight);
            button.setPrefWidth(buttonWidth);*/

            // Scales the width and height of the button with the window size
            button.prefHeightProperty().bind(Bindings.divide(stage.widthProperty(), 1.0));
            button.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 1.0));
        }
    }

    private void styleButtons(Button button) {
        button.setStyle("-fx-background-color: #2e303f; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 18pt; " +
                "-fx-border-radius: 5px;" +
                "-fx-font-family: Roboto;");
    }

    /**
     * Setup the resultLabel in which the result of the calculation is displayed
     *
     * @param vbox the parent VBox to position resultLabel
     */
    public void setupResultLabel(VBox vbox) {
        resultLabel.setMinSize(300, 100);
        resultLabel.setMaxSize(1920, 150);
        resultLabel.setAlignment(Pos.BASELINE_RIGHT);
        resultLabel.setWrapText(false);
        resultLabel.setStyle("-fx-background-color: #1c1e27; -fx-text-fill: white; -fx-padding: 0 10 0 0; -fx-font-family: Roboto; -fx-font-size: 60");
        vbox.getChildren().add(resultLabel);
    }

    /**
     * Setup a given label and add it to the parent vbox
     *
     * @param vbox the parent VBox to position the given label
     * @param label the label that should be set up
     */
    public void setupCalculationLabels(VBox vbox, Label label) {
        label.setMinSize(minLabelWidth, minLabelHeight);
        label.setMaxSize(1920, 50);
        label.setAlignment(Pos.BASELINE_RIGHT);
        label.setWrapText(false);
        System.out.println(javafx.scene.text.Font.getFamilies());
        label.setStyle("-fx-background-color: #1c1e27 -fx-text-fill: white; -fx-padding: 0 10 0 0; -fx-font-family: Roboto; -fx-font-size: 15");
        vbox.getChildren().add(label);
    }

    /**
     * Edits the infixCalculation
     *
     * @param element the value of the button that's pressed
     */
    public void editCalculation(String element) {
        String calculation = resultLabel.getText();
        switch (element){
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "(":
                resultLabel.setText(calculation + " " + element + " ");
                break;

            case ")":
                // if there hasn't been a opening bracket add a opening bracket and the other way around
                if (calculation.indexOf('(') != -1) {
                    resultLabel.setText(calculation + " ) ");
                }
                break;

            case "=":
                try {
                    double result = Calculator.getResult(calculation);
                    // Show the calculation in postfix in the postfixLabel
                    postfixLabel.setText(InfixToPostfixConverter.toPostfix(calculation));
                    // Show the calculation in infix in the infixLabel
                    infixLabel.setText((calculation));
                    // Show the result of the calculation in resultLabel
                    resultLabel.setText(Double.toString(result));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            default:
                resultLabel.setText(calculation + element);
        }
        System.out.println("infixCalculation: " + resultLabel.getText());
    }

    /**
     * Clears the infixCalculation
     */
    private void clearCalculation() {
        resultLabel.setText("");
    }
}
