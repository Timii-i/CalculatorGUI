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

    public static int getResult(String infix) throws IOException {
        Stack<Token> tokenStack = new Stack<>();
        List<Token> tokenList = InfixToPostfixConverter.toPostfixListOfToken(infix);
        for (Token token : tokenList) {
            switch (token.getType()) {
                case LITERAL:
                    tokenStack.push(token);
                    break;
                case OPERATOR:
                    int operandB = Integer.valueOf(tokenStack.pop().getValue());
                    int operandA = Integer.valueOf(tokenStack.pop().getValue());
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
                        default:
                            throw new IllegalStateException("Programing Error! Implement: " + token.getOperator());
                    }
                    break;
                default:
                    throw new IllegalStateException("Programing Error! Implement: " + token.getType());
            }
        }
        return Integer.valueOf(tokenStack.pop().getValue());
    }
}
