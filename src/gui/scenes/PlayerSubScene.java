package gui.scenes;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class PlayerSubScene extends SubScene {
    private static final int WIDTH = 780;
    private static final int HEIGHT = 500;

    public PlayerSubScene() {
        super(new AnchorPane(), WIDTH, HEIGHT);
        prefWidth(WIDTH);
        prefHeight(HEIGHT);
        BackgroundImage image = new BackgroundImage(new Image("character_pick.png", WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        setLayoutX(1024);
        setLayoutY(90);
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        transition.setToX(-930);


        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

}
