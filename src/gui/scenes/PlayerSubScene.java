package gui.scenes;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class PlayerSubScene extends SubScene {
    private static final int WIDTH = 780;
    private static final int HEIGHT = 500;

    public PlayerSubScene(String scene) {
        super(new AnchorPane(), WIDTH, HEIGHT);
        BackgroundImage image;
        prefWidth(WIDTH);
        prefHeight(HEIGHT);
        if (scene.equals("opening")) {
            image = new BackgroundImage(new Image("character_pick.png", WIDTH, HEIGHT, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        } else if (scene.equals("inventory")) {
            image = new BackgroundImage(new Image("inventory.png", WIDTH, HEIGHT, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        } else {
            image = new BackgroundImage(new Image("inventory.png", WIDTH, HEIGHT, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        }

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        setLayoutX(1024);
        setLayoutY(90);
    }

    public void moveSubScene(boolean isHidden) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden) {
            transition.setToX(-930);
        } else {
            transition.setToX(0);
        }

        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

}
