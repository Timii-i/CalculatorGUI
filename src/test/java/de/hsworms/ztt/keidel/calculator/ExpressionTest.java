package de.hsworms.ztt.keidel.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionTest {

    private Expression expression;
    private static final String TEST_INPUT = "1 + 2 + 3";

    @Before
    public void setUp() throws Exception {
        expression = new Expression(TEST_INPUT);
    }

    @After
    public void tearDown() throws Exception {
        expression = null;
    }

    @Test
    public void evaluate() {
        Expression.Token[] tokens = expression.evaluate();
        assertEquals(Expression.Type.LITERAL, tokens[0].getType());
        assertEquals(Expression.Type.LITERAL, tokens[2].getType());
        assertEquals(Expression.Type.LITERAL, tokens[4].getType());
        assertEquals(Expression.Type.OPERATOR, tokens[1].getType());
        assertEquals(Expression.Type.OPERATOR, tokens[3].getType());
    }
}