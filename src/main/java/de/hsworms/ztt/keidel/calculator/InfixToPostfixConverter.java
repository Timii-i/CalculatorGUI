package de.hsworms.ztt.keidel.calculator;

import de.hsworms.ztt.keidel.calculator.tokenizer.Token;
import de.hsworms.ztt.keidel.calculator.tokenizer.TokenizerUtil;

import java.io.IOException;
import java.util.*;

/**
 * Source: <a href="https://eddmann.com/posts/shunting-yard-implementation-in-java/">Edd Mann</a>
 */
public class InfixToPostfixConverter {

    private static boolean isHigherPrecedence(String op, String sub) {
        return (Token.ops.containsKey(sub) && Token.ops.get(sub).precedence >= Token.ops.get(op).precedence);
    }

    public static String toPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Deque<String> stack = new LinkedList<>();

        for (String token : infix.split("\\s")) {
            // operator
            if (Token.ops.containsKey(token)) {
                while (!stack.isEmpty() && isHigherPrecedence(token, stack.peek())) {
                    output.append(stack.pop()).append(' ');
                }
                stack.push(token);

                // left parenthesis
            } else if (token.equals("(")) {
                stack.push(token);

                // right parenthesis
            } else if (token.equals(")")) {
                while (!stack.peek().equals("(")) {
                    output.append(stack.pop()).append(' ');
                }
                stack.pop();

                // digit
            } else {
                output.append(token).append(' ');
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(' ');
        }

        return output.toString();
    }

    public static List<Token> toPostfixListOfToken(String infix) throws IOException {
        List<Token> output = new ArrayList<>();
        Deque<Token> stack = new LinkedList<>();

        for (Token token : TokenizerUtil.tokenizeToTokenList(infix)) {

            switch (token.getType()) {
                case OPERATOR:
                    while (!stack.isEmpty() && isHigherPrecedence(token.getValue(), stack.peek().getValue())) {
                        output.add(stack.pop());
                    }
                    stack.push(token);
                    break;
                case LEFT_BRACKET:
                    stack.push(token);
                    break;
                case RIGHT_BRACKET:
                    while (stack.peek().getType() != Token.Type.LEFT_BRACKET) {
                        output.add(stack.pop());
                    }
                    stack.pop();
                    break;
                case LITERAL:
                    output.add(token);
                    break;
                default:
                    throw new IllegalStateException("Programing Error! Implement: " + token.toString());
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }
}
