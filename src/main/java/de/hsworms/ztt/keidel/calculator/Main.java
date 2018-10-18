package de.hsworms.ztt.keidel.calculator;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        StackInterface stack = new StackImpl();
        try {
            stack.pop();
        } catch (IndexOutOfBoundsException e) {
            // what to do now?
        }
    }
}
