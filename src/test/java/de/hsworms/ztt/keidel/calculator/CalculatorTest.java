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
        assertEquals(3.0, Calculator.getResult("1 + 1 * 2"), DELTA);
        assertEquals(2.0, Calculator.getResult("1 + 1 * 2 / 2"), DELTA);
        assertEquals(3.0, Calculator.getResult("1 + 1 * 2 / 2 + 1"), DELTA);
        assertEquals(8.871, Calculator.getResult("1.581 + 7.29"), DELTA);

        // Praktikum task: Modulo
        assertEquals(0., Calculator.getResult("2 % 2"), DELTA);
        assertEquals(1., Calculator.getResult("1 % 2"), DELTA);
        assertEquals(0., Calculator.getResult("0 % 2"), DELTA);
        assertEquals(2., Calculator.getResult("1 + 1 % 2"), DELTA);
        assertEquals(0., Calculator.getResult("(1 + 1) % 2"), DELTA);
        assertEquals(0., Calculator.getResult("(1 + 1) % (1 + 1)"), DELTA);
        assertEquals(0., Calculator.getResult("(1023 + 1) % (1 + 1)"), DELTA);

        // Exponent tests
        assertEquals(1., Calculator.getResult("2 ^ 0"), DELTA);
        assertEquals(2., Calculator.getResult("2 ^ 1"), DELTA);
        assertEquals(0., Calculator.getResult("0 ^ 2"), DELTA);
        assertEquals(243., Calculator.getResult("3 ^ 5"), DELTA);
        assertEquals(288., Calculator.getResult("(2 ^ 5) * (3 ^ 2)"), DELTA);
        assertEquals(16807., Calculator.getResult("(2 + 5) ^ (3 + 2)"), DELTA);

        // Sin tests
        assertEquals(0., Calculator.getResult("sin ( 0 )"), DELTA);
        assertEquals(0.8414709848, Calculator.getResult("sin ( 1 )"), DELTA);
        assertEquals(0.90929742682, Calculator.getResult("sin ( 2 )"), DELTA);
        assertEquals(0.14112000806, Calculator.getResult("sin ( 3 )"), DELTA);
        assertEquals(-0.54402111088, Calculator.getResult("sin ( 3 + 7 )"), DELTA);
        assertEquals(0.98935824662, Calculator.getResult("sin ( 2 * 4 )"), DELTA);
    }
}