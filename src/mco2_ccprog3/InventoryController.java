package mco2_ccprog3;

import farm.FarmingGame;
import farm.Tools;
import gui.scenes.PlayerSubScene;
import gui.scenes.SceneHeaderTxts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable{

    private FarmingGame farmingGame = new FarmingGame();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setButtonInventory();
        makeToolTips();
    }

    @FXML
    private GridPane buttonsPane;

    public void setButtonInventory() {
        int row;
        int col;
        String tool;

        for (Node node : buttonsPane.getChildren()) {

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

            if (row == 0) {
                //set style for seeds
                if (col < farmingGame.getSeeds().getPlants().size()) {
                    String plantName = farmingGame.getSeeds().getPlants().get(col).getPlantName();
                    if (farmingGame.getFarmer().getFarmerInventory().getSeedsOwned().get(plantName) != null) {
                        node.setStyle("-fx-background-image: url(\"" + plantName.toLowerCase() + ".png\"); -fx-background-size: 50 50; -fx-background-repeat: stretch;");
                        node.setId(plantName);
                    }
                }
            } else {
                //set style for tools

                if (col < farmingGame.getFarmer().getFarmerInventory().getTools().size()) {
                    tool = farmingGame.getFarmer().getFarmerInventory().getTools().get(col).getToolName();

                    if (tool != null) {
                        if (tool.equals("Watering Can")) {
                            tool = "watering_can";
                        }
                        node.setStyle("-fx-background-image: url(\"" + tool.toLowerCase() + ".png\"); -fx-background-size: 50 50; -fx-background-repeat: stretch;");
                        node.setId(tool);
                    }
                }
            }

            if (node.getId() == null) {
                node.setStyle("-fx-background-image: url(\"empty.png\"); -fx-background-size: 50 50; -fx-background-repeat: stretch;");
            }
        }
    }

    public void makeToolTips() {
        for (Node node : buttonsPane.getChildren()) {
            Tooltip tTip = new Tooltip();
            String name = node.getId();
            int col;
            int row;

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

            if (col < farmingGame.getFarmer().getFarmerInventory().getTools().size() && row == 1) {
                String toolFunction = farmingGame.getFarmer().getFarmerInventory().getTools().get(col).getToolFunction();
                tTip.setText(name + ". " + toolFunction);
                tTip.setStyle("-fx-font-size: 30");
                Tooltip.install(node, tTip);
            }
        }
    }


}