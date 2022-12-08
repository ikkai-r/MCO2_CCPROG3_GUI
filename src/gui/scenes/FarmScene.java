package gui.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;


public class FarmScene {
    private static final int WIDTH = 980;
    private static final int HEIGHT = 700;
    private Scene gameScene;
    private Stage gameStage;

    public FarmScene() {
        initializeFarmScene();
    }

    public void initializeFarmScene() {
        gameStage = new Stage();
        gameStage.getIcons().add(new Image("moondew_valley.png"));
        gameStage.setTitle("Moondew Valley");
        gameStage.setResizable(false);
    }

    public void createFarm(Stage mainStage) throws IOException {
        mainStage.close();
        Parent root = FXMLLoader.load(this.getClass().getResource("/farmscene.fxml"));
        gameScene = new Scene(root, WIDTH, HEIGHT);
        gameStage.setScene(gameScene);
        gameScene.getRoot().setStyle("-fx-background-image: url('farm.png');\n" +
                "    -fx-background-repeat: stretch;");
        gameScene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
        gameStage.show();
    }




}
