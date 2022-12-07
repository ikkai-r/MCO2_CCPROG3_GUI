package mco2_ccprog3;

import farm.FarmingGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;

public class StoreController implements Initializable {

    private FarmingGame farmingGame = new FarmingGame();
    @FXML
    private GridPane storeButtonsPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStoreButtons();
    }

    public void setStoreButtons() {
        int row;
        String seed;

        for (Node node : storeButtonsPane.getChildren()) {
            if (GridPane.getRowIndex(node) == null) {
                row = 0;
            } else {
                row = GridPane.getRowIndex(node);
            }

            seed = farmingGame.getStore().getProducts().getPlants().get(row).getPlantName();
            node.setId(seed);
            node.setStyle("-fx-background-image: url(\"storebutton.png\"); -fx-background-size: 659 64;");

            if (node instanceof Button) {
                final Button button = (Button) node;
                ImageView im = new ImageView(new Image(seed+".png"));
                button.setGraphic(im);
                im.setFitHeight(20);
                im.setFitWidth(40);
                button.setText(" " + seed + "\t\t\t" + farmingGame.getStore().getProducts().getPlants().get(row).getSeedCost());
                button.setAlignment(Pos.BASELINE_LEFT);
            }
        }
    }
}
