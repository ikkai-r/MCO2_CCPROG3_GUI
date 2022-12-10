package controller;

import gui.scenes.PlayerSubScene;
import gui.scenes.SceneHeaderTxts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class StoreController extends FarmController implements Initializable {
    @FXML
    private AnchorPane storePane;
    @FXML
    private GridPane storeButtonsPane;
    @FXML
    private Text objCoins;

    /**
     * Calls the setStoreButtons() and sets the object coins display
     * to the value of the object coins owned by the farmer in initialization
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStoreButtons();
        objCoins.setText(String.valueOf(farmingGame.getFarmer().getFarmerInventory().getObjectCoins()));
    }

    /**
     * Styles the buttons corresponding to the seed and displays
     * to the user in the store
     */
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

            if (node instanceof final Button button) {
                ImageView im = new ImageView(new Image(seed+".png"));
                button.setGraphic(im);
                im.setFitHeight(50);
                im.setFitWidth(50);
                if (seed.equals("Sunflower")) {
                    button.setText(" " + seed + "\t\t    " + (farmingGame.getStore().getProducts().getPlants().get(row).getSeedCost()-farmingGame.getFarmer().getSeedCostReduction()));
                } else if (seed.equals("Mango") || seed.equals("Apple")) {
                    button.setText(" " + seed + "\t\t\t   " + (farmingGame.getStore().getProducts().getPlants().get(row).getSeedCost()-farmingGame.getFarmer().getSeedCostReduction()));
                } else {
                    button.setText(" " + seed + "\t\t\t    " + (farmingGame.getStore().getProducts().getPlants().get(row).getSeedCost()-farmingGame.getFarmer().getSeedCostReduction()));
                }
                button.setAlignment(Pos.BASELINE_LEFT);
            }
        }

    }

    /**
     * Enables the action on click of a certain button and passes the feedback
     * from sellItem() to the popUpAction()
     * @param event represents the action from the button, in this case, the click
     */
    public void storeAction(ActionEvent event) {
        Object node = event.getSource();
        int plantChoice = -1;

        if (node instanceof final Button button) {
            if (GridPane.getRowIndex(button) == null) {
                plantChoice = 0;
            } else {
                plantChoice = GridPane.getRowIndex(button);
            }
        }
        popUpAction(farmingGame.getStore().sellItem(farmingGame.getFarmer(), plantChoice));
    }

    /**
     * Displays the value of the popString as a pop-up in the user's screen
     * @param popString the feedback from the store action
     */
    public void popUpAction(String popString) {
        PlayerSubScene popUpScene = new PlayerSubScene("pop-up", 500, 150);
        popUpScene.moveSubScene(true);
        SceneHeaderTxts sceneHeaderTxts = new SceneHeaderTxts(popString, 50);
        sceneHeaderTxts.prefWidthProperty().bind(popUpScene.widthProperty());
        popUpScene.getPane().getChildren().add(sceneHeaderTxts);
        storePane.getChildren().add(popUpScene);
        objCoins.setText(String.valueOf(farmingGame.getFarmer().getFarmerInventory().getObjectCoins()));
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */
    public AnchorPane getStorePane() {
        return storePane;
    }

    public void setStorePane(AnchorPane storePane) {
        this.storePane = storePane;
    }

    public GridPane getStoreButtonsPane() {
        return storeButtonsPane;
    }

    public void setStoreButtonsPane(GridPane storeButtonsPane) {
        this.storeButtonsPane = storeButtonsPane;
    }

    public Text getObjCoins() {
        return objCoins;
    }

    public void setObjCoins(Text objCoins) {
        this.objCoins = objCoins;
    }
}
