package gui;

import gui.scenes.SceneButtons;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
        SceneButtons startButton = new SceneButtons("Start");
        mainPane.getChildren().add(startButton);
        mainScene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());
    }

}
