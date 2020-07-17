package de.hsworms.ztt.keidel.calculator;

import de.hsworms.ztt.keidel.calculator.gui.CalculationLabels;
import de.hsworms.ztt.keidel.calculator.tokenizer.Token;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

/**
 * For an idea how it works:
 * <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">
 * Wikipedia: Reverse Polish notation (left-to-right algorithm)</a>
 */
public class Calculator {

    public static double getResult(String infix) throws IOException {
        List<Token> tokenList = InfixToPostfixConverter.toPostfixListOfToken(infix);
        Stack<Token> tokenStack = new Stack<>();
        for (Token token : tokenList) {
            switch (token.getType()) {
                case LITERAL:
                    tokenStack.push(token);
                    break;

                    // constants (pi and e)
                case CONSTANT:
                    switch (token.getConstant()) {
                        case PI:
                            tokenStack.push(new Token(String.valueOf(Math.PI)));
                            break;
                        case E:
                            tokenStack.push(new Token(String.valueOf(Math.E)));
                            break;
                        default:
                            CalculationLabels.putError();
                            throw new IllegalStateException("Programing Error! Implement Function: " + token.getConstant());
                    }
                    break;

                    // operators (+, -, *, /, %, ^, etc.)
                case OPERATOR:
                    double operandB = Double.parseDouble(tokenStack.pop().getValue());
                    double operandA = Double.parseDouble(tokenStack.pop().getValue());
                    switch (token.getOperator()) {
                        case ADD:
                            tokenStack.push(new Token(String.valueOf(operandA + operandB)));
                            break;
                        case SUBTRACT:
                            tokenStack.push(new Token(String.valueOf(operandA - operandB)));
                            break;
                        case MULTIPLY:
                            tokenStack.push(new Token(String.valueOf(operandA * operandB)));
                            break;
                        case DIVIDE:
                            tokenStack.push(new Token(String.valueOf(operandA / operandB)));
                            break;
                        case MODULO:
                            tokenStack.push(new Token(String.valueOf(operandA % operandB)));
                            break;
                        case EXPONENT:
                            tokenStack.push(new Token(String.valueOf(Math.pow(operandA, operandB))));
                            break;
                        default:
                            CalculationLabels.putError();
                            throw new IllegalStateException("Programing Error! Implement: " + token.getOperator());
                    }
                    break;

                    // function (sin(), cos(), tan(), sqrt(), etc.)
                case FUNCTION:
                    double operand = Double.parseDouble(tokenStack.pop().getValue());
                    switch (token.getFunction()) {
                        case SIN:
                            tokenStack.push(new Token(String.valueOf(Math.sin(operand))));
                            break;
                        case COS:
                            tokenStack.push(new Token(String.valueOf(Math.cos(operand))));
                            break;
                        case TAN:
                            tokenStack.push(new Token(String.valueOf(Math.tan(operand))));
                            break;
                        case SQRT:
                            tokenStack.push(new Token(String.valueOf(Math.sqrt(operand))));
                            break;
                        case FACTORIAL:
                            tokenStack.push(new Token(String.valueOf(factorial((int)operand))));
                            break;
                        case LOG:
                            tokenStack.push(new Token(String.valueOf(Math.log10(operand))));
                            break;
                        case LN:
                            tokenStack.push(new Token(String.valueOf(Math.log(operand))));
                            break;
                        default:
                            CalculationLabels.putError();
                            throw new IllegalStateException("Programing Error! Implement Function: " + token.getFunction());
                    }
                    break;
                default:
                    CalculationLabels.putError();
                    throw new IllegalStateException("Programing Error! Implement: " + token.getType());
            }
        }

        /**
         * Returns the result of the calculation and rounds it to 7 decimals if needed
         *
         * <a href="https://stackoverflow.com/a/153753">
         * Stackoverflow asterite</a>
         */
        return Math.round(Double.parseDouble(tokenStack.pop().getValue()) * 10000000d) / 10000000d;
    }

    /**
     * Calculates the product of all positive integers less than or equal to a given number
     *
     * @param n the value for the factorial
     * @return the product of all positive integers less than or equal to n
     */
    private static int factorial(int n) {
        try {
            if (n >= 1) { return n * factorial(n - 1); }
            else { return 1; }
        } catch (Exception soe){
            // show "Error" in the resultLabel if an Exception appears
            CalculationLabels.putError();

            soe.printStackTrace();
        }
        return -1;
    }
}
