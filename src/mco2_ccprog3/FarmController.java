package mco2_ccprog3;

import farm.FarmingGame;
import gui.scenes.PlayerSubScene;
import gui.scenes.SceneHeaderTxts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FarmController implements Initializable {

    private FarmingGame farmingGame = new FarmingGame();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        farmingGame.displayLand();
        setFieldButtonTiles();
//        farmingGame.startSimulator();
    }

    @FXML
    private GridPane fieldPane;
    @FXML
    private AnchorPane farmPane;


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
    }

    public void showInventory() throws IOException {
        PlayerSubScene playerSubScene = new PlayerSubScene("inventory");
        farmPane.getChildren().add(playerSubScene);
        playerSubScene.moveSubScene();
        playerSubScene.getPane().getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());

        AnchorPane inventoryPane = FXMLLoader.load(getClass().getResource("/inventory.fxml"));
        playerSubScene.getPane().getChildren().add(inventoryPane);

        showFarmerDetails(playerSubScene);
    }

    public void showFarmerDetails(PlayerSubScene playerSubScene) {
        SceneHeaderTxts characterTxt = new SceneHeaderTxts(farmingGame.getFarmer().getFarmerName() + "'s Profile and Inventory");
        characterTxt.prefWidthProperty().bind(playerSubScene.widthProperty());
        playerSubScene.getPane().getChildren().add(characterTxt);

        System.out.println(farmingGame.getFarmer().getFarmerCharacter());
        System.out.println(farmingGame.getFarmer().getFarmerName());
        System.out.println(farmingGame.getFarmer().getExperience());
        System.out.println(farmingGame.getFarmer().getFarmerInventory().getObjectCoins());
    }

    public void showStore() {
    }

    public void sleepForTheDay() {
    }
}