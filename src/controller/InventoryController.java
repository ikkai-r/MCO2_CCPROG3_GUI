package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController extends FarmController implements Initializable{

    /**
     * Calls the setButtonInventory(), makeToolEquipTips() and
     * showFarmerDetails() in initialization
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setButtonInventory();
        makeToolEquipTips();
        showFarmerDetails();
    }

    @FXML
    private GridPane buttonsPane;
    @FXML
    private Text farmerName;
    @FXML
    private Text farmerLvl;
    @FXML
    private Text objectCoins;
    @FXML
    private Text farmerXP;
    @FXML
    private Text farmerStatus;
    @FXML
    private ImageView farmerChar;

    /**
     * Styles the buttons to display
     * the farmer's inventory
     */
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

    /**
     * Creates tool tips for the items inside
     * the farmer's inventory
     */
    public void makeToolEquipTips() {
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

            if (row == 1 && col < farmingGame.getFarmer().getFarmerInventory().getTools().size()) {
                String toolFunction = farmingGame.getFarmer().getFarmerInventory().getTools().get(col).getToolFunction();
                if (name.equals("watering_can")) {
                    name = "Watering Can";
                }
                tTip.setText(name + ".\n" + toolFunction);
                tTip.setStyle("-fx-font-size: 25");
                Tooltip.install(node, tTip);
            } else if(row == 0 && col < farmingGame.getSeeds().getPlants().size()) {
                String seed = node.getId();
                if (seed != null) {
                    tTip.setText(seed + ". Owns " + farmingGame.getFarmer().getFarmerInventory().getSeedsOwned().get(seed) + ".");
                    tTip.setStyle("-fx-font-size: 25");
                    Tooltip.install(node, tTip);
                }
            }
        }
    }


    /**
     * Displays the details of the current farmer
     */
    public void showFarmerDetails() {
        farmerName.setText(farmingGame.getFarmer().getFarmerName());
        farmerLvl.setText(String.valueOf(farmingGame.getFarmer().getFarmerLevel()));
        objectCoins.setText(String.valueOf(farmingGame.getFarmer().getFarmerInventory().getObjectCoins()));
        farmerXP.setText(String.valueOf(farmingGame.getFarmer().getExperience()));
        farmerStatus.setText(farmingGame.getFarmer().getFarmerStatus());
        if (farmingGame.getFarmer().getFarmerCharacter().equals("Boy")) {
            farmerChar.setImage(new Image("farmerboy.png"));
        } else {
            farmerChar.setImage(new Image("farmergirl.png"));
        }
    }


    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */

    /**
     * Acts as the getter for the grid pane with the buttons for inventory
     * @return the inventory's grid pane
     */
    public GridPane getButtonsPane() {
        return buttonsPane;
    }

    /**
     * Acts as the setter for the grid pane with the buttons for inventory
     * @param buttonsPane the inventory's grid pane to be set
     */
    public void setButtonsPane(GridPane buttonsPane) {
        this.buttonsPane = buttonsPane;
    }

    /**
     * Acts as the getter for the Text node with the farmer's name
     * @return the Text node with the name of the farmer
     */
    public Text getFarmerName() {
        return farmerName;
    }

    /**
     * Acts as the setter for the Text node with the farmer's name
     * @param farmerName the Text node with the name of the farmer to be set
     */
    public void setFarmerName(Text farmerName) {
        this.farmerName = farmerName;
    }

    /**
     * Acts as the getter for the Text node with the farmer's level
     * @return the Text node with the level of the farmer
     */
    public Text getFarmerLvl() {
        return farmerLvl;
    }

    /**
     * Acts as the setter for the Text node with the farmer's level
     * @param farmerLvl the Text node with the level of the farmer to be set
     */
    public void setFarmerLvl(Text farmerLvl) {
        this.farmerLvl = farmerLvl;
    }

    /**
     * Acts as the getter for the Text node with the farmer's objectCoins
     * @return the Text node with the objectCoins of the farmer
     */
    public Text getObjectCoins() {
        return objectCoins;
    }

    /**
     * Acts as the setter for the Text node with the farmer's objectCoins
     * @param objectCoins the Text node with the objectCoins of the farmer to be set
     */
    public void setObjectCoins(Text objectCoins) {
        this.objectCoins = objectCoins;
    }

    /**
     * Acts as the getter for the Text node with the farmer's experience
     * @return the Text node with the experience of the farmer
     */
    public Text getFarmerXP() {
        return farmerXP;
    }

    /**
     * Acts as the setter for the Text node with the farmer's experience
     * @param farmerXP the Text node with the experience of the farmer to be set
     */
    public void setFarmerXP(Text farmerXP) {
        this.farmerXP = farmerXP;
    }

    /**
     * Acts as the getter for the Text node with the farmer's experience
     * @return the Text node with the experience of the farmer
     */
    public Text getFarmerStatus() {
        return farmerStatus;
    }

    /**
     * Acts as the getter for the Text node with the farmer's status
     * @return the Text node with the status of the farmer
     */
    public void setFarmerStatus(Text farmerStatus) {
        this.farmerStatus = farmerStatus;
    }

    /**
     * Acts as the getter for the ImageView node with the farmer's character image
     * @return the ImageView node with the image of the farmer's character
     */
    public ImageView getFarmerChar() {
        return farmerChar;
    }

    /**
     * Acts as the setter for the ImageView node with the farmer's character image
     * @param farmerChar ImageView node with the image of the farmer's character to be set
     */
    public void setFarmerChar(ImageView farmerChar) {
        this.farmerChar = farmerChar;
    }
}