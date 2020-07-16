package de.hsworms.ztt.keidel.calculator.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Class for the custom windowDecoration
 */
public class WindowDecoration {

    double xCoordinate;
    double yCoordinate;

    // BorderPane for the whole WindowDecoration
    BorderPane windowDecoration = new BorderPane();
    // HBox for the minimize and close button
    HBox windowDecorationButtons = new HBox();
    // Window close button
    Button closeButton = new Button();
    // Window minimize button
    Button minButton = new Button();
    // Calculator Text in the center of the window decoration
    Label decorationLabel = new Label("Calculator");
    // Calculator Icon from the FontAwesomeFX Library on the left side of the window decoration
    FontAwesomeIconView calcIcon;

    /**
     * Creates and setups a custom Window Decoration with close and minimize buttons, the "Calculator" text and
     * a small calculator icon
     *
     * @param vbox the parent VBox in which the HBox is placed
     */
    public void setupWindowDecoration(VBox vbox, Stage stage) {
        // Load the calculator icon from the FontAwesomeFX Library
        calcIcon = new FontAwesomeIconView(FontAwesomeIcon.CALCULATOR);
        calcIcon.setSize("1.3em");
        calcIcon.setFill(Color.WHITE);
        // Put the calculator icon horizontally in the center
        calcIcon.setTranslateX(10);
        calcIcon.setTranslateY(7);

        // Add handler for minButton to minimize on click
        styleWindowButtons(minButton);
        minButton.setOnAction(event -> stage.setIconified(true));
        minButton.setOnMouseEntered(event -> setWindowButtonHover(minButton));
        minButton.setOnMouseExited(event -> styleWindowButtons(minButton));

        // Load the minus Icon from the FontAwesomeFX Library and set it to the minButton
        FontAwesomeIconView minImageView = new FontAwesomeIconView(FontAwesomeIcon.MINUS);
        addIconToButton(minButton, minImageView);

        // Add handler for closeButton to close the window on click
        styleWindowButtons(closeButton);
        closeButton.setOnAction(event -> stage.close());
        closeButton.setOnMouseEntered(event -> setWindowButtonHover(closeButton));
        closeButton.setOnMouseExited(event -> styleWindowButtons(closeButton));

        // Load the close Icon from the FontAwesomeFX Library and set it to the closeButton
        FontAwesomeIconView closeImageView = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        addIconToButton(closeButton, closeImageView);

        decorationLabel.setStyle("-fx-text-fill: white");
        windowDecorationButtons.getChildren().addAll(minButton, closeButton);

        // Set the positions of each element in the BorderPane
        windowDecoration.setLeft(calcIcon);
        windowDecoration.setCenter(decorationLabel);
        windowDecoration.setRight(windowDecorationButtons);

        addWindowDragging(stage);
        styleWindowDecoration(windowDecoration);

        BorderPane.setMargin(decorationLabel, new Insets(0, -50, 0 , 0));
        BorderPane.setMargin(windowDecorationButtons, new Insets(0, 0, 1, 0));
        vbox.getChildren().add(windowDecoration);
    }

    /**
     * Adds window dragging to the custom window decoration
     *
     * @param stage the stage that should be dragged
     */
    private void addWindowDragging(Stage stage) {
        // Add handler for the whole windowDecoration to get the x and y coordinates on click
        windowDecoration.setOnMousePressed(event -> {
            xCoordinate = event.getSceneX();
            yCoordinate = event.getSceneY();
        });

        // Add handler for the whole windowDecoration to set the scene to the new x and y coordinates on mouse drag
        windowDecoration.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xCoordinate);
            stage.setY(event.getScreenY() - yCoordinate);
        });
    }

    /**
     * Adds an FontAwesomeFX icon to a button
     *
     * @param button the button where the icon should be added
     * @param icon the icon that's added to the button
     */
    private void addIconToButton(Button button, FontAwesomeIconView icon) {
        icon.setSize("1em");
        icon.setFill(Color.WHITE);
        button.setGraphic(icon);
    }

    /**
     * Adds DropShadow and Css Styling to each window button
     *
     * @param button the button that is styled
     */
    private void styleWindowButtons(Button button) {
        button.setStyle("-fx-background-color: #1c1e27;");
    }

    /**
     * Adds css styling to the whole window decoration
     *
     * @param windowDecoration the BorderPane where the styling is added
     */
    private void styleWindowDecoration(BorderPane windowDecoration) {
        windowDecoration.setStyle("-fx-background-color: #1c1e27; " +
                "-fx-text-fill: white; " +
                "-fx-font-family: Roboto; " +
                "-fx-font-size: 15; " +
                "-fx-background-radius: 5px");
    }

    /**
     * Add button hover effect
     *
     * @param button for which the hover effect is added
     */
    private void setWindowButtonHover(Button button) {
        // brighten the background
        button.setStyle("-fx-background-color: #2e303f;");
    }
}
