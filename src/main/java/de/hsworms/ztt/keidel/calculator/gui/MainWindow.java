package de.hsworms.ztt.keidel.calculator.gui;

import de.hsworms.ztt.keidel.calculator.Calculator;
import de.hsworms.ztt.keidel.calculator.InfixToPostfixConverter;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainWindow extends Application{

    // Declaring constants
    final int padding = 4;
    final int buttonWidth = 75;
    final int buttonHeight = 75;
    final int minLabelHeight = 30;
    final int minLabelWidth = 300;

    BorderPane windowDecoration = new BorderPane();
    HBox windowDecorationButtons = new HBox(padding);
    Button closeButton = new Button("x");
    Button minButton = new Button("_");
    Label decorationLabel = new Label("Calculator");
    Label postfixLabel = new Label();
    Label infixLabel = new Label();
    Label resultLabel = new Label();
    BorderPane root = new BorderPane();
    VBox vbox = new VBox(padding);
    double xCoord;
    double yCoord;

    /**
     * Start a given stage (Entry point for the application)
     *
     * @param stage the stage that should be started
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        root.setPadding(new Insets(5 ));
        root.setStyle("-fx-background-radius: 10px");

        // Center the VBox in the BorderPane
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #383b49");

        // Creates a custom Window Decoration
        setupWindowDecoration(vbox, stage);

        // Setup the postfixLabel, infixLabel and resultLabel in the Top of the window
        setupCalculationLabels(vbox, postfixLabel);
        setupCalculationLabels(vbox, infixLabel);
        setupResultLabel(vbox);

        // Setup all the buttons needed for the calculator
        setupButtonSize(vbox, stage);

        // Centers the VBox in the BorderPane
        root.setCenter(vbox);
        root.setStyle("-fx-background-color: #383b49");

        // Scale the BorderPane with the window size
        //root.prefWidthProperty().bind(stage.widthProperty());
        //root.prefHeightProperty().bind(stage.heightProperty());

        // Create a scene with the size of 320 x 470
        Scene scene = new Scene(root, 320, 470);
        // Import the Roboto font from Google Web Fonts for us to use
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap");

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setMinHeight(520);
        stage.setMinWidth(320);
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
    }

    /**
     * Creates and setups a custom Window Decoration with close and minimize buttons
     *
     * @param vbox the parent VBox in which the HBox is placed
     */
    private void setupWindowDecoration(VBox vbox, Stage stage) {

        // Add handler for minButton to minimize on click
        styleWindowButtons(minButton);
        minButton.setOnAction(event -> stage.setIconified(true));

        // Add handler for closeButton to close the window on click
        styleWindowButtons(closeButton);
        closeButton.setOnAction(event -> stage.close());

        // Add handler for the whole windowDecoration to get the x and y coordinates on click
        windowDecoration.setOnMousePressed(event -> {
            xCoord = event.getSceneX();
            yCoord = event.getSceneY();
        });

        // Add handler for the whole windowDecoration to set the scene to the new x and y coordinates on mouse drag
        windowDecoration.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xCoord);
            stage.setY(event.getScreenY() - yCoord);
        });

        decorationLabel.setStyle("-fx-text-fill: #c2c1c2");
        windowDecorationButtons.getChildren().addAll(minButton, closeButton);

        windowDecoration.setCenter(decorationLabel);
        windowDecoration.setRight(windowDecorationButtons);

        windowDecoration.setStyle("-fx-background-color: #1c1e27; -fx-text-fill: white; -fx-font-family: Roboto; -fx-font-size: 15; -fx-background-radius: 5px");
        BorderPane.setMargin(decorationLabel, new Insets(0, -55, 0 , 0));
        vbox.getChildren().add(windowDecoration);
    }

    /**
     * Adds DropShadow and Css Styling to each window button
     *
     * @param button the button that is styled
     */
    private void styleWindowButtons(Button button) {
        button.setStyle("-fx-background-color: #2e303f; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 10pt; " +
                "-fx-font-family: Roboto;" +
                "-fx-background-radius: 5px;");
    }

    /**
     * Setups every button with a name, size and place in the window
     *
     * @param vbox the columns in which the hboxes are placed
     */
    public void setupButtonSize(VBox vbox, Stage stage) {
        HBox row = null;
        // Array for the Button texts
        String[] buttonText = {"AC","(",")","%","7","8","9","/","4","5","6","*","1","2","3","-","0","=","+"};
        // Array for each button
        Button[] buttons = new Button[buttonText.length];

        // Give each button the corresponding name from buttonText array
        for (int i = 0; i < buttonText.length; i++) {
            String buttonName = buttonText[i];
            buttons[i] = new Button(buttonText[i]);
            setupButtonSize(buttons[i], stage);
            styleButtons(buttons[i]);

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
    public void setupButtonSize(Button button, Stage stage) {
        //FIXME Scaling of the "0" Button

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

    /**
     * Adds DropShadow and Css Styling to each button
     *
     * @param button the button that is styled
     */
    private void styleButtons(Button button) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(1);
        dropShadow.setOffsetY(1);
        dropShadow.setWidth(10);
        dropShadow.setHeight(10);
        button.setEffect(dropShadow);
        button.setStyle("-fx-background-color: #2e303f; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 18pt; " +
                "-fx-font-family: Roboto;" +
                "-fx-background-radius: 5px");
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
        resultLabel.setStyle("-fx-background-color: #1c1e27; -fx-text-fill: white; -fx-padding: 0 10 0 0; -fx-font-family: Roboto; -fx-font-size: 60; -fx-background-radius: 5px");
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
        label.setStyle("-fx-background-color: #1c1e27; -fx-text-fill: white; -fx-padding: 0 10 0 0; -fx-font-family: Roboto; -fx-font-size: 15; -fx-background-radius: 5px");
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
        postfixLabel.setText("");
        infixLabel.setText("");
        resultLabel.setText("");
    }
}
