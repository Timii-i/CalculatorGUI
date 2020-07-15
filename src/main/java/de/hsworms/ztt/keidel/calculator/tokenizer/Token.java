package de.hsworms.ztt.keidel.calculator.tokenizer;


import de.hsworms.ztt.keidel.calculator.gui.CalculationLabels;

import java.util.HashMap;
import java.util.Map;

public class Token {

    public enum Type {
        LITERAL, OPERATOR, FUNCTION, CONSTANT, LEFT_BRACKET, RIGHT_BRACKET, NOT_DETERMINED
    }

    /**
     * For information about Infix Preferences see
     * <a href="https://en.wikipedia.org/wiki/Order_of_operations">
     * https://en.wikipedia.org/wiki/Order_of_operations</a>
     */
    public enum Operator {
        ADD(1), SUBTRACT(1), MULTIPLY(2), DIVIDE(2), MODULO(2), EXPONENT(3);
        public final int precedence;

        Operator(int p) {
            precedence = p;
        }
    }

    public enum Function {
        SIN(4), COS(4), TAN(4), SQRT(4), FACTORIAL(4), LOG(4), LN(4);
        public final int precedence;

        Function(int p) {
            precedence = p;
        }
    }

    public enum Constant {
        PI, E
    }

    public static Map<String, Token.Operator> ops = new HashMap<String, Operator>() {{
        put("+", Token.Operator.ADD);
        put("-", Token.Operator.SUBTRACT);
        put("*", Token.Operator.MULTIPLY);
        put("/", Token.Operator.DIVIDE);
        put("%", Operator.MODULO);
        put("^", Operator.EXPONENT);
    }};

    public static Map<String, Token.Function> funcs = new HashMap<String, Function>() {{
        put("sin", Function.SIN);
        put("cos", Function.COS);
        put("tan", Function.TAN);
        put("sqrt", Function.SQRT);
        put("fac", Function.FACTORIAL);
        put("log", Function.LOG);
        put("ln", Function.LN);
    }};

    public static Map<String, Token.Constant> cons = new HashMap<String, Constant>() {{
        put("pi", Constant.PI);
        put("e", Constant.E);
    }};

    private Type type;
    private String value;

    private Operator operator = null;
    private Function function = null;
    private Constant constant = null;

    /**
     * Evaluates input String on the fly.
     *
     * @param value Input String for Token constructor
     */
    public Token(String value) {
        if (value.equalsIgnoreCase("(")) {
            type = Type.LEFT_BRACKET;
        } else if (value.equalsIgnoreCase(")")) {
            type = Type.RIGHT_BRACKET;
        } else if (value.matches("-?[0-9.]+")) {
            type = Type.LITERAL;
        } else if (value.matches("[-+*/%^]")) {
            type = Type.OPERATOR;
            operator = ops.get(value);
        } else if (value.matches("(sin|cos|tan|sqrt|fac|log|ln)")) {
            type = Type.FUNCTION;
            function = funcs.get(value);
        } else if (value.matches("(e|pi)")) {
            type = Type.CONSTANT;
            constant = cons.get(value);
        } else {
            CalculationLabels calculationLabels = new CalculationLabels();
            calculationLabels.setResultLabel("Error");

            throw new IllegalStateException("Programing Error! Implement: " + value);
        }
        this.value = value;
    }

    boolean isOperator() {
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

    public Constant getConstant() { return constant; }

    public Function getFunction() { return function; }
}
