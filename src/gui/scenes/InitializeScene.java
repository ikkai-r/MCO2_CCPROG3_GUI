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
import javafx.stage.Stage;

import java.io.IOException;

public class InitializeScene {
    private  FarmingGame farmingGame;
    private GUI gui = new GUI();
    private boolean isGameOver;
    private SceneButtons startButton;
    private SceneButtons exitButton;
    private Stage mainStage = gui.getMainStage();

    /**
     * Class constructor for initializing the scene's GUI
     * Creates all attributes, sets the scenes,
     * and inserting the panes, VBox, HBox
     */
    public InitializeScene() {
        farmingGame = new FarmingGame();
        gui.getMainPane().setId("pane");
        VBox box = openingSceneVBOX();
        gui.getMainPane().getChildren().add(box);
        box.setAlignment(Pos.CENTER);
        box.prefWidthProperty().bind(gui.getMainPane().widthProperty());
        box.prefHeightProperty().bind(gui.getMainPane().heightProperty());
        gui.getMainScene().getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
    }

    /**
     * Class constructor for initializing the scene's GUI if the
     * game is already over
     * Creates all attributes, sets the scenes,
     * and inserting the panes, VBox, HBox,
     * and sets the gameOver attribute to the isGameOver param
     *
     * @param isGameOver boolean value indicating the game's state
     */
    public InitializeScene(boolean isGameOver) {
        this.isGameOver = isGameOver;
        gui.getMainPane().setId("pane");
        VBox box = openingSceneVBOX();
        gui.getMainPane().getChildren().add(box);
        box.setAlignment(Pos.CENTER);
        box.prefWidthProperty().bind(gui.getMainPane().widthProperty());
        box.prefHeightProperty().bind(gui.getMainPane().heightProperty());
        gui.getMainScene().getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
    }

    /**
     * Creates the buttons on the scene
     * for prompting the user to play the game or exit the game
     */
    public void setButtons() {

        if (isGameOver) {
            startButton = new SceneButtons("Restart");
        } else {
            startButton = new SceneButtons("Start");
        }
        exitButton = new SceneButtons("Exit");
        startButton.setId("startBtn");
        exitButton.setId("exitBtn");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PlayerSubScene playerSubScene = new PlayerSubScene("opening", 780, 500);
                gui.getMainPane().getChildren().add(playerSubScene);
                playerSubScene.moveSubScene(true);
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
    }

    /**
     * Creates a HBox for the buttons for alignment
     * @return HBox containing the buttons created
     */
    public HBox openingSceneHBOX() {
        HBox hbox = new HBox(40);
        hbox.getChildren().addAll(startButton, exitButton);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    /**
     * Creates an image view for the opening/game over screen
     * @return image for the game over or the title screen
     */
    public ImageView openingImage() {
        Image image;

        if (isGameOver) {
            image = new Image("game_over.png");
        } else {
            image = new Image("title_screen.png");
        }

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(750);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    /**
     * Creates a VBox from the buttons and Image created
     * @return VBox containing the nodes to display
     */
    public VBox openingSceneVBOX() {
        Font.loadFont(this.getClass().getResource("/Stardew_Valley.ttf").toExternalForm(), 20);
        setButtons();

        return new VBox(50, openingImage(), openingSceneHBOX());
    }

    /**
     * Creates the player select GUI for the user
     * @param playerSubScene the subscene with the current pane being displayed
     */
    public void playerSelect(PlayerSubScene playerSubScene) {
        SceneHeaderTxts characterTxt = new SceneHeaderTxts("Choose your character!", 50);
        characterTxt.prefWidthProperty().bind(playerSubScene.widthProperty());
        playerSubScene.getPane().getChildren().add(characterTxt);

        HBox characterHBox = selectCharacter(playerSubScene);
        HBox nameHBox = promptPlayerName(playerSubScene, characterTxt);

        VBox vbox = new VBox(-110, characterHBox, nameHBox);
        playerSubScene.getPane().getChildren().addAll(vbox);
    }

    /**
     * Creates the boy and girl character buttons
     * for the playerSelect
     * @param playerSubScene the subscene with the current pane being displayed
     * @return the HBox of the buttons of the boy and girl character
     */
    public HBox selectCharacter(PlayerSubScene playerSubScene) {
        Label label = new Label();
        label.setStyle("-fx-font-size: 40");

        Button boyCharacter = new Button("Boy");
        boyCharacter.setId("boy-button");

        Button girlCharacter = new Button("Girl");
        girlCharacter.setId("girl-button");

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

    /**
     * Creates a text field and submit button for
     * the player's desired name for the farmer
     * @param playerSubScene the subscene with the current pane being displayed
     * @param characterTxt the farmer's character, either "Boy" or "Girl"
     * @return the HBox of the submit button and text field
     */
    public HBox promptPlayerName(PlayerSubScene playerSubScene, SceneHeaderTxts characterTxt) {
        Label label = new Label("Name: ");
        label.setId("name-label");

        TextField nameField = new TextField();
        nameField.setPrefHeight(70);
        nameField.setId("name-field");

        SceneButtons startGameBtn = new SceneButtons("Go!");
        startGameBtn.setId("goBtn");

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
                        fs.createFarm(gui.getMainStage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        return hbox;
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */

    /**
     * Acts as the getter for the boolean if the game is already over
     * @return true if the game is over, otherwise, false
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Acts as the setter for the boolean if the game is already over
     * @param gameOver true if the game is over, otherwise, false
     */
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    /**
     * Acts as the getter for the start button
     * @return the start button for the opening scene
     */
    public SceneButtons getStartButton() {
        return startButton;
    }

    /**
     * Acts as the setter for the start button
     * @param startButton the start button for the opening scene
     */
    public void setStartButton(SceneButtons startButton) {
        this.startButton = startButton;
    }

    /**
     * Acts as the getter for the exit button
     * @return the exit button for the opening scene
     */
    public SceneButtons getExitButton() {
        return exitButton;
    }

    /**
     * Acts as the setter for the exit button
     * @param exitButton the exit button for the opening scene
     */
    public void setExitButton(SceneButtons exitButton) {
        this.exitButton = exitButton;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
