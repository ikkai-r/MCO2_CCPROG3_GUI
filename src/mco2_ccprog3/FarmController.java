package mco2_ccprog3;

import farm.FarmingGame;
import farm.Tile;
import gui.scenes.FarmScene;
import gui.scenes.OpeningScene;
import gui.scenes.PlayerSubScene;
import gui.scenes.SceneHeaderTxts;
import javafx.application.Platform;
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
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FarmController implements Initializable {

    protected static FarmingGame farmingGame;
    @FXML
    protected GridPane fieldPane;
    @FXML
    private AnchorPane farmPane;
    @FXML
    private Label day;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        farmingGame = new FarmingGame();
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
            node.setId(row+" "+col);
        }

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

        Object node = event.getSource();
        PlayerSubScene tileActionPane = new PlayerSubScene("tileActions", 700, 550);

        if (node instanceof final Button button) {
            farmPane.getChildren().add(tileActionPane);

            tileActionPane.moveSubScene(true);
            tileActionPane.getPane().getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());



            String tile = button.getId();
            int row = Integer.parseInt(String.valueOf(tile.charAt(0)));
            int col = Integer.parseInt(String.valueOf(tile.charAt(2)));

            farmingGame.getBoard().getFarmTile(row, col).setSelected(true);

            AnchorPane storePane = FXMLLoader.load(getClass().getResource("/actions.fxml"));
            tileActionPane.getPane().getChildren().add(storePane);
            setFieldButtonTiles();
            createExitButtonPanes(tileActionPane, farmingGame.getBoard().getFarmTile(row, col));
        }
    }

    public void gameOver() {
        farmingGame.getFarmer().resetFarmer(farmingGame.getFarmer());
        Stage stage = (Stage) fieldPane.getScene().getWindow();
        stage.close();
        OpeningScene openingScene = new OpeningScene(true);
        Stage primaryStage = openingScene.getMainStage();
        primaryStage.show();
    }

    public String checkFarmerExp() {
        return farmingGame.getProgressChecker().checkExperience(farmingGame.getFarmer());
    }

}