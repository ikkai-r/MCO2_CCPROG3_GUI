package controller;

import farm.FarmingGame;
import farm.Tile;
import farmerprogress.FarmerLevel;
import gui.GUI;
import gui.scenes.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static gui.scenes.InitializeScene.getFarmingGame;

public class FarmController implements Initializable {

    protected static FarmingGame farmingGame = getFarmingGame();
    @FXML
    protected GridPane fieldPane;
    @FXML
    private AnchorPane farmPane;
    @FXML
    private Label day;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setFieldButtonTiles();
        initializeDay();
    }

    public void initializeDay() {
        day.setText("Day " + farmingGame.getFarmer().getDayCount());
        day.setStyle("-fx-background-image: url(\"pop-up.png\"); -fx-background-size: 150 50; -fx-background-repeat: stretch;" +
                "-fx-font-size: 40");
        day.setPrefSize(150, 50);
        day.setAlignment(Pos.CENTER);
    }

    public void setFieldButtonTiles() {
        int row;
        int col;
        String tileCondition;

        for (Node node : fieldPane.getChildren()) {
            if (GridPane.getRowIndex(node) == null) {
                row = 0;
            } else {
                row = GridPane.getRowIndex(node);
            }

            if(GridPane.getColumnIndex(node) == null) {
                col = 0;
            } else {
                col = GridPane.getColumnIndex(node);
            }

            //access the index of the gridpane for the buttons
            //set node to condition of corresponding tile
            tileCondition = farmingGame.getBoard().checkTileCondition(farmingGame.getBoard().getFarmTile(row, col));
            node.setStyle("-fx-background-image: url(\""+tileCondition+".png\"); -fx-background-size: 54 50;");
        }

        checkFarmerExp();

        if (farmingGame.isGameOver()) {
            gameOver();
        }
    }

    public void showInventory() throws IOException {
        PlayerSubScene playerSubScene = new PlayerSubScene("inventory", 780, 500);
        farmPane.getChildren().add(playerSubScene);
        playerSubScene.moveSubScene(true);
        playerSubScene.getPane().getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());

        AnchorPane inventoryPane = FXMLLoader.load(getClass().getResource("/inventory.fxml"));
        playerSubScene.getPane().getChildren().add(inventoryPane);
        makeFarmerHeader(playerSubScene);

        createExitButtonPanes(playerSubScene);

    }

    public void makeFarmerHeader(PlayerSubScene playerSubScene) {
        SceneHeaderTxts characterTxt = new SceneHeaderTxts(farmingGame.getFarmer().getFarmerName() + "'s Profile and Inventory", 50);
        characterTxt.prefWidthProperty().bind(playerSubScene.widthProperty());
        playerSubScene.getPane().getChildren().add(characterTxt);
    }

    public void showStore() throws IOException {
        PlayerSubScene storeSubScene = new PlayerSubScene("store", 780, 500);
        farmPane.getChildren().add(storeSubScene);
        storeSubScene.moveSubScene(true);
        storeSubScene.getPane().getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());

        AnchorPane storePane = FXMLLoader.load(getClass().getResource("/store.fxml"));
        storeSubScene.getPane().getChildren().add(storePane);

        createExitButtonPanes(storeSubScene);
    }

    public void sleepForTheDay() {
        PlayerSubScene popUpScene = new PlayerSubScene("sleep", 980, 700);
        popUpScene.moveSubScene(true);
        SceneHeaderTxts sceneHeaderTxts = new SceneHeaderTxts("Sleeping for the day...", 300);
        sceneHeaderTxts.setStyle("-fx-font-size: 70; -fx-text-fill: white");
        sceneHeaderTxts.prefWidthProperty().bind(farmPane.widthProperty());
        sceneHeaderTxts.setAlignment(Pos.CENTER);
        popUpScene.getPane().getChildren().add(sceneHeaderTxts);
        farmPane.getChildren().add(popUpScene);
        farmingGame.sleep();
        day.setText("Day " + farmingGame.getFarmer().getDayCount());
        setFieldButtonTiles();
    }

    public void createExitButtonPanes(PlayerSubScene playerSubScene) {
        Button exitButton = new Button();
        exitButton.setText("Back");
        exitButton.setPrefSize(100, 40);
        exitButton.setId("back-button");
        HBox box = new HBox();
        box.getChildren().add(exitButton);
        box.setLayoutX(box.getLayoutX()-40);
        box.setLayoutY(box.getLayoutY()+25.0);
        box.prefWidthProperty().bind(playerSubScene.getPane().widthProperty());
        box.setAlignment(Pos.TOP_RIGHT);
        playerSubScene.getPane().getChildren().add(box);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                playerSubScene.moveSubScene(false);
                setFieldButtonTiles();
            }
        });

    }

    public void createExitButtonPanes(PlayerSubScene playerSubScene, Tile tile) {
        Button exitButton = new Button();
        exitButton.setText("Back");
        exitButton.setPrefSize(100, 40);
        exitButton.setId("back-button");
        HBox box = new HBox();
        box.getChildren().add(exitButton);
        box.setLayoutX(box.getLayoutX()-40);
        box.setLayoutY(box.getLayoutY()+25.0);
        box.prefWidthProperty().bind(playerSubScene.getPane().widthProperty());
        box.setAlignment(Pos.TOP_RIGHT);
        playerSubScene.getPane().getChildren().add(box);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                playerSubScene.moveSubScene(false);
                setFieldButtonTiles();
                tile.setSelected(false);
            }
        });

    }

    public void tileAction(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        PlayerSubScene tileActionPane = new PlayerSubScene("tileActions", 700, 550);
        int row;
        int col;

        if (GridPane.getRowIndex(node) == null) {
            row = 0;
        } else {
            row = GridPane.getRowIndex(node);
        }

        if (GridPane.getColumnIndex(node) == null) {
            col = 0;
        } else {
            col = GridPane.getColumnIndex(node);
        }
            farmPane.getChildren().add(tileActionPane);

            tileActionPane.moveSubScene(true);
            tileActionPane.getPane().getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());

            farmingGame.getBoard().getFarmTile(row, col).setSelected(true);

            AnchorPane storePane = FXMLLoader.load(getClass().getResource("/actions.fxml"));
            tileActionPane.getPane().getChildren().add(storePane);
            setFieldButtonTiles();
            createExitButtonPanes(tileActionPane, farmingGame.getBoard().getFarmTile(row, col));

    }

    public void gameOver() {
        farmingGame = new FarmingGame();
        Stage stage = (Stage) fieldPane.getScene().getWindow();
        stage.close();
        InitializeScene initializeScene = new InitializeScene(true);
        Stage primaryStage = initializeScene.getMainStage();
        primaryStage.show();
    }

    public void checkFarmerExp() {
        ArrayList<Object> farmerAlertList = farmingGame.getProgressChecker().checkExperience(farmingGame.getFarmer());

        if (!farmerAlertList.isEmpty()) {

            if (farmerAlertList.size() == 1) {

                PlayerSubScene levelUp = new PlayerSubScene("level-up", 747, 421);
                levelUp.getPane().getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
                //if only leveled up
                SceneHeaderTxts levelUpTxt = new SceneHeaderTxts(farmerAlertList.get(0).toString(), 200);
                levelUpTxt.prefWidthProperty().bind(levelUp.widthProperty());
                levelUp.getPane().getChildren().add(levelUpTxt);
                levelUp.moveSubScene(true);
                farmPane.getChildren().add(levelUp);

            } else {

                PlayerSubScene levelUpRegis = new PlayerSubScene("level-up-regis", 747, 600);
                levelUpRegis.getPane().getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
                //if leveled up with registration
                String reg = farmerAlertList.get(0).toString() + farmerAlertList.get(1).toString();
                SceneHeaderTxts levelUpTxt = new SceneHeaderTxts(reg, 200);
                levelUpTxt.prefWidthProperty().bind(levelUpRegis.widthProperty());
                levelUpTxt.setTextAlignment(TextAlignment.CENTER);
                levelUpTxt.setStyle("-fx-font-size: 30");
                levelUpRegis.getPane().getChildren().add(levelUpTxt);
                levelUpRegis.moveSubScene(true);
                farmPane.getChildren().add(levelUpRegis);

                SceneButtons registerButton = new SceneButtons("Accept");
                SceneButtons declineButton = new SceneButtons("Decline");
                registerButton.setId("registerBtn");
                declineButton.setId("declineBtn");

                registerButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String response = farmingGame.getProgressChecker().checkRegister(farmingGame.getFarmer(), (FarmerLevel)farmerAlertList.get(2));
                        levelUpTxt.setText(response);
                        levelUpTxt.prefHeightProperty().bind(levelUpRegis.heightProperty().divide(2));
                        levelUpTxt.setAlignment(Pos.CENTER);
                        levelUpTxt.setStyle("-fx-font-size: 40");
                        declineButton.setVisible(false);
                        registerButton.setVisible(false);
                        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                        pause.setOnFinished(e ->{
                            levelUpRegis.moveSubScene(false);
                        });
                        pause.play();
                    }
                });

                declineButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        levelUpRegis.moveSubScene(false);
                    }
                });

                HBox hbox = new HBox(40);
                hbox.getChildren().addAll(registerButton, declineButton);
                hbox.prefWidthProperty().bind(levelUpRegis.widthProperty());
                hbox.prefHeightProperty().bind(levelUpRegis.heightProperty().add(-60));
                hbox.setAlignment(Pos.BOTTOM_CENTER);
                levelUpRegis.getPane().getChildren().add(hbox);

            }
        }

    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */

    /**
     * Acts as the getter for the grid pane of the field
     * @return the grid pane for field display
     */
    public GridPane getFieldPane() {
        return fieldPane;
    }

    /**
     * Acts as the setter for the grid pane of the field
     * @param fieldPane the grid pane for field display to be set
     */
    public void setFieldPane(GridPane fieldPane) {
        this.fieldPane = fieldPane;
    }

    /**
     * Acts as the getter for the anchor pane of the farm
     * @return the anchor pane for farm display
     */
    public AnchorPane getFarmPane() {
        return farmPane;
    }

    /**
     * Acts as the setter for the anchor pane of the farm
     * @param farmPane the grid pane for field display to be set
     */
    public void setFarmPane(AnchorPane farmPane) {
        this.farmPane = farmPane;
    }

    /**
     * Acts as the getter for the label with the current day
     * @return the label with the current day of the farm
     */
    public Label getDay() {
        return day;
    }

    /**
     * Acts as the setter for the label with the day
     * @return the label with the day of the farm to be set
     */
    public void setDay(Label day) {
        this.day = day;
    }
}