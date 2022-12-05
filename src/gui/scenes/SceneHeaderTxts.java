package gui.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class SceneHeaderTxts extends Label{

    public SceneHeaderTxts(String text) {
        setText(text);
        setStyle("-fx-font-size: 40");
        setAlignment(Pos.CENTER);
    }
}
