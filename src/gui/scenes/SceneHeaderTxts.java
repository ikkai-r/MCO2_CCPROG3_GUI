package gui.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SceneHeaderTxts extends Label{

    public SceneHeaderTxts(String text) {
        setText(text);
        setStyle("-fx-font-size: 45");
        setAlignment(Pos.CENTER);
        setLayoutY(50);
    }

}
