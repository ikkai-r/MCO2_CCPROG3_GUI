package mainapp;

import gui.scenes.InitializeScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
          launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        InitializeScene initializeScene = new InitializeScene();
        primaryStage = initializeScene.getMainStage();
        primaryStage.show();
    }
}