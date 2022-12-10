package gui.scenes;

import javafx.animation.*;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class PlayerSubScene extends SubScene {

    private String scene;
    private BackgroundImage image;
    private int width;
    private int height;

    /**
     * Creates all attributes, sets the scenes
     * and inserting the title and icons for the stage
     *
     * @param scene the text for the scene name
     * @param width the width for the scene's image
     * @param height the height for the scene's image
     */
    public PlayerSubScene(String scene, int width, int height) {
        super(new AnchorPane(), width, height);
        this.scene = scene;
        this.width = width;
        this.height = height;
        prefWidth(width);
        prefHeight(height);
        setImage();
    }

    /**
     * sets the background image based on the scene name and
     * calls the setLayout() for the position
     */
    public void setImage() {
        image = new BackgroundImage(new Image(scene+".png", width, height, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        setLayout();
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));
    }

    /**
     * sets the layout for the scene's position
     * in the GUI
     */
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
        } else if (scene.equals("level-up")) {
            setLayoutX(1095);
            setLayoutY(100);
        } else if(scene.equals("level-up-regis")) {
            setLayoutX(1095);
            setLayoutY(50);
        } else {
            setLayoutX(1024);
            setLayoutY(90);
        }
    }

    /**
     * Displays the transition for the current subscene
     * @param isHidden determines if it will transition away or towards the screen
     */
    public void moveSubScene(boolean isHidden) {

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden) {
            if (scene.equals("sleep")) {
                transition.setToX(0);
            } else if (scene.contains("level-up")) {
                transition.setToX(-990);
            } else {
                transition.setToX(-930);
            }
        } else {
            transition.setToX(0);
        }

        if (scene.contains("pop-up") || scene.equals("level-up")) {

            //make transition that goes away after 0.3 seconds

            TranslateTransition transitionOut = new TranslateTransition();
            transitionOut.setDuration(Duration.seconds(0.3));
            transitionOut.setNode(this);
            transitionOut.setToX(0);

            SequentialTransition sequentialTransition = new SequentialTransition(transition, new PauseTransition(Duration.millis(1400)), transitionOut);

            sequentialTransition.play();

        } else if (scene.equals("sleep")) {

            //make transition that fades for 3.5 seconds

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

        } else {
            //plays the transition based on isHidden for the X value
            transition.play();
        }
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }

    public String getSceneName() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public BackgroundImage getImage() {
        return image;
    }

    public void setImage(BackgroundImage image) {
        this.image = image;
    }

    public int getSceneWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getSceneHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
