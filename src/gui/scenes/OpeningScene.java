package gui.scenes;

import farm.FarmingGame;
import gui.GUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;

public class OpeningScene extends GUI {

    private FarmingGame farmingGame = new FarmingGame();

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
        imageView.setImage(image);
        imageView.setFitWidth(750);
        imageView.setPreserveRatio(true);
        return new VBox(50, imageView, hbox);
    }

    public void promptPlayerNameChoice(PlayerSubScene playerSubScene) {
        SceneHeaderTxts characterTxt = new SceneHeaderTxts("Choose your character!");
        playerSubScene.getPane().getChildren().add(characterTxt);
        characterTxt.prefWidthProperty().bind(playerSubScene.widthProperty());
        selectCharacter(playerSubScene);
    }

    public void selectCharacter(PlayerSubScene playerSubScene) {
        Label label = new Label();
        label.setStyle("-fx-font-size: 40");
        Button boyCharacter = new Button();
        boyCharacter.setText("Boy");
        boyCharacter.setStyle("-fx-text-fill: transparent; -fx-background-color: transparent; -fx-background-image: url('farmerboy.png'); -fx-background-repeat: stretch; -fx-background-size: 120 230;");
        boyCharacter.setPrefWidth(120);
        boyCharacter.setPrefHeight(230);

        Button girlCharacter = new Button();
        girlCharacter.setStyle("-fx-text-fill: transparent; -fx-background-color: transparent; -fx-background-image: url('farmergirl.png'); -fx-background-repeat: stretch; -fx-background-size: 120 230;");
        girlCharacter.setPrefWidth(120);
        girlCharacter.setPrefHeight(230);
        girlCharacter.setText("Girl");
        girlCharacter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                farmingGame.getFarmer().setFarmerCharacter(girlCharacter.getText());
                label.setText("You are a " + girlCharacter.getText());
                System.out.println(farmingGame.getFarmer().getFarmerCharacter());
            }
        });

        boyCharacter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                farmingGame.getFarmer().setFarmerCharacter(boyCharacter.getText());
                label.setText("You are a " + boyCharacter.getText());
                System.out.println(farmingGame.getFarmer().getFarmerCharacter());
            }
        });

        HBox hbox = new HBox(40);
        hbox.getChildren().addAll(boyCharacter, girlCharacter, label);
        hbox.setAlignment(Pos.CENTER);
        hbox.prefWidthProperty().bind(playerSubScene.widthProperty());
        hbox.prefHeightProperty().bind(playerSubScene.heightProperty());
        playerSubScene.getPane().getChildren().add(hbox);
    }

}
