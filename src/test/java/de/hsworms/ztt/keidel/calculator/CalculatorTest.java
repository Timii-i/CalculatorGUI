package de.hsworms.ztt.keidel.calculator;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void getResult() throws IOException {
        assertEquals(2, Calculator.getResult("(1+1)"));
        assertEquals(4, Calculator.getResult("(1+1)*2"));
        assertEquals(2, Calculator.getResult("(1+1)*(2 - 1)"));
        assertEquals(121, Calculator.getResult("(120+1)*(2 - 1)"));
        assertEquals(121, Calculator.getResult("(120+1)/(2 - 1)"));
        assertEquals(12, Calculator.getResult("((120+1) - 1)/(11 - 1)"));
        assertEquals(0, Calculator.getResult("(   1 -1)"));
        assertEquals(-1, Calculator.getResult("(   1 -2)"));

    }
}