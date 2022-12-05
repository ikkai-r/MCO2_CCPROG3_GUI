package gui.scenes;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;

public class SceneButtons extends Button {

    public SceneButtons(String text) {
        setText(text);
        initButtonListeners();
        setPrefWidth(190);
        setPrefHeight(70);
        setButtonStyleUnpressed();
    }

    public void setButtonStyleUnpressed() {
        setStyle("-fx-background-color: transparent; -fx-background-image: url('button_pressed.png'); -fx-background-repeat: stretch; -fx-background-size: 190 70;");
        setPrefWidth(190);
        setPrefHeight(70);
    }

    public void setButtonStylePressed() {
        setStyle("-fx-background-color: transparent; -fx-background-image: url('button_pressed2.png'); -fx-background-repeat: stretch; -fx-background-size: 190 70;");
    }

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
