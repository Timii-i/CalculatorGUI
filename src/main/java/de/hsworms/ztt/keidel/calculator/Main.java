package de.hsworms.ztt.keidel.calculator;

import java.io.IOException;
import java.util.Scanner;

// imports for javafx
import de.hsworms.ztt.keidel.calculator.gui.MainLayout;
import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) {

        // Launches the gui application
        launch(MainLayout.class, args);

        /*while(true) {
            System.out.println("Please type an infix mathematical expression:");
            Scanner scanner = new Scanner(System.in);
            String infix = scanner.nextLine();

            try {
                System.out.println(infix + " = " + Calculator.getResult(infix));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    public static double getCalculatorResult(String infix) throws IOException {
        return Calculator.getResult(infix);
    }
}
