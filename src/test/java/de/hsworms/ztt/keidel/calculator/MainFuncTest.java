package de.hsworms.ztt.keidel.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MainFuncTest {

    public static double DELTA = 1e-6;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCalculatorResult() throws IOException {
        assertEquals(0., Main.getCalculatorResult("-1 + 1"), DELTA);
    }
}