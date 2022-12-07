package mco2_ccprog3;

import farm.FarmingGame;
import farm.Tile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;

public class ActionsController implements Initializable {

    private FarmingGame farmingGame = new FarmingGame();
    @FXML
    private GridPane actionGridPane;
    @FXML
    private AnchorPane actionAncPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtons();
    }

    public void setButtons() {
        for (Node node: actionGridPane.getChildren()) {
            if (node instanceof final Button button) {
                ImageView im = new ImageView(new Image(button.getId()+".png"));
                im.setFitHeight(50);
                im.setFitWidth(50);
                button.setStyle("-fx-background-image: url(\"actionbtn.png\"); -fx-background-size: 637 130; -fx-background-repeat: stretch;");
                if (button.getId().equals("useTool")) {
                    button.setText(" Use Tool");
                    button.setGraphic(im);
                } else if (button.getId().equals("plantSeed")) {
                    button.setText(" Plant Seed");
                    button.setGraphic(im);
                } else if (button.getId().equals("harvestCrop")) {
                    button.setText(" Harvest Crop");
                    button.setGraphic(im);
                }
            }
        }
    }

    public void useTool() {
        int numOfTools = farmingGame.getFarmer().getFarmerInventory().getTools().size();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(610, 350);
        scrollPane.setLayoutY(160);
        scrollPane.setLayoutX(50);

        actionAncPane.getChildren().remove(actionGridPane);

        GridPane toolPane = new GridPane();

        for (int row = 0; row < numOfTools; row++) {
            Button button = new Button();
            button.setStyle("-fx-font-size: 35; -fx-background-image: url(\"actionbtn.png\"); -fx-background-repeat: stretch; -fx-background-size: 100% 100%; " +
                    "-fx-alignment:center-left;");
            button.setPrefWidth(1000);
            button.setText(farmingGame.getFarmer().getFarmerInventory().getTools().get(row).getToolName()
                    + "\n" + farmingGame.getFarmer().getFarmerInventory().getTools().get(row).getToolFunction());
            button.setTextAlignment(TextAlignment.LEFT);
            String tool = farmingGame.getFarmer().getFarmerInventory().getTools().get(row).getToolName();
            if (tool.equals("Watering Can")) {
                tool = "watering_can";
            }

            ImageView im = new ImageView(new Image(tool.toLowerCase()+".png"));
            im.setFitHeight(60);
            im.setFitWidth(60);
            button.setGraphic(im);
            button.setId(String.valueOf(row));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    enableTool(Integer.parseInt(button.getId()), farmingGame.getBoard().getSelectedTile());
                }
            });
            toolPane.addRow(row, button);

        }

        scrollPane.setContent(toolPane);
        actionAncPane.getChildren().add(scrollPane);
    }

    public void enableTool(int tool, Tile tile) {
        System.out.println(farmingGame.getBoard().getFarmTile(0, 0).isSelected());
        farmingGame.getFarmer().useTools(tile, tool);
    }

    public void plantSeed() {

    }

    public void harvestCrop() {

    }
}
