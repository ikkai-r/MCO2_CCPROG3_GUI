package gui.scenes;

import javafx.animation.*;
import javafx.scene.SubScene;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class PlayerSubScene extends SubScene {

    private String scene;
    private BackgroundImage image;
    private int width;
    private int height;

    public PlayerSubScene(String scene, int width, int height) {
        super(new AnchorPane(), width, height);
        this.scene = scene;
        this.width = width;
        this.height = height;
        prefWidth(width);
        prefHeight(height);
        setImage();
    }

    public void setImage() {
        if (scene.equals("opening")) {
            image = new BackgroundImage(new Image("character_pick.png", width, height, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        } else if (scene.equals("inventory")) {
            image = new BackgroundImage(new Image("inventory.png", width, height, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        } else if (scene.equals("store")){
            image = new BackgroundImage(new Image("store.png", width, height, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        } else {
            image = new BackgroundImage(new Image("pop-up.png", width, height, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        }

        setLayout();
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));
    }

    public void setLayout() {
        if (scene.equals("pop-up")) {
            setLayoutX(1095);
            setLayoutY(150);
        } else {
            setLayoutX(1024);
            setLayoutY(90);
        }
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

        if (scene.equals("pop-up")) {
            TranslateTransition transitionOut = new TranslateTransition();
            transitionOut.setDuration(Duration.seconds(0.3));
            transitionOut.setNode(this);
            transitionOut.setToX(0);
            SequentialTransition sequentialTransition = new SequentialTransition(transition, new PauseTransition(Duration.millis(1400)), transitionOut);
            sequentialTransition.play();
        } else {
            transition.play();
        }
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

}
