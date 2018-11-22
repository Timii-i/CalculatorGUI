package de.hsworms.ztt.keidel.calculator;

import java.util.Iterator;
import java.util.StringTokenizer;

public class MyCollection implements Iterator<String> {

    private String[] strings = new String[100];

    public MyCollection(String inputString) {
        StringTokenizer st = new StringTokenizer(inputString);
        int counter = 0;
        while (st.hasMoreTokens()) {
            strings[counter] = st.nextToken();
            counter++;
        }
    }


    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        // FIXME
        return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws java.util.NoSuchElementException if the iteration has no more elements
     */
    @Override
    public String next() {
        return null;
    }
}
