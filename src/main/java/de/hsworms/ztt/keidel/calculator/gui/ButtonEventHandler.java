package de.hsworms.ztt.keidel.calculator.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonEventHandler implements EventHandler<ActionEvent> {

    /**
     * Handles action when an event happens
     *
     * @param actionEvent the event that should be handled
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        // Gets the value of the button clicked
        String buttonClicked = ((Button)actionEvent.getSource()).getText();
        System.out.println(buttonClicked);
    }
}
