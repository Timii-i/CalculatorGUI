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
        // Array for the Button texts (\u221A is the Unicode for the square root icon)
        String[] buttonText = {"!","\u03C0","e","log()","ln()","AC","(",")","%","^","7","8","9","/","\u221A","4","5","6","*","sin()","1","2","3","-","cos()","0","=","+","tan()"};
        // Array for each button
        Button[] buttons = new Button[buttonText.length];

        // Give each button the corresponding name from buttonText array
        for (int i = 0; i < buttonText.length; i++) {
            String buttonName = buttonText[i];
            buttons[i] = new Button(buttonText[i]);
            setupButtonSize(buttons[i]);
            styleButtons(buttons[i]);
            setButtonHover(buttons[i]);

            // Sets the handler for each button
            buttons[i].setOnAction(event -> {
                if (!(buttonName.equals("AC"))) { editCalculation(buttonName); }
                else { clearCalculation(); }
            });

            // HBox to hold each row of Buttons
            if (i % 5 == 0) {
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
            case "^":
                calculationLabels.setResultLabel(calculation + " " + element + " ");
                break;

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

                // pi
            case "\u03C0":
                calculationLabels.setResultLabel(calculation + "pi");
                break;

                // square root
            case "\u221A":
                calculationLabels.setResultLabel(calculation + "sqrt ( ");
                break;

            case "sin()":
                calculationLabels.setResultLabel(calculation + "sin ( ");
                break;

            case "cos()":
                calculationLabels.setResultLabel(calculation + "cos ( ");
                break;

            case "tan()":
                calculationLabels.setResultLabel(calculation + "tan ( ");
                break;

            case "!":
                calculationLabels.setResultLabel(calculation + "fac ( ");
                break;

            case "log()":
                calculationLabels.setResultLabel(calculation + "log ( ");
                break;

            case "ln()":
                calculationLabels.setResultLabel(calculation + "ln ( ");
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
        calculationLabels.setResultLabel("");
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
                "-fx-font-size: 15pt; " +
                "-fx-font-family: Roboto;" +
                "-fx-background-radius: 5px");
    }

    /**
     * Sets the height and width for a given button
     *
     * @param button the button that should be adjusted in size
     */
    public void setupButtonSize(Button button) {
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
                "-fx-font-size: 15pt; " +
                "-fx-font-family: Roboto;" +
                "-fx-background-radius: 5px");
    }
}
