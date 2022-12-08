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
        } else if (scene.equals("pop-up")){
            image = new BackgroundImage(new Image("pop-up.png", width, height, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        } else if(scene.equals("sleep")){
            image =  new BackgroundImage(new Image("sleep.jpg", width, height, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        } else {
            image =  new BackgroundImage(new Image("tileActions.png", width, height, false, true),
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
        } else if(scene.equals("sleep")) {
            setLayoutX(0);
            setLayoutY(0);
        } else if(scene.equals("tileActions")) {
            setLayoutX(1190);
            setLayoutY(90);
        } else if (scene.equals("action-pop-up")) {
            setLayoutX(985);
            setLayoutY(130);
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
            if (scene.equals("sleep")) {
                transition.setToX(0);
            } else {
                transition.setToX(-930);
            }
        } else {
            transition.setToX(0);
        }

        if (scene.contains("pop-up")) {
            TranslateTransition transitionOut = new TranslateTransition();
            transitionOut.setDuration(Duration.seconds(0.3));
            transitionOut.setNode(this);
            transitionOut.setToX(0);
            SequentialTransition sequentialTransition = new SequentialTransition(transition, new PauseTransition(Duration.millis(1400)), transitionOut);
            sequentialTransition.play();
        } else if (scene.equals("sleep")) {
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.seconds(3.5));
            fade.setFromValue(10);
            fade.setToValue(0.1);
            fade.setNode(this);

            TranslateTransition transitionOut = new TranslateTransition();
            transitionOut.setDuration(Duration.seconds(0.001));
            transitionOut.setNode(this);
            transitionOut.setToX(1000);

            SequentialTransition sequentialTransition = new SequentialTransition(fade, transitionOut);
            sequentialTransition.play();
        }else {
            transition.play();
        }
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

}
