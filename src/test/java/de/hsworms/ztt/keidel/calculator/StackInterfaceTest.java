package de.hsworms.ztt.keidel.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackInterfaceTest {

    private StackInterface stack;

    @Before
    public void setUp() throws Exception {
        stack = new StackImpl();
    }

    @After
    public void tearDown() throws Exception {
        stack = null;
    }

    @Test
    public void testAutoBoxing() {
        Integer one = new Integer(1);
        assertEquals(one, new Integer(1));
    }

    @Test
    public void push() {
        stack.push(new Integer(1));
        stack.push(new Integer(2));
        assertEquals(new Integer(2), stack.pop());
        assertEquals(1, stack.size());
    }

    @Test
    public void pop() {
    }

    @Test
    public void clearAll() {
    }

    @Test
    public void size() {
    }
}