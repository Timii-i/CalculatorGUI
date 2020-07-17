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
    final int buttonWidth = 80;
    final int buttonHeight = 75;

    /**
     * Setups every button with a name, size and place in the window
     *
     * @param vbox the columns in which the hboxes are placed
     */
    public void setupCalculatorButtons(VBox vbox, Stage stage) {
        HBox row = null;
        // Array for the Button texts (\u221A is the Unicode for the square root icon and \u03C0 is the Unicode for the pi symbol)
        String[] buttonText = {"log()","ln()","\u03C0","e","AC","\u221A","(",")","%","^","fac()","7","8","9","/","sin()","4","5","6","*","cos()","1","2","3","-","tan()",".","0","=","+"};
        // Array for each button
        Button[] buttons = new Button[buttonText.length];

        // Give each button the corresponding name from buttonText array
        for (int i = 0; i < buttonText.length; i++) {
            String buttonName = buttonText[i];
            buttons[i] = new Button(buttonText[i]);
            setupButtonSize(buttons[i]);

            // Colors the button borders differently compared to the "AC", "." and "=" the number buttons:
            if (i <= 10 || (i >= 14 && i <= 15) || (i >= 19 && i <= 20) || (i >= 24 && i <= 25) || i == 29) {
                styleNonNumberButtons(buttons[i]);
                // Add a button hover effect
                setNonNumberButtonHover(buttons[i]);
                // Add pressed effect
                setNonNumberButtonPressed(buttons[i]);
            }
            else {
                styleNumberButtons(buttons[i]);
                // Add a button hover effect
                setNumberButtonHover(buttons[i]);
                // Add pressed effect
                setNumberButtonPressed(buttons[i]);
            }

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
        String calculation = CalculationLabels.getResultLabel();
        switch (element){
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
            case "(":
            case "^":
                CalculationLabels.setResultLabel(calculation + " " + element + " ");
                break;

            case ")":
                // if there hasn't been a opening bracket add a opening bracket else add a closing bracket
                if (calculation.indexOf('(') != -1) {
                    CalculationLabels.setResultLabel(calculation + " ) ");
                }
                break;

            case "=":
                try {
                    double result = Calculator.getResult(calculation);
                    // Show the calculation in postfix in the postfixLabel
                    CalculationLabels.setPostfixLabel(InfixToPostfixConverter.toPostfix(calculation));
                    // Show the calculation in infix in the infixLabel
                    CalculationLabels.setInfixLabel((calculation));
                    // Show the result of the calculation in resultLabel
                    CalculationLabels.setResultLabel(Double.toString(result));

                }
                catch (IOException e) {
                    CalculationLabels.putError();
                    e.printStackTrace();
                }
                break;

                // pi
            case "\u03C0":
                CalculationLabels.setResultLabel(calculation + "pi");
                break;

                // square root
            case "\u221A":
                CalculationLabels.setResultLabel(calculation + "sqrt ( ");
                break;

            case "sin()":
                CalculationLabels.setResultLabel(calculation + "sin ( ");
                break;

            case "cos()":
                CalculationLabels.setResultLabel(calculation + "cos ( ");
                break;

            case "tan()":
                CalculationLabels.setResultLabel(calculation + "tan ( ");
                break;

            case "fac()":
                CalculationLabels.setResultLabel(calculation + "fac ( ");
                break;

            case "log()":
                CalculationLabels.setResultLabel(calculation + "log ( ");
                break;

            case "ln()":
                CalculationLabels.setResultLabel(calculation + "ln ( ");
                break;

            default:
                CalculationLabels.setResultLabel(calculation + element);

        }
        System.out.println("calculation: " + CalculationLabels.getResultLabel());
    }

    /**
     * Clears postfixLabel, infixLabel and resultLabel
     */
    public void clearCalculation() {
        CalculationLabels.setPostfixLabel("");
        CalculationLabels.setInfixLabel("");
        CalculationLabels.setResultLabel("");
    }

    /**
     * Adds an effect for when a button pressed and the mouse pressed and released handler
     *
     * @param button the button for which the "pressed" effect is added
     */
    private void setNonNumberButtonPressed(Button button) {
        // Set even brighter background
        button.setOnMousePressed(event -> {
            button.setEffect(setDropShadow());
            if (button.getText().equals("AC")) {
                setButtonStyle(button, "#4b4e68", "#fc5e87");
            } else {
                setButtonStyle(button, "#4b4e68", "#ffc093");
            }
        });

        // Set the background back to when mouse hovers
        button.setOnMouseReleased(event -> {
            button.setEffect(setDropShadow());
            if (button.getText().equals("AC")) {
                setButtonStyle(button, "#36384a", "#fc5e87");
            } else {
                setButtonStyle(button, "#36384a", "#ffc093");
            }
        });
    }

    /**
     * Adds an effect for when a button pressed and the mouse pressed and released handler
     *
     * @param button the button for which the "pressed" effect is added
     */
    private void setNumberButtonPressed(Button button) {
        // Set even brighter background
        button.setOnMousePressed(event -> {
            button.setEffect(setDropShadow());
            if (button.getText().equals("=")) {
                setButtonStyle(button, "#4b4e68", "#01dfa0");
            } else if (button.getText().equals(".")) {
                setButtonStyle(button, "#4b4e68", "#6699ff");
            } else {
                setButtonStyle(button, "#4b4e68", "#404459");
            }
        });

        // Set the background back to how it looks when the mouse hovers over the button
        button.setOnMouseReleased(event -> {
            button.setEffect(setDropShadow());
            if (button.getText().equals("=")) {
                setButtonStyle(button, "#36384a", "#01dfa0");
            } else if (button.getText().equals(".")) {
                setButtonStyle(button, "#36384a", "#6699ff");
            } else {
                setButtonStyle(button, "#36384a", "#404459");
            }
        });
    }

    /**
     * Adds the mouse hover handlers for a given number button
     *
     * @param button the button for which a hover effect should be added
     */
    public void setNumberButtonHover(Button button) {
        // Set brighter background when mouse enters the button
        button.setOnMouseEntered(event -> {
                    button.setEffect(setDropShadow());
                    if (button.getText().equals("=")) {
                        setButtonStyle(button, "#36384a", "#01dfa0");
                    } else if (button.getText().equals(".")) {
                        setButtonStyle(button, "#36384a", "#6699ff");
                    } else {
                        setButtonStyle(button, "#36384a", "#404459");
                    }
                });

        // Back to normal background color after the mouse exited
        button.setOnMouseExited(event -> styleNumberButtons(button));
    }

    /**
     * Adds the mouse hover handlers for a given non number button
     *
     * @param button the button for which a hover effect should be added
     */
    public void setNonNumberButtonHover(Button button) {
        // Set brighter background when mouse enters the button
        button.setOnMouseEntered(event -> {
            button.setEffect(setDropShadow());
            if (button.getText().equals("AC")) {
                setButtonStyle(button, "#36384a", "#fc5e87");
            } else {
                setButtonStyle(button, "#36384a", "#ffc093");
            }
        });

        // Back to normal background color after the mouse exited
        button.setOnMouseExited(event -> styleNonNumberButtons(button));
    }

    /**
     * Sets the height and width for a given button
     *
     * @param button the button that should be adjusted in size
     */
    public void setupButtonSize(Button button) {
        button.setPrefHeight(buttonHeight);
        button.setPrefWidth(buttonWidth);
    }

    /**
     * Adds DropShadow and Css Styling to each number button
     *
     * @param button the button that is styled
     */
    private void styleNumberButtons(Button button) {
        button.setEffect(setDropShadow());
        if (button.getText().equals("=")) {
            setButtonStyle(button, "#1c1e27", "#01dfa0");
        } else if (button.getText().equals(".")) {
            setButtonStyle(button, "#1c1e27", "#6699ff");
        } else {
            setButtonStyle(button, "#1c1e27", "#606685");
        }
    }

    /**
     * Adds DropShadow and Css Styling to each non number button
     *
     * @param button the button that is styled
     */
    private void styleNonNumberButtons(Button button) {
        button.setEffect(setDropShadow());
        if (button.getText().equals("AC")) {
            setButtonStyle(button, "#1c1e27", "#fc5e87");
        } else {
            setButtonStyle(button, "#1c1e27", "#ffc093");
        }
    }

    /**
     * Creates a new DropShadow
     *
     * @return the created dropShadow
     */
    private DropShadow setDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(1);
        dropShadow.setOffsetY(1);
        dropShadow.setWidth(10);
        dropShadow.setHeight(10);
        return dropShadow;
    }

    /**
     * Set the css styling of given button
     *
     * @param button where the styling should be added
     * @param backgroundColor the color for the background
     * @param borderColor the color for the border
     */
    private void setButtonStyle(Button button, String backgroundColor, String borderColor) {
        button.setStyle("-fx-background-color: " + backgroundColor + "; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 15pt; " +
                "-fx-font-family: Roboto;" +
                "-fx-background-radius: 5px;" +
                "-fx-border-width: 1;" +
                "-fx-border-color: " + borderColor + ";" +
                "-fx-border-radius: 5px;");
    }
}
