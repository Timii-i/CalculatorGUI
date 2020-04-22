package de.hsworms.ztt.keidel.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class InfixToPostfixConverterTest {

    @Test
    public void toPostfix() {
        assertEquals("1 2 3 4 - * + ", InfixToPostfixConverter.toPostfix("1 + 2 * ( 3 - 4 )"));
    }
}