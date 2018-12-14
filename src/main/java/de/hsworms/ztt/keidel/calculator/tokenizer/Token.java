package de.hsworms.ztt.keidel.calculator.tokenizer;


import de.hsworms.ztt.keidel.calculator.InfixToPostfixConverter;

import java.util.HashMap;
import java.util.Map;

public class Token {

    public enum Type {
        LITERAL, OPERATOR, LEFT_BRACKET, RIGHT_BRACKET
    }

    public enum Operator {
        ADD(1), SUBTRACT(2), MULTIPLY(3), DIVIDE(4);
        public final int precedence;

        Operator(int p) {
            precedence = p;
        }
    }

    public static Map<String, Token.Operator> ops = new HashMap<String, Operator>() {{
        put("+", Token.Operator.ADD);
        put("-", Token.Operator.SUBTRACT);
        put("*", Token.Operator.MULTIPLY);
        put("/", Token.Operator.DIVIDE);
    }};

    private Type type;
    private String value;

    private Operator operator = null;

    public Token(String value) {
        if (value.equalsIgnoreCase("(")) {
            type = Type.LEFT_BRACKET;
        } else if (value.equalsIgnoreCase(")")) {
            type = Type.RIGHT_BRACKET;
        } else if (value.matches("[-+*/]")) {
            type = Type.OPERATOR;
            operator = ops.get(value);
        } else if (value.matches("-?[0-9.]+")) {
            type = Type.LITERAL;
        } else {
            throw new IllegalStateException("Programing Error! Implement: " + value);
        }
        this.value = value;
    }

    public boolean isOperator() {
        return operator != null;
    }

    public Operator getOperator() {
        return operator;
    }

    public String getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }
}
