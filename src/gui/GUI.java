package gui;

import farm.FarmingGame;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GUI {

    protected static FarmingGame farmingGame = new FarmingGame();
    private static final int WIDTH = 980;
    private static final int HEIGHT = 700;

    protected AnchorPane mainPane;
    protected Scene mainScene;
    private Stage mainStage;

    public GUI() {
        initializeGUI();
    }

    public void initializeGUI() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.getIcons().add(new Image("moondew_valley.png"));
        mainStage.setTitle("Moondew Valley");
        mainStage.setResizable(false);
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public static void setFarmingGame(FarmingGame farmingGame) {
        GUI.farmingGame = farmingGame;
    }

    public FarmingGame getFarmingGame() {
        return farmingGame;
    }
}
