package de.hsworms.ztt.keidel.calculator;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Source: <a href="https://eddmann.com/posts/shunting-yard-implementation-in-java/">Edd Mann</a>
 */
public class InfixToPostfixConverter {

    private enum Operator {
        ADD(1), SUBTRACT(2), MULTIPLY(3), DIVIDE(4);
        final int precedence;

        Operator(int p) {
            precedence = p;
        }
    }

    private static Map<String, InfixToPostfixConverter.Operator> ops = new HashMap<String, Operator>() {{
        put("+", InfixToPostfixConverter.Operator.ADD);
        put("-", InfixToPostfixConverter.Operator.SUBTRACT);
        put("*", InfixToPostfixConverter.Operator.MULTIPLY);
        put("/", InfixToPostfixConverter.Operator.DIVIDE);
    }};

    private static boolean isHigherPrecedence(String op, String sub) {
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }

    public static String toPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Deque<String> stack = new LinkedList<>();

        for (String token : infix.split("\\s")) {
            // operator
            if (ops.containsKey(token)) {
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
