package gui.scenes;

import gui.GUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class OpeningScene extends GUI {

    public OpeningScene() {
        super.mainPane.setId("pane");
        VBox box = openingSceneVBOX();
        super.mainPane.getChildren().add(box);
        box.setAlignment(Pos.CENTER);
        box.prefWidthProperty().bind(super.mainPane.widthProperty());
        box.prefHeightProperty().bind(super.mainPane.heightProperty());
        super.mainScene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
    }

    public VBox openingSceneVBOX() {
        Font.loadFont(this.getClass().getResource("/Stardew_Valley.ttf").toExternalForm(), 20);
        SceneButtons startButton = new SceneButtons("Start");
        SceneButtons exitButton = new SceneButtons("Exit");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                        PlayerSubScene playerSubScene = new PlayerSubScene();
                        mainPane.getChildren().add(playerSubScene);
                        playerSubScene.moveSubScene();

                        promptPlayerNameChoice(playerSubScene);

            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        HBox hbox = new HBox(40);
        hbox.getChildren().addAll(startButton, exitButton);
        hbox.setAlignment(Pos.CENTER);
        Image image = new Image("title_screen.png");
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image);
        //Setting the image view parameters
        imageView.setFitWidth(750);
        imageView.setPreserveRatio(true);
        return new VBox(50, imageView, hbox);
    }

    public void promptPlayerNameChoice(PlayerSubScene playerSubScene) {
        SceneHeaderTxts characterTxt = new SceneHeaderTxts("Choose your character!");
        playerSubScene.getPane().getChildren().add(characterTxt);
        characterTxt.prefWidthProperty().bind(super.mainPane.widthProperty());
        characterTxt.prefHeightProperty().bind(super.mainPane.heightProperty());
    }

}
