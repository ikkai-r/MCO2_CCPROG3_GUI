package gui.scenes;

import farm.FarmingGame;
import gui.GUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
                playerSubScene.getPane().getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
                playerSelect(playerSubScene);

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

    public void playerSelect(PlayerSubScene playerSubScene) {
        SceneHeaderTxts characterTxt = new SceneHeaderTxts("Choose your character!");
        playerSubScene.getPane().getChildren().add(characterTxt);
        characterTxt.prefWidthProperty().bind(playerSubScene.widthProperty());
        HBox characterHBox = selectCharacter(playerSubScene);
        HBox nameHBox = promptPlayerName(playerSubScene, characterTxt);
        VBox vbox = new VBox(-110, characterHBox, nameHBox);
        playerSubScene.getPane().getChildren().addAll(vbox);
    }

    public HBox selectCharacter(PlayerSubScene playerSubScene) {
        Label label = new Label();
        label.setStyle("-fx-font-size: 40");

        Button boyCharacter = new Button();
        boyCharacter.setText("Boy");
        boyCharacter.setId("boy-button");
        boyCharacter.setPrefSize(120, 230);

        Button girlCharacter = new Button();
        girlCharacter.setId("girl-button");
        girlCharacter.setPrefSize(120, 230);
        girlCharacter.setText("Girl");
        girlCharacter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                farmingGame.getFarmer().setFarmerCharacter(girlCharacter.getText());
                label.setText("You are a " + girlCharacter.getText());
            }
        });

        boyCharacter.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                farmingGame.getFarmer().setFarmerCharacter(boyCharacter.getText());
                label.setText("You are a " + boyCharacter.getText());
            }
        });

        HBox hbox = new HBox(40);
        hbox.getChildren().addAll(boyCharacter, girlCharacter, label);
        hbox.setAlignment(Pos.CENTER);
        hbox.prefWidthProperty().bind(playerSubScene.widthProperty());
        hbox.prefHeightProperty().bind(playerSubScene.heightProperty());

        return hbox;
    }

    public HBox promptPlayerName(PlayerSubScene playerSubScene, SceneHeaderTxts characterTxt) {
        Label label = new Label();
        label.setStyle("-fx-font-size: 50");
        label.setText("Name: ");
        TextField nameField = new TextField();
        nameField.setPrefHeight(70);
        nameField.setId("name-field");
        SceneButtons startGameBtn = new SceneButtons("Go!");
        HBox hbox = new HBox(30);
        hbox.getChildren().addAll(label, nameField, startGameBtn);
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.prefWidthProperty().bind(playerSubScene.widthProperty());
        hbox.prefHeightProperty().bind(playerSubScene.heightProperty().divide(2).add(220));

        startGameBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                farmingGame.getFarmer().setFarmerName(nameField.getText());
                if (farmingGame.getFarmer().getFarmerName() == null ||
                        farmingGame.getFarmer().getFarmerName() == "" || farmingGame.getFarmer().getFarmerCharacter() == null) {
                    characterTxt.setText("Choose a character and a name first!");
                } else {
                    farmingGame.getFarmer().setFarmerName(nameField.getText());
                    FarmScene fs = new FarmScene();
                    try {
                        System.out.println("opened");
                        fs.createFarm(OpeningScene.super.getMainStage());
                    } catch (IOException e) {
                        System.out.println("but failed");
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        return hbox;
    }

}
