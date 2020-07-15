package de.hsworms.ztt.keidel.calculator;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CalculatorTest {

    private static final double DELTA = 0.0000001;

    @Test
    public void getResult() throws IOException {
        assertEquals(2.0, Calculator.getResult("(1+1)"), DELTA);
        assertEquals(2.0, Calculator.getResult("1+1"), DELTA);
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

        // sin (Sine) tests
        assertEquals(0., Calculator.getResult("sin ( 0 )"), DELTA);
        assertEquals(0., Calculator.getResult("sin (0)"), DELTA);
        assertEquals(0., Calculator.getResult("sin(0)"), DELTA);
        assertEquals(0., Calculator.getResult("sin (0  )"), DELTA);
        assertEquals(1., Calculator.getResult("1 + sin (0)"), DELTA);
        assertEquals(1., Calculator.getResult("sin (0) + 1"), DELTA);
        assertEquals(1., Calculator.getResult("sin(0)+1"), DELTA);
        assertEquals(0.8414709848, Calculator.getResult("sin (1)"), DELTA);
        assertEquals(0.90929742682, Calculator.getResult("sin (2)"), DELTA);
        assertEquals(0.14112000806, Calculator.getResult("sin (3)"), DELTA);
        assertEquals(-0.54402111088, Calculator.getResult("sin (3 + 7)"), DELTA);
        assertEquals(2.98935824662, Calculator.getResult("2 + sin (2 * 4)"), DELTA);

        // cos (Cosine) tests
        assertEquals(1., Calculator.getResult("cos ( 0 )"), DELTA);
        assertEquals(1., Calculator.getResult("cos (0)"), DELTA);
        assertEquals(0.54030230586, Calculator.getResult("cos (1)"), DELTA);
        assertEquals(-0.41614683654, Calculator.getResult("cos (2)"), DELTA);
        assertEquals(-0.9899924966, Calculator.getResult("cos (3)"), DELTA);
        assertEquals(0.28366218546, Calculator.getResult("cos (3 + 2)"), DELTA);
        assertEquals(0.1367372182, Calculator.getResult("cos (4 * 3 + 2)"), DELTA);

        // tan (Tangent) tests
        assertEquals(0., Calculator.getResult("tan (0    )"), DELTA);
        assertEquals(0., Calculator.getResult("tan (0)"), DELTA);
        assertEquals(1.55740772465, Calculator.getResult("tan (1)"), DELTA);
        assertEquals(-2.18503986326, Calculator.getResult("tan (2)"), DELTA);
        assertEquals(-0.14254654307, Calculator.getResult("tan (3)"), DELTA);
        assertEquals(0.87144798272, Calculator.getResult("tan (3 + 4)"), DELTA);
        assertEquals(1.58815308339, Calculator.getResult("tan (3 + 4 * 5)"), DELTA);

        // sqrt (Square root) tests
        assertEquals(0., Calculator.getResult("sqrt (0)"), DELTA);
        assertEquals(0., Calculator.getResult("sqrt (   0   )"), DELTA);
        assertEquals(1., Calculator.getResult("sqrt (1)"), DELTA);
        assertEquals(1.41421356237, Calculator.getResult("sqrt (2)"), DELTA);
        assertEquals(1.73205080757, Calculator.getResult("sqrt (3)"), DELTA);
        assertEquals(3.87298334621, Calculator.getResult("sqrt (3 * 5)"), DELTA);
        assertEquals(4.05144586072, Calculator.getResult("sqrt (3 * 5 + sqrt (2))"), DELTA);

        // fac (factorial) tests
        assertEquals(1., Calculator.getResult("fac (0)"), DELTA);
        assertEquals(1., Calculator.getResult("fac (1)"), DELTA);
        assertEquals(2., Calculator.getResult("fac (2)"), DELTA);
        assertEquals(6., Calculator.getResult("fac (3)"), DELTA);
        assertEquals(24., Calculator.getResult("fac (4)"), DELTA);
        assertEquals(25., Calculator.getResult("fac (4) + 1"), DELTA);
        assertEquals(25., Calculator.getResult("1 + fac (4)"), DELTA);
        assertEquals(30., Calculator.getResult("3 * 2 + fac (4)"), DELTA);

        // log (log base 10) tests
        assertEquals(0., Calculator.getResult("log (1)"), DELTA);
        assertEquals(0.30102999566, Calculator.getResult("log (2)"), DELTA);
        assertEquals(0.47712125472, Calculator.getResult("log (3)"), DELTA);
        assertEquals(2.47712125472, Calculator.getResult("log (3) + 2"), DELTA);
        assertEquals(2.47712125472, Calculator.getResult("2 + log (3)"), DELTA);
        assertEquals(0.69897000433, Calculator.getResult("log (3 + 2)"), DELTA);
        assertEquals(0.95424250943, Calculator.getResult("log (3 ^ 2)"), DELTA);

        // ln (log base e) tests
        assertEquals(0., Calculator.getResult("ln (1)"), DELTA);
        assertEquals(0.69314718056, Calculator.getResult("ln (2)"), DELTA);
        assertEquals(1.09861228867, Calculator.getResult("ln (3)"), DELTA);
        assertEquals(3.09861228867, Calculator.getResult("ln (3) + 2"), DELTA);
        assertEquals(3.09861228867, Calculator.getResult("2 + ln (3)"), DELTA);
        assertEquals(1.60943791243, Calculator.getResult("ln (3 + 2)"), DELTA);
        assertEquals(2.19722457734, Calculator.getResult("ln (3 ^ 2)"), DELTA);
        assertEquals(2.8903717579, Calculator.getResult("ln (3 ^ 2) + ln (2)"), DELTA);

        // pi tests
        assertEquals(3.14159265359, Calculator.getResult("pi"), DELTA);
        assertEquals(4.14159265359, Calculator.getResult("pi + 1"), DELTA);
        assertEquals(4.14159265359, Calculator.getResult("1 + pi"), DELTA);
        assertEquals(36.4621596072, Calculator.getResult("pi ^ pi"), DELTA);
        assertEquals(-0.75313736415, Calculator.getResult("pi * sin (3 + 2) / 4"), DELTA);
        assertEquals(1.14472988585, Calculator.getResult("ln (pi)"), DELTA);

        // e tests
        assertEquals(2.71828182846, Calculator.getResult("e"), DELTA);
        assertEquals(3.71828182846, Calculator.getResult("e + 1"), DELTA);
        assertEquals(3.71828182846, Calculator.getResult("1 + e"), DELTA);
        assertEquals(15.1542622415, Calculator.getResult("e ^ e"), DELTA);
    }
}