package de.hsworms.ztt.keidel.calculator.gui;

import de.hsworms.ztt.keidel.calculator.Calculator;
import de.hsworms.ztt.keidel.calculator.InfixToPostfixConverter;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class for creating and styling the calculator buttons
 */
public class CalculationButtons {

    final int padding = 4;
    final int buttonWidth = 75;
    final int buttonHeight = 75;

    CalculationLabels calculationLabels = new CalculationLabels();

    /**
     * Setups every button with a name, size and place in the window
     *
     * @param vbox the columns in which the hboxes are placed
     */
    public void setupCalculatorButtons(VBox vbox, Stage stage) {
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
            setButtonHover(buttons[i]);

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
     * Edits the resultLabel corresponding to the button that's pressed
     *
     * @param element the text of the button that's pressed
     */
    public void editCalculation(String element) {
        String calculation = calculationLabels.getResultLabel();
        switch (element){
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "(":
                calculationLabels.setResultLabel(calculation + " " + element + " ");

            case ")":
                // if there hasn't been a opening bracket add a opening bracket else add a closing bracket
                if (calculation.indexOf('(') != -1) {
                    calculationLabels.setResultLabel(calculation + " ) ");
                }
                break;

            case "=":
                try {
                    double result = Calculator.getResult(calculation);
                    // Show the calculation in postfix in the postfixLabel
                    calculationLabels.setPostfixLabel(InfixToPostfixConverter.toPostfix(calculation));
                    // Show the calculation in infix in the infixLabel
                    calculationLabels.setInfixLabel((calculation));
                    // Show the result of the calculation in resultLabel
                    calculationLabels.setResultLabel(Double.toString(result));

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            default:
                calculationLabels.setResultLabel(calculation + element);

        }
        System.out.println("calculation: " + calculationLabels.getResultLabel());
    }

    /**
     * Clears postfixLabel, infixLabel and resultLabel
     */
    public void clearCalculation() {
        calculationLabels.setPostfixLabel("");
        calculationLabels.setInfixLabel("");
        calculationLabels.setPostfixLabel("");
    }

    /**
     * Adds the mouse hover handlers for a given button
     *
     * @param button the button for which a hover effect should be added
     */
    public void setButtonHover(Button button) {
        button.setOnMouseEntered(event -> setButtonsHover(button));
        button.setOnMouseExited(event -> styleButtons(button));
    }

    /**
     * Adds a hover effect for a given button
     *
     * @param button the button for which the hover effect is added
     */
    private void setButtonsHover(Button button) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(1);
        dropShadow.setOffsetY(1);
        dropShadow.setWidth(10);
        dropShadow.setHeight(10);
        button.setEffect(dropShadow);
        button.setStyle("-fx-background-color: #36384a; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 18pt; " +
                "-fx-font-family: Roboto;" +
                "-fx-background-radius: 5px");
    }

    /**
     * Sets the height and width for a given button
     *
     * @param button the button that should be adjusted in size
     */
    public void setupButtonSize(Button button, Stage stage) {
        // Makes the "0" button double the width
        if (button.getText().equals("0")) {
            button.setPrefHeight(buttonHeight);
            button.setPrefWidth(buttonWidth * 2);

            // Scales the width and height of the button with the window size
            //button.prefHeightProperty().bind(Bindings.divide(stage.widthProperty(), 1.0));
            //button.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 0.81));
        } else {
            button.setPrefHeight(buttonHeight);
            button.setPrefWidth(buttonWidth);

            // Scales the width and height of the button with the window size
            //button.prefHeightProperty().bind(Bindings.divide(stage.widthProperty(), 1.0));
            //button.prefWidthProperty().bind(Bindings.divide(stage.widthProperty(), 1.0));
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
}
