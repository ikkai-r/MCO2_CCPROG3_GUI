package gui;

import farm.FarmingGame;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GUI {
    private static final int WIDTH = 980;
    private static final int HEIGHT = 700;

    private AnchorPane mainPane;
    private Scene mainScene;
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
     * Acts as a getter for the scene's main pane
     * @return the main pane of the scene
     */
    public AnchorPane getMainPane() {
        return mainPane;
    }

    /**
     * Acts as a setter for the scene's main pane
     * @param mainPane the pane you want to set the mainPane variable to
     */
    public void setMainPane(AnchorPane mainPane) {
        this.mainPane = mainPane;
    }

    /**
     * Acts as a getter for the main scene of the GUI
     * @return the main scene
     */
    public Scene getMainScene() {
        return mainScene;
    }

    /**
     * Acts as a setter for the main scene of the GUI
     * @param mainScene the value that will be set as a main scene
     */
    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    /**
     * Acts as a setter for the main stage of the GUI
     * @param mainStage the value that will be set as a main stage
     */
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
