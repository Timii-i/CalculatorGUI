package de.hsworms.ztt.keidel.calculator;

public interface StackInterface {

    void push(Object o);

    Object pop() throws IndexOutOfBoundsException;

    void clearAll();

    int size();

}
