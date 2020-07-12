package de.hsworms.ztt.keidel.calculator;

import de.hsworms.ztt.keidel.calculator.InfixToPostfixConverter;
import de.hsworms.ztt.keidel.calculator.tokenizer.Token;
import de.hsworms.ztt.keidel.calculator.tokenizer.TokenizerUtil;

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
                case OPERATOR:
                    double operandB = Double.valueOf(tokenStack.pop().getValue());
                    double operandA = Double.valueOf(tokenStack.pop().getValue());
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
                            throw new IllegalStateException("Programing Error! Implement: " + token.getOperator());
                    }
                    break;
                default:
                    throw new IllegalStateException("Programing Error! Implement: " + token.getType());
            }
        }
        return Double.valueOf(tokenStack.pop().getValue());
    }
}
