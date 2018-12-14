package de.hsworms.ztt.keidel.calculator;

import de.hsworms.ztt.keidel.calculator.tokenizer.Token;

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
}
