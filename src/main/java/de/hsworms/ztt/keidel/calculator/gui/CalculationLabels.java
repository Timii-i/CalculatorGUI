package de.hsworms.ztt.keidel.calculator.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Class for creating and styling the calculationLabels postfixLabel, infixLabel and resultLabel
 * that each show a different version of the calculation
 */
public class CalculationLabels {

    // Declaring constants
    final int minLabelHeight = 30;
    final int minLabelWidth = 300;
    int fontSize = 60;

    // Label that shows the calculation in postfix after the user pressed "="
    static Label postfixLabel = new Label();
    // Label that shows the calculation in infix after the user pressed "="
    static Label infixLabel = new Label();
    // Label that shows the current calculation and the result of the calculation after the user pressed "="
    static Label resultLabel = new Label();

    /**
     * Setup a postfixLabel and add it to the parent vbox
     *
     * @param vbox the parent VBox to position the postfixLabel
     */
    public void setupPostfixLabel(VBox vbox) {
        postfixLabel.setMinSize(minLabelWidth, minLabelHeight);
        postfixLabel.setMaxSize(1920, 50);
        postfixLabel.setAlignment(Pos.BASELINE_RIGHT);
        postfixLabel.setWrapText(false);
        postfixLabel.setStyle("-fx-background-color: #1c1e27; " +
                "-fx-text-fill: white; " +
                "-fx-padding: 0 10 0 0; " +
                "-fx-font-family: Roboto; " +
                "-fx-font-size: 15; " +
                "-fx-background-radius: 5px");
        vbox.getChildren().add(postfixLabel);
    }

    /**
     * Setup the infixLabel and add it to the parent vbox
     *
     * @param vbox the parent VBox to position the infixLabel
     */
    public void setupInfixLabel(VBox vbox) {
        infixLabel.setMinSize(minLabelWidth, minLabelHeight);
        infixLabel.setMaxSize(1920, 50);
        infixLabel.setAlignment(Pos.BASELINE_RIGHT);
        infixLabel.setWrapText(false);
        infixLabel.setStyle("-fx-background-color: #1c1e27; " +
                "-fx-text-fill: white; " +
                "-fx-padding: 0 10 0 0; " +
                "-fx-font-family: Roboto; " +
                "-fx-font-size: 15; " +
                "-fx-background-radius: 5px");
        vbox.getChildren().add(infixLabel);
    }

    /**
     * Setup the resultLabel in which the current calculation and the result of the calculation is displayed
     *
     * @param vbox the parent VBox to position resultLabel
     */
    public void setupResultLabel(VBox vbox) {

        resultLabel.setMinSize(300, 100);
        resultLabel.setMaxSize(1920, 150);

        // Add a text listener to check after every character if the font size needs to be scaled down
        resultLabel.textProperty().addListener((observable, oldValue, newValue) -> scaleDownFont(resultLabel));

        resultLabel.setAlignment(Pos.BASELINE_RIGHT);
        resultLabel.setWrapText(false);
        resultLabel.setStyle("-fx-background-color: #1c1e27; " +
                "-fx-text-fill: white; " +
                "-fx-padding: 0 10 0 0; " +
                "-fx-font-family: Roboto; " +
                "-fx-background-radius: 5px; " +
                "-fx-font-size: 60px");
        vbox.getChildren().add(resultLabel);
    }

    /**
     * Function to check if the font size needs to be scaled down or not. If yes scale down the font size
     *
     * @param resultLabel the label in which the text needs to be scaled down
     */
    private void scaleDownFont(Label resultLabel) {
        Platform.runLater(() -> {
            // Changes the string of the calculation to a Text object to get the width if the text
            Text resultText = new Text(resultLabel.getText());
            resultText.setFont(Font.font(fontSize));

            // Checks if the width of the text is bigger than the width of the label. If yes divide the fontSize with 1.3
            if (resultText.getLayoutBounds().getWidth() >= resultLabel.getBoundsInLocal().getWidth() - 20 && fontSize > 15) {
                System.out.println(fontSize);
                fontSize /= 1.3;
                resultLabel.setStyle("-fx-background-color: #1c1e27; " +
                        "-fx-text-fill: white; " +
                        "-fx-padding: 0 10 0 0; " +
                        "-fx-font-family: Roboto; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-font-size: " + fontSize + "px");
            }
        });
    }

    /**
     * Getters and Setters for every Label
     */
    public void setResultLabel(String calculation) { resultLabel.setText(calculation);; }

    public String getResultLabel() { return resultLabel.getText(); }

    public void setInfixLabel(String calculation) {
        infixLabel.setText(calculation);
    }

    public String getInfixLabel() {
        return infixLabel.getText();
    }

    public void setPostfixLabel(String calculation) {
        postfixLabel.setText(calculation);
    }

    public String getPostfixLabel() {
        return postfixLabel.getText();
    }
}
