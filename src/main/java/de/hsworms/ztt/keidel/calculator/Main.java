package de.hsworms.ztt.keidel.calculator;

import java.io.IOException;

// imports for javafx
import de.hsworms.ztt.keidel.calculator.gui.Window;
import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) {

        // Launches the main window class application
        launch(Window.class, args);

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
