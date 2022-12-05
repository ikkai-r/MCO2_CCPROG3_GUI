package mco2_ccprog3;

import farm.FarmingGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.spreadsheet.Grid;

import java.net.URL;
import java.util.ResourceBundle;

public class FarmController implements Initializable {

    FarmingGame farmingGame = new FarmingGame();
    public void print() {
        System.out.println(farmingGame.getFarmer().getFarmerCharacter());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        farmingGame.displayLand();
        setFieldButtons();
    }

    @FXML
    private GridPane fieldPane;

    public void setFieldButtons() {
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
            tileCondition = farmingGame.getBoard().checkTileCondition(farmingGame.getBoard().getFarmTile(row, col));
            node.setStyle("-fx-background-image: url(\""+tileCondition+".png\"); -fx-background-size: 54 50;");

        }
    }
    //set the buttons to the board tiles corresponding to its indices




}