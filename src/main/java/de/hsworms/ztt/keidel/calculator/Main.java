package de.hsworms.ztt.keidel.calculator;

import java.io.IOException;
import java.util.Scanner;

// imports for javafx
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    Button button;

    public static void main(String[] args) {
        launch(args);

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

    /**
     * Start a given stage
     *
     * @param stage     the stage that should be started
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Sets the title of the stage
        stage.setTitle("Title of the Window");

        // Initiate a new instance of a button
        button = new Button();
        button.setText("Click me");

        // Create a new layout and add the button to it
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout,300, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static double getCalculatorResult(String infix) throws IOException {
        return Calculator.getResult(infix);
    }
}
