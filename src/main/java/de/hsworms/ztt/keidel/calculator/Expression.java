package de.hsworms.ztt.keidel.calculator;

import de.hsworms.ztt.keidel.calculator.util.CharacterUtil;

import java.util.StringTokenizer;

public class Expression {

    private StringTokenizer stringTokenizer;

    enum Type {
        LITERAL, OPERATOR
    }

    class Token {
        private String surface;
        private Type type;

        public Token(String surface, Type type) {
            this.surface = surface;
            this.type = type;
        }

        public String getSurface() {
            return surface;
        }

        public Type getType() {
            return type;
        }
    }

    public Expression(String input) {
        stringTokenizer = new StringTokenizer(input);
    }

    public Token[] evaluate() {
        Token[] tokens = new Token[100];
        int counter = 0;
        while(stringTokenizer.hasMoreTokens()) {
            String item = stringTokenizer.nextToken();

            if(CharacterUtil.isDigit(item.charAt(0))) {
                tokens[counter] = new Token(item, Type.LITERAL);
                counter++;
            } else if(CharacterUtil.isOperator(item.charAt(0))) {
                tokens[counter] = new Token(item, Type.OPERATOR);
                counter++;
            } else {
                throw new IllegalStateException("expected [0-9] or [-+/*] but got " + item);
            }
        }
        return tokens;
    }
}
