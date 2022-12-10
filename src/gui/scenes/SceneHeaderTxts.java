package gui.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class SceneHeaderTxts extends Label{

    /**
     * Class constructor for initializing a text header for the scene
     * its font size, alignment, and Y layout
     * @param text the String value for the scene header
     * @param layout the integer for the Y value of the header
     */
    public SceneHeaderTxts(String text, int layout) {
        setText(text);
        setStyle("-fx-font-size: 45");
        setAlignment(Pos.CENTER);
        setLayoutY(layout);
    }

}
