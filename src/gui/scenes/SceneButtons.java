package gui.scenes;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class SceneButtons extends Button {

    /**
     * Class constructor for initializing the button's text,
     * width, height, listeners, and style
     * @param text the String value for the button
     */
    public SceneButtons(String text) {
        setText(text);
        initButtonListeners();
        setPrefWidth(190);
        setPrefHeight(70);
        setButtonStyleUnpressed();
    }

    /**
     * Styles the button as an unpressed element
     */
    public void setButtonStyleUnpressed() {
        setStyle("-fx-background-image: url('button_pressed.png'); -fx-background-repeat: stretch; -fx-background-size: 190 70;");
        setPrefWidth(190);
        setPrefHeight(70);
        setAlignment(Pos.CENTER);
        setLayoutY(-300);
        setLayoutX(-87);
    }

    /**
     * Styles the button as a pressed element
     */
    public void setButtonStylePressed() {
        setStyle("-fx-background-color: transparent; -fx-background-image: url('button_pressed2.png'); -fx-background-repeat: stretch; -fx-background-size: 190 70;");
    }

    /**
     * Initializes the event handlers for the buttons
     * When button is clicked, it will call setButtonStylePressed(),
     * if released, it will call setButtonStyleUnpressed()
     */
    public void initButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonStylePressed();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonStyleUnpressed();
                }
            }
        });

    }



}
