package de.hsworms.ztt.keidel.calculator.tokenizer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenTest {

    @Test
    public void testToken() {
        Token token = new Token("(");
        assertEquals("(", token.getValue());
        assertFalse(token.isOperator());
        assertEquals(Token.Type.LEFT_BRACKET, token.getType());

        token = new Token(")");
        assertEquals(")", token.getValue());
        assertFalse(token.isOperator());
        assertEquals(Token.Type.RIGHT_BRACKET, token.getType());

        token = new Token("-");
        assertEquals("-", token.getValue());
        assertTrue(token.isOperator());
        assertEquals(Token.Type.OPERATOR, token.getType());
        assertEquals(Token.Operator.SUBTRACT, token.getOperator());

        token = new Token("*");
        assertEquals("*", token.getValue());
        assertTrue(token.isOperator());
        assertEquals(Token.Type.OPERATOR, token.getType());
        assertEquals(Token.Operator.MULTIPLY, token.getOperator());

        token = new Token("-1");
        assertEquals("-1", token.getValue());
        assertFalse(token.isOperator());
        assertEquals(Token.Type.LITERAL, token.getType());
    }
}