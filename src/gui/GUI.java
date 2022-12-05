package gui;

import gui.scenes.SceneButtons;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI {

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public GUI() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, 980, 700);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.getIcons().add(new Image("moondew_valley.png"));
        mainStage.setTitle("Moondew Valley");
        mainStage.setResizable(false);
        openingSceneButtons();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void openingSceneButtons() {
        mainPane.setId("pane");
        VBox box = openingSceneVBOX();
        mainPane.getChildren().add(box);
        box.setAlignment(Pos.CENTER);
        box.prefWidthProperty().bind(mainPane.widthProperty());
        box.prefHeightProperty().bind(mainPane.heightProperty());
        mainScene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
    }

    public VBox openingSceneVBOX() {
        Font.loadFont(this.getClass().getResource("/Stardew_Valley.ttf").toExternalForm(), 20);

        SceneButtons startButton = new SceneButtons("Start");
        SceneButtons exitButton = new SceneButtons("Exit");
        HBox hbox = new HBox(40);
        hbox.getChildren().addAll(startButton, exitButton);
        hbox.setAlignment(Pos.CENTER);
        Image image = new Image("title_screen.png");
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image);
        //Setting the image view parameters
        imageView.setFitWidth(750);
        imageView.setPreserveRatio(true);
        return new VBox(50, imageView, hbox);
    }

}
