package gui.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class FarmScene {
    private static final int WIDTH = 980;
    private static final int HEIGHT = 700;
    private Scene gameScene;
    private Stage gameStage;

    /**
     * Class constructor for initializing the farm scene GUI
     *
     */
    public FarmScene() {
        initializeFarmScene();
    }

    /**
     * Creates all attributes, sets the scenes
     * and inserting the title and icons for the stage
     */
    public void initializeFarmScene() {
        gameStage = new Stage();
        gameStage.getIcons().add(new Image("moondew_valley.png"));
        gameStage.setTitle("Moondew Valley");
        gameStage.setResizable(false);
    }

    /**
     * Loads and shows the farm stage and scene
     *
     * @param mainStage the opening stage
     * @throws IOException
     */
    public void createFarm(Stage mainStage) throws IOException {
        mainStage.close();
        Parent root = FXMLLoader.load(this.getClass().getResource("/farmscene.fxml"));
        gameScene = new Scene(root, WIDTH, HEIGHT);
        gameStage.setScene(gameScene);
        gameScene.getRoot().setId("farm-root");
        gameScene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
        gameStage.show();
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */

    public Scene getGameScene() {
        return gameScene;
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public void setGameStage(Stage gameStage) {
        this.gameStage = gameStage;
    }
}
