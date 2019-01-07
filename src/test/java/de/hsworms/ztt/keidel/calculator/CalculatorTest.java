package de.hsworms.ztt.keidel.calculator;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CalculatorTest {

    private static final double DELTA = 0.0000001;

    @Test
    public void getResult() throws IOException {
        assertEquals(2.0, Calculator.getResult("(1+1)"), DELTA);
        assertEquals(4.0, Calculator.getResult("(1+1)*2"), DELTA);
        assertEquals(2.0, Calculator.getResult("(1+1)*(2 - 1)"), DELTA);
        assertEquals(121.0, Calculator.getResult("(120+1)*(2 - 1)"), DELTA);
        assertEquals(121.0, Calculator.getResult("(120+1)/(2 - 1)"), DELTA);
        assertEquals(12.0, Calculator.getResult("((120+1) - 1)/(11 - 1)"), DELTA);
        assertEquals(0., Calculator.getResult("(   1 -1)"), DELTA);
        assertEquals(-1.0, Calculator.getResult("(   1 -2)"), DELTA);
        assertEquals(-1.0, Calculator.getResult("(   1.1 -2.1)"), DELTA);
        assertEquals(0.5, Calculator.getResult("(   1.0 / 2.0)"), DELTA);

        // Praktikum task: Modulo
        assertEquals(0., Calculator.getResult("2 % 2"), DELTA);
        assertEquals(1., Calculator.getResult("1 % 2"), DELTA);
        assertEquals(0., Calculator.getResult("0 % 2"), DELTA);
        assertEquals(2., Calculator.getResult("1 + 1 % 2"), DELTA);
        assertEquals(0., Calculator.getResult("(1 + 1) % 2"), DELTA);
        assertEquals(0., Calculator.getResult("(1 + 1) % (1 + 1)"), DELTA);
        assertEquals(0., Calculator.getResult("(1023 + 1) % (1 + 1)"), DELTA);
    }
}