package gui.scenes;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SceneButtons extends Button {

    public SceneButtons(String text) {
        setText(text);
        setPrefWidth(180);
        setPrefHeight(49);
        setTranslateX(200);
        setTranslateY(25);
        setButtonStyleUnpressed();
        initButtonListeners();
    }

    public void setButtonStyleUnpressed() {
        setStyle("-fx-background-color: transparent; -fx-background-image: url('button_pressed.png'); -fx-background-repeat: stretch; -fx-background-size: 180 45;");
        setPrefWidth(180);
        setPrefHeight(45);
        setLayoutY(getLayoutY()+4);
    }

    public void setButtonStylePressed() {
        setStyle("-fx-background-color: transparent; -fx-background-image: url('button_pressed.png'); -fx-background-repeat: stretch; -fx-background-size: 180 45;");
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
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

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DropShadow dshadow = new DropShadow();
                dshadow.setColor(Color.WHITE);
                setEffect(dshadow);
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }


}
