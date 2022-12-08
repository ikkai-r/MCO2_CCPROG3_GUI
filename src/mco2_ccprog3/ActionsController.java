package mco2_ccprog3;

import farm.*;
import gui.scenes.PlayerSubScene;
import gui.scenes.SceneHeaderTxts;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ActionsController extends FarmController implements Initializable {

    @FXML
    private Text actionHeader;
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
                button.setStyle("-fx-background-image: url(\"actionbtn.png\"); -fx-background-size: 615 130; -fx-background-repeat: stretch;");
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
        ArrayList<String> feedback;
        int layout;
        StringBuilder strFeedback = new StringBuilder();
        feedback = farmingGame.getFarmer().useTools(tile, tool);
        PlayerSubScene popUpScene = new PlayerSubScene("action-pop-up", 500, 100);
        popUpScene.moveSubScene(true);
        for (String a : feedback) {
            strFeedback.append(a);
            strFeedback.append("\n");
        }
        if (feedback.size() == 1) {
            layout = 30;
        } else {
            layout = 10;
        }
        SceneHeaderTxts sceneHeaderTxts = new SceneHeaderTxts(strFeedback.toString(), layout);
        sceneHeaderTxts.prefWidthProperty().bind(popUpScene.widthProperty());
        sceneHeaderTxts.setStyle("-fx-font-size: 40");

        System.out.println(strFeedback);
        sceneHeaderTxts.setTextAlignment(TextAlignment.CENTER);
        popUpScene.getPane().getChildren().add(sceneHeaderTxts);
        actionAncPane.getChildren().add(popUpScene);
    }

    public void plantSeed() {

            int row = 0;

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setPrefSize(610, 350);
            scrollPane.setLayoutY(160);
            scrollPane.setLayoutX(50);

            actionAncPane.getChildren().remove(actionGridPane);

            GridPane seedPane = new GridPane();
            for (String seed: farmingGame.getFarmer().getFarmerInventory().getSeedsOwned().keySet()) {
                Button button = new Button();
                button.setStyle("-fx-font-size: 35; -fx-background-image: url(\"actionbtn.png\"); -fx-background-repeat: stretch; -fx-background-size: 100% 100%; " +
                        "-fx-alignment:center-left;");
                button.setPrefWidth(605);
                button.setText(seed + "\t\t\t" + farmingGame.getFarmer().getFarmerInventory().getSeedsOwned().get(seed));
                button.setTextAlignment(TextAlignment.LEFT);
                ImageView im = new ImageView(new Image(seed.toLowerCase()+".png"));
                im.setFitHeight(60);
                im.setFitWidth(60);
                button.setGraphic(im);
                button.setId(seed);

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        enablePlanting(farmingGame.getBoard().getSelectedTile(), farmingGame.getSeeds(), farmingGame.getBoard(), seed);
                        actionAncPane.getChildren().removeAll(scrollPane, seedPane);
                    }
                });
                seedPane.addRow(row, button);
                row++;
            }

            if (farmingGame.getFarmer().getFarmerInventory().getNumberOfSeedsOwned() != 0) {
                scrollPane.setContent(seedPane);
                actionAncPane.getChildren().add(scrollPane);
            } else {
                SceneHeaderTxts sceneHeaderTxts = new SceneHeaderTxts("No seeds owned yet. \nBuy at the store!", 20);
                sceneHeaderTxts.setLayoutX(170);
                sceneHeaderTxts.prefHeightProperty().bind(actionAncPane.heightProperty());
                sceneHeaderTxts.setStyle("-fx-font-size: 50");
                actionAncPane.getChildren().add(sceneHeaderTxts);
                actionAncPane.getChildren().removeAll(scrollPane, seedPane);
            }

    }

    public void enablePlanting(Tile tile, Seeds seeds, Board board, String seed) {
        String feedback;
        feedback = farmingGame.getFarmer().plantSeeds(tile, seeds, board, seed);
        actionHeader.setText(feedback);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e ->{
            actionHeader.setText("Pick an action for the clicked tile!");
        });
        pause.play();
        plantSeed();
    }

    public void harvestCrop() {
        ArrayList<String> cropFeedback = farmingGame.getFarmer().harvestCrop(farmingGame.getBoard().getSelectedTile(), farmingGame.getSeeds());
        StringBuilder strFeedback = new StringBuilder();

        PlayerSubScene cropPopUp;

        for (String cropFeed : cropFeedback) {
            strFeedback.append(cropFeed);
            strFeedback.append("\n\n");
        }

        if (cropFeedback.size() <= 1) {
            cropPopUp = new PlayerSubScene("action-pop-up", 500, 100);
        } else {
            cropPopUp = new PlayerSubScene("action-pop-up", 600, 300);
        }

        cropPopUp.moveSubScene(true);

        SceneHeaderTxts sceneHeaderTxts = new SceneHeaderTxts(strFeedback.toString(), 30);
        sceneHeaderTxts.prefWidthProperty().bind(cropPopUp.widthProperty());
        sceneHeaderTxts.setStyle("-fx-font-size: 40");

        System.out.println(strFeedback);
        sceneHeaderTxts.setTextAlignment(TextAlignment.CENTER);
        cropPopUp.getPane().getChildren().add(sceneHeaderTxts);

        actionAncPane.getChildren().add(cropPopUp);

    }

}
