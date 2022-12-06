package mco2_ccprog3;

import farm.FarmingGame;
import gui.scenes.PlayerSubScene;
import gui.scenes.SceneHeaderTxts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable{

    private FarmingGame farmingGame = new FarmingGame();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setButtonInventory();
    }

    @FXML
    private GridPane buttonsPane;
    @FXML
    private Tooltip toolTip1;

    public void setButtonInventory() {

        for (Node node : buttonsPane.getChildren()) {
            node.setStyle("-fx-background-image: url(\"pickaxe.png\"); -fx-background-size: 50 50; -fx-background-repeat: stretch;");
        }
    }



}