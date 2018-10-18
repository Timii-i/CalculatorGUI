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
        stack.push(new Integer(1));
        stack.push(new Integer(2));
        assertEquals(new Integer(2), stack.pop());
    }

    @Test
    public void popWithException() {
        try {
            stack.pop();
            fail("Expected 'IndexOutOfBoundsException'");
        } catch (IndexOutOfBoundsException e) {
            // OK, test passed
        } catch (Exception e) {
            fail("Wrong Exception fired: " + e.getMessage());
        }
    }

    @Test
    public void clearAll() {
        stack.push(new Integer(1));
        stack.push(new Integer(2));
        stack.clearAll();
        assertEquals(0, stack.size());
    }

    private static final int TEST_SIZE = 100;

    @Test
    public void size() {

        for (int i = 0; i < TEST_SIZE; i++) {
            stack.push(i);
        }
        assertEquals(TEST_SIZE, stack.size());
    }
}