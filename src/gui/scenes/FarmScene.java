package gui.scenes;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class FarmScene {
    private static final int WIDTH = 980;
    private static final int HEIGHT = 700;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    public FarmScene() {
        initializeFarmScene();
    }

    public void initializeFarmScene() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, WIDTH, HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.getIcons().add(new Image("moondew_valley.png"));
        gameStage.setTitle("Moondew Valley");
        gameStage.setResizable(false);
    }

    public void createFarm(Stage mainStage) {
        mainStage.close();
        gamePane.setId("farmPane");
        gameScene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
        gameStage.show();
    }


}
