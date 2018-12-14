package de.hsworms.ztt.keidel.calculator;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while(true) {
            System.out.println("Please type an infix mathematical expression:");
            Scanner scanner = new Scanner(System.in);
            String infix = scanner.nextLine();

            try {
                System.out.println(infix + " = " + Calculator.getResult(infix));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
