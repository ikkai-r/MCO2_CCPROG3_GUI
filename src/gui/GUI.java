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

    /**
     * Class constructor for initializing the graphical user
     * interface
     *
     */
    public GUI() {
        initializeGUI();
    }

    /**
     * Creates all attributes, sets the scenes
     * and inserting the title and icons for the stage
     */
    public void initializeGUI() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.getIcons().add(new Image("moondew_valley.png"));
        mainStage.setTitle("Moondew Valley");
        mainStage.setResizable(false);
    }

    /**
     *
     * The following methods serve as the getters and setters of the private attributes.
     */


    /**
     * Acts as a getter for the main stage shown
     * @return main stage
     */
    public Stage getMainStage() {
        return mainStage;
    }

    /**
     * Acts as a setter for the farming game
     * @param farmingGame the object containing the farming game data
     */
    public static void setFarmingGame(FarmingGame farmingGame) {
        GUI.farmingGame = farmingGame;
    }

    /**
     * Acts as a getter for the farming game
     * @return the object containing the current farming game data
     */
    public FarmingGame getFarmingGame() {
        return farmingGame;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(AnchorPane mainPane) {
        this.mainPane = mainPane;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
